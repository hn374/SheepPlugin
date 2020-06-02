package com.company;

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
        this.getCommand("sheep").setExecutor(new SheepCommand());
    }
}
