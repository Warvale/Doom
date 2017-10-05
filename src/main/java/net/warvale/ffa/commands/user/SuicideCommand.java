package net.warvale.ffa.commands.user;

import net.warvale.ffa.commands.AbstractCommand;
import net.warvale.ffa.exceptions.CommandException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SuicideCommand extends AbstractCommand {


    public SuicideCommand() {
        super("suicide", "");
    }

    @Override
    public boolean execute(final CommandSender sender, final String[] args) throws CommandException {

        if (!(sender instanceof Player)) {
            throw new CommandException("Only players can view someones stats.");
        }

        Player player = (Player) sender;
        player.setHealth(0); // kill them.
        player.sendMessage(ChatColor.GRAY+"Killed yourself.");

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return allPlayers();
    }

}
