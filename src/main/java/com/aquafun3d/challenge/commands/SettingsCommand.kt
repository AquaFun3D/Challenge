package com.aquafun3d.challenge.commands

import com.aquafun3d.challenge.Main
import com.aquafun3d.challenge.utils.Settings
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.GameRule.NATURAL_REGENERATION
import org.bukkit.attribute.Attribute
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


class SettingsCommand: CommandExecutor {

	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
		if (sender is Player) {
			if (sender.isOp) {
				if (args.size == 1 && args[0] == "reset"){
					for (world in Bukkit.getWorlds()) {
						world.setGameRule(NATURAL_REGENERATION, true)
						Bukkit.getOnlinePlayers().forEach { p: Player? -> p!!.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = 20.0
						}
					}
					Settings.send("" + ChatColor.GREEN + "Setting reset", sender)
				}else{
					Main.settingsInv?.newInventory(sender)
				}
			}
		}
		return false
	}
}