package ru.mishaneyt.indicator;

import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ru.mishaneyt.indicator.utils.Utils;
import ru.mishaneyt.indicator.utils.UtilsConfig;

public class DamageEvents implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void DamageEvent(EntityDamageByEntityEvent e) {

        if (Utils.getBoolean("Settings.EnablePlugin")) {

            if (!(e.getDamager() instanceof Arrow)) return;
            Arrow arrow = (Arrow) e.getDamager();

            if (!(arrow.getShooter() instanceof Player)) return;
            Player shooter = (Player) arrow.getShooter();
            Damageable damagePlayer = (Damageable) e.getEntity();

            if (!(damagePlayer instanceof Player)) return;

            Player victim = (Player) damagePlayer;
            double vh = damagePlayer.getHealth();
            int damage = (int) e.getDamage();
            int health = (int) (vh - damage);

            if (!damagePlayer.isDead()) {

                if (health > 0) {
                    if (Utils.getBoolean("Settings.EnableTitleDamage")) {
                        shooter.sendTitle("", "§c" + health, 0, 10, 0);
                    }

                    if (Utils.getBoolean("Settings.BloodEffect")) {
                        Utils.getBlood((Player) damagePlayer);
                    }
                } else if (health < 0) {
                    if (Utils.getBoolean("Settings.DeathMessage")) {
                        String msg = UtilsConfig.MDeathMessage.replace("%player%", victim.getName());
                        shooter.sendMessage(msg);
                    }

                    if (Utils.getBoolean("Settings.EnableTitleDeath")) {
                        shooter.sendTitle("§c§l☠", "", 0, 15, 0);
                    }
                    shooter.playSound(shooter.getLocation(), Sound.valueOf(UtilsConfig.SoundDeath), 1.0F, 1.0F);
                }
            }
        }
    }
}