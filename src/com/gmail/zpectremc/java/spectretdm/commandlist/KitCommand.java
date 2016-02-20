package com.gmail.zpectremc.java.spectretdm.commandlist;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.zpectremc.java.spectretdm.arenas.Arena;
import com.gmail.zpectremc.java.spectretdm.arenas.ArenaManager;
import com.gmail.zpectremc.java.spectretdm.commands.Argument;
import com.gmail.zpectremc.java.spectretdm.kits.KitManager;

public class KitCommand extends Argument {

	public KitCommand() {
		super("kit");
		setAliases("k");
		setPermNode("tdm.kit");
		setArgParams("<kitName>");
		setUsage(getDefaultUsage());
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		if (!hasPermission(sender)) {
			noPermissionCmd(sender);
			return;
		}
		if (args.length > getArgParams().length) {
			tooManyArgs(sender);
			return;
		}
		if (ArenaManager.getInstance().getByPlayer((Player) sender) == null) {
			sender.sendMessage("You are not in an arena!");
			return;
		}

		Arena isInThisArena = ArenaManager.getInstance().getByPlayer((Player) sender);

		if (args.length == 0) {
			sender.sendMessage("Opening kit GUI...");
			// TODO open kit gui
			return;
		}
		if (KitManager.getInstance().getByName(args[0]) == null) {
			sender.sendMessage("Sorry, but that kit does not exist!");
			return;
		}
		isInThisArena.getPlayers().get((OfflinePlayer) sender).setKit(KitManager.getInstance().getByName(args[0]));
		sender.sendMessage("Successfully changed kit to " + args[0]);
		return;
	}

}
