package net.warvale.ffa.commands.admin;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import net.warvale.ffa.WarvaleFFA;
import net.warvale.ffa.commands.AbstractCommand;
import net.warvale.ffa.message.MessageManager;
import net.warvale.ffa.message.PrefixType;

import java.util.ArrayList;
import java.util.List;


public class TogglestatsCommand extends AbstractCommand {

	public TogglestatsCommand() {
		super("togglestats", "");
	}

	@Override
	public boolean execute(final CommandSender sender, final String[] args) throws CommandException {

		//toggle stats
		WarvaleFFA.get().getGame().toggleStats();

		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String[] args) {
		return new ArrayList<>();
	}
}