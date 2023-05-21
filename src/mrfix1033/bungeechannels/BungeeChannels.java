package mrfix1033.bungeechannels;

import mrfix1033.bungeechannels.bungeecord.BungeeBukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class BungeeChannels extends JavaPlugin implements CommandExecutor, Listener {

    private static BungeeChannels instance;
    public BungeeBukkit bungeeBukkit;

    @Override
    public void onEnable() {
        instance = this;
        bungeeBukkit = new BungeeBukkit(this);
        bungeeBukkit.register();
        getCommand("bb").setExecutor(new CommandHandler(this));
        getLogger().info("Плагин BungeeChannels включен");
    }

    @Override
    public void onDisable() {
        if (bungeeBukkit != null) bungeeBukkit.unregister();
        getLogger().info("Плагин BungeeChannels выключен");
    }
}
