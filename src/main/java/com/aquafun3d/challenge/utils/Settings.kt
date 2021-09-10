package com.aquafun3d.challenge.utils

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object Settings {
	val PREFIX: String = "" + ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + "Challenge" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_PURPLE

	//Sends message to player with Challenge prefix
	fun send(message: String, player : Player){
		player.sendMessage("$PREFIX $message")
	}

	//Sends message to all players with Challenge prefix
	fun atAll(message: String){
		for(p in Bukkit.getOnlinePlayers()){
			p.sendMessage("$PREFIX $message")
		}
	}

}