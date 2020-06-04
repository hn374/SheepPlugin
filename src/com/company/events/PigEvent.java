package com.company.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PigEvent implements Listener {

    @EventHandler
    public void PigRightClick(PlayerInteractEntityEvent event) {

        if (event.getRightClicked().getType() == EntityType.PIG) {
            Player player = (Player) event.getPlayer();
            Entity pig = (Entity) event.getRightClicked();

            // Set the passenger to be the player
            pig.setPassenger(player);
        }
    }
}
