package net.warvale.ffa.commands;

import net.warvale.ffa.WarvaleFFA;
import net.warvale.ffa.commands.admin.MaxplayersCommand;
import net.warvale.ffa.commands.admin.SetTypeCommand;
import net.warvale.ffa.commands.admin.SetspawnCommand;
import net.warvale.ffa.commands.admin.TogglestatsCommand;
import net.warvale.ffa.commands.user.StatsCommand;
import net.warvale.ffa.commands.user.SuicideCommand;
import net.warvale.ffa.exceptions.CommandException;
import net.warvale.ffa.gui.GUIManager;
import net.warvale.ffa.message.MessageManager;
import net.warvale.ffa.message.PrefixType;
import org.bukkit.ChatColor;
import org.bukkit.command.*;

import java.util.ArrayList;
import java.util.List;


public class CommandHandler implements CommandExecutor, TabCompleter {
    private final WarvaleFFA plugin;

    public CommandHandler(WarvaleFFA plugin) {
        this.plugin = plugin;
    }

    private List<AbstractCommand> cmds = new ArrayList<AbstractCommand>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        AbstractCommand command = getCommand(cmd.getName());

        if (command == null) { // this shouldn't happen, it only uses registered commands but incase.
            return true;
        }

        if (!sender.hasPermission(command.getPermission())) {
            sender.sendMessage("§cYou do not have permission to execute this command");
            return true;
        }

        try {
            if (!command.execute(sender, args)) {
                sender.sendMessage(ChatColor.RED + "Usage: " + command.getUsage());
            }
        } catch (CommandException ex) {
            sender.sendMessage(ChatColor.RED + ex.getMessage()); // send them the exception message
        } catch (Exception ex) {
            sender.sendMessage(ChatColor.RED + ex.getClass().getName() + ": " + ex.getMessage());
            ex.printStackTrace(); // send them the exception and tell the console the error if its not a command exception
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        AbstractCommand command = getCommand(cmd.getName());

        if (command == null) { // this shouldn't happen, it only uses registered commands but incase.
            return null;
        }

        if (!sender.hasPermission(command.getPermission())) {
            return null;
        }

        try {
            List<String> list = command.tabComplete(sender, args);

            // if the list is null, replace it with everyone online.
            if (list == null) {
                return null;
            }

            // I don't want anything done if the list is empty.
            if (list.isEmpty()) {
                return list;
            }

            List<String> toReturn = new ArrayList<String>();

            if (args[args.length - 1].isEmpty()) {
                for (String type : list) {
                    toReturn.add(type);
                }
            } else {
                for (String type : list) {
                    if (type.toLowerCase().startsWith(args[args.length - 1].toLowerCase())) {
                        toReturn.add(type);
                    }
                }
            }

            return toReturn;
        } catch (Exception ex) {
            sender.sendMessage(ChatColor.RED + ex.getClass().getName() + ": " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Get a uhc command.
     *
     * @param name The name of the uhc command
     * @return The UHCCommand if found, null otherwise.
     */
    protected AbstractCommand getCommand(String name) {
        for (AbstractCommand cmd : cmds) {
            if (cmd.getName().equalsIgnoreCase(name)) {
                return cmd;
            }
        }
        
        return null;
    }

    /**
     * Register all the commands.
     */
    public void registerCommands(GUIManager gui) {

        //admin
        cmds.add(new MaxplayersCommand());
        cmds.add(new SetspawnCommand());
        cmds.add(new SetTypeCommand());
        cmds.add(new TogglestatsCommand());



        //user
        cmds.add(new StatsCommand(gui));
        cmds.add(new SuicideCommand());



        for (AbstractCommand cmd : cmds) {
            PluginCommand pCmd = plugin.getCommand(cmd.getName());
            
            cmd.setupInstances(plugin);

            // if its null, broadcast the command name so I know which one it is (so I can fix it).
            if (pCmd == null) {
                MessageManager.broadcast(PrefixType.MAIN, cmd.getName(), "devoted.errors");
                continue;
            }

            pCmd.setExecutor(this);
            pCmd.setTabCompleter(this);
        }
    }
}