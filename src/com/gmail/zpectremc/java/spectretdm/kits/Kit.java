package com.gmail.zpectremc.java.spectretdm.kits;

import org.bukkit.inventory.ItemStack;

public abstract class Kit {

	String name;
	ItemStack[] items;
	int cost;

	public Kit(String name, int cost, ItemStack... items) {
		this.name = name;
		this.items = items;
		this.cost = cost;
	}

	public Kit(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	public void setItems(ItemStack... is) {
		this.items = is;
	}

	public boolean isKit(String kit) {
		if (kit.equalsIgnoreCase(getName()))
			return true;
		return false;
	}

}