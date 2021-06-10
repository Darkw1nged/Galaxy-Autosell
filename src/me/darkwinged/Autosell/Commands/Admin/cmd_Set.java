package me.darkwinged.Autosell.Commands.Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.darkwinged.Autosell.Main;

public class cmd_Set implements CommandExecutor {
	
	private Main plugin;
	public cmd_Set(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (cmd.getName().equalsIgnoreCase("set")) {
			if (!(sender instanceof Player)) {
				if (args[0].equalsIgnoreCase("")) {
					
				}
			}
		}
		return false;
	}
	

}
