package de.aquafun3d.challenge.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChallengeInv {

	private Inventory inv = Bukkit.createInventory(null,27, Component.text(ChatColor.DARK_PURPLE + "Challenges"));

	public void newInventory(Player player){

		ItemStack empty = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta emptyMeta = empty.getItemMeta();
		emptyMeta.displayName(Component.text(""));
		empty.setItemMeta(emptyMeta);

		ItemStack back = new ItemStack(Material.BARRIER);
		ItemMeta backMeta = back.getItemMeta();
		backMeta.displayName(Component.text(ChatColor.RED + "Back"));
		back.setItemMeta(backMeta);

		for(int i = 0; i < 27; i++){
			inv.setItem(i, empty);
		}

		inv.setItem(26,back);

		player.openInventory(inv);

	}
}
