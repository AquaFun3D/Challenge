package com.aquafun3d.challenge.utils

import com.aquafun3d.challenge.Main
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.Plugin
import java.io.IOException
import java.time.LocalDateTime


class TimerService(plugin: Plugin) {

	enum class TimerState {PAUSED, RUNNING}
	@Volatile
	private var instance: TimerService? = this
	private var reversed = false//false = +, true = -
	private var state: TimerState = TimerState.PAUSED
	private var lastSec = 0
	private var sec = 0

	init {
		Bukkit.getScheduler().scheduleSyncRepeatingTask( plugin, {
			if (state === TimerState.RUNNING && lastSec != LocalDateTime.now().second) {
				lastSec = LocalDateTime.now().second
				if (reversed) {
					sec--
				} else {
					sec++
				}
			}
			if (state === TimerState.RUNNING) {
				for (p in Bukkit.getOnlinePlayers()) {
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR,TextComponent(getTimerString()))
				}
			} else {
				for (p in Bukkit.getOnlinePlayers()) {
					p.spigot().sendMessage(
						ChatMessageType.ACTION_BAR,
						TextComponent(ChatColor.GREEN.toString() + "" + ChatColor.ITALIC + "Timer paused")
					)
				}
			}
		}, 5L, 5L)
	}

	fun toggle() {
		state = if(getState() != TimerState.RUNNING){
			val cfg = Main.timerConfig
			if (cfg!!.getInt("timer.time") != 0){
				setSec(cfg.getInt("timer.time"));
			}
			TimerState.RUNNING
		}else{
			TimerState.PAUSED
		}
	}

	fun reset() {
		state = TimerState.PAUSED
		sec = 0
		val cfg = Main.timerConfig
		try {
			cfg!!.set("timer.time", 0)
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	fun isPaused(): Boolean {
		return state === TimerState.PAUSED
	}

	fun changeReversedState() {
		reversed = !reversed
	}

	private fun getTimerString(): String {
		var hms: String
		hms = if (sec < 3600) {
			String.format("%02d:%02d", sec / 60, sec % 60)
		} else if (sec < 3600 * 24) {
			String.format("%02d:%02d:%02d", sec / 60 / 60, sec / 60 % 60, sec % 60)
		} else {
			String.format("%d Days, %02d:%02d:%02d", sec / 60 / 60 / 24, sec / 60 / 60, sec / 60 % 60, sec % 60)
		}
		if (reversed) {
			hms = ChatColor.GOLD.toString() + "" + "" + hms
			hms += ChatColor.GREEN.toString() + " remaining"
		} else {
			hms = ChatColor.GOLD.toString() + ""  + "" + hms
		}
		return hms
	}

	fun isReversed(): Boolean {
		return reversed
	}

	fun getSec(): Int {
		return sec
	}

	private fun getState(): TimerState {
		return state
	}

	fun setReversed(reversed: Boolean) {
		this.reversed = reversed
	}

	fun setState(state: TimerState?) {
		this.state = state!!
	}

	private fun setSec(sec: Int) {
		this.sec = sec
	}

	@Synchronized
	fun getInstance(): TimerService? {
		return instance
	}

}