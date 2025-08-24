package it.jakegblp.skriptdiscord;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.util.Version;
import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.DrilldownPie;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static it.jakegblp.skriptdiscord.api.BotRegistry.unregisterAllBots;

public final class SkriptDiscord extends JavaPlugin {
    @Getter
    private static SkriptDiscord instance;
    private SkriptAddon addon;

    @Override
    public void onEnable() {
        instance = this;
        addon = Skript.registerAddon(this);
        int pluginId = 26063;
        Metrics metrics = new Metrics(this, pluginId);
        String version = getDescription().getVersion().toLowerCase();
        String buildType = getBuildType();

        try {
            addon.loadClasses("it.jakegblp.skriptdiscord", "elements");
        } catch (IOException e) {
            e.printStackTrace();
        }

        metrics.addCustomChart(new DrilldownPie("version_type", () -> {
            Map<String, Map<String, Integer>> map = new HashMap<>();

            String localBuildType = getBuildType();
            Map<String, Integer> entry = new HashMap<>();
            entry.put(localBuildType, 1);

            map.put(switch (localBuildType) {
                case "beta" -> "Beta";
                case "prerelease" -> "Prerelease";
                case "alpha" -> "Alpha";
                case "release" -> "Release";
                default -> "Other";
            }, entry);

            return map;
        }));

        metrics.addCustomChart(new DrilldownPie("skript_version", () -> {
            Map<String, Map<String, Integer>> map = new HashMap<>();

            Version skriptVersion = Skript.getVersion();
            Map<String, Integer> entry = new HashMap<>();
            entry.put(skriptVersion.toString(), 1);

            map.put(skriptVersion.getMajor()+"."+skriptVersion.getMinor()+"."+skriptVersion.getRevision(), entry);

            return map;
        }));
        getLogger().info("skript-discord has been enabled!");
    }

    @Override
    public void onDisable() {
        unregisterAllBots();
    }

    public SkriptAddon getAddonInstance() {
        return addon;
    }

    public String getBuildType() {
        String version = getDescription().getVersion().toLowerCase();
        if (version.contains("beta")) {
            return "beta";
        } else if (version.contains("pre")) {
            return "prerelease";
        } else if (version.contains("alpha")) {
            return "alpha";
        }
        return "release";
    }

    public static void runSync(Runnable runnable) {
        Bukkit.getScheduler().runTask(getInstance(), runnable);
    }

    public static void callEventSync(Event event) {
        if (Bukkit.isPrimaryThread()) {
            Bukkit.getPluginManager().callEvent(event);
        } else if (instance.isEnabled()) {
            runSync(() -> Bukkit.getPluginManager().callEvent(event));
        }
    }

    public static void callEvent(Event event) {
        Bukkit.getPluginManager().callEvent(event);
    }

}
