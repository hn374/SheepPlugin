package com.company;

import net.minecraft.server.v1_8_R3.EntitySheep;
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
        getServer().getPluginManager().registerEvents(new RidingFallEvent(), this);
        NMSUtils.registerEntity("Sheep", 91, EntitySheep.class, CustomSheep.class);

        this.getCommand("sheep").setExecutor(new SheepCommand());
    }
}
