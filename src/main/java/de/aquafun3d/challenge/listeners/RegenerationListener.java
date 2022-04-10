package de.aquafun3d.challenge.listeners;

import de.aquafun3d.challenge.Main;
import de.aquafun3d.challenge.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class RegenerationListener implements Listener {

	@EventHandler
	public void onReg(EntityRegainHealthEvent e) {
		if (Main.settingsInv.getHardcore() == Utils.Hardcore.UUHC) {
			if (e.getEntity() instanceof Player) {
				if (e.getRegainReason() == EntityRegainHealthEvent.RegainReason.MAGIC || e.getRegainReason() == EntityRegainHealthEvent.RegainReason.MAGIC_REGEN || e.getRegainReason() == EntityRegainHealthEvent.RegainReason.EATING || e.getRegainReason() == EntityRegainHealthEvent.RegainReason.CUSTOM) {
					e.setCancelled(true);
				}
			}
		}
	}
}
