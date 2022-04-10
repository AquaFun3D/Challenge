package de.aquafun3d.challenge.listeners;

import de.aquafun3d.challenge.Main;
import de.aquafun3d.challenge.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class ChallengeListeners implements Listener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player player = e.getPlayer();
		if (!Main.timerService.getInstance().isPaused()) {
			Utils.atAll(ChatColor.YELLOW + "Player " + ChatColor.GOLD + player.getName() + ChatColor.YELLOW + " died");
			Utils.atAll(ChatColor.RED + "Challenge has stopped");
			Utils.atAll(ChatColor.GREEN + "Time wasted: " + ChatColor.GOLD + Main.timerService.getInstance().getTimerString());
			for (Player p : Bukkit.getOnlinePlayers()){
				p.setGameMode(GameMode.SPECTATOR);
			}
			Main.timerService.getInstance().toggle();
		}
	}

	@EventHandler
	public void onPlayerWin(EntityDeathEvent e) {
		if (e.getEntity() instanceof EnderDragon) {
			Utils.atAll(ChatColor.GREEN + "Time: " + ChatColor.GOLD + Main.timerService.getInstance().getTimerString());
			Utils.atAll(ChatColor.GOLD + "Challenge won, you killed the Enderdragon!");
			for (Player p : Bukkit.getOnlinePlayers()){
				p.setGameMode(GameMode.SPECTATOR);
			}
			Main.timerService.getInstance().toggle();
		}
	}
}
