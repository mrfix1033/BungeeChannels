package mrfix1033.bungeechannels;

import com.google.common.io.ByteArrayDataOutput;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {
    private final BungeeChannels instance;

    public CommandHandler(BungeeChannels instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        // создание массива байтов с возможностью дозаписи
        ByteArrayDataOutput builderMessage =
                instance.bungeeBukkit.getBuilderMessage(((Player) sender).getUniqueId());
        // пример записи данных
        builderMessage.writeUTF(String.join(" ", args));
        // отправка
        instance.bungeeBukkit.sendMessage(builderMessage);
        return true;
    }
}
