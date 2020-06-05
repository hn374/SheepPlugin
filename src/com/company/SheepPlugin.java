package com.company;

import com.company.commands.*;
import com.company.customEntities.*;
import com.company.events.*;
import com.company.utils.NMSUtils;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Pig;
import org.bukkit.plugin.java.JavaPlugin;

public final class SheepPlugin extends JavaPlugin {
    private static SheepPlugin instance;

    public static SheepPlugin getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("SheepPlugin is working!");

        getServer().getPluginManager().registerEvents(new SheepEvent(), this);
        getServer().getPluginManager().registerEvents(new PigEvent(), this);
        getServer().getPluginManager().registerEvents(new CowEvent(), this);
        getServer().getPluginManager().registerEvents(new BatEvent(), this);
        getServer().getPluginManager().registerEvents(new ZombieEvent(), this);
        getServer().getPluginManager().registerEvents(new SlimeEvent(), this);
        getServer().getPluginManager().registerEvents(new RidingFallEvent(), this);

        NMSUtils.registerEntity("Cow", 92, EntityCow.class, CustomCow.class);
        NMSUtils.registerEntity("Sheep", 91, EntitySheep.class, CustomSheep.class);
        NMSUtils.registerEntity("Pig", 90, EntityPig.class, CustomPig.class);
        NMSUtils.registerEntity("Bat", 65, EntityBat.class, CustomBat.class);
        NMSUtils.registerEntity("Slime", 55, EntitySlime.class, CustomSlime.class);
        NMSUtils.registerEntity("Zombie", 54, EntityZombie.class, CustomZombie.class);

        this.getCommand("sheep").setExecutor(new SheepCommand());
        this.getCommand("pig").setExecutor(new PigCommand());
        this.getCommand("cow").setExecutor(new CowCommand());
        this.getCommand("bat").setExecutor(new BatCommand());
        this.getCommand("zombie").setExecutor(new BabyZombieCommand());
        this.getCommand("slime").setExecutor(new SlimeCommand());
    }
}
