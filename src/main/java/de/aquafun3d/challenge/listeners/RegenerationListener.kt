package de.aquafun3d.challenge.listeners

import de.aquafun3d.challenge.Main
import de.aquafun3d.challenge.utils.Utils
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityRegainHealthEvent


class RegenerationListener: Listener {

	//handles regeneration of health
	@EventHandler
	fun onReg(e: EntityRegainHealthEvent) {
		if (Main.settingsInv?.getHardcore() == Utils.Hardcore.UUHC) {
			if (e.entity is Player) {
				if (e.regainReason == EntityRegainHealthEvent.RegainReason.MAGIC || e.regainReason == EntityRegainHealthEvent.RegainReason.MAGIC_REGEN || e.regainReason == EntityRegainHealthEvent.RegainReason.EATING || e.regainReason == EntityRegainHealthEvent.RegainReason.CUSTOM) {
					e.isCancelled = true
				}
			}
		}
	}
}