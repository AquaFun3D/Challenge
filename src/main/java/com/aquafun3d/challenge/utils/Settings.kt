package com.aquafun3d.challenge.utils

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent

object Settings {
	val PREFIX: String = "" + ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + "Challenge" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_PURPLE

	//Sends message to player with Challenge prefix
	fun send(message: String, player : Player){
		player.sendMessage("$PREFIX $message")
	}

	//Sends message to all players with Challenge prefix
	fun atAll(message: String){
		for(p in Bukkit.getOnlinePlayers()){
			p.sendMessage("$PREFIX$message")
		}
	}

	var dmgListenerToggle = true

	fun dmgMessage(player: String, amount: String, source: String ):String{
		return PREFIX + ChatColor.AQUA + player + ChatColor.GREEN.toString() + " got " + ChatColor.GOLD.toString() + amount + ChatColor.GREEN.toString() + " hearts damage by " + ChatColor.BLUE.toString() + source
	}

	enum class Hardcore {
		NUHC, UHC, UUHC
	}

	val damageCauseMap = mutableMapOf<EntityDamageEvent.DamageCause, String>()
	fun fillMap() {
		damageCauseMap[EntityDamageEvent.DamageCause.BLOCK_EXPLOSION] = "EXPLOSION"
		damageCauseMap[EntityDamageEvent.DamageCause.CONTACT] = "CONTACT"
		damageCauseMap[EntityDamageEvent.DamageCause.CRAMMING] = "TO MANY MOBS"
		damageCauseMap[EntityDamageEvent.DamageCause.CUSTOM] = "CUSTOM"
		damageCauseMap[EntityDamageEvent.DamageCause.DRAGON_BREATH] = "DRAGON BREATH"
		damageCauseMap[EntityDamageEvent.DamageCause.DROWNING] = "DROWNING"
		damageCauseMap[EntityDamageEvent.DamageCause.FALL] = "FALL"
		damageCauseMap[EntityDamageEvent.DamageCause.FALLING_BLOCK] = "FALLING BLOCK"
		damageCauseMap[EntityDamageEvent.DamageCause.FIRE] = "FIRE"
		damageCauseMap[EntityDamageEvent.DamageCause.FIRE_TICK] = "FIRE TICK"
		damageCauseMap[EntityDamageEvent.DamageCause.FLY_INTO_WALL] = "FLYING INTO A WALL"
		damageCauseMap[EntityDamageEvent.DamageCause.FREEZE] = "FREEZE"
		damageCauseMap[EntityDamageEvent.DamageCause.HOT_FLOOR] = "MAGMA BLOCK"
		damageCauseMap[EntityDamageEvent.DamageCause.LAVA] = "LAVA"
		damageCauseMap[EntityDamageEvent.DamageCause.LIGHTNING] = "LIGHTNING"
		damageCauseMap[EntityDamageEvent.DamageCause.MAGIC] = "MAGIC"
		damageCauseMap[EntityDamageEvent.DamageCause.POISON] = "POISON"
		damageCauseMap[EntityDamageEvent.DamageCause.STARVATION] = "STARVING"
		damageCauseMap[EntityDamageEvent.DamageCause.SUFFOCATION] = "SUFFOCATING"
		damageCauseMap[EntityDamageEvent.DamageCause.SUICIDE] = "SUICIDE"
		damageCauseMap[EntityDamageEvent.DamageCause.THORNS] = "THORNS"
		damageCauseMap[EntityDamageEvent.DamageCause.VOID] = "VOID"
		damageCauseMap[EntityDamageEvent.DamageCause.WITHER] = "WITHER"
	}
}