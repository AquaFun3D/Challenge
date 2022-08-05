package de.aquafun3d.challenge.commands

import de.aquafun3d.challenge.Main
import de.aquafun3d.challenge.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Difficulty
import org.bukkit.GameMode
import org.bukkit.GameRule.ANNOUNCE_ADVANCEMENTS
import org.bukkit.GameRule.NATURAL_REGENERATION
import org.bukkit.attribute.Attribute
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


class StartCommand:CommandExecutor {

	//Starts the challenge
	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
		if(!Main.timerService?.getInstance()?.isPaused()!! && Main.timerService?.getInstance()?.getSec() != 0 ){
			return false
		}
		for (world in Bukkit.getWorlds()){
			world.setGameRule(ANNOUNCE_ADVANCEMENTS,false)
		}
		Bukkit.getOnlinePlayers().forEach { p: Player? -> p!!.gameMode = GameMode.SURVIVAL }
		Utils.atAll("${ChatColor.LIGHT_PURPLE}Challenge started")
		if(Main.settingsInv?.getHardcore() == Utils.Hardcore.UHC){
			for (world in Bukkit.getWorlds()) {
				world.setGameRule(NATURAL_REGENERATION, false)
			}
		}
		when (Main.settingsInv?.getDifficulty()) {
			Difficulty.EASY -> for (world in Bukkit.getWorlds()){ world.difficulty = Difficulty.EASY }
			Difficulty.HARD -> for (world in Bukkit.getWorlds()){ world.difficulty = Difficulty.HARD }
			Difficulty.NORMAL -> for (world in Bukkit.getWorlds()){ world.difficulty = Difficulty.NORMAL }
			Difficulty.PEACEFUL -> for (world in Bukkit.getWorlds()){ world.difficulty = Difficulty.PEACEFUL }
			else -> {}
		}
		Bukkit.getOnlinePlayers().forEach { p: Player? ->
			p!!.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = Main.settingsInv?.getHealth()!!.toDouble()
		}
		if (Main.timerService?.getSec()!! <= 100) {
			Bukkit.getOnlinePlayers().forEach { p: Player? -> p!!.health = Main.settingsInv?.getHealth()!!.toDouble() }
		}
		Main.timerService?.getInstance()?.toggle()
		Main.settingsInv?.writeSettings()
		return false
	}
}