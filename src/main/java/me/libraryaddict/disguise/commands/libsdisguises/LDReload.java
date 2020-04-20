package me.libraryaddict.disguise.commands.libsdisguises;

import me.libraryaddict.disguise.DisguiseConfig;
import me.libraryaddict.disguise.utilities.translations.LibsMsg;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

/**
 * Created by libraryaddict on 20/04/2020.
 */
public class LDReload implements LDCommand {
    @Override
    public List<String> getTabComplete() {
        return Collections.singletonList("reload");
    }

    @Override
    public String getPermission() {
        return "libsdisguises.reload";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        DisguiseConfig.loadConfig();
        sender.sendMessage(LibsMsg.RELOADED_CONFIG.get());
    }

    @Override
    public LibsMsg getHelp() {
        return null;
    }
}
