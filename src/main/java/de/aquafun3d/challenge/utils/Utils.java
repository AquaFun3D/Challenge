package de.aquafun3d.challenge.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;

public class Utils {
	public static String PREFIX = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + "Challenge" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_PURPLE;

	public static void send(Player player, String message){
		player.sendMessage(PREFIX + message);
	}

	public static void atAll(String message){
		Bukkit.broadcast(Component.text(PREFIX + message));
	}

	public static Boolean dmgListenerToggle = true;

	public static String dmgMessage(String player, String amount, String source){
		return PREFIX + ChatColor.AQUA + player + ChatColor.GREEN + " got " + ChatColor.GOLD + amount + ChatColor.GREEN + " hearts damage by " + ChatColor.BLUE + source;
	}

	public enum Hardcore {
		NUHC, UHC, UUHC
	}

	public static Hardcore stringToHardcore(String s){
		switch (s){
			case "NUHC" -> {return Hardcore.NUHC;}
			case "UHC" -> {return Hardcore.UHC;}
			case "UUHC" -> {return Hardcore.UUHC;}
			default -> {return Hardcore.NUHC;}
		}
	}

	public static Difficulty stringToDifficulty(String s){
		switch (s){
			case "EASY" -> {return Difficulty.EASY;}
			case "NORMAL" -> {return Difficulty.NORMAL;}
			case "HARD" -> {return Difficulty.HARD;}
			default -> {return Difficulty.PEACEFUL;}
		}
	}

	public static HashMap<EntityDamageEvent.DamageCause, String> damageCauseMap = new HashMap<>();

	public static void fillMap(){
		damageCauseMap.put(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION, "Explosion");
		damageCauseMap.put(EntityDamageEvent.DamageCause.CONTACT, "Contact");
		damageCauseMap.put(EntityDamageEvent.DamageCause.CRAMMING, "To many mobs");
		damageCauseMap.put(EntityDamageEvent.DamageCause.CUSTOM, "Custom");
		damageCauseMap.put(EntityDamageEvent.DamageCause.DRAGON_BREATH, "Dragon breath");
		damageCauseMap.put(EntityDamageEvent.DamageCause.DROWNING, "Drowning");
		damageCauseMap.put(EntityDamageEvent.DamageCause.FALL, "Fall");
		damageCauseMap.put(EntityDamageEvent.DamageCause.FALLING_BLOCK, "Falling block");
		damageCauseMap.put(EntityDamageEvent.DamageCause.FIRE, "Fire");
		damageCauseMap.put(EntityDamageEvent.DamageCause.FIRE_TICK, "Fire tick");
		damageCauseMap.put(EntityDamageEvent.DamageCause.FLY_INTO_WALL, "Flying into a wall");
		damageCauseMap.put(EntityDamageEvent.DamageCause.FREEZE, "Freeze");
		damageCauseMap.put(EntityDamageEvent.DamageCause.HOT_FLOOR, "Magma block");
		damageCauseMap.put(EntityDamageEvent.DamageCause.LAVA, "Lava");
		damageCauseMap.put(EntityDamageEvent.DamageCause.LIGHTNING, "Lightning");
		damageCauseMap.put(EntityDamageEvent.DamageCause.MAGIC, "Magic");
		damageCauseMap.put(EntityDamageEvent.DamageCause.POISON, "Poison");
		damageCauseMap.put(EntityDamageEvent.DamageCause.STARVATION, "Starving");
		damageCauseMap.put(EntityDamageEvent.DamageCause.SUFFOCATION, "Suffocation");
		damageCauseMap.put(EntityDamageEvent.DamageCause.SUICIDE, "Suicide");
		damageCauseMap.put(EntityDamageEvent.DamageCause.THORNS, "Thorns");
		damageCauseMap.put(EntityDamageEvent.DamageCause.VOID, "Void");
		damageCauseMap.put(EntityDamageEvent.DamageCause.WITHER, "Wither");
	}
}
