package me.darkwinged.Autosell.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.darkwinged.Autosell.Main;
import me.darkwinged.Autosell.Utils.Utils;

public class cmd_Sellall implements CommandExecutor {
	
	private Main plugin;
	public cmd_Sellall(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sellall")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Utils.chat("&cYou can not do this in console."));
				return true;
			}
			Player player = (Player)sender;
			if (player.hasPermission("GAutosell.Sellall")) {
				double amount = 1;
    			for (int i = 0; i <= 36; i++) {
    				ItemStack item = player.getInventory().getItem(i);
    				if (item == null) continue;
    				if (plugin.WorthFile.getConfig().isDouble("worth." + item.getType().name())) {
    					amount += plugin.WorthFile.getConfig().getDouble("worth." + item.getType().name()) * item.getAmount();
    					item.setAmount(0);
    				}
                }
    			plugin.getEconomy().depositPlayer(player, amount);
			}
		}
		return false;
	}
	

}
