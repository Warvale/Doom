package net.warvale.ffa.player;

import com.sun.org.apache.xpath.internal.operations.Bool;
import net.warvale.ffa.gui.guis.KitSelectorGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import net.warvale.ffa.WarvaleFFA;
import net.warvale.ffa.utils.DatabaseUtils;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;

public class FFAPlayer {

    private UUID uuid;
    private int kills = 0;
    private int deaths = 0;
    private int killStreak = 0;
    private int totalKills = 0;
    private int totalDeaths = 0;
    private int highestKillStreak = 0;
    private int embers = 0;
    private int xp = 0;
    private String purchasedKits = "";


    public FFAPlayer(UUID uuid) {
        this.uuid = uuid;
        if (WarvaleFFA.get().getGame().isStatsEnabled()) {
            this.loadData();
        }
    }

    public UUID getUUID() {
        return this.uuid;
    }
    public void addPurcashedKit(String kitName){
        if (hasKit(kitName)) return;
        this.setPurchasedKits(getPurchasedKits()+" "+kitName.toUpperCase());
    }
    public Boolean hasKit(String kitName) {
        Boolean res = false;
        for (String s : this.getPurchasedKits().split(" +")) {
            if (s.toUpperCase().equalsIgnoreCase(kitName.toUpperCase())) res = true;
        }
        return res;
    }
    public String getPurchasedKits() {return this.purchasedKits;}
    public void setPurchasedKits(String newValue) {this.purchasedKits = newValue;}
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public double getKd() {
        double d = 0.0;
        d = this.totalKills > 0 && this.totalDeaths == 0 ? (double)this.totalKills : (this.totalKills == 0 && this.totalDeaths == 0 ? 0.0 : (double)(this.totalKills / this.totalDeaths));
        return d;
    }

    public String getName() {
        return Bukkit.getServer().getOfflinePlayer(uuid).getName();
    }

    public int getXp() {return this.xp;}
    public void setXp(int xp) {this.xp = xp;
    getPlayer().setLevel(getLevel());
    }
    public int getLevel() {
        int returning = 12;
        for (int i = KitSelectorGUI.getLevelupxp().length; i < 1; i--) {
                if (getXp() >= KitSelectorGUI.getLevelupxp()[i]) returning= i + 1;
        }
        return returning;
    }
    public int getXPtoNextLevel() {
        return KitSelectorGUI.getLevelupxp()[this.getLevel()-2]-(getXp() - getUselessXP());
    }
    public int getUselessXP() {
        int useless = 0;
        for (int i = getLevel() - 1; i < 1; i--) {
            useless+=KitSelectorGUI.getLevelupxp()[this.getLevel()];
        }
        return useless;
    }
    public void addTotalDeath() {
        ++this.totalDeaths;
    }

