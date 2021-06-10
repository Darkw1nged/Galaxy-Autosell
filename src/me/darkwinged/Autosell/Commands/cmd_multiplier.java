package me.darkwinged.Autosell.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.darkwinged.Autosell.Main;
import me.darkwinged.Autosell.Utils.Utils;

public class cmd_multiplier implements CommandExecutor {
	
	private Main plugin;
	public cmd_multiplier(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (cmd.getName().equalsIgnoreCase("multiplier")) {
			if (!(sender instanceof Player)) {
				if (args.length != 3) {
					sender.sendMessage(Utils.chat("&cError! Usage: /multiplier [give|remove] [player|global] [time]"));
					return true;
				}
				if (args[0].equalsIgnoreCase("give")) {
					Player target = Bukkit.getPlayer(args[0]);
					if (target == null) {
						sender.sendMessage(Utils.chat("&cError! Sorry I could not find that player."));
						return true;
					}
					if (args[2].equalsIgnoreCase("-1")) {
						
					}
				}
				return true;
			}
			Player player = (Player)sender;
			if (player.hasPermission("GAutosell.Multiplier")) {
				
			}
		}
		return false;
	}

}
