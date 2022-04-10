package de.aquafun3d.challenge.listeners;

import de.aquafun3d.challenge.Main;
import de.aquafun3d.challenge.utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;

public class JoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		e.joinMessage(Component.text(Utils.PREFIX + ChatColor.DARK_PURPLE +  ChatColor.AQUA + player.getName() + ChatColor.LIGHT_PURPLE + " has joined"));
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("health", "health", Component.text("health"), RenderType.HEARTS);
		obj.getScore("health").setScore(1);
		obj.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		player.setScoreboard(board);
		player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Main.settingsInv.getHealth());
		player.setHealth(Main.settingsInv.getHealth());

		if (Main.challengeConfig.getInt("time") != 0) {
			Main.timerService.setTimerActionbar(player);
		}
		for(Player p : Bukkit.getOnlinePlayers()){
			p.damage(0.1);
		}
	}


	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player player= e.getPlayer();
		e.quitMessage(Component.text(Utils.PREFIX + ChatColor.AQUA + player.getName() + ChatColor.LIGHT_PURPLE + " has left"));
	}
}
