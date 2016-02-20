package com.gmail.zpectremc.java.spectretdm.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.zpectremc.java.spectretdm.kits.Kit;
import com.gmail.zpectremc.java.spectretdm.utils.ParticleEffect.ParticleType;

import net.md_5.bungee.api.ChatColor;

public abstract class Argument {

	private String name;
	private String[] aliases;
	private String usage;
	private String permNode;
	private String[] argParams;

	public Argument(String name) {
		this.name = name;
		this.aliases = new String[0];
		this.permNode = "";
		this.argParams = new String[0];
		this.usage = getDefaultUsage();
	}

	public String getDefaultUsage() {
		return "/tdm " + name + " " + getArgParamsAsString();
	}

	public String getArgParamsAsString() {
		String returnedArgs = "";
		if (getArgParams().length == 0)
			return returnedArgs;
		for (String s : getArgParams())
			returnedArgs += (" " + s);
		return returnedArgs.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getAliases() {
		return aliases;
	}

	public void setAliases(String... aliases) {
		this.aliases = aliases;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getPermNode() {
		return permNode;
	}

	public void setPermNode(String permNode) {
		this.permNode = permNode;
	}

	public String[] getArgParams() {
		return argParams;
	}

	public void setArgParams(String... argParams) {
		this.argParams = argParams;
	}

	public boolean hasPermission(CommandSender sender) {
		return sender.isOp() || sender.hasPermission(getPermNode());
	}

	public void sendUsage(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "Usage: " + getUsage());
	}

	public void noPermissionCmd(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "You do not have permission for the command \"" + getName() + "\"!");
	}

	public void noPermissionKit(CommandSender sender, Kit kit) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have access to the kit &6&l"
				+ kit.getName() + "&c! Earn the kit by killing enemies, or visit the store @ *paste link here*"));
	}

	public void noPermissionTrail(CommandSender sender, ParticleType pt) {
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have access to the gun trail &6&l"
				+ pt.toString() + "&c! Earn the kit by killing enemies, or visit the store @ *paste link here*"));
	}

	public void missingArgs(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "Not enough arguments! Check your command format.");
		sendUsage(sender);
	}
	
	public void tooManyArgs(CommandSender sender){
		sender.sendMessage(ChatColor.RED + "Too many arguments! Check your command format.");
		sendUsage(sender);
	}

	public boolean isPlayer(CommandSender sender) {
		return sender instanceof Player;
	}

	public void cmdIsPlayerOnly(CommandSender sender) {
		sender.sendMessage(ChatColor.DARK_RED + "This is a player only command.");
	}

	public String[] removeFirst(String... a) {
		if (a.length < 2)
			return new String[0];
		String[] ret = new String[a.length - 1];
		for (int i = 0; i < ret.length; i++)
			ret[i] = a[i + 1];
		return ret;
	}

	public abstract void onCommand(CommandSender sender, String args[]);

	public boolean isCommand(String cmd) {
		if (cmd.equalsIgnoreCase(getName()))
			return true;
		if (aliases.length == 0)
			return false;
		for (String s : aliases)
			if (s.equalsIgnoreCase(cmd))
				return true;
		return false;
	}

}
