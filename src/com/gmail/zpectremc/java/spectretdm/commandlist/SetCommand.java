package com.gmail.zpectremc.java.spectretdm.commandlist;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.zpectremc.java.spectretdm.arenas.Arena;
import com.gmail.zpectremc.java.spectretdm.arenas.ArenaManager;
import com.gmail.zpectremc.java.spectretdm.commands.Argument;

import net.md_5.bungee.api.ChatColor;

public class SetCommand extends Argument {

	private final String[] params = { "redspawn", "bluespawn", "lobby" };

	public SetCommand() {
		super("set");
		setAliases("s");
		setPermNode("tdm.set");
		setArgParams("<arenaName>", "<parameter>");
		setUsage(getDefaultUsage());
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		if (!hasPermission(sender)) {
			noPermissionCmd(sender);
			return;
		}
		if (args.length < getArgParams().length) {
			missingArgs(sender);
			return;
		}
		if (args.length > getArgParams().length) {
			tooManyArgs(sender);
			return;
		}
		Arena a = ArenaManager.getInstance().getByName(args[0]);
		if (a == null) {
			sender.sendMessage(ChatColor.RED + "Arena \"" + args[0] + "\" does not exist.");
			return;
		}
		Player p = (Player) sender;
		switch (args[1].toLowerCase()) {
		case "redspawn":
			a.setTeamSpawn(a.getTeams().getTeamByName("RED"), p.getLocation());
			sender.sendMessage(ChatColor.GREEN + "Successfully set spawn for RED team for arena " + args[0] + ".");
			break;
		case "bluespawn":
			a.setTeamSpawn(a.getTeams().getTeamByName("BLUE"), p.getLocation());
			sender.sendMessage(ChatColor.GREEN + "Successfully set spawn for BLUE team for arena " + args[0] + ".");
			break;
		case "lobby":
			a.setLobby(p.getLocation());
			sender.sendMessage(ChatColor.GREEN + "Successfully set lobby point for arena " + args[0] + ".");
			break;
		case "bs":
			a.setTeamSpawn(a.getTeams().getTeamByName("BLUE"), p.getLocation());
			sender.sendMessage(ChatColor.GREEN + "Successfully set spawn for BLUE team for arena " + args[0] + ".");
			break;
		case "rs":
			a.setTeamSpawn(a.getTeams().getTeamByName("RED"), p.getLocation());
			sender.sendMessage(ChatColor.GREEN + "Successfully set spawn for RED team for arena " + args[0] + ".");
			break;
		case "l":
			a.setLobby(p.getLocation());
			sender.sendMessage(ChatColor.GREEN + "Successfully set lobby point for arena " + args[0] + ".");
			break;
		default:
			sender.sendMessage(ChatColor.RED + "Valid parameters are: " + params);
		}

	}

}
