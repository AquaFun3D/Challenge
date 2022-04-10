package de.aquafun3d.challenge.commands;

import de.aquafun3d.challenge.Main;
import de.aquafun3d.challenge.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class TimerCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player player){
			if(player.isOp()){
				if (args.length != 1 || !Objects.equals(args[0], "toggle") && !Objects.equals(args[0], "reset")){
					Utils.send(player, ChatColor.GREEN + "Please use " + ChatColor.GOLD + "./timer toggle");
					return false;
				}else {
					if (args[0].equals("toggle")){
						Main.timerService.getInstance().toggle();
					}else{
						Main.timerService.getInstance().reset();
						Utils.send(player,ChatColor.RED + "Timer reset");
					}
				}
			}
		}
		return false;
	}
}
