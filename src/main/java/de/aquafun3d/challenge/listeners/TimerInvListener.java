package de.aquafun3d.challenge.listeners;

import de.aquafun3d.challenge.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Objects;

public class TimerInvListener implements Listener {

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		ItemStack item = e.getCurrentItem();

		if (e.getView().title().equals(Component.text(ChatColor.DARK_PURPLE + "Timer Settings"))) {
			e.setCancelled(true);
		}

		if (item == null || !item.hasItemMeta()) {
			return;
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.GREEN + "+5 Minutes"))) {
			if (e.isLeftClick()) {
				Main.timerService.setSec(Main.timerService.getSec() + 300);
				Main.timerInv.newInventory(player);
			}
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.RED + "-5 Minutes"))) {
			if (e.isLeftClick()) {
				if(Main.timerService.getSec() < 300){
					Main.timerService.setSec(0);
				}else {
					Main.timerService.setSec(Main.timerService.getSec() - 300);
				}
				Main.timerInv.newInventory(player);
			}
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.GREEN + "+30 Minutes"))) {
			if (e.isLeftClick()) {
				Main.timerService.setSec(Main.timerService.getSec() + 1800);
				Main.timerInv.newInventory(player);
			}
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.RED + "-30 Minutes"))) {
			if (e.isLeftClick()) {
				if(Main.timerService.getSec() < 1800){
					Main.timerService.setSec(0);
				}else {
					Main.timerService.setSec(Main.timerService.getSec() - 1800);
				}
				Main.timerInv.newInventory(player);
			}
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.AQUA + "Reversed"))) {
			if (e.isLeftClick()) {
				Main.timerService.setReversed(false);
				Main.timerInv.newInventory(player);
			}
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.YELLOW + "Time"))) {
			if (e.isShiftClick()) {
				try {
					Main.challengeConfig.set("time",0);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				Main.timerService.setSec(0);
				Main.timerInv.newInventory(player);
			}
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.GOLD + "Clockwise"))) {
			if (e.isLeftClick()) {
				Main.timerService.setReversed(true);
				Main.timerInv.newInventory(player);
			}
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.RED + "Back"))) {
			if (e.isLeftClick()) {
				Main.settingsInv.newInventory(player);
			}
		}
	}
}
