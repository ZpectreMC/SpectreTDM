package com.gmail.zpectremc.java.spectretdm.kits.freekits;

import org.bukkit.Material;

import com.gmail.zpectremc.java.spectretdm.kits.Kit;
import com.gmail.zpectremc.java.spectretdm.utils.Utility;

public class Medic extends Kit{

	//TODO give player assigned gun
	
	public Medic() {
		super("Medic", 0, Utility.createStack(Material.INK_SACK, 90, (byte) 1, "&6&l5.56x45mm NATO", "Ammo for HK MP5"));
	}
}
