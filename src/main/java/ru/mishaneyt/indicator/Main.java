package ru.mishaneyt.indicator;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Main extends JavaPlugin implements Listener, CommandExecutor {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(this, this);

        Objects.requireNonNull(getCommand("ibowhp")).setExecutor(this);

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "---------------------------------------------------------------");
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "IndicatorBowHP - has been enabled, Author: MishaNeYT");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "---------------------------------------------------------------");

        saveConfig();
    }

    @Override
    public void onDisable() { saveConfig(); }


    /* ******************************************************************************************* */


    @EventHandler(priority = EventPriority.HIGH)
    public void DamageEvent(EntityDamageByEntityEvent event) {
        if (getConfig().getBoolean("Settings.EnablePlugin")) {
            if (event.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) event.getDamager();
                if (arrow.getShooter() instanceof Player) {
                    Player shooter = (Player) arrow.getShooter();
                    Damageable damagePlayer = (Damageable) event.getEntity();
                    if (damagePlayer instanceof Player) {
                        Player victim = (Player) damagePlayer;
                        double vh = damagePlayer.getHealth();
                        int damage = (int) event.getDamage();
                        if (!damagePlayer.isDead()) {
                            int health = (int) (vh - damage);

                            if (health > 0)
                                if (getConfig().getBoolean("Settings.NeedPermission")) {
                                    if (shooter.hasPermission("ibowhp.hit")) {
                                        shooter.sendTitle("", ChatColor.RED + "" + health, 1, 10, 1);
                                        if (getConfig().getBoolean("Settings.BloodEffect")) {
                                            damagePlayer.getWorld().playEffect(damagePlayer.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
                                        }
                                    }
                                } else {
                                    shooter.sendTitle("", ChatColor.RED + "" + health, 1, 10, 1);
                                    if (getConfig().getBoolean("Settings.BloodEffect")) {
                                        damagePlayer.getWorld().playEffect(damagePlayer.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
                                    }
                                }


                            if (health < 0)
                                if (getConfig().getBoolean("Settings.NeedPermission")) {

                                    if (getConfig().getBoolean("Settings.DeathMessage")) {
                                        String mHit = ChatColor.translateAlternateColorCodes('&', String.valueOf(getConfig().getString("Messages.DeathMessage")));
                                        mHit = mHit.replaceAll("%player%", victim.getName());
                                        shooter.sendMessage(mHit);
                                    }

                                    shooter.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "☠", "", 1, 10, 1);
                                    shooter.playSound(shooter.getLocation(), Sound.valueOf(this.getConfig().getString("Settings.SoundDeath")), 1.0F, 1.0F);
                                } else {
                                    if (getConfig().getBoolean("Settings.DeathMessage")) {
                                        String mHit = ChatColor.translateAlternateColorCodes('&', String.valueOf(getConfig().getString("Messages.DeathMessage")));
                                        mHit = mHit.replaceAll("%player%", victim.getName());
                                        shooter.sendMessage(mHit);
                                    }

                                    shooter.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "☠", "", 1, 10, 1);
                                    shooter.playSound(shooter.getLocation(), Sound.valueOf(this.getConfig().getString("Settings.SoundDeath")), 1.0F, 1.0F);
                                }
                        }
                    }
                }
            }
        }
    }


    /* ******************************************************************************************* */


    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ibowhp")) {
            if (sender.hasPermission("ibowhp.admin")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(this.getConfig().getString("Messages.Reload"))));
                reloadConfig();
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(this.getConfig().getString("Messages.noPerms"))));
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(this.getConfig().getString("Messages.Reload"))));
            reloadConfig();
        }
        return false;
    }
}
