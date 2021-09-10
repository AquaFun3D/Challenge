package com.aquafun3d.challenge

import com.aquafun3d.challenge.commands.TimerCommand
import com.aquafun3d.challenge.utils.TimerConfig
import com.aquafun3d.challenge.utils.TimerService
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin


class Main : JavaPlugin() {

    override fun onEnable() {
        Bukkit.getLogger().fine("Plugin activated")

        timerConfig = TimerConfig()
        timerService = TimerService(this)

        commandRegistration()
        listenerRegistration()
    }

    override fun onDisable() {
        Bukkit.getLogger().fine("Plugin deactivated")
    }


    private fun commandRegistration() {
        getCommand("timer")!!.setExecutor(TimerCommand())
    }

    private fun listenerRegistration() {

    }

    companion object {
        var timerConfig: TimerConfig? = null
        var timerService: TimerService? = null
    }
}