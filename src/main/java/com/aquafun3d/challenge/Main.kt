package com.aquafun3d.challenge

import com.aquafun3d.challenge.utils.Settings
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    private var plugin: Main? = this

    override fun onEnable() {
        println(Settings.PREFIX)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    fun getPlugin(): Main? {
        return plugin
    }
}