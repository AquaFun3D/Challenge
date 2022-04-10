package de.aquafun3d.challenge.utils;

import de.aquafun3d.challenge.Main;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.ArrayList;

public class SettingsInv {

	private Difficulty difficulty = Difficulty.EASY;
	private double damage = 1.0;
	private Utils.Hardcore hardcore = Utils.Hardcore.NUHC;
	private int health = 20;
	private Inventory inv = Bukkit.createInventory(null,27, Component.text(ChatColor.DARK_PURPLE + "Challenge Settings"));


	public SettingsInv(){
		if(Main.challengeConfig.contains("damage")){
			damage = Main.challengeConfig.getDouble("damage");
		}
		if(Main.challengeConfig.contains("health")){
			health = Main.challengeConfig.getInt("health");
		}
		if(Main.challengeConfig.contains("hardcore")){
			hardcore = Utils.stringToHardcore(Main.challengeConfig.getString("hardcore"));
		}
		if(Main.challengeConfig.contains("difficulty")){
			difficulty = Utils.stringToDifficulty(Main.challengeConfig.getString("difficulty"));
		}
	}

	public void newInventory(Player player){

		ItemStack empty = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta emptyMeta = empty.getItemMeta();
		emptyMeta.displayName(Component.text(""));
		empty.setItemMeta(emptyMeta);

		ItemStack health = new ItemStack(Material.HEART_OF_THE_SEA, this.health);
		ItemMeta healthMeta = health.getItemMeta();
		healthMeta.displayName(Component.text(ChatColor.YELLOW + "Health"));
		health.setItemMeta(healthMeta);

		ItemStack nuhc = new ItemStack(Material.APPLE);
		ItemMeta nuhcMeta = nuhc.getItemMeta();
		nuhcMeta.displayName(Component.text(ChatColor.GREEN + "No Ultra Hardcore"));
		nuhc.setItemMeta(nuhcMeta);

		ItemStack uhc = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta uhcMeta = uhc.getItemMeta();
		uhcMeta.displayName(Component.text(ChatColor.YELLOW + "Ultra Hardcore"));
		uhc.setItemMeta(uhcMeta);

		ItemStack uuhc = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
		ItemMeta uuhcMeta = uuhc.getItemMeta();
		uuhcMeta.displayName(Component.text(ChatColor.RED + "Ultra Ultra Hardcore"));
		uuhc.setItemMeta(uuhcMeta);

		ItemStack peace = new ItemStack(Material.NETHERITE_INGOT);
		ItemMeta peaceMeta = peace.getItemMeta();
		peaceMeta.displayName(Component.text(ChatColor.LIGHT_PURPLE + "Peaceful"));
		peace.setItemMeta(peaceMeta);

		ItemStack easy = new ItemStack(Material.COPPER_INGOT);
		ItemMeta easyMeta = easy.getItemMeta();
		easyMeta.displayName(Component.text(ChatColor.GREEN + "Easy"));
		easy.setItemMeta(easyMeta);

		ItemStack normal = new ItemStack(Material.IRON_INGOT);
		ItemMeta normalMeta = normal.getItemMeta();
		normalMeta.displayName(Component.text(ChatColor.YELLOW + "Normal"));
		normal.setItemMeta(normalMeta);

		ItemStack hard = new ItemStack(Material.GOLD_INGOT);
		ItemMeta hardMeta = hard.getItemMeta();
		hardMeta.displayName(Component.text(ChatColor.RED + "Hard"));
		hard.setItemMeta(hardMeta);

		ItemStack dmg = new ItemStack(Material.ARROW);
		ItemMeta dmgMeta = dmg.getItemMeta();
		dmgMeta.displayName(Component.text(ChatColor.YELLOW + "Damage multiplier"));
		ArrayList<Component> lore = new ArrayList<>();
		lore.add(Component.text(damage));
		dmgMeta.lore(lore);
		dmg.setItemMeta(dmgMeta);

		ItemStack challenges = new ItemStack(Material.TOTEM_OF_UNDYING);
		ItemMeta challengesMeta = challenges.getItemMeta();
		challengesMeta.displayName(Component.text(ChatColor.GOLD + "Challenges"));
		challenges.setItemMeta(challengesMeta);

		ItemStack timer = new ItemStack(Material.CLOCK);
		ItemMeta timerMeta = timer.getItemMeta();
		timerMeta.displayName(Component.text(ChatColor.YELLOW + "Timer"));
		timer.setItemMeta(timerMeta);

		for(int i = 0; i < 27; i++){
			inv.setItem(i, empty);
		}

		switch(this.difficulty){
			case EASY -> inv.setItem(4,easy);
			case NORMAL -> inv.setItem(4,normal);
			case HARD -> inv.setItem(4,hard);
			default -> inv.setItem(4,peace);
		}

		switch(this.hardcore){
			case NUHC -> inv.setItem(19,nuhc);
			case UHC -> inv.setItem(19,uhc);
			case UUHC -> inv.setItem(19,uuhc);
		}

		inv.setItem(1,health);
		inv.setItem(22,dmg);
		inv.setItem(7,challenges);
		inv.setItem(25,timer);

		player.openInventory(inv);
	}

	public void writeSettings(){
		try {
			Main.challengeConfig.set("health",this.health);
			Main.challengeConfig.set("damage",this.damage);
			Main.challengeConfig.set("hardcore",this.hardcore);
			Main.challengeConfig.set("difficulty",this.difficulty);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reset(){
		health = 20;
		difficulty = Difficulty.EASY;
		hardcore = Utils.Hardcore.NUHC;
		damage = 1.0;
	}

	public Utils.Hardcore getHardcore() {
		return hardcore;
	}

	public double getDamage() {
		return damage;
	}

	public int getHealth() {
		return health;
	}

	public Difficulty getDifficulty(){
		return difficulty;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setDamage(double damage){
		this.damage = damage;
	}

	public void setDifficulty(Difficulty difficulty){
		this.difficulty = difficulty;
	}

	public void setHardcore(Utils.Hardcore hardcore){
		this.hardcore = hardcore;
	}
}
