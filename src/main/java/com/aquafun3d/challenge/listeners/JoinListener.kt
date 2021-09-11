package com.aquafun3d.challenge.listeners

import com.aquafun3d.challenge.Main
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.RenderType
import org.bukkit.scoreboard.Scoreboard

class JoinListener : Listener {

	@EventHandler
	fun onJoin(e: PlayerJoinEvent) {
		val player: Player = e.player

		val board: Scoreboard = Bukkit.getScoreboardManager()!!.newScoreboard
		val obj: Objective = board.registerNewObjective("health", "health", "health")
		obj.getScore("health").score = 1
		obj.displaySlot = DisplaySlot.PLAYER_LIST
		obj.renderType = RenderType.HEARTS
		player.scoreboard = board

		if (Main.timerConfig!!.getInt("timer.time") != 0) {
			player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent( "" + ChatColor.GREEN + "" + ChatColor.ITALIC + "Timer paused"))
		}
		//TODO Bugfix new player join show health of others
	}
}