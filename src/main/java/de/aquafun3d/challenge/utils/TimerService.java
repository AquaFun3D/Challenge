package de.aquafun3d.challenge.utils;

import de.aquafun3d.challenge.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.time.LocalDateTime;

public class TimerService {

	enum TimerState {PAUSED, RUNNING}
	private volatile TimerService instance = this;
	private Boolean reversed = false;
	private TimerState state = TimerState.PAUSED;
	private int lastSec = 0;
	private int sec = 0;

	public TimerService(Plugin plugin){
		if(Main.challengeConfig.contains("time")){
			sec = Main.challengeConfig.getInt("time");
		}
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,() ->  {
			if(state == TimerState.RUNNING && lastSec != LocalDateTime.now().getSecond()){
				lastSec = LocalDateTime.now().getSecond();
				if (reversed) {
					if(sec <= 0){
						timeRunsOut();
					}
					sec--;
				} else {
					sec++;
				}
				if(state == TimerState.RUNNING){
					for(Player p : Bukkit.getOnlinePlayers()){
						p.sendActionBar(Component.text(ChatColor.GOLD + getTimerString()));
					}
				}else{
					for(Player p : Bukkit.getOnlinePlayers()){
						setTimerActionbar(p);
					}
				}
			}
		},5L,5L);
	}

	public void toggle(){
		if(state != TimerState.RUNNING){
			if(Main.challengeConfig.contains("time") && Main.challengeConfig.getInt("time") != 0){
				sec = Main.challengeConfig.getInt("time");
			}
			state = TimerState.RUNNING;
		}else {
			try {
				Main.challengeConfig.set("time",sec);
			} catch (IOException e) {
				e.printStackTrace();
			}
			state = TimerState.PAUSED;
		}
	}

	public void reset(){
		state = TimerState.PAUSED;
		sec = 0;
		try {
			Main.challengeConfig.set("time",0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isPaused(){
		return state == TimerState.PAUSED;
	}

	public boolean isReversed(){
		return reversed;
	}

	public String getTimerString(){
		String hms;
		if(sec < 3600){
			hms = String.format("%02d:%02d", sec / 60, sec % 60);
		} else if (sec < 3600 * 24) {
			hms = String.format("%02d:%02d:%02d", sec / 60 / 60, sec / 60 % 60, sec % 60);
		} else {
			hms = String.format("%d Days, %02d:%02d:%02d", sec / 60 / 60 / 24, sec / 60 / 60, sec / 60 % 60, sec % 60);
		}
		if(reversed){
			hms = ChatColor.GOLD + "" + "" + hms;
			hms += ChatColor.GREEN + " remaining";
		}
		return hms;
	}

	private void timeRunsOut(){
		Utils.atAll(ChatColor.GOLD + "Time is up!");
		Utils.atAll(ChatColor.RED + "Challenge has stopped");
		for(Player p : Bukkit.getOnlinePlayers()){
			p.setGameMode(GameMode.SPECTATOR);
		}
		Main.timerService.getInstance().toggle();
	}

	public synchronized TimerService getInstance(){
		return instance;
	}

	public void setTimerActionbar(Player p){
		p.sendActionBar(Component.text("" + ChatColor.GREEN + "" + ChatColor.ITALIC + "Timer paused"));
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public void setReversed(boolean reversed){
		this.reversed = reversed;
	}
}

