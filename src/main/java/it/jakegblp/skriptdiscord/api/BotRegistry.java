package it.jakegblp.skriptdiscord.api;

import com.google.common.collect.Sets;
import it.jakegblp.skriptdiscord.api.bukkit.events.BukkitPreShutdownEvent;
import it.jakegblp.skriptdiscord.api.jda.BotSessionState;
import net.dv8tion.jda.api.JDA;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

import static it.jakegblp.skriptdiscord.SkriptDiscord.callEvent;

public class BotRegistry {

    private static final Set<String> BOT_NAMES = Sets.newConcurrentHashSet();
    private static final Set<Bot> BOT_REGISTRY = Sets.newConcurrentHashSet();
    private static Bot LAST_BOT = null;


    public static void registerBot(@Nullable String name, JDA jda) {
        if (name == null) name = jda.getSelfUser().getName();
        unregisterBot(name, true);
        Bot bot = new Bot(jda, name);
        LAST_BOT = bot;
        BOT_REGISTRY.add(bot);
        BOT_NAMES.add(bot.name());
    }
    public static void unregisterBot(@NotNull String name) {
        unregisterBot(name, false);
    }

    public static void unregisterBot(@NotNull String name, boolean forceShutdown) {
        Bot bot = getFromName(name);
        if (bot != null) unregisterBot(bot, forceShutdown);
    }

    public static void unregisterBot(@NotNull Bot bot) {
        unregisterBot(bot, false);
    }

    public static void unregisterBot(@NotNull Bot bot, boolean forceShutdown) {
        if (LAST_BOT == bot) {
            LAST_BOT = null;
        }
        callEvent(new BukkitPreShutdownEvent(bot, BotSessionState.SHUTTING_DOWN));
        if (forceShutdown) bot.shutdownNow();
        else bot.shutdown();
        try {
            bot.awaitShutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void unregisterAllBots() {
        for (Bot bot : BOT_REGISTRY) {
            unregisterBot(bot);
        }
        BOT_REGISTRY.clear();
        BOT_NAMES.clear();
    }


    public static Bot getFromName(String name) {
        if (!isRegistered(name)) return null;
        for (Bot bot : BOT_REGISTRY)
            if (name.equals(bot.name()))
                return bot;
        return null;
    }

    public static Bot getFromInstance(JDA jda) {
        for (Bot bot : BOT_REGISTRY)
            if (bot.jda() == jda)
                return bot;
        return null;
    }

    public static boolean isAnyRegistered() {
        return !BOT_REGISTRY.isEmpty();
    }

    public static boolean isRegistered(String name) {
        return BOT_NAMES.contains(name);
    }

    @Nullable
    public static Bot getLastBot() {
        return LAST_BOT;
    }

    public static Set<Bot> getBots() {
        return UnmodifiableSet.unmodifiableSet(BOT_REGISTRY);
    }

}
