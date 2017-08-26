package net.warvale.ffa.commands.admin;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.warvale.ffa.commands.AbstractCommand;
import net.warvale.ffa.game.FFAMode;
import net.warvale.ffa.listeners.ChatListener;
import net.warvale.ffa.message.MessageManager;
import net.warvale.ffa.message.PrefixType;

import java.util.ArrayList;
import java.util.List;


public class SetTypeCommand extends AbstractCommand {

	public SetTypeCommand() {
		super("settype", "");
	}

	@Override
	public boolean execute(final CommandSender sender, final String[] args) throws CommandException {

		if (args.length == 0) {
			return false;
		}

		FFAMode ffaMode;
		try {
			ffaMode = FFAMode.valueOf(args[0]);
			plugin.getGame().setFFAMode(ffaMode);

			MessageManager.broadcast(MessageManager.getPrefix(PrefixType.FFA) + "The server mode is now " + ffaMode.toString());

		} catch (Exception ex) {
			throw new CommandException("Invalid server mode");
		}

		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String[] args) {
		List<String> toReturn = new ArrayList<>();

		if (args.length == 1) {
			for (FFAMode ffaMode : FFAMode.values()) {
				toReturn.add(ffaMode.toString());
			}
		}

		return toReturn;
	}
}