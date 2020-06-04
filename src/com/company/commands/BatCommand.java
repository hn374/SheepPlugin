package com.company.commands;

import com.company.SheepPlugin;
import com.company.customEntities.CustomBat;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.PathEntity;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftBat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playerName = player.getDisplayName();

            Location spawnLocation = player.getLocation().add(2, 0,0);
//            World world = player.getWorld();

            Bat bat = CustomBat.spawn(spawnLocation);
//            ((CraftBat) bat).getHandle().k(true);
            bat.setCustomName(playerName + "\'s Bat");
            bat.setCustomNameVisible(true);

            player.sendMessage("Congratulations, you have a new pet bat!");

            followPlayer(player, bat);

            return true;
        } else {
            System.out.println("Must be a player to call this command.");
            return false;
        }
    }

    public void followPlayer(Player player, Entity bat) {
        System.out.println("The follow player command has been called.");
        new BukkitRunnable() {

            @Override
            public void run() {
                PathEntity path;

                if(!bat.isValid() || !player.isOnline()) {
                    this.cancel();
                    return;
                }

                Object batObject = ((CraftEntity) bat).getHandle();

                Location location = player.getLocation();

                path = ((EntityInsentient) batObject).getNavigation().a(location.getX() + 1, location.getY(), location.getZ() + 1);

                if(path != null) {
                    ((EntityInsentient) batObject).getNavigation().a(path, 1.0D);
                    ((EntityInsentient) batObject).getNavigation().a(2.0D);
                }

                int distance = (int) location.distance(bat.getLocation());

                if(distance > 10 && !bat.isDead()) {
                    bat.teleport(location);
                }
            }
        }.runTaskTimerAsynchronously(SheepPlugin.getInstance(), 0L, 20L);
    }
}
