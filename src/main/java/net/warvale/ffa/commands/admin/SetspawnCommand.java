package net.warvale.ffa.commands.admin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.warvale.ffa.commands.AbstractCommand;
import net.warvale.ffa.config.ConfigManager;
import net.warvale.ffa.exceptions.CommandException;
import net.warvale.ffa.message.MessageManager;
import net.warvale.ffa.message.PrefixType;


public class SetspawnCommand extends AbstractCommand {

    public SetspawnCommand() {
        super("setspawn", "");

    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)) {
            throw new CommandException("Only players can set the spawn point.");
        }

        Player player = (Player) sender;
        Location loc = player.getLocation();

        ConfigManager.getConfig().set("spawn.world", loc.getWorld().getName());
        ConfigManager.getConfig().set("spawn.x", loc.getX());
        ConfigManager.getConfig().set("spawn.y", loc.getY());
        ConfigManager.getConfig().set("spawn.z", loc.getZ());
        ConfigManager.getConfig().set("spawn.yaw", loc.getYaw());
        ConfigManager.getConfig().set("spawn.pitch", loc.getPitch());
        ConfigManager.getInstance().saveConfig();
        
        player.sendMessage(String.format(MessageManager.getPrefix(PrefixType.FFA) + "You have set the spawnpoint to W: §a%s §7X: §a%s §7Y: §a%s §7Z: §a%s§7.",
                player.getWorld().getName(), loc.getX(), loc.getY(), loc.getZ()));
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}