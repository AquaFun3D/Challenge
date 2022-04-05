package de.aquafun3d.challenge.commands

import de.aquafun3d.challenge.Main
import de.aquafun3d.challenge.utils.Utils
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.time.LocalDateTime


class WaypointCommand: CommandExecutor{

	private var clear: Int = 0
	private var timeClear: Int = 0

	//handles waypoints
	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
		if (sender is Player) {
			val player: Player = sender
			if (args.isEmpty()) {
				Utils.send(player,ChatColor.RED.toString() + "/waypoint <name> or list/remove/clear")
			}
			when(args[0]){
				"list" -> {
					val list: ArrayList<String>? = Main.waypointConfig?.getAll("waypoints.")
					Utils.send(player,ChatColor.GREEN.toString()  + "Available Waypoints are:")
					if(list == null || list.size == 0) {
						Utils.send(player,ChatColor.RED.toString() + "NONE")
					}else {
						var out = ""
						for (i in 0 until list.size) {
							out += if ( i != list.size - 1) {
								list[i] + ", "
							}else{
								list[i]
							}
						}
						Utils.send(player,ChatColor.LIGHT_PURPLE.toString() + out)
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
						Utils.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + str + ChatColor.GOLD + " deleted")
					}else {
						Utils.send(player,ChatColor.GOLD.toString() + "You don't have Op permissions")
					}
				}
				"clear" -> {
					if (player.isOp) {
						if(LocalDateTime.now().minute - timeClear >= 1){
							clear = 0
						}
						if(clear == 0){
							Utils.send(player,ChatColor.RED.toString() + "Are you Sure? " + ChatColor.GREEN + "Run Command again.")
							clear = 1
							timeClear = LocalDateTime.now().minute
						}else if(clear == 1) {
							Main.waypointConfig?.set("waypoints", null)
							Utils.send(player,ChatColor.DARK_RED.toString() + "All waypoints deleted!")
							clear = 0
						}
					} else {
						Utils.send(player,ChatColor.GOLD.toString() + "You are not Admin!")
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
							"world" -> Utils.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + str + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in overworld")
							"world_nether" -> Utils.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + str + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in nether")
							"world_the_end" -> 	Utils.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + str + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in the end")
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
							"world" -> Utils.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + str + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in overworld by " + ChatColor.AQUA + player.name)
							"world_nether" -> Utils.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + str + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in nether by " + ChatColor.AQUA + player.name)
							"world_the_end" -> Utils.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + str + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in the end by " + ChatColor.AQUA + player.name)
						}
					}
				}
			}

		}
		return false
	}
}