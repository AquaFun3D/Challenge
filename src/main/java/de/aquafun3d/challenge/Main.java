package de.aquafun3d.challenge;

import de.aquafun3d.challenge.commands.*;
import de.aquafun3d.challenge.listeners.*;
import de.aquafun3d.challenge.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin {

	public Main plugin;
	public static ChallengeConfig challengeConfig;
	public static TimerService timerService;
	public static SettingsInv settingsInv;
	public static TimerInv timerInv;
	public static ChallengeInv challengeInv;

	@Override
	public void onEnable() {
		Bukkit.getLogger().fine("Plugin activated");
		plugin = this;

		challengeConfig = new ChallengeConfig();
		settingsInv = new SettingsInv();
		timerService = new TimerService(this);
		timerInv = new TimerInv();
		challengeInv = new ChallengeInv();
		Utils.fillMap();

		listenerRegistration();
		commandRegistraion();
	}

	@Override
	public void onDisable() {
		Bukkit.getLogger().fine("Plugin deactivated");
		try {
			challengeConfig.set("time", timerService.getSec());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void commandRegistraion(){
		getCommand("timer").setExecutor(new TimerCommand());
		getCommand("damage").setExecutor(new DamageCommand());
		getCommand("waypoint").setExecutor(new WaypointCommand());
		getCommand("settings").setExecutor(new SettingsCommand());
		getCommand("start").setExecutor(new StartCommand());

	}

	private void listenerRegistration(){
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new JoinListener(), this);
		pluginManager.registerEvents(new DmgListener(),this);
		pluginManager.registerEvents(new SettingsInvListener(),this);
		pluginManager.registerEvents(new ChallengeListeners(),this);
		pluginManager.registerEvents(new RegenerationListener(),this);
		pluginManager.registerEvents(new TimerInvListener(),this);
		pluginManager.registerEvents(new ChallengeInvListener(),this);

	}
}
