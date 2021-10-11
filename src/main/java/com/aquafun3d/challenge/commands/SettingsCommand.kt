package com.aquafun3d.challenge.commands

import com.aquafun3d.challenge.Main
import com.aquafun3d.challenge.utils.SettingsInv
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SettingsCommand: CommandExecutor {

	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
		if (sender is Player) {
			if (sender.isOp) {
				Main.settingsInv?.newInventory(sender)
			}
		}
		return false
	}
}