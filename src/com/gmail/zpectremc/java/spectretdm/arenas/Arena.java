package com.gmail.zpectremc.java.spectretdm.arenas;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.gmail.zpectremc.java.spectretdm.kits.KitManager;
import com.gmail.zpectremc.java.spectretdm.players.PlayerManager;
import com.gmail.zpectremc.java.spectretdm.teams.Team;
import com.gmail.zpectremc.java.spectretdm.teams.TeamManager;
import com.gmail.zpectremc.java.spectretdm.utils.Utility;

import net.md_5.bungee.api.ChatColor;

public class Arena implements Listener {

	private Map<OfflinePlayer, PlayerManager> players;;
	private int maxPlayers = 20;
	private int minPlayers = 2;
	private TeamManager teams;
	private GameState state;
	private String name;
	private int lobbyTime = 620;
	private int gameTime = 6020;
	private Location lobby;

	public Arena(String name) {
		this.name = name;
		this.players = new HashMap<OfflinePlayer, PlayerManager>();
		this.teams = new TeamManager();
		setState(GameState.LOBBY);
		teams.createNewTeam("RED");
		teams.createNewTeam("BLUE");
	}

	public void endGame() {
		setState(GameState.ENDING);
		for (Team t : teams.getAllTeams())
			t.clearTeam();
		for (OfflinePlayer p : players.keySet()) {
			removePlayer(p);
		}
		timerCount = 0;
	}

	public void startGame() {
		for (OfflinePlayer p : players.keySet()) {
			teams.addPlayerToShuffle(p);
		}
		teams.initialShuffle(teams.getTeamByName("RED"), teams.getTeamByName("BLUE"));
		teleportPlayersToSpawns();
		setState(GameState.INGAME);
	}

	public void forceEndGame(String reason) {
		endGame();
		for (OfflinePlayer op : players.keySet()) {
			Player p = (Player) op;
			p.sendMessage("Your game was ended by an administrator because: " + reason);
		}
	}

	public void setTeamSpawn(Team t, Location l) {
		t.setSpawn(l);
	}

	public void teleportPlayersToSpawns() {
		for (OfflinePlayer p : players.keySet()) {
			Player toTeleport = (Player) p;
			for (Team t : teams.getAllTeams()) {
				if (t.hasPlayer(toTeleport))
					toTeleport.teleport(t.getSpawn());
			}
		}
	}
	
	public void giveAllKits(){
		for(OfflinePlayer p : players.keySet()){
			KitManager.getInstance().giveKit(p, this.getPlayers().get(p).getKit());
		}
	}

	public boolean hasPlayer(OfflinePlayer p) {
		return players.containsKey(p);
	}

	public String getName() {
		return name;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public Map<OfflinePlayer, PlayerManager> getPlayers() {
		return players;
	}

	public boolean addPlayer(OfflinePlayer op) {
		Player p = (Player) op;
		if (!isArenaComplete(p)) {
			statusOfLocs(p);
			return false;
		}
		if (players.size() < getMaxPlayers()) {
			players.put(op, new PlayerManager((Player) op));
			players.get(op).overwriteData();
			p.teleport(this.getLobby());
			return true;
		}
		Utility.convertToOLP(op).sendMessage(ChatColor.RED + "Sorry, but this arena is full!");
		return false;
	}

	public void removePlayer(OfflinePlayer op) {
		players.get(op).restore();
		players.remove(op);
	}

	public TeamManager getTeams() {
		return teams;
	}

	public void setName(String name) {
		this.name = name;
	}

	private int timerCount = 0;

	public void tick() {
		if (getState() == GameState.ENDING)
			return;
		if (getState() == GameState.DISABLED)
			return;
		++timerCount;
		if (getState() == GameState.LOBBY) {
			if (getPlayers().size() < 2)
				timerCount = 0;
			if (timerCount >= lobbyTime) {
				timerCount = 0;
				startGame();
			}
			if (timerCount % 20 == 0 && timerCount != 0) {
				for (OfflinePlayer op : players.keySet()) {
					Player p = (Player) op;
					p.sendMessage("Game starts in " + ((lobbyTime / 20) - (timerCount / 20)) + " seconds");
				}
			}
			return;
		}
		if (getState() == GameState.INGAME) {
			if (timerCount >= gameTime) {
				timerCount = 0;
				endGame();
				return;
			}
			teams.rebalanceTeams();
			if (gameNeedsEnding()) {
				endGame();
				return;
			}
			if (timerCount % 20 == 0) {
				for (OfflinePlayer op : players.keySet()) {
					Player p = (Player) op;
					p.sendMessage("Game will end in " + ((gameTime / 20) - (timerCount / 20)) + " seconds");
				}
			}
		}
	}

	public boolean checkPlayerSize() {
		if (getState() == GameState.INGAME && players.size() < 2)
			return false;
		return true;
	}

	public boolean gameNeedsEnding() {
		if (checkPlayerSize() == false) {
			return true;
		}
		return false;
	}

	public int getTimerCount() {
		return timerCount;
	}

	public void setTimerCount(int timerCount) {
		this.timerCount = timerCount;
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		OfflinePlayer p = e.getPlayer();
		removePlayer(p);
		for (Team t : teams.getAllTeams()) {
			t.removePlayer(p);
		}
		Player toSpawn = (Player) p;
		toSpawn.teleport(toSpawn.getWorld().getSpawnLocation());

	}

	@EventHandler
	public void onFriendlyFire(EntityDamageByEntityEvent e) {
		Entity target = e.getEntity();
		Entity attacker = e.getDamager();
		if (target instanceof Player && attacker instanceof Player) {
			if (players.containsKey((OfflinePlayer) target) && players.containsKey((OfflinePlayer) attacker))
				if (players.get(target).getTeam() == players.get(attacker).getTeam())
					e.setCancelled(true);
		}
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		if (players.containsKey((OfflinePlayer) p))
			p.teleport(players.get(p).getTeam().getSpawn());
		else
			p.teleport(p.getWorld().getSpawnLocation());
	}

	public Location getLobby() {
		return lobby;
	}

	public void setLobby(Location lobby) {
		this.lobby = lobby;
	}

	public void statusOfLocs(Player p) {
		if (getLobby() == null)
			p.sendMessage(ChatColor.DARK_RED + "LOBBY spawn");
		if (teams.getTeamByName("RED").getSpawn() == null)
			p.sendMessage(ChatColor.DARK_RED + "RED spawn");
		if (teams.getTeamByName("BLUE").getSpawn() == null)
			p.sendMessage(ChatColor.DARK_RED + "BLUE spawn");
	}

	public boolean isArenaComplete(Player p) {
		Location[] locs = new Location[] { getLobby(), teams.getTeamByName("RED").getSpawn(),
				teams.getTeamByName("BLUE").getSpawn() };
		for (Location loc : locs) {
			if (loc == null) {
				p.sendMessage(ChatColor.RED
						+ "Sorry, failed to add you to arena. It is incomplete: here's a list of settings that are null!");
				return false;
			}
		}
		return true;
	}
}
