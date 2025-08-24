package it.jakegblp.skriptdiscord.api.bukkit.events;

import it.jakegblp.skriptdiscord.api.Bot;
import it.jakegblp.skriptdiscord.api.jda.BotSessionState;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.GenericSessionEvent;
import org.jspecify.annotations.NullMarked;

@Getter
@NullMarked
public abstract class BukkitGenericSessionEvent extends BukkitCustomDiscordEvent {

    private final BotSessionState sessionState;

    protected BukkitGenericSessionEvent(GenericSessionEvent event) {
        this(event, BotSessionState.valueOf(event.getState().name()));
    }

    protected BukkitGenericSessionEvent(GenericEvent event, BotSessionState sessionState) {
        this(event.getJDA(), sessionState);
    }

    protected BukkitGenericSessionEvent(JDA jda, BotSessionState sessionState) {
        super(jda);
        this.sessionState = sessionState;
    }

    protected BukkitGenericSessionEvent(Bot bot, BotSessionState sessionState) {
        super(bot);
        this.sessionState = sessionState;
    }
}
