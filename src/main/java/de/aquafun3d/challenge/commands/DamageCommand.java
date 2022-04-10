package de.aquafun3d.challenge.commands;

import de.aquafun3d.challenge.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DamageCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player player){
			if(player.isOp()){
				if (Utils.dmgListenerToggle) {
					Utils.dmgListenerToggle = false;
					Utils.send(player, ChatColor.GOLD + "Damage visibility" + ChatColor.GREEN + " OFF");
				} else {
					Utils.dmgListenerToggle = true;
					Utils.send(player,ChatColor.GOLD + "Damage visibility" + ChatColor.GREEN + " ON");
				}
			}
		}
		return false;
	}
}
