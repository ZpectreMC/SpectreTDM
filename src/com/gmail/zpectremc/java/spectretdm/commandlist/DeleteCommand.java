package com.gmail.zpectremc.java.spectretdm.commandlist;

import org.bukkit.command.CommandSender;

import com.gmail.zpectremc.java.spectretdm.arenas.ArenaManager;
import com.gmail.zpectremc.java.spectretdm.commands.Argument;

import net.md_5.bungee.api.ChatColor;

public class DeleteCommand extends Argument {

	public DeleteCommand() {
		super("delete");
		setAliases("d");
		setPermNode("tdm.delete");
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
		ArenaManager.getInstance().deleteArena(args[0]);
		;
		sender.sendMessage(ChatColor.GREEN + "Successfuly deleted arena " + args[0] + "!");

	}

}
