package it.jakegblp.skriptdiscord.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import it.jakegblp.skriptdiscord.api.Bot;
import it.jakegblp.skriptdiscord.api.BotRegistry;
import it.jakegblp.skriptdiscord.api.bukkit.events.BukkitPostShutdownEvent;
import net.dv8tion.jda.api.requests.CloseCode;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class EvtDiscordBotPostShutdown extends SkriptEvent {

    static {
        Skript.registerEvent("Discord - on Bot Post Shutdown", EvtDiscordBotPostShutdown.class, BukkitPostShutdownEvent.class, "[skript( |-)]discord bot[s] [%-*strings%] post shutdown")
                .description("""
                        Called right after a discord bot is shutdown.
                        """)
                .examples("on discord bot post shutdown:")
                .since("1.0.0");
        EventValues.registerEventValue(
                BukkitPostShutdownEvent.class,
                CloseCode.class,
                BukkitPostShutdownEvent::getCloseCode
        );
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
        Bot bot = ((BukkitPostShutdownEvent) event).getBot();
        String botName = bot.name();
        return Arrays.stream(nameLiteral.getAll()).anyMatch(name -> name.equalsIgnoreCase(botName));
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "skript-discord bot "+nameLiteral.toString(event, debug)+" post shutdown";
    }
}
