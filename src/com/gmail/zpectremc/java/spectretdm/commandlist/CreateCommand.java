package com.gmail.zpectremc.java.spectretdm.commandlist;

import org.bukkit.command.CommandSender;

import com.gmail.zpectremc.java.spectretdm.arenas.ArenaManager;
import com.gmail.zpectremc.java.spectretdm.commands.Argument;

import net.md_5.bungee.api.ChatColor;

public class CreateCommand extends Argument {

	public CreateCommand() {
		super("create");
		setAliases("c");
		setPermNode("tdm.create");
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
		if (args.length > getArgParams().length){
			tooManyArgs(sender);
			return;
		}
		if (ArenaManager.getInstance().getByName(args[0]) != null) {
			sender.sendMessage(ChatColor.RED + "An arena with the name " + args[0] + " already exists.");
			return;
		}
		ArenaManager.getInstance().createArena(args[0]);
		sender.sendMessage(ChatColor.GREEN + "Successfuly created arena " + args[0] + "!");
	}

}
