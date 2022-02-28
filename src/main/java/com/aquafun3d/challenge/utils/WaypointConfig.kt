package com.aquafun3d.challenge.utils

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException


class WaypointConfig {
	private var file: File? = null
	private var config: YamlConfiguration? = null

	//creates file if not existing
	init {
		val dir = File("./plugins/Configs/")
		if (!dir.exists()) {
			dir.mkdirs()
		}
		file = File(dir, "waypoint_config.yml")
		if (!file!!.exists()) {
			try {
				file!!.createNewFile()
			} catch (e: IOException) {
				e.printStackTrace()
			}
		}
		config = YamlConfiguration.loadConfiguration(file!!)
	}

	//checks if config contains path
	operator fun contains(path: String?): Boolean {
		return config!!.contains(path!!)
	}

	//write to config
	@Throws(IOException::class)
	operator fun set(path: String?, value: Any?) {
		config!![path!!] = value
		config!!.save(file!!)
	}

	//get from config by given path
	operator fun get(path: String?): Any? {
		return if (!contains(path)) {
			null
		} else config!![path!!]
	}

	//get everything from config
	fun getAll(path: String?): ArrayList<String>? {
		val list = ArrayList<String>()
		return if (!contains(path)) {
			null
		} else {
			for (key in config!!.getConfigurationSection(path!!)!!.getKeys(false)) {
				list.add(key)
			}
			list
		}
	}

	//get a String of the path
	fun getString(path: String?): String? {
		return if (!contains(path)) {
			null
		} else config!!.getString(path!!)
	}

	//get a double of the path
	fun getDouble(path: String?): Double {
		return if (!contains(path)) {
			0.0
		} else config!!.getDouble(path!!)
	}
}