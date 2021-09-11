package com.aquafun3d.challenge.listeners

import com.aquafun3d.challenge.utils.Settings
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import kotlin.math.roundToInt


class DmgEntityListener: Listener {
	@EventHandler
	fun onDmg(e: EntityDamageByEntityEvent){
		if (Settings.dmgListenerToggle) {
			val attacker: String = e.damager.name.replace('_',' ')
			if (e.entity is Player) {
				var damageAmount: Double = e.finalDamage / 2
				damageAmount = (damageAmount * 100).roundToInt() / 100.0
				val amounts = damageAmount.toString()
				if (damageAmount > 0.01) {
					Bukkit.getOnlinePlayers().forEach { p: Player? -> p!!.sendMessage(Settings.dmgMessage(e.entity.name, amounts, attacker)) }
				}
			}
		}
	}
}