    public int getTotalDeaths() {
        return this.totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public void addTotalKill() {
        ++this.totalKills;
    }

    public int getTotalKills() {
        return this.totalKills;
    }

    public int getEmbers() { return embers; }

    public void setEmbers(int embers) { this.embers = embers; }

    public void addEmber() { ++this.embers; }


    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    public void addKill() {
        ++this.kills;
    }

    public int getKills() {
        return this.kills;
    }

    public void addDeath() {
        ++this.deaths;
    }

    public int getDeaths() {
        return this.deaths;
    }

    public int getHighestKillStreak() {
        return this.highestKillStreak;
    }

    public void setHighestKillStreak(int n) {
        this.highestKillStreak = n;
    }

    public int getKillStreak() {
        return killStreak;
    }

    public void addKillStreak() {
        ++this.killStreak;
    }

    public void resetKillStreak() {
        this.killStreak = 0;
    }

    private void loadData() {

        if (this.hasData()) {
           new BukkitRunnable(){

               @Override
               public void run() {

                   Connection connection = null;
                   PreparedStatement stmt = null;
                   ResultSet set = null;

                   try {

                       connection = WarvaleFFA.getStorageBackend().getPoolManager().getConnection();

                       stmt = connection.prepareStatement("SELECT * FROM `" + DatabaseUtils.getTable() + "` WHERE `uuid` = ?;");
                       stmt.setString(1, FFAPlayer.this.uuid.toString());
                       stmt.executeQuery();
                       set = stmt.getResultSet();
                       if (set.next()) {

                           FFAPlayer.this.setTotalKills(set.getInt("kills"));
                           FFAPlayer.this.setTotalDeaths(set.getInt("deaths"));
                           FFAPlayer.this.setHighestKillStreak(set.getInt("killstreak"));
                           FFAPlayer.this.setEmbers(set.getInt("embers"));
                           FFAPlayer.this.setXp(set.getInt("xp"));
                           FFAPlayer.this.setPurchasedKits(set.getString("purchasedKits"));

                       }
                       set.close();
                       stmt.close();
                   } catch (SQLException ex) {
                       WarvaleFFA.get().getLogger().log(Level.SEVERE, "Could not load data for player: " + FFAPlayer.this.getName(), ex);
                   } finally {
                       WarvaleFFA.getStorageBackend().getPoolManager().close(connection, stmt, set);
                   }
               }

           }.runTaskAsynchronously(WarvaleFFA.get());
        } else {
            WarvaleFFA.get().getLogger().log(Level.INFO, "Creating data for player: " + this.getName());
            this.createData();
        }

    }

    public void createData() {
        new BukkitRunnable(){

            @Override
            public void run() {

                Connection connection = null;
                PreparedStatement stmt = null;

                try {
                    WarvaleFFA.get().getLogger().log(Level.INFO, "Creating player data for player: " + FFAPlayer.this.getName());

                    connection = WarvaleFFA.getStorageBackend().getPoolManager().getConnection();

                    stmt = connection.prepareStatement("INSERT INTO `" + DatabaseUtils.getTable() + "` " +
                            "(uuid, kills, deaths, killstreak, embers, xp, purchasedKits) VALUES (?, ?, ?, ?, ?)");
                    stmt.setString(1, FFAPlayer.this.uuid.toString());
                    stmt.setInt(2, FFAPlayer.this.getTotalKills());
                    stmt.setInt(3, FFAPlayer.this.getTotalDeaths());
                    stmt.setInt(4, FFAPlayer.this.getHighestKillStreak());
                    stmt.setInt(5, FFAPlayer.this.getEmbers());
                    stmt.setInt(6, FFAPlayer.this.getXp());
                    stmt.setString(7, FFAPlayer.this.getPurchasedKits());
                    stmt.execute();
                    stmt.close();

                } catch (SQLException ex) {
                    WarvaleFFA.get().getLogger().log(Level.SEVERE, "Could not create data for player: " + FFAPlayer.this.getName(), ex);
                } finally {
                    WarvaleFFA.getStorageBackend().getPoolManager().close(connection, stmt, null);
                }

            }

        }.runTaskAsynchronously(WarvaleFFA.get());
    }

    public void saveData() {

        if (this.hasData()) {

            new BukkitRunnable(){

                @Override
                public void run() {

                    Connection connection = null;
                    PreparedStatement stmt = null;

                    try {
                        connection = WarvaleFFA.getStorageBackend().getPoolManager().getConnection();

                        stmt = connection.prepareStatement("SELECT * FROM `" + DatabaseUtils.getTable() +  "` WHERE uuid = ? LIMIT 1;");
                        stmt.setString(1, FFAPlayer.this.uuid.toString());
                        ResultSet set = stmt.executeQuery();

                        if (set.next()) {
                            stmt.close();

                            stmt = connection.prepareStatement("UPDATE `" + DatabaseUtils.getTable() + "` SET kills = ? WHERE uuid = ?;");
                            stmt.setInt(1, FFAPlayer.this.getTotalKills());
                            stmt.setString(2, FFAPlayer.this.uuid.toString());
                            stmt.executeUpdate();
                            stmt.close();

                            stmt = connection.prepareStatement("UPDATE `" + DatabaseUtils.getTable() + "` SET deaths = ? WHERE uuid = ?;");
                            stmt.setInt(1, FFAPlayer.this.getTotalDeaths());
                            stmt.setString(2, FFAPlayer.this.uuid.toString());
                            stmt.executeUpdate();
                            stmt.close();

                            stmt = connection.prepareStatement("UPDATE `" + DatabaseUtils.getTable() + "` SET killstreak = ? WHERE uuid = ?;");
                            stmt.setInt(1, FFAPlayer.this.getHighestKillStreak());
                            stmt.setString(2, FFAPlayer.this.uuid.toString());
                            stmt.executeUpdate();
                            stmt.close();

                            stmt = connection.prepareStatement("UPDATE `" + DatabaseUtils.getTable() + "` SET embers = ? WHERE uuid = ?;");
                            stmt.setInt(1, FFAPlayer.this.getEmbers());
                            stmt.setString(2, FFAPlayer.this.uuid.toString());
                            stmt.executeUpdate();
                            stmt.close();

                            stmt = connection.prepareStatement("UPDATE `" + DatabaseUtils.getTable() + "` SET xp = ? WHERE uuid = ?;");
                            stmt.setInt(1, FFAPlayer.this.getXp());
                            stmt.setString(2, FFAPlayer.this.uuid.toString());
                            stmt.executeUpdate();
                            stmt.close();

                            stmt = connection.prepareStatement("UPDATE `" + DatabaseUtils.getTable() + "` SET purchasedKits = ? WHERE uuid = ?;");
                            stmt.setString(1, FFAPlayer.this.getPurchasedKits());
                            stmt.setString(2, FFAPlayer.this.uuid.toString());
                            stmt.executeUpdate();
                            stmt.close();
                        }

                    } catch (SQLException ex) {
                        WarvaleFFA.get().getLogger().log(Level.SEVERE, "Could not update data for player: " + FFAPlayer.this.getName(), ex);
                    } finally {
                        WarvaleFFA.getStorageBackend().getPoolManager().close(connection, stmt, null);
                    }

                }

            }.runTaskAsynchronously(WarvaleFFA.get());

        } else {
            WarvaleFFA.get().getLogger().log(Level.INFO, "Creating data for player: " + this.getName());
            this.createData();
        }

    }

    public boolean hasData() {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = WarvaleFFA.getStorageBackend().getPoolManager().getConnection();

            stmt = connection.prepareStatement("SELECT `uuid` FROM `" + DatabaseUtils.getTable() + "` WHERE `uuid` = ?;");
            stmt.setString(1, this.uuid.toString());
            stmt.executeQuery();
            ResultSet set = stmt.getResultSet();
            if (set.next()) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            WarvaleFFA.getStorageBackend().getPoolManager().close(connection, stmt, null);
        }
        return false;
    }

}
