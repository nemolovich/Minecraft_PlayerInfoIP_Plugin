/*
 * Nemolovich
 * GNU GPL 3 Licence
 */
package fr.nemolovich.apps.playeripinfo;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Nemolovich
 */
public class PlayerIPInfoPlugin extends JavaPlugin {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean result = false;

        if (command.getName().equalsIgnoreCase("players")) {
            if (!(sender instanceof Player)
                || (sender instanceof Player
                && ((Player) sender).hasPermission("ipinfo.players"))) {
                result = true;
                StringBuilder response = new StringBuilder();
                Player[] players = getServer().getOnlinePlayers();
                if (players.length > 0) {
                    response.append(String.format(
                        "%1$sThere are %3$s%4$d%1$s/%2$s%5$d%1$s players online:",
                        ChatColor.WHITE, ChatColor.AQUA, ChatColor.GREEN,
                        players.length, getServer().getMaxPlayers()));
                    String ip;
                    String playerName;
                    for (Player player : players) {
                        playerName = String.format("%s%s ",
                            ChatColor.GREEN, player.getName());
                        ip = String.format("%1$s[%2$s%3$s%1$s]",
                            ChatColor.YELLOW, ChatColor.AQUA,
                            player.getAddress().getAddress().getHostAddress());
                        response.append(String.format("\n%-20s%16s", playerName, ip));
                    }
                } else {
                    response.append(String.format("%sThere are no players online",
                        ChatColor.WHITE));
                }
                sender.sendMessage(response.toString());
            }
        }

        return result;
    }
}
