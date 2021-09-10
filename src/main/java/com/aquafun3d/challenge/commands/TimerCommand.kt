package com.aquafun3d.challenge.commands

import com.aquafun3d.challenge.Main
import com.aquafun3d.challenge.utils.Settings
import com.aquafun3d.challenge.utils.TimerService
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player




class TimerCommand: CommandExecutor {

	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
		if (sender !is Player) {
			return false
		}
		if(sender.isOp) {
			if (args.size != 1 || args[0] != "toggle" && args[0] != "reset"){
				sender.sendMessage(Settings.PREFIX + ChatColor.GREEN + " Please use " + ChatColor.GOLD + "./timer toggle")
				return false
			}else {
				if (args[0] == "toggle"){
					Main.timerService?.getInstance()?.toggle()
				}else{
					Main.timerService?.getInstance()?.reset()
				}
			}
		}
		return false
	}


}