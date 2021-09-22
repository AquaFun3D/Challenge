package com.aquafun3d.challenge.commands

import com.aquafun3d.challenge.Main
import com.aquafun3d.challenge.utils.Settings
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


class WaypointCommand: CommandExecutor{
	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
		if (sender is Player) {
			val player: Player = sender
			if (args.isEmpty()) {
				player.sendMessage(Settings.PREFIX + ChatColor.RED + "/waypoint <name> or list/remove/clear")
			}
			when(args[0]){
				"list" -> {
					val list: ArrayList<String>? = Main.waypointConfig?.getAll("waypoints.")
					player.sendMessage(Settings.PREFIX + ChatColor.GREEN  + "Available Waypoints are:")
					if(list == null || list.size == 0) {
						player.sendMessage(Settings.PREFIX + ChatColor.RED + "NONE")
					}else {
						var out = ""
						for (i in 0 until list.size) {
							out += if ( i != list.size - 1) {
								list[i] + ", "
							}else{
								list[i]
							}
						}
						player.sendMessage(Settings.PREFIX + ChatColor.LIGHT_PURPLE + out)
					}
				}
				"remove" -> {
					if (player.isOp){
						val str: String = if (args.size == 3){
							args[1] + "_" + args[2]
						}else{
							args[1]
						}
						Main.waypointConfig?.set("waypoints.$str",null)
						Settings.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + str + ChatColor.GOLD + " deleted")
					}else {
						player.sendMessage(Settings.PREFIX + ChatColor.GOLD + "You don't have Op permissions")
					}
				}
				"clear" -> {
					if (player.name == "AquaFun3D") {
						Main.waypointConfig?.set("waypoints", null)
						player.sendMessage(Settings.PREFIX + ChatColor.DARK_RED + "All waypoints deleted!")
					} else {
						player.sendMessage(Settings.PREFIX + ChatColor.GOLD + "You are not AquaFun3D!")
					}
				}
				else -> {
					val str = if (args.size >= 2) {
						args[0] + "_" + args[1]
					} else {
						args[0]
					}
					if(Main.waypointConfig?.contains("waypoints.$str") == true){
						val wp: String = Main.waypointConfig?.getString("waypoints.$str.world")!!
						val x: Int = Main.waypointConfig?.getDouble("waypoints.$str.x")!!.toInt()
						val y: Int = Main.waypointConfig?.getDouble("waypoints.$str.y")!!.toInt()
						val z: Int = Main.waypointConfig?.getDouble("waypoints.$str.z")!!.toInt()
						when (wp){
							"world" -> Settings.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + str + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in overworld")
							"world_nether" -> Settings.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + str + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in nether")
							"world_the_end" -> 	Settings.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + str + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in the end")
						}
					}else {
						Main.waypointConfig?.set("waypoints.$str.world", player.world.name)
						Main.waypointConfig?.set("waypoints.$str.x", player.location.x)
						Main.waypointConfig?.set("waypoints.$str.y", player.location.y)
						Main.waypointConfig?.set("waypoints.$str.z", player.location.z)
						val x = player.location.x.toInt()
						val y = player.location.y.toInt()
						val z = player.location.z.toInt()
						when (player.world.name) {
							"world" -> Settings.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + str + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in overworld by " + ChatColor.AQUA + player.name)
							"world_nether" -> Settings.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + str + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in nether by " + ChatColor.AQUA + player.name)
							"world_the_end" -> Settings.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + str + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in the end by " + ChatColor.AQUA + player.name)
						}
					}
				}
			}

		}
		return false
	}

	// TODO Clear confirm


}