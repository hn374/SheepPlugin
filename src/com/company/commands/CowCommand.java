package com.company.commands;

import com.company.SheepPlugin;
import com.company.customEntities.CustomCow;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.PathEntity;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CowCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playerName = player.getDisplayName();

            Location spawnLocation = player.getLocation().add(2, 0,0);
            World world = player.getWorld();

            Cow cow = CustomCow.spawn(spawnLocation);
            cow.setCustomName(playerName + "\'s Cow");
            cow.setCustomNameVisible(true);

            player.sendMessage("Congratulations, you have a new pet cow!");

            followPlayer(player, (Entity) cow);

            return true;
        } else {
            System.out.println("Must be a player to call this command.");
            return false;
        }
    }

    public void followPlayer(Player player, Entity cow) {
        System.out.println("The follow player command has been called.");
        new BukkitRunnable() {

            @Override
            public void run() {
                PathEntity path;

                if(!cow.isValid() || !player.isOnline()) {
                    this.cancel();
                    return;
                }

                Object cowObject = ((CraftEntity) cow).getHandle();

                Location location = player.getLocation();

                path = ((EntityInsentient) cowObject).getNavigation().a(location.getX() + 1, location.getY(), location.getZ() + 1);

                if(path != null) {
                    ((EntityInsentient) cowObject).getNavigation().a(path, 1.0D);
                    ((EntityInsentient) cowObject).getNavigation().a(2.0D);
                }

                int distance = (int) location.distance(cow.getLocation());

                if(distance > 10 && !cow.isDead()) {
                    cow.teleport(location);
                }
            }
        }.runTaskTimerAsynchronously(SheepPlugin.getInstance(), 0L, 20L);
    }
}
