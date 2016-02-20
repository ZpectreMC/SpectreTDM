package com.gmail.zpectremc.java.spectretdm.kits.vipkits;

import org.bukkit.Material;

import com.gmail.zpectremc.java.spectretdm.kits.Kit;
import com.gmail.zpectremc.java.spectretdm.utils.Utility;

public class Engineer extends Kit{
	
	//TODO give player assigned gun
	
	public Engineer() {
		super("Engineer", 0, Utility.createStack(Material.INK_SACK, 90, (byte) 4, "&6&l12 Gauge", "Ammo for Remington 870"));
	}
}
