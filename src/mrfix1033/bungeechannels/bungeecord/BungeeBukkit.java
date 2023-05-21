package mrfix1033.bungeechannels.bungeecord;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import mrfix1033.bungeechannels.BungeeChannels;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.UUID;

public class BungeeBukkit implements PluginMessageListener, Listener {

    private final BungeeChannels instance;

    public BungeeBukkit(BungeeChannels instance) {
        this.instance = instance;
    }

    public void register() {
        instance.getServer().getMessenger().registerOutgoingPluginChannel(instance, "BungeeCord");
        instance.getServer().getMessenger().registerIncomingPluginChannel(instance, "BungeeCord", this);
        instance.getServer().getPluginManager().registerEvents(this, instance);
    }

    public void unregister() {
        instance.getServer().getMessenger().unregisterOutgoingPluginChannel(instance);
        instance.getServer().getMessenger().unregisterIncomingPluginChannel(instance);
    }

    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) return;
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (!subchannel.equals("BungeeChannels")) return;
        UUID uuid = UUID.fromString(in.readUTF());
        String data = in.readUTF();
        // делаете что нужно с вашими данными, если нужно, читаете дальше
    }

    public ByteArrayDataOutput getBuilderMessage(UUID senderUUID) {
        ByteArrayDataOutput bado = ByteStreams.newDataOutput();
        bado.writeUTF("BungeeChannels");
        bado.writeUTF(senderUUID.toString());
        return bado;
    }

    public void sendMessage(ByteArrayDataOutput out) {
        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
        player.sendPluginMessage(instance, "BungeeCord", out.toByteArray());
    }
}
