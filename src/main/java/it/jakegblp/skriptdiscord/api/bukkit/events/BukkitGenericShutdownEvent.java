package it.jakegblp.skriptdiscord.api.bukkit.events;

import it.jakegblp.skriptdiscord.api.Bot;
import it.jakegblp.skriptdiscord.api.jda.BotSessionState;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.GenericSessionEvent;
import org.bukkit.event.HandlerList;
import org.jspecify.annotations.NullMarked;

@NullMarked
public abstract class BukkitGenericShutdownEvent extends BukkitGenericSessionEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    protected BukkitGenericShutdownEvent(GenericSessionEvent event) {
        super(event);
    }

    protected BukkitGenericShutdownEvent(GenericEvent event, BotSessionState sessionState) {
        super(event, sessionState);
    }

    protected BukkitGenericShutdownEvent(JDA jda, BotSessionState sessionState) {
        super(jda, sessionState);
    }

    protected BukkitGenericShutdownEvent(Bot bot, BotSessionState sessionState) {
        super(bot, sessionState);
    }
}
