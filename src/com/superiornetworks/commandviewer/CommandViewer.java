package com.superiornetworks.commandviewer;

import com.superiornetworks.commandviewer.commands.Command_cmdtoggle;
import com.superiornetworks.commandviewer.listeners.PlayerListener;
import java.util.List;
import net.pravian.bukkitlib.BukkitLib;
import net.pravian.bukkitlib.command.BukkitCommandHandler;
import net.pravian.bukkitlib.config.YamlConfig;
import net.pravian.bukkitlib.implementation.BukkitPlugin;
import net.pravian.bukkitlib.util.LoggerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;

public class CommandViewer extends BukkitPlugin
{

    private CommandViewer plugin;
    public BukkitCommandHandler handler;

    // YAML Files
    public static YamlConfig config;
    public static YamlConfig players;
    //
    public static List<String> allowedplayers;    

    @Override
    public void onLoad()
    {
        this.plugin = this;
        this.handler = new BukkitCommandHandler(plugin);
    }

    @Override
    public void onEnable()
    {
        BukkitLib.init(plugin);
        handler.setCommandLocation(Command_cmdtoggle.class.getPackage());
        this.config = new YamlConfig(plugin, "config.yml");
        this.players = new YamlConfig(plugin, "players.yml");
        config.load();
        players.load();
        final PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(plugin), plugin);
        plugin.allowedplayers = (List<String>) plugin.players.getList("playerlist");

        LoggerUtils.info(plugin, "Has been created by Wild1145 - Check out www.superior-networks.com for great value servers!");
    }

    @Override
    public void onDisable()
    {
        config.save();
        LoggerUtils.info(plugin, "Has been created by Wild1145 - Check out www.superior-networks.com for great value servers!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        return handler.handleCommand(sender, cmd, commandLabel, args);
    }

}
