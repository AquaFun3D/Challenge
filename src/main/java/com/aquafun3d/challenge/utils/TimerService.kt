package com.aquafun3d.challenge.utils

import com.aquafun3d.challenge.Main
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.entity.Player
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

	//initialize the runnable timer
	init {
		Bukkit.getScheduler().scheduleSyncRepeatingTask( plugin, {
			if (state === TimerState.RUNNING && lastSec != LocalDateTime.now().second) {
				lastSec = LocalDateTime.now().second
				if (reversed) {
					if(sec <= 0){
						timeRunsOut()
					}
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
					setTimerActionbar(p)
				}
			}
		}, 5L, 5L)
	}

	//toggle timer running
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

	//reset timer
	fun reset() {
		state = TimerState.PAUSED
		sec = 0
		val cfg = Main.timerConfig
		try {
			cfg!!["timer.time"] = 0
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	//check if timer is paused
	fun isPaused(): Boolean {
		return state === TimerState.PAUSED
	}

	//returns formatted string of timers time
	fun getTimerString(): String {
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

	//Ends challenge if time runs out
	private fun timeRunsOut(){
		Settings.atAll(ChatColor.GOLD.toString() + "Time is up!")
		Settings.atAll(ChatColor.RED.toString() + "Challenge has stopped")
		Bukkit.getOnlinePlayers().forEach { p: Player? -> p!!.gameMode = GameMode.SPECTATOR }
		Main.timerService?.getInstance()?.toggle()
	}

	//check if timer is reversed
	fun isReversed(): Boolean {
		return reversed
	}

	//return second timer is running
	fun getSec(): Int {
		return this.sec
	}

	//returns state of the timer
	private fun getState(): TimerState {
		return state
	}

	//make timer reverse
	fun setReversed(reversed: Boolean) {
		this.reversed = reversed
	}

	//set timer state
	fun setState(state: TimerState?) {
		this.state = state!!
	}

	//set timer seconds
	fun setSec(sec: Int) {
		this.sec = sec
	}

	//Get timer instance
	@Synchronized
	fun getInstance(): TimerService? {
		return instance
	}

	//display actionbar for timer to player
	fun setTimerActionbar(p: Player){
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent( "" + ChatColor.GREEN + "" + ChatColor.ITALIC + "Timer paused"))
	}

}