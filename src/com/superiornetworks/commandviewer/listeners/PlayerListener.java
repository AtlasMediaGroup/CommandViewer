package com.superiornetworks.commandviewer.listeners;

import com.superiornetworks.commandviewer.CommandViewer;
import net.pravian.aero.util.Loggers;
import net.pravian.aero.util.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener
{

    private final CommandViewer plugin;

    public PlayerListener(CommandViewer plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        String command = event.getMessage();
        final Player player = event.getPlayer();

        for (Player staff : Bukkit.getOnlinePlayers())
        {
            if (plugin.allowedplayers.contains(staff.getName()))
            {
                staff.sendMessage(ChatColor.GRAY + player.getName() + " : " + command);
            }
        }

        // This is for Debugging only now
        // LoggerUtils.info(plugin, command);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onUncancelledPlayerJoin(PlayerJoinEvent event)
    {
        final Player player = event.getPlayer();

        if (player.hasPermission("commandviewer.viewcommands"))
        {
            if (plugin.pluginConfig.getBoolean("autoaddplayers"))
            {

                if (plugin.allowedplayers.contains(player.getName()))
                {
                    Loggers.info(plugin, player.getName() + "is already authorised");
                }
                else
                {
                    plugin.allowedplayers.add(player.getName());
                    Loggers.info(plugin, player.getName() + "has been added to the authorised players list.");
                    player.sendMessage(plugin.pluginConfig.getString(ChatUtils.colorize("nowallowed")));
                }
            }
        }
        else
        {
            if (plugin.allowedplayers.contains(player.getName()))
            {
                plugin.allowedplayers.remove(player.getName());
                Loggers.info(plugin, player.getName() + "Has been removed from the authorised players list");
                player.sendMessage(plugin.pluginConfig.getString(ChatUtils.colorize("nolongerallowed")));
            }
            else
            {
                Loggers.info(plugin, player.getName() + "does not have the required permissions and was not already authorised. ");
            }
        }
    }

}
