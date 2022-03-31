package de.aquafun3d.challenge.listeners

import de.aquafun3d.challenge.Main
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class TimerInvListener: Listener {

	//Handle clicks in timer inventory
	@EventHandler
	fun onInvClick(e: InventoryClickEvent) {
		val player = e.whoClicked as Player
		val item: ItemStack? = e.currentItem

		if (e.view.title().toString() == ChatColor.DARK_PURPLE.toString() + "Timer Settings") {
			e.isCancelled = true
		}

		if (item == null || !item.hasItemMeta()) {
			return
		}

		if (item.itemMeta!!.displayName().toString() == ChatColor.GREEN.toString() + "+5 Minutes") {
			if (e.isLeftClick) {
				Main.timerService?.setSec(Main.timerService?.getSec()!!.plus(300))
				Main.timerInv?.newInventory(player)
			}
		}

		if (item.itemMeta!!.displayName().toString() == ChatColor.RED.toString() + "-5 Minutes") {
			if (e.isLeftClick) {
				if(Main.timerService?.getSec()!! < 300){
					Main.timerService?.setSec(0)
				}else {
					Main.timerService?.setSec(Main.timerService?.getSec()!!.minus(300))
				}
				Main.timerInv?.newInventory(player)
			}
		}

		if (item.itemMeta!!.displayName().toString() == ChatColor.GREEN.toString() + "+30 Minutes") {
			if (e.isLeftClick) {
				Main.timerService?.setSec(Main.timerService?.getSec()!!.plus(1800))
				Main.timerInv?.newInventory(player)
			}
		}

		if (item.itemMeta!!.displayName().toString() == ChatColor.RED.toString() + "-30 Minutes") {
			if (e.isLeftClick) {
				if(Main.timerService?.getSec()!! < 1800){
					Main.timerService?.setSec(0)
				}else {
					Main.timerService?.setSec(Main.timerService?.getSec()!!.minus(1800))
				}
				Main.timerInv?.newInventory(player)
			}
		}

		if (item.itemMeta!!.displayName().toString() == ChatColor.AQUA.toString() + "Reverse") {
			if (e.isLeftClick) {
				Main.timerService?.setReversed(false)
				Main.timerInv?.newInventory(player)
			}
		}

		if (item.itemMeta!!.displayName().toString() == ChatColor.GOLD.toString() + "Normal") {
			if (e.isLeftClick) {
				Main.timerService?.setReversed(true)
				Main.timerInv?.newInventory(player)
			}
		}

		if (item.itemMeta!!.displayName().toString() == ChatColor.RED.toString() + "Back") {
			if (e.isLeftClick) {
				Main.settingsInv?.newInventory(player)
			}
		}
	}
}