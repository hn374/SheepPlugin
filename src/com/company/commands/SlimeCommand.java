package com.company.commands;

import com.company.SheepPlugin;
import com.company.customEntities.CustomSlime;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.PathEntity;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.scheduler.BukkitRunnable;

public class SlimeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playerName = player.getDisplayName();

            Location spawnLocation = player.getLocation().add(2, 0,0);

            Slime slime = CustomSlime.spawn(spawnLocation);
            slime.setCustomName(playerName + "\'s Slime");
            slime.setCustomNameVisible(true);

            player.sendMessage("Congratulations, you have a new pet slime!");

            followPlayer(player, slime);

            return true;
        } else {
            System.out.println("Must be a player to call this command.");
            return false;
        }
    }

    public void followPlayer(Player player, Entity slime) {
        System.out.println("The follow player command has been called.");
        new BukkitRunnable() {

            @Override
            public void run() {
                PathEntity path;

                if(!slime.isValid() || !player.isOnline()) {
                    this.cancel();
                    return;
                }

                Object slimeObject = ((CraftEntity) slime).getHandle();

                Location location = player.getLocation();

                path = ((EntityInsentient) slimeObject).getNavigation().a(location.getX() + 1, location.getY(), location.getZ() + 1);

                if(path != null) {
                    ((EntityInsentient) slimeObject).getNavigation().a(path, 1.0D);
                    ((EntityInsentient) slimeObject).getNavigation().a(2.0D);
                }

                int distance = (int) location.distance(slime.getLocation());

                if(distance > 10 && !slime.isDead()) {
                    slime.teleport(location);
                }
            }
        }.runTaskTimerAsynchronously(SheepPlugin.getInstance(), 0L, 20L);
    }
}
