package ru.mishaneyt.indicator.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.mishaneyt.indicator.Main;
import ru.mishaneyt.indicator.utils.UtilsConfig;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("ibowhp.admin")) {
            sender.sendMessage(UtilsConfig.noPerm);
            return false;
        }

        sender.sendMessage(UtilsConfig.Reload);
        Main.getInstance().reloadConfig();
        Main.getInstance().saveConfig();
        return false;
    }
}