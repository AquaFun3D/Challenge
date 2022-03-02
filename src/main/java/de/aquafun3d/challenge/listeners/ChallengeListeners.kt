package de.aquafun3d.challenge.listeners

import de.aquafun3d.challenge.Main
import de.aquafun3d.challenge.utils.Settings
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

	//handles death of the player (challenge lose)
	@EventHandler
	fun onPlayerDeath(e: PlayerDeathEvent) {
		val player = e.entity
		if (!Main.timerService?.getInstance()?.isPaused()!!) {
			Settings.atAll(ChatColor.YELLOW.toString() + "Player " + ChatColor.GOLD.toString() + player.name + ChatColor.YELLOW.toString() + " died")
			Settings.atAll(ChatColor.RED.toString() + "Challenge has stopped")
			Settings.atAll(ChatColor.GREEN.toString() + "Time wasted: " + ChatColor.GOLD.toString() + Main.timerService?.getInstance()?.getTimerString())
			Bukkit.getOnlinePlayers().forEach { p: Player -> p.gameMode = GameMode.SPECTATOR }
			Main.timerService?.getInstance()?.toggle()
		}
	}

	//handles the finish of the challenge (Ender-dragon kill)
	@EventHandler
	fun onPlayerWin(e: EntityDeathEvent) {
		if (e.entity is EnderDragon) {
			Settings.atAll(ChatColor.GREEN.toString() + "Time: " + ChatColor.GOLD.toString() + Main.timerService?.getInstance()?.getTimerString())
			Settings.atAll(ChatColor.GOLD.toString() + "Challenge won, you killed the Enderdragon!")
			Bukkit.getOnlinePlayers().forEach { p: Player? -> p!!.gameMode = GameMode.SPECTATOR }
			Main.timerService?.getInstance()?.toggle()
		}
	}
}