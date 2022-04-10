package de.aquafun3d.challenge.listeners;

import de.aquafun3d.challenge.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ChallengeInvListener implements Listener {

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		ItemStack item = e.getCurrentItem();

		if (e.getView().title().equals(Component.text(ChatColor.DARK_PURPLE.toString() + "Challenges"))) {
			e.setCancelled(true);
		}

		if (item == null || !item.hasItemMeta()) {
			return;
		}

		if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.RED + "Back"))) {
			if (e.isLeftClick()) {
				Main.settingsInv.newInventory(player);
			}
		}
	}
}
