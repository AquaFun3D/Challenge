package com.aquafun3d.challenge.utils

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Difficulty
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack


class SettingsInv{

	private var difficulty: Difficulty = Difficulty.EASY
	private var damage: Double = 1.0
	private var hardcore: Settings.Hardcore = Settings.Hardcore.NUHC
	private var health: Int = 20
	private val inv: Inventory = Bukkit.createInventory(null,27, "" + ChatColor.DARK_PURPLE + "Challenge Settings")

	fun newInventory(player: Player){
		val empty: ItemStack = ItemStack(Material.BLACK_STAINED_GLASS_PANE)
		val emptyMeta = empty.itemMeta
		emptyMeta!!.setDisplayName(" ")
		empty.itemMeta = emptyMeta

		val health: ItemStack = ItemStack(Material.HEART_OF_THE_SEA, this.health)
		val healthMeta = health.itemMeta
		healthMeta!!.setDisplayName(ChatColor.YELLOW.toString() + "Health")
		health.itemMeta = healthMeta

		val NUHC = ItemStack(Material.APPLE)
		val UHNMeta = NUHC.itemMeta
		UHNMeta!!.setDisplayName(ChatColor.GREEN.toString() + "No Ultra Hardcore")
		NUHC.itemMeta = UHNMeta

		val UHC = ItemStack(Material.GOLDEN_APPLE)
		val UHCMeta = UHC.itemMeta
		UHCMeta!!.setDisplayName(ChatColor.YELLOW.toString() + "Ultra Hardcore")
		UHC.itemMeta = UHCMeta

		val UUHC = ItemStack(Material.ENCHANTED_GOLDEN_APPLE)
		val UUHMeta = UUHC.itemMeta
		UUHMeta!!.setDisplayName(ChatColor.RED.toString() + "Ultra Ultra Hardcore")
		UUHC.itemMeta = UUHMeta

		val peace = ItemStack(Material.NETHERITE_INGOT)
		val peaceMeta = peace.itemMeta
		peaceMeta!!.setDisplayName(ChatColor.LIGHT_PURPLE.toString() + "Peaceful")
		peace.itemMeta = peaceMeta

		val easy = ItemStack(Material.COPPER_INGOT)
		val easyMeta = easy.itemMeta
		easyMeta!!.setDisplayName(ChatColor.GREEN.toString() + "Easy")
		easy.itemMeta = easyMeta

		val normal = ItemStack(Material.IRON_INGOT)
		val normalMeta = normal.itemMeta
		normalMeta!!.setDisplayName(ChatColor.YELLOW.toString() + "Normal")
		normal.itemMeta = normalMeta

		val hard = ItemStack(Material.GOLD_INGOT)
		val hardMeta = hard.itemMeta
		hardMeta!!.setDisplayName(ChatColor.RED.toString() + "Hard")
		hard.itemMeta = hardMeta

		val dmg = ItemStack(Material.ARROW)
		val dmgMeta = dmg.itemMeta
		dmgMeta!!.setDisplayName(ChatColor.YELLOW.toString() + "Damage multiplier")
		val lore = ArrayList<String>()
		lore.add(damage.toString())
		dmgMeta.lore = lore
		dmg.itemMeta = dmgMeta

		val challenges = ItemStack(Material.TOTEM_OF_UNDYING)
		val challengesMeta = challenges.itemMeta
		challengesMeta!!.setDisplayName(ChatColor.YELLOW.toString() + "Challenges")
		challenges.itemMeta = challengesMeta

		val timer = ItemStack(Material.CLOCK)
		val timerMeta = timer.itemMeta
		timerMeta!!.setDisplayName(ChatColor.YELLOW.toString() + "Timer")
		timer.itemMeta = timerMeta

		for (j in 0..26) {
			inv.setItem(j, empty)
		}

		when (this.difficulty) {
			Difficulty.EASY -> inv.setItem(4, easy)
			Difficulty.NORMAL -> inv.setItem(4, normal)
			Difficulty.HARD -> inv.setItem(4, hard)
			else ->  inv.setItem(4, peace)
		}

		when (this.hardcore) {
			Settings.Hardcore.NUHC -> inv.setItem(19,NUHC);
			Settings.Hardcore.UHC -> inv.setItem(19,UHC);
			Settings.Hardcore.UUHC -> inv.setItem(19,UUHC);
		}

		inv.setItem(1,health);
		inv.setItem(22,dmg);
		inv.setItem(7,challenges);
		inv.setItem(25,timer);

		player.openInventory(inv)
	}

	fun getHealth(): Int {
		return this.health
	}

	fun setHealth(health: Int){
		this.health = health
	}

	fun getDmg(): Double{
		return this.damage
	}

	fun setDmg(dmg: Double){
		this.damage = dmg
	}

	fun getHardcore(): Settings.Hardcore{
		return  this.hardcore
	}

	fun setHardcore(value: Settings.Hardcore){
		this.hardcore = value
	}

	fun getDifficulty(): Difficulty{
		return this.difficulty
	}

	fun setDifficulty(value: Difficulty){
		this.difficulty = value
	}
}