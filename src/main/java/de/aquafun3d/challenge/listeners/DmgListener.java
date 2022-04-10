package de.aquafun3d.challenge.listeners;

import de.aquafun3d.challenge.Main;
import de.aquafun3d.challenge.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Objects;

public class DmgListener implements Listener {

	@EventHandler
	public void onDmg(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			e.setDamage(e.getDamage() * Main.settingsInv.getDamage());
			if (Utils.dmgListenerToggle) {
				String cause = Utils.damageCauseMap.get(e.getCause());
				int amountd = (int) (e.getFinalDamage() / 2);
				amountd = (int) ((amountd * 100) / 100.0);
				String amounts = String.valueOf(amountd);
				if (!(Objects.equals(e.getCause().toString(), "ENTITY_ATTACK")
						|| Objects.equals(e.getCause().toString(), "ENTITY_EXPLOSION")
						|| Objects.equals(e.getCause().toString(), "ENTITY_SWEEP_ATTACK")
						|| Objects.equals(e.getCause().toString(), "PROJECTILE"))
				) {
					if (amountd > 0.01) {
						Utils.atAll(Utils.dmgMessage(e.getEntity().getName(), amounts, cause));
					}
				}
			}
		}
	}

	@EventHandler
	public void onDmg(EntityDamageByEntityEvent e){
		if (Utils.dmgListenerToggle) {
			String attacker = e.getDamager().getName().replace('_',' ');
			if (e.getEntity() instanceof Player) {
				double damageAmount = e.getFinalDamage() / 2;
				damageAmount = (damageAmount * 100) / 100.0;
				String amounts = String.valueOf(damageAmount);
				if (damageAmount > 0.01) {
					Utils.atAll(Utils.dmgMessage(e.getEntity().getName(), amounts, attacker));
				}
			}
		}
	}
}
