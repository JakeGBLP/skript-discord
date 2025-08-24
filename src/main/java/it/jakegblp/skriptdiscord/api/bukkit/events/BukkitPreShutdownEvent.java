package it.jakegblp.skriptdiscord.api.bukkit.events;

import it.jakegblp.skriptdiscord.api.Bot;
import it.jakegblp.skriptdiscord.api.jda.BotSessionState;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.GenericSessionEvent;
import org.bukkit.event.HandlerList;
import org.jspecify.annotations.NullMarked;

@Getter
@NullMarked
public class BukkitPreShutdownEvent extends BukkitGenericShutdownEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public BukkitPreShutdownEvent(GenericEvent event, BotSessionState sessionState) {
        super(event, sessionState);
    }

    public BukkitPreShutdownEvent(GenericSessionEvent event) {
        super(event);
    }

    public BukkitPreShutdownEvent(JDA jda, BotSessionState sessionState) {
        super(jda, sessionState);
    }

    public BukkitPreShutdownEvent(Bot bot, BotSessionState sessionState) {
        super(bot, sessionState);
    }
}
