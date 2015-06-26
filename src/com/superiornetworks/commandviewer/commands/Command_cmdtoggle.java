package com.superiornetworks.commandviewer.commands;

import com.superiornetworks.commandviewer.CommandViewer;
import net.pravian.bukkitlib.command.BukkitCommand;
import net.pravian.bukkitlib.command.CommandPermissions;
import net.pravian.bukkitlib.command.SourceType;
import net.pravian.bukkitlib.util.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandPermissions(source = SourceType.ANY, permission = "commandviewer.viewcommands")
public class Command_cmdtoggle extends BukkitCommand
{

    @Override
    public boolean run(CommandSender commandSender, Command command, String commandLabel, String[] args)
    {

        if (CommandViewer.allowedplayers.contains(commandSender.getName()))
        {

            CommandViewer.allowedplayers.remove(commandSender.getName());
            commandSender.sendMessage(ChatUtils.colorize(CommandViewer.config.getString("toggledoff")));

        }
        else
        {
            CommandViewer.allowedplayers.add(commandSender.getName());
            commandSender.sendMessage(ChatUtils.colorize(CommandViewer.config.getString("toggledon")));
            
        }
        return true;

    }
}
