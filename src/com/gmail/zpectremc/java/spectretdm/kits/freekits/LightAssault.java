package com.gmail.zpectremc.java.spectretdm.kits.freekits;

import org.bukkit.Material;

import com.gmail.zpectremc.java.spectretdm.kits.Kit;
import com.gmail.zpectremc.java.spectretdm.utils.Utility;

public class LightAssault extends Kit {

	// TODO give player assigned gun

	public LightAssault() {
		super("LightAssault", 0);
		setItems(Utility.getGun("HK416E5"),
				Utility.createStack(Material.INK_SACK, 90, 1, "&6&l5.56x45mm NATO", "Ammo for HK416E5"));
	}
}
