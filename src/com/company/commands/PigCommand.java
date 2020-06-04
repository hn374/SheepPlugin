package com.company.commands;

import com.company.SheepPlugin;
import com.company.customEntities.CustomPig;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.PathEntity;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PigCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playerName = player.getDisplayName();

            Location spawnLocation = player.getLocation().add(2, 0,0);
            World world = player.getWorld();

            Pig pig = CustomPig.spawn(spawnLocation);
            pig.setCustomName(playerName + "\'s Pig");
            pig.setCustomNameVisible(true);

            player.sendMessage("Congratulations, you have a new pet pig!");

            followPlayer(player, (Entity) pig);

            return true;
        } else {
            System.out.println("Must be a player to call this command.");
            return false;
        }
    }

    public void followPlayer(Player player, Entity pig) {
        System.out.println("The follow player command has been called.");
        new BukkitRunnable() {

            @Override
            public void run() {
                PathEntity path;

                if(!pig.isValid() || !player.isOnline()) {
                    this.cancel();
                    return;
                }

                Object pigObject = ((CraftEntity) pig).getHandle();

                Location location = player.getLocation();

                path = ((EntityInsentient) pigObject).getNavigation().a(location.getX() + 1, location.getY(), location.getZ() + 1);

                if(path != null) {
                    ((EntityInsentient) pigObject).getNavigation().a(path, 1.0D);
                    ((EntityInsentient) pigObject).getNavigation().a(2.0D);
                }

                int distance = (int) location.distance(pig.getLocation());

                if(distance > 10 && !pig.isDead()) {
                    pig.teleport(location);
                }
            }
        }.runTaskTimerAsynchronously(SheepPlugin.getInstance(), 0L, 20L);
    }
}
