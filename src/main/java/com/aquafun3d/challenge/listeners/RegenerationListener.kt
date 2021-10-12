package com.aquafun3d.challenge.listeners

import com.aquafun3d.challenge.Main
import com.aquafun3d.challenge.utils.Settings
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityRegainHealthEvent


class RegenerationListener: Listener {

	@EventHandler
	fun onReg(e: EntityRegainHealthEvent) {
		if (Main.settingsInv?.getHardcore() == Settings.Hardcore.UUHC) {
			if (e.entity is Player) {
				if (e.regainReason == EntityRegainHealthEvent.RegainReason.MAGIC || e.regainReason == EntityRegainHealthEvent.RegainReason.MAGIC_REGEN || e.regainReason == EntityRegainHealthEvent.RegainReason.EATING || e.regainReason == EntityRegainHealthEvent.RegainReason.CUSTOM) {
					e.isCancelled = true
				}
			}
		}
	}
}