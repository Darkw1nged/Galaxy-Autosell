package me.darkwinged.Autosell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.darkwinged.Autosell.Commands.cmd_Autoblock;
import me.darkwinged.Autosell.Commands.cmd_Autosell;
import me.darkwinged.Autosell.Commands.cmd_Autosmelt;
import me.darkwinged.Autosell.Commands.cmd_Sellall;
import me.darkwinged.Autosell.Utils.CustomConfig;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	private static Economy econ = null;
	public CustomConfig WorthFile = new CustomConfig(this, "worth");
	public List<UUID> AutoSmelt = new ArrayList<>();
	public List<UUID> AutoBlock = new ArrayList<>();
	public List<UUID> AutoSell = new ArrayList<>();
	
	public List<Integer> Global_Booster_Time = new ArrayList<>();
	public List<Integer> Global_Booster_Multipler = new ArrayList<>();
	public Map<UUID, Integer> Player_Booster_Time = new HashMap<>();
	public Map<UUID, Integer> Player_Booster_Multiplier = new HashMap<>();
	
	
	public void onEnable() {
		if (!setupEconomy() ) {
            getServer().getConsoleSender().sendMessage(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
		WorthFile.saveDefaultConfig();
		loadCommands();
		AutoSmeltCheck();
		AutoBlockCheck();
		AutoSellCheck();
	}
	
	public void loadCommands() {
		getCommand("autosmelt").setExecutor(new cmd_Autosmelt(this));
		getCommand("autoblock").setExecutor(new cmd_Autoblock(this));
		getCommand("autosell").setExecutor(new cmd_Autosell(this));
		getCommand("sellall").setExecutor(new cmd_Sellall(this));
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	public Economy getEconomy() {
        return econ;
    }
	
	public void AutoSmeltCheck() {
		new BukkitRunnable() {
            public void run() {
            	for (Player online : Bukkit.getOnlinePlayers()) {
            		if (AutoSmelt.contains(online.getUniqueId())) {
            			for (int i = 0; i <= 36; i++) {
            				ItemStack item = online.getInventory().getItem(i);
            				if (item == null) return;
            				if (item.getType().equals(Material.GOLD_ORE)) {
            					item.setType(Material.GOLD_INGOT);
            				} else if (item.getType().equals(Material.IRON_ORE)) {
            					item.setType(Material.IRON_INGOT);
            				} else if (item.getType().equals(Material.NETHER_GOLD_ORE)) {
            					item.setType(Material.GOLD_INGOT);
            				}
                        }
            		}
                }
            }
        }.runTaskTimer(this, 0L, 20L);
	}
	public void AutoBlockCheck() {
		new BukkitRunnable() {
            public void run() {
            	for (Player online : Bukkit.getOnlinePlayers()) {
            		if (AutoBlock.contains(online.getUniqueId())) {
            			for (int i = 0; i <= 36; i++) {
            				ItemStack item = online.getInventory().getItem(i);
            				if (item == null) return;
            				if (item.getType().equals(Material.COAL) && item.getAmount() >= 9) {
            					item.setAmount(item.getAmount() - 9);
            					ItemStack block = new ItemStack(Material.COAL_BLOCK);
            					online.getInventory().addItem(block);
            				} else if (item.getType().equals(Material.IRON_INGOT) && item.getAmount() >= 9) {
            					item.setAmount(item.getAmount() - 9);
            					ItemStack block = new ItemStack(Material.IRON_BLOCK);
            					online.getInventory().addItem(block);
            				} else if (item.getType().equals(Material.GOLD_INGOT) && item.getAmount() >= 9) {
            					item.setAmount(item.getAmount() - 9);
            					ItemStack block = new ItemStack(Material.GOLD_BLOCK);
            					online.getInventory().addItem(block);
            				} else if (item.getType().equals(Material.DIAMOND) && item.getAmount() >= 9) {
            					item.setAmount(item.getAmount() - 9);
            					ItemStack block = new ItemStack(Material.DIAMOND_BLOCK);
            					online.getInventory().addItem(block);
            				} else if (item.getType().equals(Material.EMERALD) && item.getAmount() >= 9) {
            					item.setAmount(item.getAmount() - 9);
            					ItemStack block = new ItemStack(Material.EMERALD_BLOCK);
            					online.getInventory().addItem(block);
            				} else if (item.getType().equals(Material.LAPIS_LAZULI) && item.getAmount() >= 9) {
            					item.setAmount(item.getAmount() - 9);
            					ItemStack block = new ItemStack(Material.LAPIS_BLOCK);
            					online.getInventory().addItem(block);
            				} else if (item.getType().equals(Material.REDSTONE) && item.getAmount() >= 9) {
            					item.setAmount(item.getAmount() - 9);
            					ItemStack block = new ItemStack(Material.REDSTONE_BLOCK);
            					online.getInventory().addItem(block);
            				} else if (item.getType().equals(Material.NETHERITE_INGOT) && item.getAmount() >= 9) {
            					item.setAmount(item.getAmount() - 9);
            					ItemStack block = new ItemStack(Material.NETHERITE_BLOCK);
            					online.getInventory().addItem(block);
            				}
                        }
            		}
                }
            }
        }.runTaskTimer(this, 0L, 20L);
	}
	public void AutoSellCheck() {
		new BukkitRunnable() {
            public void run() {
            	for (Player online : Bukkit.getOnlinePlayers()) {
            		if (AutoSell.contains(online.getUniqueId())) {
            			double amount = 1;
            			for (int i = 0; i <= 36; i++) {
            				ItemStack item = online.getInventory().getItem(i);
            				if (item == null) continue;
            				if (WorthFile.getConfig().isDouble("worth." + item.getType().name())) {
            					amount += WorthFile.getConfig().getDouble("worth." + item.getType().name()) * item.getAmount();
            					item.setAmount(0);
            				}
                        }
            			if (!Global_Booster_Multipler.isEmpty()) {
            				if (Player_Booster_Multiplier.containsKey(online.getUniqueId())) {
                				getEconomy().depositPlayer(online, amount * Player_Booster_Multiplier.get(online.getUniqueId()) * Global_Booster_Multipler.get(0));
                				return;
                			} else {
                				getEconomy().depositPlayer(online, amount * Global_Booster_Multipler.get(0));
                			}
            				return;
            			}            			
            			if (Player_Booster_Multiplier.containsKey(online.getUniqueId())) {
            				getEconomy().depositPlayer(online, amount * Player_Booster_Multiplier.get(online.getUniqueId()));
            				return;
            			}
            			getEconomy().depositPlayer(online, amount);
            		}
                }
            }
        }.runTaskTimer(this, 0L, 20L);
	}
	public void Boosters() {
		new BukkitRunnable() {
            public void run() {
            	for (Player online : Bukkit.getOnlinePlayers()) {
            		if (Player_Booster_Time.containsKey(online.getUniqueId())) {
            			
            		}
                }
            }
        }.runTaskTimer(this, 0L, 20L);
	}

}
