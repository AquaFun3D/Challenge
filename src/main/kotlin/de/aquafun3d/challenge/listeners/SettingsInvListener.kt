package de.aquafun3d.challenge.listeners

import de.aquafun3d.challenge.Main
import de.aquafun3d.challenge.utils.Utils
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Difficulty
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack


class SettingsInvListener: Listener {

	//Handle clicks in settings inventory
	@EventHandler
	fun onInvClick(e: InventoryClickEvent){
		val player = e.whoClicked as Player
		val item: ItemStack? = e.currentItem

		if (e.view.title() == Component.text(ChatColor.DARK_PURPLE.toString() + "Challenge Settings")) {
			println("test")
			e.isCancelled = true
		}

		if(item == null || !item.hasItemMeta()){
			println("test2")
			return
		}

		if (item.itemMeta.displayName() == Component.text(ChatColor.YELLOW.toString() + "Health")) {
			println("test1")
			if (e.isShiftClick) {
				Main.settingsInv?.setHealth(20)
			} else if (e.isLeftClick) {
				Main.settingsInv?.setHealth(Main.settingsInv?.getHealth()!! + 1)
			} else if (e.isRightClick) {
				Main.settingsInv?.setHealth(Main.settingsInv?.getHealth()!! - 1)
			}
			Main.settingsInv?.newInventory(player)
		}

		if (item.itemMeta!!.displayName() == Component.text(ChatColor.YELLOW.toString() + "Damage multiplier")) {
			if (e.isShiftClick) {
				Main.settingsInv?.setDmg(1.0)
			} else if (e.isLeftClick) {
				Main.settingsInv?.setDmg(Main.settingsInv?.getDmg()!! + 1)
			} else if (e.isRightClick) {
				Main.settingsInv?.setDmg(Main.settingsInv?.getDmg()!! / 2)
			}
			Main.settingsInv?.newInventory(player)
		}

		if(item.itemMeta!!.displayName() == Component.text(ChatColor.YELLOW.toString() + "Challenges")) {
			Main.challengeInv?.newInventory(player)
		}

		if(item.itemMeta!!.displayName() == Component.text(ChatColor.YELLOW.toString() + "Timer")){
			Main.timerInv?.newInventory(player)
		}

		if (item.itemMeta!!.displayName() == Component.text(ChatColor.GREEN.toString() + "Easy")) {
			if (e.isLeftClick) {
				Main.settingsInv?.setDifficulty(Difficulty.NORMAL)
			} else if (e.isRightClick) {
				Main.settingsInv?.setDifficulty(Difficulty.HARD)
			}
			Main.settingsInv?.newInventory(player)
		}

		if (item.itemMeta!!.displayName() == Component.text(ChatColor.YELLOW.toString() + "Normal")) {
			if (e.isLeftClick) {
				Main.settingsInv?.setDifficulty(Difficulty.HARD)
			} else if (e.isRightClick) {
				Main.settingsInv?.setDifficulty(Difficulty.EASY)
			}
			Main.settingsInv?.newInventory(player)
		}

		if (item.itemMeta!!.displayName() == Component.text(ChatColor.RED.toString() + "Hard")) {
			if (e.isLeftClick) {
				Main.settingsInv?.setDifficulty(Difficulty.EASY)
			} else if (e.isRightClick) {
				Main.settingsInv?.setDifficulty(Difficulty.NORMAL)
			}
			Main.settingsInv?.newInventory(player)
		}

		if (item.itemMeta!!.displayName() == Component.text(ChatColor.GREEN.toString() + "No Ultra Hardcore")) {
			if (e.isLeftClick) {
				Main.settingsInv?.setHardcore(Utils.Hardcore.UHC)
			} else if (e.isRightClick) {
				Main.settingsInv?.setHardcore(Utils.Hardcore.UUHC)
			}
			Main.settingsInv?.newInventory(player)
		}

		if (item.itemMeta!!.displayName() == Component.text(ChatColor.YELLOW.toString() + "Ultra Hardcore")) {
			if (e.isLeftClick) {
				Main.settingsInv?.setHardcore(Utils.Hardcore.UUHC)
			} else if (e.isRightClick) {
				Main.settingsInv?.setHardcore(Utils.Hardcore.NUHC)
			}
			Main.settingsInv?.newInventory(player)
		}

		if (item.itemMeta!!.displayName() == Component.text(ChatColor.RED.toString() + "Ultra Ultra Hardcore")) {
			if (e.isLeftClick) {
				Main.settingsInv?.setHardcore(Utils.Hardcore.NUHC)
			} else if (e.isRightClick) {
				Main.settingsInv?.setHardcore(Utils.Hardcore.UHC)
			}
			Main.settingsInv?.newInventory(player)
		}
	}
}