package ru.mishaneyt.indicator;

import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;
import ru.mishaneyt.indicator.commands.Commands;
import ru.mishaneyt.indicator.utils.Utils;
import ru.mishaneyt.indicator.utils.UtilsConfig;

public class Main extends JavaPlugin {
    public static Main instance;
    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;

        Bukkit.getPluginManager().registerEvents(new DamageEvents(), this);
        getCommand("ibowhp").setExecutor(new Commands());

        UtilsConfig.onConfig();
        Utils.onLogger();
    }
}