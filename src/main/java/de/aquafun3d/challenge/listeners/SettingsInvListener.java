package de.aquafun3d.challenge.listeners;

import de.aquafun3d.challenge.Main;
import de.aquafun3d.challenge.utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class SettingsInvListener implements Listener {

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		ItemStack item = e.getCurrentItem();

		if (e.getView().title().equals(Component.text(ChatColor.DARK_PURPLE + "Challenge Settings"))) {
			e.setCancelled(true);
		}

		if (item == null || !item.hasItemMeta()) {
			return;
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.YELLOW + "Health"))) {
			if (e.isShiftClick()) {
				Main.settingsInv.setHealth(20);
			} else if (e.isLeftClick()) {
				Main.settingsInv.setHealth(Main.settingsInv.getHealth() +1);
			} else if (e.isRightClick()) {
				Main.settingsInv.setHealth(Main.settingsInv.getHealth() -1);
			}
			Main.settingsInv.newInventory(player);
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.YELLOW + "Damage multiplier"))){
			if (e.isShiftClick()) {
				Main.settingsInv.setDamage(1.0);
			} else if (e.isLeftClick()) {
				Main.settingsInv.setDamage(Main.settingsInv.getDamage() +1);
			} else if (e.isRightClick()) {
				Main.settingsInv.setDamage(Main.settingsInv.getDamage() / 2);
			}
			Main.settingsInv.newInventory(player);
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.GOLD + "Challenges"))){
			Main.challengeInv.newInventory(player);
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.YELLOW + "Timer"))){
			Main.timerInv.newInventory(player);
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.LIGHT_PURPLE + "Peaceful"))){
			if (e.isLeftClick()) {
				Main.settingsInv.setDifficulty(Difficulty.EASY);
			} else if (e.isRightClick()) {
				Main.settingsInv.setDifficulty(Difficulty.HARD);
			}
			Main.settingsInv.newInventory(player);
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.GREEN + "Easy"))){
			if (e.isLeftClick()) {
				Main.settingsInv.setDifficulty(Difficulty.NORMAL);
			} else if (e.isRightClick()) {
				Main.settingsInv.setDifficulty(Difficulty.PEACEFUL);
			}
			Main.settingsInv.newInventory(player);
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.YELLOW + "Normal"))){
			if (e.isLeftClick()) {
				Main.settingsInv.setDifficulty(Difficulty.HARD);
			} else if (e.isRightClick()) {
				Main.settingsInv.setDifficulty(Difficulty.EASY);
			}
			Main.settingsInv.newInventory(player);
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.RED + "Hard"))){
			if (e.isLeftClick()) {
				Main.settingsInv.setDifficulty(Difficulty.PEACEFUL);
			} else if (e.isRightClick()) {
				Main.settingsInv.setDifficulty(Difficulty.NORMAL);
			}
			Main.settingsInv.newInventory(player);
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.GREEN + "No Ultra Hardcore"))){
			if (e.isLeftClick()) {
				Main.settingsInv.setHardcore(Utils.Hardcore.UHC);
			} else if (e.isRightClick()) {
				Main.settingsInv.setHardcore(Utils.Hardcore.UUHC);
			}
			Main.settingsInv.newInventory(player);
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.YELLOW + "Ultra Hardcore"))){
			if (e.isLeftClick()) {
				Main.settingsInv.setHardcore(Utils.Hardcore.UUHC);
			} else if (e.isRightClick()) {
				Main.settingsInv.setHardcore(Utils.Hardcore.NUHC);
			}
			Main.settingsInv.newInventory(player);
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.RED + "Ultra Ultra Hardcore"))){
			if (e.isLeftClick()) {
				Main.settingsInv.setHardcore(Utils.Hardcore.NUHC);
			} else if (e.isRightClick()) {
				Main.settingsInv.setHardcore(Utils.Hardcore.UHC);
			}
			Main.settingsInv.newInventory(player);
		}
	}
}
