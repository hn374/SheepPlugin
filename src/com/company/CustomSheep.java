package com.company;

import net.minecraft.server.v1_8_R3.EntitySheep;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Sheep;

public class CustomSheep extends EntitySheep {
    public CustomSheep(org.bukkit.World world) {
        super(((CraftWorld) world).getHandle());

        Sheep customSheep = (Sheep) this.getBukkitEntity();
        this.setCustomName("Fuck Me");

        // Add the custom sheep to the world when constructor is called
        this.getWorld().addEntity(this);
    }
}
