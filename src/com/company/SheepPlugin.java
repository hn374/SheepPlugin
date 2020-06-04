package com.company;

import com.company.commands.CowCommand;
import com.company.commands.PigCommand;
import com.company.customEntities.CustomCow;
import com.company.customEntities.CustomPig;
import com.company.customEntities.CustomSheep;
import com.company.commands.SheepCommand;
import com.company.events.CowEvent;
import com.company.events.PigEvent;
import com.company.events.RidingFallEvent;
import com.company.events.SheepEvent;
import com.company.utils.NMSUtils;
import net.minecraft.server.v1_8_R3.EntityCow;
import net.minecraft.server.v1_8_R3.EntityPig;
import net.minecraft.server.v1_8_R3.EntitySheep;
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
        getServer().getPluginManager().registerEvents(new RidingFallEvent(), this);

        NMSUtils.registerEntity("Cow", 92, EntityCow.class, CustomCow.class);
        NMSUtils.registerEntity("Sheep", 91, EntitySheep.class, CustomSheep.class);
        NMSUtils.registerEntity("Pig", 90, EntityPig.class, CustomPig.class);

        this.getCommand("sheep").setExecutor(new SheepCommand());
        this.getCommand("pig").setExecutor(new PigCommand());
        this.getCommand("cow").setExecutor(new CowCommand());
    }
}
