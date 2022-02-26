package ru.mishaneyt.indicator.utils;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import ru.mishaneyt.indicator.Main;

public class Utils {

    public static boolean getBoolean(String config) {
        return Main.getInstance().getConfig().getBoolean(config);
    }

    public static String color(String config) {
        return ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString(config));
    }

    public static void getBlood(Player damagePlayer) {
        damagePlayer.getWorld().playEffect(damagePlayer.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
    }

    public static void onLogger() {
        Main.getInstance().getLogger().info("");
        Main.getInstance().getLogger().info("§a---------------------------------------------------------------");
        Main.getInstance().getLogger().info("§aIndicatorBowHP - плагин успешно включен! Создатель MishaNeYT.");
        Main.getInstance().getLogger().info("§a---------------------------------------------------------------");
        Main.getInstance().getLogger().info("");
    }
}