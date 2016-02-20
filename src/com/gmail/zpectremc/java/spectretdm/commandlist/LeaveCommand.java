package com.gmail.zpectremc.java.spectretdm.commandlist;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.zpectremc.java.spectretdm.arenas.Arena;
import com.gmail.zpectremc.java.spectretdm.arenas.ArenaManager;
import com.gmail.zpectremc.java.spectretdm.commands.Argument;

import net.md_5.bungee.api.ChatColor;

public class LeaveCommand extends Argument {

	public LeaveCommand() {
		super("leave");
		setAliases("l");
		setPermNode("tdm.leave");
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
		sender.sendMessage(ChatColor.GREEN + "Successfuly left arena "
				+ ArenaManager.getInstance().getByPlayer((Player) sender).getName() + "!");
		for (Arena a : ArenaManager.getInstance().getAllArenas())
			a.removePlayer((OfflinePlayer) sender);

	}

}
