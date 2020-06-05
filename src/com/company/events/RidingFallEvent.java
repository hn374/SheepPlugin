package com.company.events;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class RidingFallEvent implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if((event.getEntity() instanceof Player)){ // Player
            Player player = (Player) event.getEntity();

            try {
                if(player.isInsideVehicle()) {
                    if(event.getCause() == EntityDamageEvent.DamageCause.FALL){
                        event.setCancelled(true);
                    }
                    if(player.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL){
                        event.setCancelled(true);
                    }
                }
            } catch(Exception e) {
                // Ignore error
            }
        } else if (event.getEntity() instanceof Sheep) { // Sheep
            Sheep sheep = (Sheep) event.getEntity();

            try {
                if (sheep.getPassenger() != null) {
                    if (event.getCause() == EntityDamageEvent.DamageCause.FALL){
                        event.setCancelled(true);
                    }
                    if (sheep.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL){
                        event.setCancelled(true);
                    }
                }
            } catch(Exception e) {
                // Ignore error
            }
        } else if (event.getEntity() instanceof Pig) { // Pig
            Pig pig = (Pig) event.getEntity();

            try {
                if (pig.getPassenger() != null) {
                    if (event.getCause() == EntityDamageEvent.DamageCause.FALL){
                        event.setCancelled(true);
                    }
                    if (pig.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL){
                        event.setCancelled(true);
                    }
                }
            } catch(Exception e) {
                // Ignore error
            }
        } else if (event.getEntity() instanceof Cow) { // Cow
            Cow cow = (Cow) event.getEntity();

            try {
                if (cow.getPassenger() != null) {
                    if (event.getCause() == EntityDamageEvent.DamageCause.FALL){
                        event.setCancelled(true);
                    }
                    if (cow.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL){
                        event.setCancelled(true);
                    }
                }
            } catch(Exception e) {
                // Ignore error
            }
        } else if (event.getEntity() instanceof Bat) { // Bat
            Bat bat = (Bat) event.getEntity();

            try {
                if (bat.getPassenger() != null) {
                    if (event.getCause() == EntityDamageEvent.DamageCause.FALL){
                        event.setCancelled(true);
                    }
                    if (bat.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL){
                        event.setCancelled(true);
                    }
                }
            } catch(Exception e) {
                // Ignore error
            }
        } else if (event.getEntity() instanceof Zombie) { // Zombie
            Zombie zombie = (Zombie) event.getEntity();

            try {
                if (zombie.getPassenger() != null) {
                    if (event.getCause() == EntityDamageEvent.DamageCause.FALL){
                        event.setCancelled(true);
                    }
                    if (zombie.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL){
                        event.setCancelled(true);
                    }
                }
            } catch(Exception e) {
                // Ignore error
            }
        } else if (event.getEntity() instanceof Slime) { // Slime
            Slime slime = (Slime) event.getEntity();

            try {
                if (slime.getPassenger() != null) {
                    if (event.getCause() == EntityDamageEvent.DamageCause.FALL){
                        event.setCancelled(true);
                    }
                    if (slime.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL){
                        event.setCancelled(true);
                    }
                }
            } catch(Exception e) {
                // Ignore error
            }
        }
    }
}

