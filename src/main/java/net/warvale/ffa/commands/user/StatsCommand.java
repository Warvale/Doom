package net.warvale.ffa.commands.user;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.warvale.ffa.commands.AbstractCommand;
import net.warvale.ffa.exceptions.CommandException;
import net.warvale.ffa.gui.GUIManager;
import net.warvale.ffa.gui.guis.StatsGUI;
import net.warvale.ffa.player.FFAPlayer;
import net.warvale.ffa.player.PlayerManager;

import java.util.ArrayList;
import java.util.List;

public class StatsCommand extends AbstractCommand {

    private final GUIManager gui;

    public StatsCommand(GUIManager gui) {
        super("stats", "<player>");

        this.gui = gui;
    }

    @Override
    public boolean execute(final CommandSender sender, final String[] args) throws CommandException {

        if (!(sender instanceof Player)) {
            throw new CommandException("Only players can view someones stats.");
        }

        Player player = (Player) sender;

        Player target;

        if (args.length == 0) {
            target = player;
        } else {
            target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                throw new CommandException("'" + args[0] + "' is not online.");
            }
        }

        if (!plugin.getGame().isStatsEnabled()) {
            throw new CommandException("Stats are currently disabled");
        }

        StatsGUI inv = gui.getGUI(StatsGUI.class);
        FFAPlayer ffaPlayer = PlayerManager.getInstance().getFFAPlayer(target.getUniqueId());

        player.openInventory(inv.get(ffaPlayer));

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return allPlayers();
    }

}
