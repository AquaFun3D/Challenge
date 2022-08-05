package de.aquafun3d.challenge.commands

import de.aquafun3d.challenge.Main
import de.aquafun3d.challenge.utils.Utils
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TimerCommand: CommandExecutor {

	//toggles the timer
	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
		if (sender !is Player) {
			return false
		}
		if(sender.isOp) {
			if (args.size != 1 || args[0] != "toggle" && args[0] != "reset"){
				Utils.send(sender,"${ChatColor.GREEN}Please use ${ChatColor.GOLD}./timer toggle")
				return false
			}else {
				if (args[0] == "toggle"){
					Main.timerService?.getInstance()?.toggle()
				}else{
					Main.timerService?.getInstance()?.reset()
					Utils.send(sender,"${ChatColor.RED}Timer reset")
				}
			}
		}
		return false
	}
}