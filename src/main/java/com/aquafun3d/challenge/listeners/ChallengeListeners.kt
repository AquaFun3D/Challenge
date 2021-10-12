package com.aquafun3d.challenge.listeners

import com.aquafun3d.challenge.Main
import com.aquafun3d.challenge.utils.Settings
import com.aquafun3d.challenge.utils.TimerService
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.entity.EnderDragon
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.PlayerDeathEvent


class ChallengeListeners: Listener {

	@EventHandler
	fun onPlayerDeath(e: PlayerDeathEvent) {
		val player = e.entity
		if (!Main.timerService?.getInstance()?.isPaused()!!) {
			Settings.atAll(ChatColor.GRAY.toString() + "Player " + ChatColor.GOLD.toString() + player.name + ChatColor.GRAY.toString() + " died")
			Settings.atAll(ChatColor.RED.toString() + "Challenge has stopped")
			Settings.atAll(ChatColor.GREEN.toString() + "Time wasted: " + ChatColor.GOLD.toString() + Main.timerService?.getInstance()?.getTimerString())
			Bukkit.getOnlinePlayers().forEach { p: Player -> p.gameMode = GameMode.SPECTATOR }
			Main.timerService?.getInstance()?.toggle()
		}
	}

	@EventHandler
	fun onPlayerWin(e: EntityDeathEvent) {
		if (e.entity is EnderDragon) {
			Settings.atAll(ChatColor.GREEN.toString() + "Time: " + ChatColor.GOLD.toString() + Main.timerService?.getInstance()?.getTimerString())
			Settings.atAll(ChatColor.GOLD.toString() + "Challenge won, you killed the enderdragon!")
			Bukkit.getOnlinePlayers().forEach { p: Player? -> p!!.gameMode = GameMode.SPECTATOR }
			Main.timerService?.getInstance()?.toggle()
		}
	}
}