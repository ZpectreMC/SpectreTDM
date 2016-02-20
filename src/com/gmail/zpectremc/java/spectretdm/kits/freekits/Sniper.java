package com.gmail.zpectremc.java.spectretdm.kits.freekits;

import org.bukkit.Material;

import com.gmail.zpectremc.java.spectretdm.kits.Kit;
import com.gmail.zpectremc.java.spectretdm.utils.Utility;

public class Sniper extends Kit{

	//TODO give player assigned gun
	
	public Sniper() {
		super("Sniper", 0, Utility.createStack(Material.INK_SACK, 90, (byte) 3, "&6&l7.62×51mm NATO", "Ammo for SV-98"));
	}
}
