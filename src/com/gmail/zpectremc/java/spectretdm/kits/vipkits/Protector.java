package com.gmail.zpectremc.java.spectretdm.kits.vipkits;

import org.bukkit.Material;

import com.gmail.zpectremc.java.spectretdm.kits.Kit;
import com.gmail.zpectremc.java.spectretdm.utils.Utility;

public class Protector extends Kit{
	
	public Protector() {
		super("Protector", 0, Utility.createStack(Material.INK_SACK, 90, (byte) 3, "&6&l7.62×51mm NATO", "Ammo for SCAR-H"));
	}
}
