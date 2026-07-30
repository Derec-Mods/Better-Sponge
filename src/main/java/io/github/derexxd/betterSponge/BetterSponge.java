package io.github.derexxd.betterSponge;

import org.bukkit.plugin.java.JavaPlugin;

public final class BetterSponge extends JavaPlugin {

    @Override
    public void onEnable() {
        // startup
        getServer().getPluginManager().registerEvents(new SpongeAbsorbListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
