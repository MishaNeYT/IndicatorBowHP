package ru.mishaneyt.indicator.utils;

import ru.mishaneyt.indicator.Main;

public class UtilsConfig {
    public static String MDeathMessage;
    public static String noPerm;
    public static String Reload;
    public static String SoundDeath;

    public static void onConfig() {
        MDeathMessage = Utils.color("Messages.DeathMessage");
        noPerm = Utils.color("Messages.noPerm");
        Reload = Utils.color("Messages.Reload");
        SoundDeath = Main.getInstance().getConfig().getString("Settings.SoundDeath");
    }
}