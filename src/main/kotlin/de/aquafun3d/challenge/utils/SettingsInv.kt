package de.aquafun3d.challenge.utils

import de.aquafun3d.challenge.Main
import net.kyori.adventure.text.Component
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
	private var hardcore: Utils.Hardcore = Utils.Hardcore.NUHC
	private var health: Int = 20
	private val inv: Inventory = Bukkit.createInventory(null,27, Component.text(ChatColor.DARK_PURPLE.toString() + "Challenge Settings"))

	init {
		if(Main.challengeConfig!!.contains("damage")){
			damage = Main.challengeConfig!!.getDouble("damage")
		}
		if(Main.challengeConfig!!.contains("health")){
			health = Main.challengeConfig!!.getInt("health")
		}
		if(Main.challengeConfig!!.contains(("hardcore"))){
			hardcore = Utils.stringToHardcore(Main.challengeConfig!!.getString("hardcore"))
		}
		if(Main.challengeConfig!!.contains(("difficulty"))){
			difficulty = Utils.stringToDifficulty(Main.challengeConfig!!.getString("difficulty"))
		}
	}

	//Generates placeholder items for settings inventory and places them into the inventory
	fun newInventory(player: Player){

		val empty = ItemStack(Material.BLACK_STAINED_GLASS_PANE)
		val emptyMeta = empty.itemMeta
		emptyMeta!!.displayName(Component.text(" "))
		empty.itemMeta = emptyMeta

		val health = ItemStack(Material.HEART_OF_THE_SEA, this.health)
		val healthMeta = health.itemMeta
		healthMeta!!.displayName(Component.text(ChatColor.YELLOW.toString() + "Health"))
		health.itemMeta = healthMeta

		val nuhc = ItemStack(Material.APPLE)
		val nuhcMeta = nuhc.itemMeta
		nuhcMeta!!.displayName(Component.text(ChatColor.GREEN.toString() + "No Ultra Hardcore"))
		nuhc.itemMeta = nuhcMeta

		val uhc = ItemStack(Material.GOLDEN_APPLE)
		val uhcMeta = uhc.itemMeta
		uhcMeta!!.displayName(Component.text(ChatColor.YELLOW.toString() + "Ultra Hardcore"))
		uhc.itemMeta = uhcMeta

		val uuhc = ItemStack(Material.ENCHANTED_GOLDEN_APPLE)
		val uuhcMeta = uuhc.itemMeta
		uuhcMeta!!.displayName(Component.text(ChatColor.RED.toString() + "Ultra Ultra Hardcore"))
		uuhc.itemMeta = uuhcMeta

		val peace = ItemStack(Material.NETHERITE_INGOT)
		val peaceMeta = peace.itemMeta
		peaceMeta!!.displayName(Component.text(ChatColor.LIGHT_PURPLE.toString() + "Peaceful"))
		peace.itemMeta = peaceMeta

		val easy = ItemStack(Material.COPPER_INGOT)
		val easyMeta = easy.itemMeta
		easyMeta!!.displayName(Component.text(ChatColor.GREEN.toString() + "Easy"))
		easy.itemMeta = easyMeta

		val normal = ItemStack(Material.IRON_INGOT)
		val normalMeta = normal.itemMeta
		normalMeta!!.displayName(Component.text(ChatColor.YELLOW.toString() + "Normal"))
		normal.itemMeta = normalMeta

		val hard = ItemStack(Material.GOLD_INGOT)
		val hardMeta = hard.itemMeta
		hardMeta!!.displayName(Component.text(ChatColor.RED.toString() + "Hard"))
		hard.itemMeta = hardMeta

		val dmg = ItemStack(Material.ARROW)
		val dmgMeta = dmg.itemMeta
		dmgMeta!!.displayName(Component.text(ChatColor.YELLOW.toString() + "Damage multiplier"))
		val lore = ArrayList<Component>()
		lore.add(Component.text(damage.toString()))
		dmgMeta.lore(lore)
		dmg.itemMeta = dmgMeta

		val challenges = ItemStack(Material.TOTEM_OF_UNDYING)
		val challengesMeta = challenges.itemMeta
		challengesMeta!!.displayName(Component.text(ChatColor.YELLOW.toString() + "Challenges"))
		challenges.itemMeta = challengesMeta

		val timer = ItemStack(Material.CLOCK)
		val timerMeta = timer.itemMeta
		timerMeta!!.displayName(Component.text(ChatColor.YELLOW.toString() + "Timer"))
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
			Utils.Hardcore.NUHC -> inv.setItem(19,nuhc)
			Utils.Hardcore.UHC -> inv.setItem(19,uhc)
			Utils.Hardcore.UUHC -> inv.setItem(19,uuhc)
		}

		inv.setItem(1,health)
		inv.setItem(22,dmg)
		inv.setItem(7,challenges)
		inv.setItem(25,timer)

		player.openInventory(inv)
	}

	fun writeSettings(){
		Main.challengeConfig?.set("health",health)
		Main.challengeConfig?.set("damage",damage)
		Main.challengeConfig?.set("hardcore",hardcore.toString())
		Main.challengeConfig?.set("difficulty",difficulty.toString())
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

	fun getHardcore(): Utils.Hardcore{
		return  this.hardcore
	}

	fun setHardcore(value: Utils.Hardcore){
		this.hardcore = value
	}

	fun getDifficulty(): Difficulty{
		return this.difficulty
	}

	fun setDifficulty(value: Difficulty){
		this.difficulty = value
	}
}