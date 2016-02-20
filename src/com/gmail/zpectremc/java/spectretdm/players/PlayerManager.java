package com.gmail.zpectremc.java.spectretdm.players;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.zpectremc.java.spectretdm.kits.Kit;
import com.gmail.zpectremc.java.spectretdm.kits.freekits.LightAssault;
import com.gmail.zpectremc.java.spectretdm.teams.Team;

public class PlayerManager {

	private Player p;
	private Kit kit;
	private ItemStack[] inventory, armor;
	private Location lastLoc;
	private double health;
	private int exp;
	private int hunger;
	private Team team;
	
	private int kills;
	private int deaths;
	
	
	public PlayerManager(Player p){
		this.p = p;
	}
	
	public void overwriteData(){
		this.inventory = p.getInventory().getContents();
		this.armor = p.getInventory().getArmorContents();
		this.lastLoc = p.getLocation();
		this.health = p.getHealth();
		this.exp = p.getTotalExperience();
		this.hunger = p.getFoodLevel();
		//TODO kit
		
		this.kills = 0;
		this.deaths = 0;
	}
	
	public void restore(){
		p.getInventory().clear();
		p.getInventory().setContents(inventory);
		p.getInventory().setArmorContents(armor);
		p.teleport(lastLoc);
		p.setHealth(health);
		p.setTotalExperience(exp);
		p.setFoodLevel(hunger);
		p.setFireTicks(0);
		p.getActivePotionEffects().clear();
	}
	
	public void dispose(){
		p = null;
		inventory = null;
		armor = null;
		lastLoc = null;
		kit = null;
	}
	
	public double getKDR(){
		return kills / Math.max(1, deaths);
	}
	
	public Kit getKit(){
		if(kit == null)
			kit = new LightAssault();
		return kit;
	}
	
	public void setKit(Kit kit){
		this.kit = kit;
	}
	
	public int getKills(){
		return kills;
	}
	
	public int getDeaths(){
		return deaths;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
}
