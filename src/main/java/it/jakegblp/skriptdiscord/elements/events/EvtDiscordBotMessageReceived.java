package it.jakegblp.skriptdiscord.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import it.jakegblp.skriptdiscord.api.Bot;
import it.jakegblp.skriptdiscord.api.BotRegistry;
import it.jakegblp.skriptdiscord.api.bukkit.events.BukkitMessageReceivedEvent;
import net.dv8tion.jda.api.entities.Message;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class EvtDiscordBotMessageReceived extends SkriptEvent {

    static {
        Skript.registerEvent("Discord - on Message Received", EvtDiscordBotMessageReceived.class, BukkitMessageReceivedEvent.class, "[skript( |-)]discord message receive[d] [by %-*strings%]")
                .description("""
                        Called when a message is received.
                        """)
                .examples("on discord message received:")
                .since("1.0.0");
        EventValues.registerEventValue(BukkitMessageReceivedEvent.class, Message.class, BukkitMessageReceivedEvent::getMessage);
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
        Bot bot = ((BukkitMessageReceivedEvent) event).getBot();
        String botName = bot.name();
        return Arrays.stream(nameLiteral.getAll()).anyMatch(name -> name.equalsIgnoreCase(botName) && BotRegistry.isRegistered(name));
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "skript-discord message received"+ (nameLiteral == null ? "" : " by "+nameLiteral.toString(event, debug));
    }
}
