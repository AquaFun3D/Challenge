package com.aquafun3d.challenge.listeners

import com.aquafun3d.challenge.utils.Settings
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import kotlin.math.roundToInt


class DmgListener : Listener {

	@EventHandler
	fun onDmg(e: EntityDamageEvent) {
		if (e.entity is Player) {
			//TODO e.damage = e.damage * ChallengeStatsListener.dmgmulti
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
}