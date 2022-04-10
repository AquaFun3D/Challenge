package de.aquafun3d.challenge.commands;

import de.aquafun3d.challenge.Main;
import de.aquafun3d.challenge.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import static org.bukkit.GameRule.NATURAL_REGENERATION;

public class SettingsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player player){
			if(player.isOp()){
				if (args.length == 1 && Objects.equals(args[0], "reset")){
					for (World world : Bukkit.getWorlds()) {
						world.setGameRule(NATURAL_REGENERATION, true);
						for(Player p : Bukkit.getOnlinePlayers()){
							p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
						}
						Main.settingsInv.reset();
					}
					Utils.send(player,ChatColor.GREEN + "Setting reset");
				}else{
					Main.settingsInv.newInventory(player);
				}
			}
		}
		return false;
	}
}
