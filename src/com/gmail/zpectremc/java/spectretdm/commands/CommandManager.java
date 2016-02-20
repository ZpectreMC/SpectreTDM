package com.gmail.zpectremc.java.spectretdm.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.gmail.zpectremc.java.spectretdm.commandlist.CreateCommand;
import com.gmail.zpectremc.java.spectretdm.commandlist.DeleteCommand;
import com.gmail.zpectremc.java.spectretdm.commandlist.JoinCommand;
import com.gmail.zpectremc.java.spectretdm.commandlist.KitCommand;
import com.gmail.zpectremc.java.spectretdm.commandlist.LeaveCommand;
import com.gmail.zpectremc.java.spectretdm.commandlist.ReloadCommand;
import com.gmail.zpectremc.java.spectretdm.commandlist.SetCommand;
import com.gmail.zpectremc.java.spectretdm.commandlist.TrailsCommand;

public class CommandManager implements CommandExecutor {

	private List<Argument> arguments = Arrays.asList(new CreateCommand(), new DeleteCommand(), new JoinCommand(),
			new LeaveCommand(), new ReloadCommand(), new SetCommand(), new TrailsCommand(), new KitCommand());

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!cmd.getName().equalsIgnoreCase("stdm"))
			return false;
		if (args.length == 0) {
			for (Argument a : arguments)
				sender.sendMessage("/" + cmd.getName() + " " + a.getName().toUpperCase());
			return true;
		}
		for (Argument a : arguments) {
			if (a.isCommand(args[0])) {
				a.onCommand(sender, a.removeFirst(args));
				return true;
			}
		}
		sender.sendMessage("Incorrect argument \"" + args[0] + "\"");
		return true;
	}

}
