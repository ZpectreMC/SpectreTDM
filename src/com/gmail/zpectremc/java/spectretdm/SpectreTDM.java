package com.gmail.zpectremc.java.spectretdm;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.zpectremc.java.spectretdm.arenas.ArenaManager;
import com.gmail.zpectremc.java.spectretdm.commands.CommandManager;
import com.gmail.zpectremc.java.spectretdm.guns.GunListener;

public class SpectreTDM extends JavaPlugin {
	private static SpectreTDM instance;

	@Override
	public void onEnable() {
		instance = this;
		ArenaManager.getInstance();
		Bukkit.getServer().getPluginManager().registerEvents(new GunListener(), this);
		getCommand("stdm").setExecutor(new CommandManager());
		super.onEnable();
	}

	@Override
	public void onDisable() {
		ArenaManager.getInstance().dispose();
		instance = null;
		super.onDisable();
	}

	public static SpectreTDM getInstance() {
		return instance;
	}
}
