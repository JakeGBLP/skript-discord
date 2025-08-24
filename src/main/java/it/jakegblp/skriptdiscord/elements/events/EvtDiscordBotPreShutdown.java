package it.jakegblp.skriptdiscord.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import it.jakegblp.skriptdiscord.api.Bot;
import it.jakegblp.skriptdiscord.api.BotRegistry;
import it.jakegblp.skriptdiscord.api.bukkit.events.BukkitPreShutdownEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class EvtDiscordBotPreShutdown extends SkriptEvent {

    static {
        Skript.registerEvent("Discord - on Bot Pre Shutdown", EvtDiscordBotPreShutdown.class, BukkitPreShutdownEvent.class, "[skript( |-)]discord bot[s] [%-*strings%] pre shutdown")
                .description("""
                        Called right before a discord bot is shutdown.
                        """)
                .examples("on discord bot pre shutdown:")
                .since("1.0.0");
    }

    private Literal<String> nameLiteral;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parseResult) {
        nameLiteral = (Literal<String>) args[0];
        return nameLiteral == null || Arrays.stream(nameLiteral.getAll()).anyMatch(BotRegistry::isRegistered);
    }

    @Override
    public boolean check(Event event) {
        if (nameLiteral == null) return true;
        Bot bot = ((BukkitPreShutdownEvent) event).getBot();
        String botName = bot.name();
        return Arrays.stream(nameLiteral.getAll()).anyMatch(name -> name.equalsIgnoreCase(botName));
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "skript-discord bot "+nameLiteral.toString(event, debug)+" pre shutdown";
    }
}
