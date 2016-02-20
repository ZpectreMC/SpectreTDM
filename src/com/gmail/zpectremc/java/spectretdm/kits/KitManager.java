package com.gmail.zpectremc.java.spectretdm.kits;

import java.util.Arrays;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.zpectremc.java.spectretdm.kits.freekits.HeavyAssault;
import com.gmail.zpectremc.java.spectretdm.kits.freekits.LightAssault;
import com.gmail.zpectremc.java.spectretdm.kits.freekits.Medic;
import com.gmail.zpectremc.java.spectretdm.kits.freekits.Sniper;
import com.gmail.zpectremc.java.spectretdm.kits.vipkits.Demolitionist;
import com.gmail.zpectremc.java.spectretdm.kits.vipkits.Engineer;
import com.gmail.zpectremc.java.spectretdm.kits.vipkits.Protector;
import com.gmail.zpectremc.java.spectretdm.kits.vipkits.Pyromaniac;
import com.gmail.zpectremc.java.spectretdm.kits.vipkits.Recon;
import com.gmail.zpectremc.java.spectretdm.utils.Utility;

public class KitManager {

	private static KitManager instance;
	private List<Kit> kits;

	private KitManager() {
		kits = Arrays.asList(new HeavyAssault(), new LightAssault(), new Medic(), new Sniper(), new Demolitionist(),
				new Engineer(), new Protector(), new Pyromaniac(), new Recon());
	}

	public static KitManager getInstance() {
		if (instance == null)
			instance = new KitManager();
		return instance;
	}

	public List<Kit> getAllKits() {
		return kits;
	}

	public Kit getByName(String kitName) {
		for (Kit k : getAllKits()) {
			if (k.getName().equalsIgnoreCase(kitName))
				return k;
		}
		return null;
	}

	public void giveKit(OfflinePlayer op, Kit kit) {
		if (op == null)
			return;
		Player p = (Player) op;
		for (ItemStack item : p.getInventory().getContents())
			Utility.setArmor(op, item);
		p.getInventory().setContents(kit.items);
	}

}
