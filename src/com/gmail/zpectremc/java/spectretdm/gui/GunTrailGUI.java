package com.gmail.zpectremc.java.spectretdm.gui;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gmail.zpectremc.java.spectretdm.SpectreTDM;
import com.gmail.zpectremc.java.spectretdm.guns.GunListener;
import com.gmail.zpectremc.java.spectretdm.utils.ParticleEffect.ParticleType;
import com.gmail.zpectremc.java.spectretdm.utils.Utility;

public class GunTrailGUI implements Listener {

	private Inventory inv;

	public GunTrailGUI() {
		List<ParticleType> unsafe = Arrays.asList(ParticleType.BARRIER, ParticleType.BLOCK_CRACK,
				ParticleType.BLOCK_DUST, ParticleType.ITEM_CRACK, ParticleType.ITEM_TAKE, ParticleType.SUSPENDED,
				ParticleType.MOB_APPEARANCE);
		int i = ParticleType.values().length - unsafe.size();
		while (i % 9 != 0)
			i++;
		inv = Bukkit.createInventory(null, i, "Gun Trails");
		for (ParticleType t : ParticleType.values()) {
			if (!unsafe.contains(t))
				inv.addItem(Utility.createStack(Material.STAINED_GLASS_PANE, 1, 0, t.toString(),
						"&fChoose this particle effect"));
		}
		Bukkit.getPluginManager().registerEvents(this, SpectreTDM.getInstance());
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if (e.getInventory().hashCode() != inv.hashCode())
			return;
		Player player = (Player) e.getWhoClicked();
		e.setCancelled(true);
		ItemStack i = e.getCurrentItem();
		if (i == null || i.getType() == Material.AIR)
			return;
		GunListener.pType = ParticleType.valueOf(i.getItemMeta().getDisplayName());
		player.sendMessage("Gun trail is now set to " + GunListener.pType.toString());
	}

	public void open(Player sender) {
		sender.openInventory(inv);
	}

}
