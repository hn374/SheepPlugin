package com.company.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class BatEvent implements Listener {

    @EventHandler
    public void BatRightClick(PlayerInteractEntityEvent event) {

        if (event.getRightClicked().getType() == EntityType.BAT) {
            Player player = (Player) event.getPlayer();
            Entity bat = (Entity) event.getRightClicked();

            // Set the passenger to be the player
            bat.setPassenger(player);
        }
    }
}
