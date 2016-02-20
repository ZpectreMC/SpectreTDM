package com.gmail.zpectremc.java.spectretdm.commandlist;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import com.gmail.zpectremc.java.spectretdm.arenas.Arena;
import com.gmail.zpectremc.java.spectretdm.arenas.ArenaManager;
import com.gmail.zpectremc.java.spectretdm.commands.Argument;
import com.gmail.zpectremc.java.spectretdm.utils.Utility;

import net.md_5.bungee.api.ChatColor;

public class JoinCommand extends Argument {

	public JoinCommand() {
		super("join");
		setAliases("j");
		setPermNode("tdm.join");
		setArgParams("<arenaName>");
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
		if (ArenaManager.getInstance().getByName(args[0]) == null) {
			sender.sendMessage(ChatColor.RED + "An arena with the name " + args[0] + " does not exist.");
			return;
		}
		Arena a = ArenaManager.getInstance().getByName(args[0]);
		if (a.addPlayer(Utility.convertToOLP((OfflinePlayer) sender)))
			sender.sendMessage(ChatColor.GREEN + "You have joined arena " + args[0] + "! (" + a.getPlayers().size() + "/"
					+ a.getMaxPlayers() + ")");

	}

}
