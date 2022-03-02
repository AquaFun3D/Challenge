package de.aquafun3d.challenge.utils

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class ChallengeInv {

	private val inv: Inventory = Bukkit.createInventory(null,27, "" + ChatColor.DARK_PURPLE + "Challenges")

	fun newInventory(player: Player) {

		val empty = ItemStack(Material.BLACK_STAINED_GLASS_PANE)
		val emptyMeta = empty.itemMeta
		emptyMeta!!.setDisplayName(" ")
		empty.itemMeta = emptyMeta

		val back = ItemStack(Material.BARRIER)
		val backMeta = back.itemMeta
		backMeta!!.setDisplayName(ChatColor.RED.toString() + "Back")
		back.itemMeta = backMeta

		for (j in 0..26) {
			inv.setItem(j, empty)
		}

		inv.setItem(26,back)

		player.openInventory(inv)
	}
}