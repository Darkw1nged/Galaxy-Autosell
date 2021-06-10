package me.darkwinged.Autosell.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.darkwinged.Autosell.Main;
import me.darkwinged.Autosell.Utils.Utils;

public class cmd_Autoblock implements CommandExecutor {
	
	private Main plugin;
	public cmd_Autoblock(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (cmd.getName().equalsIgnoreCase("autoblock")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Utils.chat("&cError! You can not do this command in cosole."));
				return true;
			}
			Player player = (Player)sender;
			if (player.hasPermission("GAutosell.Autoblock")) {
				if (plugin.AutoBlock.contains(player.getUniqueId())) {
					player.sendMessage(Utils.chat("&cYou have successfully disabled Autoblock"));
					plugin.AutoBlock.remove(player.getUniqueId());
					return true;
				}
				player.sendMessage(Utils.chat("&aYou have successfully enabled Autoblock"));
				plugin.AutoBlock.add(player.getUniqueId());
			}
		}
		return false;
	}	

}
