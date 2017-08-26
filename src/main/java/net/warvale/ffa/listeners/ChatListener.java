package net.warvale.ffa.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import net.warvale.ffa.config.ConfigManager;
import net.warvale.ffa.message.MessageManager;

public class ChatListener implements Listener {

    private static ChatListener instance;
    private boolean globalmute;

    public static ChatListener getInstance() {
        if (instance == null) {
            instance = new ChatListener();
        }
        return instance;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (isGlobalmuted() && !player.hasPermission("warvale.chat.bypass")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Global chat is currently disabled");
            return;
        }

        if (player.hasPermission("warvale.chat.color")) {
            event.setFormat(player.getName() + " " + ChatColor.DARK_GRAY + "» " + ChatColor.RESET
                    + ChatColor.translateAlternateColorCodes('&', event.getMessage()));
        } else {
            event.setFormat(player.getName() + " " + ChatColor.DARK_GRAY + "» " + ChatColor.RESET + event.getMessage());
        }

    }

    public void setGlobalmute(boolean enabled) {
        globalmute = enabled;

        //update config
        ConfigManager.getConfig().set("globalmute", enabled);
        ConfigManager.getInstance().saveConfig();
    }

    public boolean isGlobalmuted() {
        return globalmute;
    }

    public void toggleChat(){
        if (isGlobalmuted()) {
            unmuteChat();
        } else {
            muteChat();
        }
    }

    public void muteChat() {
        setGlobalmute(true);

        MessageManager.broadcast(ChatColor.AQUA + "Global mute enabled.");
    }

    public void unmuteChat() {
        setGlobalmute(false);

       MessageManager.broadcast(ChatColor.AQUA + "Global mute disabled.");
    }

    /*private String getPexPrefix(Player player){
        PermissionUser permissionUser = PermissionsEx.getPermissionManager().getUser(player.getUniqueId());
        return permissionUser.getPrefix() == null ? "" : ChatColor.translateAlternateColorCodes('&', permissionUser.getPrefix());
    }*/

}
