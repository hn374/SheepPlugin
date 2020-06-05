package com.company.commands;

import com.company.SheepPlugin;
import com.company.customEntities.CustomZombie;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.PathEntity;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;

public class BabyZombieCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playerName = player.getDisplayName();

            Location spawnLocation = player.getLocation().add(2, 0,0);
//            World world = player.getWorld();

            Zombie zombie = CustomZombie.spawn(spawnLocation);
            zombie.setBaby(true);
            zombie.setCustomName(playerName + "\'s Baby Zombie");
            zombie.setCustomNameVisible(true);

            player.sendMessage("Congratulations, you have a new pet baby zombie!");

            followPlayer(player, zombie);

            return true;
        } else {
            System.out.println("Must be a player to call this command.");
            return false;
        }
    }

    public void followPlayer(Player player, Entity zombie) {
        System.out.println("The follow player command has been called.");
        new BukkitRunnable() {

            @Override
            public void run() {
                PathEntity path;

                if(!zombie.isValid() || !player.isOnline()) {
                    this.cancel();
                    return;
                }

                Object zombieObject = ((CraftEntity) zombie).getHandle();

                Location location = player.getLocation();

                path = ((EntityInsentient) zombieObject).getNavigation().a(location.getX() + 1, location.getY(), location.getZ() + 1);

                if(path != null) {
                    ((EntityInsentient) zombieObject).getNavigation().a(path, 1.0D);
                    ((EntityInsentient) zombieObject).getNavigation().a(2.0D);
                }

                int distance = (int) location.distance(zombie.getLocation());

                if(distance > 10 && !zombie.isDead()) {
                    zombie.teleport(location);
                }
            }
        }.runTaskTimerAsynchronously(SheepPlugin.getInstance(), 0L, 20L);
    }
}
