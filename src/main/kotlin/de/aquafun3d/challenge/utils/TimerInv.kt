package de.aquafun3d.challenge.utils

import de.aquafun3d.challenge.Main
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class TimerInv {

	private val inv: Inventory = Bukkit.createInventory(null,27, Component.text(ChatColor.DARK_PURPLE.toString() + "Timer Settings"))

	//Generates placeholder items for timer inventory and places them into the inventory
	fun newInventory(player: Player) {

		val empty = ItemStack(Material.BLACK_STAINED_GLASS_PANE)
		val emptyMeta = empty.itemMeta
		emptyMeta!!.displayName(Component.text(" "))
		empty.itemMeta = emptyMeta

		val fivePlus = ItemStack(Material.GREEN_TERRACOTTA)
		val fivePlusMeta = fivePlus.itemMeta
		fivePlusMeta!!.displayName(Component.text(ChatColor.GREEN.toString() + "+5 Minutes"))
		fivePlus.itemMeta = fivePlusMeta

		val fiveMinus = ItemStack(Material.RED_TERRACOTTA)
		val fiveMinusMeta = fiveMinus.itemMeta
		fiveMinusMeta!!.displayName(Component.text(ChatColor.RED.toString() + "-5 Minutes"))
		fiveMinus.itemMeta = fiveMinusMeta

		val thrityPlus = ItemStack(Material.GREEN_WOOL)
		val thrityPlusMeta = thrityPlus.itemMeta
		thrityPlusMeta!!.displayName(Component.text(ChatColor.GREEN.toString() + "+30 Minutes"))
		thrityPlus.itemMeta = thrityPlusMeta

		val thrityMinus = ItemStack(Material.RED_WOOL)
		val thrityMinusMeta = thrityMinus.itemMeta
		thrityMinusMeta!!.displayName(Component.text(ChatColor.RED.toString() + "-30 Minutes"))
		thrityMinus.itemMeta = thrityMinusMeta

		val time = ItemStack(Material.CLOCK)
		val timeMeta = time.itemMeta
		timeMeta!!.displayName(Component.text(ChatColor.YELLOW.toString() + "Time"))
		val lore = ArrayList<Component>()
		lore.add(Component.text(Main.timerService?.getSec()!!.div(60).toString() + ChatColor.GOLD + " minutes"))
		timeMeta.lore(lore)
		time.itemMeta = timeMeta

		val reverse = ItemStack(Material.SOUL_LANTERN)
		val reverseMeta = reverse.itemMeta
		reverseMeta!!.displayName(Component.text(ChatColor.AQUA.toString() + "Reverse"))
		reverse.itemMeta = reverseMeta

		val normal = ItemStack(Material.LANTERN)
		val normalMeta = normal.itemMeta
		normalMeta!!.displayName(Component.text(ChatColor.GOLD.toString() + "Normal"))
		normal.itemMeta = normalMeta

		val back = ItemStack(Material.BARRIER)
		val backMeta = back.itemMeta
		backMeta!!.displayName(Component.text(ChatColor.RED.toString() + "Back"))
		back.itemMeta = backMeta

		for (j in 0..26) {
			inv.setItem(j, empty)
		}

		if(Main.timerService!!.isReversed()){
			inv.setItem(15,reverse)
		}else{
			inv.setItem(15,normal)
		}

		inv.setItem(1,fivePlus)
		inv.setItem(3,thrityPlus)
		inv.setItem(11,time)
		inv.setItem(19,fiveMinus)
		inv.setItem(21,thrityMinus)
		inv.setItem(26,back)

		player.openInventory(inv)
	}
}