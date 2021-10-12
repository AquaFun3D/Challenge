package com.aquafun3d.challenge.listeners

import com.aquafun3d.challenge.Main
import com.aquafun3d.challenge.utils.Settings
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import kotlin.math.roundToInt

class DmgListener : Listener {

	@EventHandler
	fun onDmg(e: EntityDamageEvent) {
		if (e.entity is Player) {
			e.damage = e.damage * Main.settingsInv?.getDmg()!!
			if (Settings.dmgListenerToggle) {
				val cause: String? = Settings.damageCauseMap[e.cause]
				var amountd = e.finalDamage / 2
				amountd = (amountd * 100).roundToInt() / 100.0
				val amounts = amountd.toString()
				if (!(e.cause.toString() == "ENTITY_ATTACK"
							|| e.cause.toString() == "ENTITY_EXPLOSION"
							|| e.cause.toString() == "ENTITY_SWEEP_ATTACK"
							|| e.cause.toString() == "PROJECTILE")
				) {
					if (amountd > 0.01) {
						Bukkit.getOnlinePlayers().forEach { p: Player? -> p!!.sendMessage(Settings.dmgMessage(e.entity.name, amounts, cause!!)) }
					}
				}
			}
		}
	}

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