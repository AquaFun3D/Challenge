package com.aquafun3d.challenge.utils

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException


class TimerConfig {
	private var file: File? = null
	private var config: YamlConfiguration? = null

	//creates config file if not existing
	init {
		val dir = File("./plugins/Configs/")
		if (!dir.exists()) {
			dir.mkdirs()
		}
		file = File(dir, "timer_config.yml")
		if (!file!!.exists()) {
			try {
				file!!.createNewFile()
			} catch (e: IOException) {
				e.printStackTrace()
			}
		}
		config = YamlConfiguration.loadConfiguration(file!!)
	}

	//checks if config contains a given path
	operator fun contains(path: String): Boolean {
		return config?.contains(path) ?: false
	}

	//writes into config to given path
	@Throws(IOException::class)
	operator fun set(path: String, value: Any?) {
		config?.set(path, value)
		config?.save(file!!)
	}

	//get Object by path
	operator fun get(path: String): Any? {
		return if (!contains(path)) {
			null
		} else config?.get(path)
	}

	//get integer by path
	fun getInt(path: String): Int {
		return if (!contains(path)) {
			0
		} else config?.getInt(path)!!
	}

}