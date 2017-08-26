package net.warvale.ffa.commands.chat;

import net.warvale.ffa.commands.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.warvale.ffa.exceptions.CommandException;
import net.warvale.ffa.listeners.ChatListener;

import java.util.ArrayList;
import java.util.List;


public class GlobalMuteCommand extends AbstractCommand {

	public GlobalMuteCommand() {
		super("globalmute", "");
	}

	@Override
	public boolean execute(final CommandSender sender, final String[] args) throws CommandException {

		ChatListener.getInstance().toggleChat();

		return true;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String[] args) {
		return new ArrayList<>();
	}
}