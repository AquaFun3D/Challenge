package de.aquafun3d.challenge.commands

import de.aquafun3d.challenge.utils.Utils
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
				if (Utils.dmgListenerToggle) {
					Utils.dmgListenerToggle = false
					Utils.send(sender,"${ChatColor.GOLD}Damage visibility ${ChatColor.GREEN}OFF")
				} else {
					Utils.dmgListenerToggle = true
					Utils.send(sender,"${ChatColor.GOLD}Damage visibility ${ChatColor.GREEN}ON")
				}
			}
		}
		return false
	}
}
