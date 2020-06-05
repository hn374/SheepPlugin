package com.company.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ZombieEvent implements Listener {

    @EventHandler
    public void ZombieRightClick(PlayerInteractEntityEvent event) {

        if (event.getRightClicked().getType() == EntityType.ZOMBIE) {
            Player player = (Player) event.getPlayer();
            Entity zombie = (Entity) event.getRightClicked();

            // Set the passenger to be the player
            zombie.setPassenger(player);
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
