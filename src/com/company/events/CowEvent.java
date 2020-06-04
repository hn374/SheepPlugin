package com.company.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class CowEvent implements Listener {

    @EventHandler
    public void CowRightClick(PlayerInteractEntityEvent event) {

        if (event.getRightClicked().getType() == EntityType.COW) {
            Player player = (Player) event.getPlayer();
            Entity cow = (Entity) event.getRightClicked();

            // Set the passenger to be the player
            cow.setPassenger(player);
        }
    }
}
