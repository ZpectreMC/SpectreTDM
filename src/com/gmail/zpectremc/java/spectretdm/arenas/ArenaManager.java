package com.gmail.zpectremc.java.spectretdm.arenas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.zpectremc.java.spectretdm.SpectreTDM;

public class ArenaManager {

	private static ArenaManager instance;
	private List<Arena> arenas;

	private ArenaManager() {
		arenas = new ArrayList<Arena>();
		startGameTicks();
	}

	private void startGameTicks() {
		new BukkitRunnable() {

			@Override
			public void run() {
				if (arenas.isEmpty())
					return;
				for (Arena a : arenas)
					a.tick();
			}
		}.runTaskTimer(SpectreTDM.getInstance(), 0L, 1L);
	}

	public static ArenaManager getInstance() {
		if (instance == null)
			instance = new ArenaManager();
		return instance;
	}

	public List<Arena> getAllArenas() {
		return Collections.unmodifiableList(arenas);
	}

	public Arena getByPlayer(OfflinePlayer p) {
		if (arenas.isEmpty())
			return null;
		for (Arena a : arenas)
			if (a.hasPlayer(p))
				return a;
		return null;
	}

	public Arena getByName(String s) {
		if (arenas.isEmpty())
			return null;
		for (Arena a : arenas)
			if (a.getName() != null && a.getName().equalsIgnoreCase(s))
				return a;
		return null;
	}

	public List<Arena> getByState(GameState gs) {
		if (arenas.isEmpty())
			return null;
		List<Arena> list = new ArrayList<Arena>();
		for (Arena a : arenas)
			if (a.getState() == gs)
				list.add(a);
		return list;
	}

	public void createArena(String s) {
		Arena newArena = new Arena(s);
		arenas.add(newArena);
	}

	public void deleteArena(String s) {
		arenas.remove(getByName(s));
	}

	public void endAllGames() {
		for (Arena a : arenas) {
			a.forceEndGame("Ending all games!");
		}
	}

	public void dispose() {
		endAllGames();
		arenas.clear();
		instance = null;
	}

	public void saveMaps() {
		// TODO save maps
	}

	public void loadMaps() {
		// TODO load maps
	}
}
