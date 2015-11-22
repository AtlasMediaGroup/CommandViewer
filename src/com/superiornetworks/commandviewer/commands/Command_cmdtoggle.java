package com.superiornetworks.commandviewer.commands;

import com.superiornetworks.commandviewer.CommandViewer;
import net.pravian.aero.command.SimpleCommand;
import net.pravian.aero.command.CommandOptions;
import net.pravian.aero.command.SourceType;
import net.pravian.aero.util.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandOptions(source = SourceType.PLAYER, subPermission = "viewcommands")
public class Command_cmdtoggle extends SimpleCommand<CommandViewer>
  {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args)
    {
        if (plugin.allowedplayers.contains(commandSender.getName()))
        {
            plugin.allowedplayers.remove(commandSender.getName());
            commandSender.sendMessage(ChatUtils.colorize(plugin.pluginConfig.getString("toggledoff")));

        }
        else
        {
            plugin.allowedplayers.add(commandSender.getName());
            commandSender.sendMessage(ChatUtils.colorize(plugin.pluginConfig.getString("toggledon")));
        }
        return true;
    }
  }
