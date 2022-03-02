package de.aquafun3d.challenge.commands

import de.aquafun3d.challenge.utils.Settings
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class DmgCommand: CommandExecutor {

	//Toggle Damage visibility in chat
	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
		if (sender is Player) {
			if (sender.isOp) {
				if (Settings.dmgListenerToggle) {
					Settings.dmgListenerToggle = false
					sender.sendMessage(Settings.PREFIX + ChatColor.GOLD.toString() + "Damage visibility" + ChatColor.GREEN.toString() + " OFF")
				} else {
					Settings.dmgListenerToggle = true
					sender.sendMessage(Settings.PREFIX + ChatColor.GOLD.toString() + "Damage visibility" + ChatColor.GREEN.toString() + " ON")
				}
			}
		}
		return false
	}
}
