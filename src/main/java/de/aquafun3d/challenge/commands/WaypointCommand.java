package de.aquafun3d.challenge.commands;

import de.aquafun3d.challenge.Main;
import de.aquafun3d.challenge.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class WaypointCommand implements CommandExecutor {

	private int clear = 0;
	private int timeClear = 0;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player player){
			if(args.length == 0){
				Utils.send(player, ChatColor.RED + "/waypoint <name> or list/remove/clear");
				return false;
			}
			switch(args[0]){
				case "list" -> {
					ArrayList<String> list = Main.challengeConfig.getAll(".waypoints");
					Utils.send(player,ChatColor.GREEN + "Available Waypoints are:");
					if(list == null || list.size() == 0){
						Utils.send(player,ChatColor.RED + "NONE");
					}else{
						StringBuilder out = new StringBuilder();
						for(int i = 0; i < list.size();i++){
							if ( i != list.size() - 1) {
								out.append(list.get(i)).append(", ");
							}else{
								out.append(list.get(i));
							}
						}
						Utils.send(player,ChatColor.LIGHT_PURPLE.toString() + out);
					}

				}
				case "remove" -> {
					if(player.isOp()){
						String s = null;
						if(args.length == 3){
							s = args[1] + "_" + args[2];
						}else if(args.length == 2){
							s = args[1];
						}else{
							Utils.send(player, ChatColor.YELLOW + "Use /wp remove <name>");
						}
						try {
							Main.challengeConfig.set("waypoints." + s, null);
							} catch (IOException e) {
							e.printStackTrace();
						}
						Utils.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + s + ChatColor.GOLD + " deleted");
					}else {
						Utils.send(player,ChatColor.GOLD + "You don't have Op permissions");
					}
				}
				case "clear" -> {
					if (player.isOp()) {
						if(LocalDateTime.now().getMinute() - timeClear >= 1){
							clear = 0;
						}
						if(clear == 0){
							Utils.send(player,ChatColor.RED + "Are you Sure? " + ChatColor.GREEN + "Run Command again.");
							clear = 1;
							timeClear = LocalDateTime.now().getMinute();
						}else if(clear == 1) {
							try {
								Main.challengeConfig.set("waypoints", null);
							} catch (IOException e) {
								e.printStackTrace();
							}
							Utils.send(player,ChatColor.DARK_RED + "All waypoints deleted!");
							clear = 0;
						}
					} else {
						Utils.send(player,ChatColor.GOLD + "You are not Admin!");
					}
				}
				default -> {
					String s;
					if (args.length >= 2) {
						s = args[0] + "_" + args[1];
					} else {
						s = args[0];
					}
					if(Main.challengeConfig.contains("waypoints." + s)){
						String w = Main.challengeConfig.getString("waypoints." + s + ".world");
						int x = Main.challengeConfig.getInt("waypoints." + s + ".x");
						int y = Main.challengeConfig.getInt("waypoints." + s + ".y");
						int z = Main.challengeConfig.getInt("waypoints." + s + ".z");
						switch(w){
							case "world" -> Utils.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + s + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in Overworld");
							case "world_nether" -> Utils.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + s + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in End");
							case "world_the_end" -> Utils.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + s + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in Nether");
						}
					}else{
						int x = player.getLocation().getBlockX();
						int y = player.getLocation().getBlockY();
						int z = player.getLocation().getBlockZ();
						try {
							Main.challengeConfig.set("waypoints."+ s + ".world", player.getWorld().getName());
							Main.challengeConfig.set("waypoints."+ s + ".x", x);
							Main.challengeConfig.set("waypoints."+ s + ".y", y);
							Main.challengeConfig.set("waypoints."+ s + ".z", z);
						} catch (IOException e) {
							e.printStackTrace();
						}
						switch(player.getWorld().getName()){
							case "world" -> Utils.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + s + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in Overworld by " + ChatColor.AQUA + player.getName());
							case "world_nether" -> Utils.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + s + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in Nether by " + ChatColor.AQUA + player.getName());
							case "world_the_end" -> Utils.atAll("" + ChatColor.GOLD + "Waypoint " + ChatColor.LIGHT_PURPLE + s + ChatColor.GREEN + " " + x + ", " + y + ", " + z + ChatColor.GOLD + " in End by " + ChatColor.AQUA + player.getName());
						}
					}
				}
			}
		}
		return false;
	}
}
