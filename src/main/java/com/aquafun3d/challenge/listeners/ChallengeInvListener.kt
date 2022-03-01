package com.aquafun3d.challenge.listeners

import com.aquafun3d.challenge.Main
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class ChallengeInvListener: Listener {

	//Handle clicks in challenges inventory
	@EventHandler
	fun onInvClick(e: InventoryClickEvent) {
		val player = e.whoClicked as Player
		val item: ItemStack? = e.currentItem

		if (e.view.title == ChatColor.DARK_PURPLE.toString() + "Challenges") {
			e.isCancelled = true
		}

		if (item == null || !item.hasItemMeta()) {
			return
		}

		if (item.itemMeta!!.displayName == ChatColor.RED.toString() + "Back") {
			if (e.isLeftClick) {
				Main.settingsInv?.newInventory(player)
			}
		}
	}
}