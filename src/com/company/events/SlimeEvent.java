package com.company.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class SlimeEvent implements Listener {

    @EventHandler
    public void SlimeRightClick(PlayerInteractEntityEvent event) {

        if (event.getRightClicked().getType() == EntityType.SLIME) {
            Player player = (Player) event.getPlayer();
            Entity slime = (Entity) event.getRightClicked();

            // Set the passenger to be the player
            slime.setPassenger(player);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity player = event.getEntity();
        if (player instanceof Player) {
            event.setCancelled(true);
        }
    }
}
