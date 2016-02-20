package com.gmail.zpectremc.java.spectretdm.teams;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Team {

	private String name;
	private Set<OfflinePlayer> players;
	private String prefix;
	private int score;
	private Location spawn;

	public Team(String name, String prefix) {
		this.name = name;
		this.prefix = prefix;
		this.score = 0;
		this.players = new HashSet<OfflinePlayer>();
	}

	public Team(String name) {
		this(name, "");
	}

	@SuppressWarnings("deprecation")
	public boolean addPlayer(String name) {
		return players.add(Bukkit.getOfflinePlayer(name));
	}

	@SuppressWarnings("deprecation")
	public boolean removePlayer(String name) {
		return players.remove(Bukkit.getOfflinePlayer(name));
	}

	public boolean hasPlayer(OfflinePlayer player) {
		return players.contains(player);
	}

	public boolean addPlayer(OfflinePlayer player) {
		Player p = (Player) player;
		p.sendMessage("You have been added to the " + this.getName() + " team!");
		return addPlayer(player.getName());
	}

	public boolean removePlayer(OfflinePlayer player) {
		return removePlayer(player.getName());
	}

	public void clearTeam() {
		players.clear();
	}

	public int getSize() {
		return players.size();
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Set<OfflinePlayer> getPlayers() {
		return players;
	}

	public Location getSpawn() {
		return spawn;
	}

	public void setSpawn(Location spawn) {
		this.spawn = spawn;
	}

}
