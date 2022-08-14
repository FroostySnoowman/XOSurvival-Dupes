package com.xosurvival.xodupes;

import com.xosurvival.xodupes.Commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("XOSurvival-Dupes plugin has enabled. tttt");

        // Commands
        getCommand("dupe").setExecutor(new DupeCommand(this));


        // Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("XOSurvival-Dupes plugin has disabled.");
    }
}
