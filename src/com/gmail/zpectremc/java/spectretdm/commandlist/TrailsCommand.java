package com.gmail.zpectremc.java.spectretdm.commandlist;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.zpectremc.java.spectretdm.commands.Argument;
import com.gmail.zpectremc.java.spectretdm.gui.GunTrailGUI;

public class TrailsCommand extends Argument {

	private GunTrailGUI gui;

	public TrailsCommand() {
		super("trail");
		setAliases("t");
		setPermNode("tdm.trails");
		setUsage(getDefaultUsage());
		gui = new GunTrailGUI();
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
		gui.open((Player) sender);

	}

}
