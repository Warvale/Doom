package net.warvale.ffa.data;


import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import net.warvale.ffa.WarvaleFFA;
import net.warvale.ffa.config.DatabaseCredentials;
import net.warvale.ffa.data.connection.ConnectionPoolManager;
import net.warvale.ffa.data.runnable.GenericCallback;
import net.warvale.ffa.data.runnable.QueryCallback;
import net.warvale.ffa.data.runnable.QueryRunnable;
import net.warvale.ffa.player.FFAPlayer;
import net.warvale.ffa.player.PlayerManager;
import net.warvale.ffa.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class StorageBackend {

    private ConnectionPoolManager poolManager;

    public StorageBackend(DatabaseCredentials credentials) {
        this.poolManager = new ConnectionPoolManager(credentials);
        this.createTables();
    }

    public ConnectionPoolManager getPoolManager() {
        return this.poolManager;
    }

    public void closeConnections() {
        this.poolManager.closePool();
    }

    private synchronized void createTables() {
        new BukkitRunnable() {
            public void run() {
                Connection connection = null;

                try {

                    connection = poolManager.getConnection();

                    connection.prepareStatement("CREATE TABLE IF NOT EXISTS `uhcffa_stats` (" +
                            "`id` BIGINT NOT NULL AUTO_INCREMENT, " +
                            "`uuid` varchar(64) NOT NULL," +
                            "`kills` int(11) DEFAULT NULL," +
                            "`deaths` int(11) DEFAULT NULL," +
                            "`killstreak` int(11) DEFAULT NULL," +
                            "PRIMARY KEY (`id`)," +
                            "KEY `uuid_index` (`uuid`)" +
                            ") ENGINE = InnoDB;").executeUpdate();

                } catch (SQLException e) {
                    if (!e.getMessage().contains("already exists")) {
                        WarvaleFFA.get().getLogger().log(Level.SEVERE, "Failed createTables");
                        e.printStackTrace();
                    }
                } finally {
                    poolManager.close(connection, null, null);
                }
            }
        }.runTaskAsynchronously(WarvaleFFA.get());
    }

    public synchronized void createProfile(FFAPlayer ffaPlayer) {
        new QueryRunnable("SELECT `uuid` FROM `" + DatabaseUtils.getTable() + "` WHERE `uuid` = '" + ffaPlayer.getUUID().toString() + "' LIMIT 1;", new QueryCallback<ResultSet, SQLException>() {
            @Override
            public void call(ResultSet result, SQLException thrown) {
                if (thrown == null && result != null) {
                    try {
                        if (!result.next()) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    Connection connection = null;
                                    PreparedStatement stmt = null;

                                    try {

                                        connection = poolManager.getConnection();

                                        stmt = connection.prepareStatement("INSERT INTO `" + DatabaseUtils.getTable() + "` " + "(uuid, kills, deaths, killstreak) VALUES (?, ?, ?, ?)");
                                        stmt.setString(1, ffaPlayer.getUUID().toString());
                                        stmt.setInt(2, ffaPlayer.getTotalKills());
                                        stmt.setInt(3, ffaPlayer.getTotalDeaths());
                                        stmt.setInt(4, ffaPlayer.getHighestKillStreak());
                                        stmt.executeUpdate();
                                        stmt.close();

                                    } catch (SQLException e) {
                                        if (!e.getMessage().contains("Duplicate entry")) {
                                            e.printStackTrace();
                                        }
                                    } finally {
                                        poolManager.close(connection, stmt, null);
                                    }

                                }
                            }.runTaskAsynchronously(WarvaleFFA.get());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).runTaskAsynchronously(WarvaleFFA.get());
    }

    public synchronized void saveProfile(Player player, GenericCallback callback) {

        new BukkitRunnable() {

            @Override
            public void run() {
                FFAPlayer ffaPlayer = PlayerManager.getInstance().getFFAPlayer(player.getUniqueId());

                if (ffaPlayer == null) {
                    WarvaleFFA.get().getLogger().log(Level.SEVERE, "Failed saveProfile (no data) -> " + player.getName());
                    return;
                }

                Connection connection = null;
                PreparedStatement stmt = null;

                try {

                    connection = poolManager.getConnection();
                    stmt = connection.prepareStatement("UPDATE `" + DatabaseUtils.getTable() + "` SET `kills` = ?, `deaths` = ?, killstreak = ? WHERE uuid = ?;");
                    stmt.setInt(1, ffaPlayer.getTotalKills());
                    stmt.setInt(2, ffaPlayer.getTotalDeaths());
                    stmt.setInt(3, ffaPlayer.getHighestKillStreak());
                    stmt.setString(4, ffaPlayer.getUUID().toString());

                } catch (SQLException e) {
                    WarvaleFFA.get().getLogger().log(Level.SEVERE,"Failed saveProfile (exception) -> " + player.getName());
                    e.printStackTrace();

                    if (callback != null) {
                        callback.call(false);
                    }
                } finally {
                    poolManager.close(connection, stmt, null);

                    if (callback != null) {
                        callback.call(true);
                    }
                }

            }

        }.runTaskAsynchronously(WarvaleFFA.get());

    }


}
