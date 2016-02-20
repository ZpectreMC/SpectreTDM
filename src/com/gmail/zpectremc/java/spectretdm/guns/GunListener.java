package com.gmail.zpectremc.java.spectretdm.guns;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSnowball;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.zpectremc.java.spectretdm.SpectreTDM;
import com.gmail.zpectremc.java.spectretdm.utils.ParticleEffect;
import com.gmail.zpectremc.java.spectretdm.utils.ParticleEffect.ParticleType;
import com.shampaggon.crackshot.events.WeaponShootEvent;

import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;

public class GunListener implements Listener {

	public static ParticleType pType = ParticleType.SMOKE_NORMAL;

	@EventHandler
	public void onShoot(WeaponShootEvent e) {
		final Entity proj = e.getProjectile();
		final Player player = e.getPlayer();
		final ParticleEffect effect = new ParticleEffect(pType, e.getProjectile().getVelocity().length() * 0, 2, 0);
		for (Player p : Bukkit.getOnlinePlayers()) {
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(
					new PacketPlayOutEntityDestroy(((CraftSnowball) e.getProjectile()).getHandle().getId()));
		}
		new BukkitRunnable() {

			@Override
			public void run() {
				if (!proj.isValid() || proj.getVelocity().length() == 0)
					this.cancel();
				if (!proj.getNearbyEntities(1, 1, 1).contains(player))
					effect.sendToLocation(proj.getLocation());
			}
		}.runTaskTimer(SpectreTDM.getInstance(), 0L, 1L);
	}
}
