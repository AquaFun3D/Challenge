package com.aquafun3d.challenge

import com.aquafun3d.challenge.commands.DmgCommand
import com.aquafun3d.challenge.commands.TimerCommand
import com.aquafun3d.challenge.commands.WaypointCommand
import com.aquafun3d.challenge.listeners.DmgEntityListener
import com.aquafun3d.challenge.listeners.DmgListener
import com.aquafun3d.challenge.listeners.JoinListener
import com.aquafun3d.challenge.utils.Settings
import com.aquafun3d.challenge.utils.TimerConfig
import com.aquafun3d.challenge.utils.TimerService
import com.aquafun3d.challenge.utils.WaypointConfig
import org.bukkit.Bukkit
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin


class Main : JavaPlugin() {

    override fun onEnable() {
        Bukkit.getLogger().fine("Plugin activated")

        timerConfig = TimerConfig()
        timerService = TimerService(this)
        waypointConfig = WaypointConfig()
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
    }

    private fun listenerRegistration() {
        pluginManager.registerEvents(JoinListener(), this)
        pluginManager.registerEvents(DmgListener(),this)
        pluginManager.registerEvents(DmgEntityListener(),this)
    }

    companion object {
        val pluginManager: PluginManager = Bukkit.getPluginManager()
        var timerConfig: TimerConfig? = null
        var waypointConfig: WaypointConfig? = null
        var timerService: TimerService? = null
    }
}