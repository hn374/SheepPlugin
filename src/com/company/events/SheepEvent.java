package com.company.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class SheepEvent implements Listener {

    @EventHandler
    public void SheepRightClick(PlayerInteractEntityEvent event) {

        if (event.getRightClicked().getType() == EntityType.SHEEP) {
            // Get the player and sheep that was right clicked
            Player player = (Player) event.getPlayer();
            Entity sheep = (Entity) event.getRightClicked();

            // Set the passenger to be the player
            sheep.setPassenger(player);
        }
    }
}
