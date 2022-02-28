package com.aquafun3d.challenge

import com.aquafun3d.challenge.commands.*
import com.aquafun3d.challenge.listeners.*
import com.aquafun3d.challenge.utils.*
import org.bukkit.Bukkit
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    override fun onEnable() {
        Bukkit.getLogger().fine("Plugin activated")

        timerConfig = TimerConfig()
        timerService = TimerService(this)
        waypointConfig = WaypointConfig()
        settingsInv = SettingsInv()
        Settings.fillMap()

        commandRegistration()
        listenerRegistration()
    }

    override fun onDisable() {
        Bukkit.getLogger().fine("Plugin deactivated")
	}

    private fun commandRegistration() {
        getCommand("timer")!!.setExecutor(TimerCommand())
        getCommand("damage")!!.setExecutor(DmgCommand())
        getCommand("waypoint")!!.setExecutor(WaypointCommand())
        getCommand("settings")!!.setExecutor(SettingsCommand())
        getCommand("start")!!.setExecutor(StartCommand())
    }

    private fun listenerRegistration() {
        pluginManager.registerEvents(JoinListener(), this)
        pluginManager.registerEvents(DmgListener(),this)
        pluginManager.registerEvents(SettingsInvListener(),this)
        pluginManager.registerEvents(ChallengeListeners(),this)
        pluginManager.registerEvents(RegenerationListener(),this)
    }

    //Global objects (Static one time instances)
    companion object {
        val pluginManager: PluginManager = Bukkit.getPluginManager()
        var timerConfig: TimerConfig? = null
        var waypointConfig: WaypointConfig? = null
        var timerService: TimerService? = null
        var settingsInv: SettingsInv? = null
    }
}