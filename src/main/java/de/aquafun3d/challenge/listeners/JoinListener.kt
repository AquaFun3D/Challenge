package de.aquafun3d.challenge.listeners

import de.aquafun3d.challenge.Main
import de.aquafun3d.challenge.utils.Utils
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.RenderType
import org.bukkit.scoreboard.Scoreboard

class JoinListener : Listener {

	//handles the player join
	@EventHandler
	fun onJoin(e: PlayerJoinEvent) {
		val player: Player = e.player
		e.joinMessage(Component.text(Utils.PREFIX + "${ChatColor.AQUA}${player.name} ${ChatColor.GOLD}has joined"))
		val board: Scoreboard = Bukkit.getScoreboardManager().newScoreboard
		val obj: Objective = board.registerNewObjective("health", "health", Component.text("health"),RenderType.HEARTS)
		obj.getScore("health").score = 1
		obj.displaySlot = DisplaySlot.PLAYER_LIST
		player.scoreboard = board
		player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = Main.settingsInv?.getHealth()!!.toDouble()
		player.health = Main.settingsInv?.getHealth()!!.toDouble()

		if (Main.challengeConfig!!.getInt("time") != 0) {
			Main.timerService?.setTimerActionbar(player)
		}
		for(p in Bukkit.getOnlinePlayers()){
			p.damage(0.1)
		}
	}

	//handles the player leaving the server
	@EventHandler
	fun onLeave(e: PlayerQuitEvent) {
		val player: Player = e.player
		e.quitMessage(Component.text(Utils.PREFIX + "${ChatColor.AQUA}${player.name} ${ChatColor.LIGHT_PURPLE}has left"))
	}
}