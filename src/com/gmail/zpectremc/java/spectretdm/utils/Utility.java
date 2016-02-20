package com.gmail.zpectremc.java.spectretdm.utils;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shampaggon.crackshot.CSUtility;

import net.md_5.bungee.api.ChatColor;

public class Utility {

	private Utility() {
	}

	private static CSUtility cs = new CSUtility();

	@SuppressWarnings("deprecation")
	public static ItemStack createStack(Material material, int amount, int data, String name, String... lore) {
		ItemStack is = new ItemStack(material, amount, material.getMaxDurability(), (byte) data);
		ItemMeta meta = is.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		for (int i = 0; i < lore.length; i++) {
			lore[i] = ChatColor.translateAlternateColorCodes('&', lore[i]);
		}
		List<String> newLore = Arrays.asList(lore);
		meta.setLore(newLore);
		is.setItemMeta(meta);
		return is;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack createStack(Material material, int amount, short damage, int data, String name,
			String... lore) {
		ItemStack is = new ItemStack(material, amount, damage, (byte) data);
		is.getItemMeta().setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		List<String> newLore = Arrays.asList(lore);
		for (int i = 0; i < newLore.size(); i++) {
			is.getItemMeta().getLore().add(ChatColor.translateAlternateColorCodes('&', newLore.get(i)));
		}
		is.getItemMeta().setLore(newLore);
		return is;
	}

	public static ItemStack getGun(String s) {
		if (cs.generateWeapon(s) == null) {
			return createStack(Material.PISTON_EXTENSION, 1, 0, "&cThis gun is broke ¯\\_(ツ)_/¯ whoops.");
		}
		return cs.generateWeapon(s);
	}

	public static Player convertToOLP(OfflinePlayer op) {
		Player converted = (Player) op;
		return converted;
	}

	@SuppressWarnings("deprecation")
	public static void setArmor(OfflinePlayer op, ItemStack item) {
		int i = 298, i2 = 302, i3 = 306, i4 = 310, i5 = 314;
		int armorIteration = 0;
		Player p = (Player) op;
		for (int armorTypes = 0; armorTypes < 4; armorTypes++) {
			if (item.getTypeId() == i || item.getTypeId() == i2 || item.getTypeId() == i3 || item.getTypeId() == i4
					|| item.getTypeId() == i5) {
				switch (armorIteration) {
				case 0:
					p.getInventory().setHelmet(item);
				case 1:
					p.getInventory().setChestplate(item);
				case 2:
					p.getInventory().setLeggings(item);
				case 3:
					p.getInventory().setBoots(item);
				}
			}
			i++;
			i2++;
			i3++;
			i4++;
			i5++;
			armorIteration++;
		}
	}

	public CSUtility getCSUtility() {
		return cs;
	}
}
