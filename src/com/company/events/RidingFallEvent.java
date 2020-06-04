package com.company.events;

import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class RidingFallEvent implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if((event.getEntity() instanceof Player)){
            Player player = (Player) event.getEntity();

            if(player.isInsideVehicle()) {
                if(event.getCause() == EntityDamageEvent.DamageCause.FALL){
                    event.setCancelled(true);
                }
                if(player.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL){
                    event.setCancelled(true);
                }
            }
        } else if (event.getEntity() instanceof Sheep) {
            Sheep sheep = (Sheep) event.getEntity();

            if (sheep.getPassenger() != null) {
                if (event.getCause() == EntityDamageEvent.DamageCause.FALL){
                    event.setCancelled(true);
                }
                if (sheep.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL){
                    event.setCancelled(true);
                }
            }
        } else if (event.getEntity() instanceof Pig) {
            Pig pig = (Pig) event.getEntity();

            if (pig.getPassenger() != null) {
                if (event.getCause() == EntityDamageEvent.DamageCause.FALL){
                    event.setCancelled(true);
                }
                if (pig.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL){
                    event.setCancelled(true);
                }
            }
        }

    }
}

