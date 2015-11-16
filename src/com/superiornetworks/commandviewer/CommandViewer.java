package com.superiornetworks.commandviewer;

import com.superiornetworks.commandviewer.commands.Command_cmdtoggle;
import com.superiornetworks.commandviewer.listeners.PlayerListener;
import java.util.List;
import net.pravian.aero.plugin.AeroPlugin;
import net.pravian.aero.util.Loggers;
import net.pravian.aero.config.YamlConfig;
import net.pravian.aero.command.handler.AeroCommandHandler;
import net.pravian.aero.command.handler.SimpleCommandHandler;
import org.bukkit.plugin.PluginManager;
import com.superiornetworks.commandviewer.commands.DummyCommand;

public class CommandViewer extends AeroPlugin<CommandViewer>
  {

    public static CommandViewer plugin;
    // YAML Files
    public YamlConfig pluginConfig;
    public YamlConfig players;
    //
    public List<String> allowedplayers;
    public List<String> keywords;
    //
    public static AeroCommandHandler handler;

    @Override
    public void load()
    {
        CommandViewer.plugin = this;
    }

    @Override
    public void enable()
    {
        CommandViewer.plugin = this;
        //
        handler = new SimpleCommandHandler(plugin);
        handler.setCommandClassPrefix("Command_");
        handler.loadFrom(DummyCommand.class.getPackage());
        handler.registerAll();
        //
        plugin.pluginConfig = new YamlConfig(plugin, "config.yml");
        plugin.players = new YamlConfig(plugin, "players.yml");
        //
        pluginConfig.load();
        players.load();
        //
        final PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(plugin), plugin);
        //
        plugin.allowedplayers = (List<String>) plugin.players.getList("playerlist");
        plugin.keywords = (List<String>) plugin.pluginConfig.getList("keywords");
        //
        Loggers.info(plugin, "Has been created by Wild1145 - Check out www.superior-networks.com for great value servers!");
    }

    @Override
    public void disable()
    {
        pluginConfig.save();
        Loggers.info(plugin, "Has been created by Wild1145 - Check out www.superior-networks.com for great value servers!");
        CommandViewer.plugin = null;
    }
  }
