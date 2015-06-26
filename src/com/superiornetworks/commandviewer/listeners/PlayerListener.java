package com.superiornetworks.commandviewer.listeners;

import com.superiornetworks.commandviewer.CommandViewer;
import net.pravian.bukkitlib.util.ChatUtils;
import net.pravian.bukkitlib.util.LoggerUtils;
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
            if (CommandViewer.config.getBoolean("autoaddplayers"))
            {

                if (CommandViewer.allowedplayers.contains(player.getName()))
                {
                    LoggerUtils.info(plugin, player.getName() + "is already authorised");
                }
                else
                {
                    CommandViewer.allowedplayers.add(player.getName());
                    LoggerUtils.info(plugin, player.getName() + "has been added to the authorised players list.");
                  //  player.sendMessage(CommandViewer.config.getString(ChatUtils.colorize("nowalloed")));
                }
            }
        }
        else
        {
            if (CommandViewer.allowedplayers.contains(player.getName()))
            {
                CommandViewer.allowedplayers.remove(player.getName());
                LoggerUtils.info(plugin, player.getName() + "Has been removed from the authorised players list");
              //  player.sendMessage(CommandViewer.config.getString(ChatUtils.colorize("nolongerallowed")));
            }
            else
            {
                LoggerUtils.info(plugin, player.getName() + "does not have the required permissions and was not already authorised. ");
            }
        }
    }

}
