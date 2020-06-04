package com.company.commands;

import com.company.customEntities.CustomSheep;
import com.company.SheepPlugin;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.scheduler.BukkitRunnable;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.PathEntity;

public class SheepCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playerName = player.getDisplayName();

            Location spawnLocation = player.getLocation().add(2, 0,0);
            World world = player.getWorld();

//            Sheep sheep = (Sheep) world.spawnEntity(spawnLocation, EntityType.SHEEP);
//            sheep.setCustomName(playerName + "\'s Sheep");
//            sheep.setCustomNameVisible(true);

            Sheep sheep = CustomSheep.spawn(spawnLocation);
            sheep.setCustomName(playerName + "\'s Sheep");
            sheep.setCustomNameVisible(true);

            player.sendMessage("Congratulations, you have a new pet sheep!");

            followPlayer(player, (Entity) sheep);

            return true;
        } else {
            System.out.println("Must be a player to call this command.");
            return false;
        }
    }

    public void followPlayer(Player player, Entity sheep) {
        System.out.println("The follow player command has been called.");
        new BukkitRunnable() {

            @Override
            public void run() {
                PathEntity path;

                if(!sheep.isValid() || !player.isOnline()) {
                    this.cancel();
                    return;
                }

                Object sheepObject = ((CraftEntity) sheep).getHandle();

                Location location = player.getLocation();

                path = ((EntityInsentient) sheepObject).getNavigation().a(location.getX() + 1, location.getY(), location.getZ() + 1);

                if(path != null) {
                    ((EntityInsentient) sheepObject).getNavigation().a(path, 1.0D);
                    ((EntityInsentient) sheepObject).getNavigation().a(2.0D);
                }

                int distance = (int) location.distance(sheep.getLocation());

                if(distance > 10 && !sheep.isDead()) {
                    sheep.teleport(location);
                }
            }
        }.runTaskTimerAsynchronously(SheepPlugin.getInstance(), 0L, 20L);
    }
}
