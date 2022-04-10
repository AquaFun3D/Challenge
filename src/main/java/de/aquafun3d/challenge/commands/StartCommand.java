package de.aquafun3d.challenge.commands;

import de.aquafun3d.challenge.Main;
import de.aquafun3d.challenge.utils.Utils;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Difficulty.HARD;
import static org.bukkit.Difficulty.PEACEFUL;
import static org.bukkit.GameRule.ANNOUNCE_ADVANCEMENTS;
import static org.bukkit.GameRule.NATURAL_REGENERATION;

public class StartCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player player){
			if(player.isOp()){
				if(!Main.timerService.getInstance().isPaused() && Main.timerService.getInstance().getSec() != 0 ){
					return false;
				}
				for (World world : Bukkit.getWorlds()){
					world.setGameRule(ANNOUNCE_ADVANCEMENTS,false);
				}
				for (Player p : Bukkit.getOnlinePlayers()){
					p.setGameMode(GameMode.SURVIVAL);
				}
				Utils.atAll(ChatColor.LIGHT_PURPLE + "Challenge started");
				if(Main.settingsInv.getHardcore() == Utils.Hardcore.UHC){
					for (World world : Bukkit.getWorlds()) {
						world.setGameRule(NATURAL_REGENERATION, false);
					}
				}
				switch(Main.settingsInv.getDifficulty()) {
					case EASY -> { for (World world : Bukkit.getWorlds()){ world.setDifficulty(Difficulty.EASY); }}
					case NORMAL -> { for (World world : Bukkit.getWorlds()){ world.setDifficulty(Difficulty.NORMAL); }}
					case HARD -> { for (World world : Bukkit.getWorlds()){ world.setDifficulty(HARD); }}
					case PEACEFUL -> { for (World world : Bukkit.getWorlds()) { world.setDifficulty(PEACEFUL); }}
				}
				for (Player p : Bukkit.getOnlinePlayers()){
					p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Main.settingsInv.getHealth());
				}
				if (Main.timerService.getSec() <= 100) {
					for (Player p : Bukkit.getOnlinePlayers()){
						p.setHealth(Main.settingsInv.getHealth());
					}
				}
				Main.timerService.getInstance().toggle();
				Main.settingsInv.writeSettings();
				return false;
			}
		}
		return false;
	}
}
