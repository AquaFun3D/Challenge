package com.aquafun3d.challenge.utils

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Difficulty
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent

object Settings {
	//Challenge Prefix
	val PREFIX: String = "" + ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + "Challenge" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_PURPLE

	//Sends message to player with Challenge prefix
	fun send(message: String, player : Player){
		player.sendMessage("$PREFIX$message")
	}

	//Sends message to all players with Challenge prefix
	fun atAll(message: String){
		for(p in Bukkit.getOnlinePlayers()){
			p.sendMessage("$PREFIX$message")
		}
	}

	//Global variable to display recieved damage in chat
	var dmgListenerToggle = true

	//Map damage values into a proper string to print
	fun dmgMessage(player: String, amount: String, source: String ):String{
		return PREFIX + ChatColor.AQUA + player + ChatColor.GREEN.toString() + " got " + ChatColor.GOLD.toString() + amount + ChatColor.GREEN.toString() + " hearts damage by " + ChatColor.BLUE.toString() + source
	}

	//Hardcore modes enum
	enum class Hardcore {
		NUHC, UHC, UUHC
	}

	fun stringToHardcore(s: String):Hardcore{
		return when (s){
			"NUHC" -> Hardcore.NUHC
			"UHC" -> Hardcore.UHC
			"UUHC" -> Hardcore.UUHC
			else -> Hardcore.NUHC
		}
	}

	fun stringToDifficulty(s: String):Difficulty{
		return when (s){
			"EASY" -> Difficulty.EASY
			"NORMAL" -> Difficulty.NORMAL
			"HARD" -> Difficulty.HARD
			else -> Difficulty.PEACEFUL
		}
	}

	//Map with better damagesource descriptions
	val damageCauseMap = mutableMapOf<EntityDamageEvent.DamageCause, String>()

	//Fills damagemap
	fun fillMap() {
		damageCauseMap[EntityDamageEvent.DamageCause.BLOCK_EXPLOSION] = "Explosion"
		damageCauseMap[EntityDamageEvent.DamageCause.CONTACT] = "Contact"
		damageCauseMap[EntityDamageEvent.DamageCause.CRAMMING] = "To many mobs"
		damageCauseMap[EntityDamageEvent.DamageCause.CUSTOM] = "Custom"
		damageCauseMap[EntityDamageEvent.DamageCause.DRAGON_BREATH] = "Dragon breath"
		damageCauseMap[EntityDamageEvent.DamageCause.DROWNING] = "Drowning"
		damageCauseMap[EntityDamageEvent.DamageCause.FALL] = "Fall"
		damageCauseMap[EntityDamageEvent.DamageCause.FALLING_BLOCK] = "Falling block"
		damageCauseMap[EntityDamageEvent.DamageCause.FIRE] = "Fire"
		damageCauseMap[EntityDamageEvent.DamageCause.FIRE_TICK] = "Fire tick"
		damageCauseMap[EntityDamageEvent.DamageCause.FLY_INTO_WALL] = "Flying into a wall"
		damageCauseMap[EntityDamageEvent.DamageCause.FREEZE] = "Freeze"
		damageCauseMap[EntityDamageEvent.DamageCause.HOT_FLOOR] = "Magma block"
		damageCauseMap[EntityDamageEvent.DamageCause.LAVA] = "Lava"
		damageCauseMap[EntityDamageEvent.DamageCause.LIGHTNING] = "Lightning"
		damageCauseMap[EntityDamageEvent.DamageCause.MAGIC] = "Magic"
		damageCauseMap[EntityDamageEvent.DamageCause.POISON] = "Poison"
		damageCauseMap[EntityDamageEvent.DamageCause.STARVATION] = "Starving"
		damageCauseMap[EntityDamageEvent.DamageCause.SUFFOCATION] = "Suffocation"
		damageCauseMap[EntityDamageEvent.DamageCause.SUICIDE] = "Suicide"
		damageCauseMap[EntityDamageEvent.DamageCause.THORNS] = "Thorns"
		damageCauseMap[EntityDamageEvent.DamageCause.VOID] = "Void"
		damageCauseMap[EntityDamageEvent.DamageCause.WITHER] = "Wither"
	}
}