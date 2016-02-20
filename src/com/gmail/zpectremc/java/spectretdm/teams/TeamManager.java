package com.gmail.zpectremc.java.spectretdm.teams;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.OfflinePlayer;

import com.google.common.collect.Iterables;

public class TeamManager {

	private Set<Team> teams;
	private List<OfflinePlayer> players;

	public TeamManager() {
		this.teams = new HashSet<Team>();
		this.players = new ArrayList<OfflinePlayer>();
	}

	public void createNewTeam(String name) {
		Team team = new Team(name);
		teams.add(team);
	}

	public Team getTeamByName(String name) {
		for (Team t : teams)
			if (t.getName().equalsIgnoreCase(name))
				return t;
		return null;
	}

	public boolean teamExists(String name) {
		return getTeamByName(name) != null;
	}

	public boolean doTeamsNeedShuffle() {
		return (getLargestTeam().getSize() - getSmallestTeam().getSize()) > 1;
	}

	public void transferPlayer(OfflinePlayer player, Team to) {
		for (Team t : teams)
			t.removePlayer(player);
		to.addPlayer(player);
	}

	public void rebalanceTeams() {
		while (doTeamsNeedShuffle())
			transferPlayer(Iterables.getFirst(getLargestTeam().getPlayers(), null), getSmallestTeam());
	}

	public Team getLargestTeam() {
		int currentSize = Integer.MIN_VALUE;
		Team currentTeam = null;
		for (Team t : teams)
			if (t.getSize() > currentSize) {
				currentSize = t.getSize();
				currentTeam = t;
			}
		return currentTeam;
	}

	public Team getSmallestTeam() {
		int currentSize = Integer.MAX_VALUE;
		Team currentTeam = null;
		for (Team t : teams)
			if (t.getSize() < currentSize) {
				currentSize = t.getSize();
				currentTeam = t;
			}
		return currentTeam;
	}

	public void scrambleTeams() {
		if (teams.isEmpty())
			return;
		for (Team t : teams)
			t.clearTeam();
	}

	public boolean addPlayerToShuffle(OfflinePlayer player) {
		return players.add(player);
	}

	public void initialShuffle(Team one, Team two) {
		int teamToAdd = 1; // 1 = one, 2 = two
		Random playerToAdd = new Random();
		do {
			if (teamToAdd == 1) {
				OfflinePlayer added = players.get(playerToAdd.nextInt(players.size()));
				one.addPlayer(added);
				teamToAdd++;
				players.remove(added);
				continue;
			}
			if (teamToAdd == 2) {
				OfflinePlayer added = players.get(playerToAdd.nextInt(players.size()));
				two.addPlayer(added);
				teamToAdd--;
				players.remove(added);
				continue;
			}
		} while (!players.isEmpty());
	}

	public Set<Team> getAllTeams() {
		return teams;
	}
}
