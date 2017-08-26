package net.warvale.ffa.commands.admin;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import net.warvale.ffa.commands.AbstractCommand;
import net.warvale.ffa.game.FFAMode;
import net.warvale.ffa.message.MessageManager;
import net.warvale.ffa.message.PrefixType;

import java.util.ArrayList;
import java.util.List;


public class MaxplayersCommand extends AbstractCommand {

	public MaxplayersCommand() {
		super("maxplayers", "<maxplayers>");
	}

	@Override
	public boolean execute(final CommandSender sender, final String[] args) throws CommandException {

		if (args.length == 0) {
			return false;
		}

		int maxPlayers;
		try {
			maxPlayers = parseInt(args[0]);
			plugin.getGame().setMaxPlayers(maxPlayers);

			MessageManager.broadcast(MessageManager.getPrefix(PrefixType.FFA) + "The max players is now " + String.valueOf(maxPlayers));

		} catch (Exception ex) {
			throw new CommandException("You must provide a number for max players");
		}

		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String[] args) {
		return new ArrayList<>();
	}
}