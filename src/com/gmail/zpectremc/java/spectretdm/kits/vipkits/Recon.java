package com.gmail.zpectremc.java.spectretdm.kits.vipkits;

import org.bukkit.Material;

import com.gmail.zpectremc.java.spectretdm.kits.Kit;
import com.gmail.zpectremc.java.spectretdm.utils.Utility;

public class Recon extends Kit{
	
	//TODO give player assigned gun
	
	public Recon() {
		super("Recon", 0, Utility.createStack(Material.INK_SACK, 90, (byte) 5, "&6&l.45 ACP", "Ammo for Tactical M1911"));
	}
}
