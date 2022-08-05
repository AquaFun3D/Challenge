package de.aquafun3d.challenge.listeners

import de.aquafun3d.challenge.Main
import de.aquafun3d.challenge.utils.Utils
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
			Utils.atAll("${ChatColor.YELLOW}Player ${ChatColor.GOLD}player.name ${ChatColor.YELLOW}died")
			Utils.atAll("${ChatColor.RED}Challenge has stopped")
			Utils.atAll("${ChatColor.GREEN}Time wasted: ${ChatColor.GOLD}" + Main.timerService?.getInstance()?.getTimerString())
			Bukkit.getOnlinePlayers().forEach { p: Player -> p.gameMode = GameMode.SPECTATOR }
			Main.timerService?.getInstance()?.toggle()
		}
	}

	//handles the finish of the challenge (Ender-dragon kill)
	@EventHandler
	fun onPlayerWin(e: EntityDeathEvent) {
		if (e.entity is EnderDragon) {
			Utils.atAll("${ChatColor.GREEN}Time: ${ChatColor.GOLD}" + Main.timerService?.getInstance()?.getTimerString())
			Utils.atAll("${ChatColor.GOLD}Challenge won, you killed the Enderdragon!")
			Bukkit.getOnlinePlayers().forEach { p: Player? -> p!!.gameMode = GameMode.SPECTATOR }
			Main.timerService?.getInstance()?.toggle()
		}
	}
}