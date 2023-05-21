package mrfix1033.bungeechannels.bungeecord;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.util.UUID;

public class BungeeBungee extends Plugin implements Listener {

    public void onEnable() {
        register();
    }

    public void onDisable() {
        unregister();
    }

    public void register() {
        BungeeCord.getInstance().registerChannel("BungeeCord");
        getProxy().getPluginManager().registerListener(this, this);
    }

    public void unregister() {
        net.md_5.bungee.BungeeCord.getInstance().unregisterChannel("BungeeCord");
    }

    @EventHandler
    public void onPluginMessageEvent(PluginMessageEvent e) {
        if (!e.getTag().equalsIgnoreCase("BungeeCord")) return;
        ByteArrayDataInput in = ByteStreams.newDataInput(e.getData());
        if (!in.readUTF().equals("BungeeChannels")) return;
        String senderUUID = in.readUTF();
        ProxiedPlayer sender = getProxy().getPlayer(UUID.fromString(senderUUID));
        for (ServerInfo serverInfo : getProxy().getServers().values()) {
            if (sender.getServer().getInfo().getName().equals(serverInfo.getName())) continue;
            serverInfo.sendData(e.getTag(), e.getData());
        }
    }
}
