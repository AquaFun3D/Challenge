package de.aquafun3d.challenge.utils;

import de.aquafun3d.challenge.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TimerInv {

	private Inventory inv = Bukkit.createInventory(null,27, Component.text(ChatColor.DARK_PURPLE + "Timer Settings"));

	public void newInventory(Player player){

		ItemStack empty = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta emptyMeta = empty.getItemMeta();
		emptyMeta.displayName(Component.text(""));
		empty.setItemMeta(emptyMeta);

		ItemStack fivePlus = new ItemStack(Material.GREEN_TERRACOTTA);
		ItemMeta fivePlusMeta = fivePlus.getItemMeta();
		fivePlusMeta.displayName(Component.text(ChatColor.GREEN + "+5 Minutes"));
		fivePlus.setItemMeta(fivePlusMeta);

		ItemStack fiveMinus = new ItemStack(Material.RED_TERRACOTTA);
		ItemMeta fiveMinusMeta = fiveMinus.getItemMeta();
		fiveMinusMeta.displayName(Component.text(ChatColor.RED + "-5 Minutes"));
		fiveMinus.setItemMeta(fiveMinusMeta);

		ItemStack thirtyPlus = new ItemStack(Material.GREEN_WOOL);
		ItemMeta thirtyPlusMeta = thirtyPlus.getItemMeta();
		thirtyPlusMeta.displayName(Component.text(ChatColor.GREEN + "+30 Minutes"));
		thirtyPlus.setItemMeta(thirtyPlusMeta);

		ItemStack thirtyMinus = new ItemStack(Material.RED_WOOL);
		ItemMeta thirtyMinusMeta = thirtyMinus.getItemMeta();
		thirtyMinusMeta.displayName(Component.text(ChatColor.RED + "-30 Minutes"));
		thirtyMinus.setItemMeta(thirtyMinusMeta);

		ItemStack time = new ItemStack(Material.CLOCK);
		ItemMeta timeMeta = time.getItemMeta();
		timeMeta.displayName(Component.text(ChatColor.YELLOW + "Time"));
		ArrayList<Component> lore = new ArrayList<>();
		lore.add(Component.text(Main.timerService.getSec() / 60 + " Minutes"));
		timeMeta.lore(lore);
		time.setItemMeta(timeMeta);

		ItemStack reverse = new ItemStack(Material.SOUL_LANTERN);
		ItemMeta reverseMeta = reverse.getItemMeta();
		reverseMeta.displayName(Component.text(ChatColor.AQUA + "Reversed"));
		reverse.setItemMeta(reverseMeta);

		ItemStack clockwise = new ItemStack(Material.LANTERN);
		ItemMeta clockwiseMeta = clockwise.getItemMeta();
		clockwiseMeta.displayName(Component.text(ChatColor.GOLD + "Clockwise"));
		clockwise.setItemMeta(clockwiseMeta);

		ItemStack back = new ItemStack(Material.BARRIER);
		ItemMeta backMeta = back.getItemMeta();
		backMeta.displayName(Component.text(ChatColor.RED + "Back"));
		back.setItemMeta(backMeta);

		for(int i = 0; i < 27; i++){
			inv.setItem(i, empty);
		}

		if(Main.timerService.isReversed()){
			inv.setItem(15, reverse);
		}else{
			inv.setItem(15,clockwise);
		}

		inv.setItem(1,fivePlus);
		inv.setItem(3,thirtyPlus);
		inv.setItem(11,time);
		inv.setItem(19,fiveMinus);
		inv.setItem(21,thirtyMinus);
		inv.setItem(26,back);

		player.openInventory(inv);
	}
}
