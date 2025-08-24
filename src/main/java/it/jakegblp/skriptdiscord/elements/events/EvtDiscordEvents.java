package it.jakegblp.skriptdiscord.elements.events;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.registrations.EventValues;
import ch.njol.util.coll.CollectionUtils;
import it.jakegblp.skriptdiscord.api.Bot;
import it.jakegblp.skriptdiscord.api.ListenerRegistry;
import it.jakegblp.skriptdiscord.api.bukkit.events.BukkitCustomDiscordEvent;
import it.jakegblp.skriptdiscord.api.bukkit.events.BukkitGenericSessionEvent;
import it.jakegblp.skriptdiscord.api.bukkit.events.BukkitPostShutdownEvent;
import it.jakegblp.skriptdiscord.api.jda.BotSessionState;

public class EvtDiscordEvents {
    static {
        EventValues.registerEventValue(BukkitCustomDiscordEvent.class, Bot.class, BukkitCustomDiscordEvent::getBot,
                EventValues.TIME_NOW,
                "There is no discord bot in the Post Shutdown event. Use the Pre Shutdown event.",
                CollectionUtils.array(BukkitPostShutdownEvent.class));
        EventValues.registerEventValue(BukkitGenericSessionEvent.class, BotSessionState.class, BukkitGenericSessionEvent::getSessionState);
        ScriptLoader.eventRegistry().register(ScriptLoader.ScriptUnloadEvent.class,
                (parser, script) -> ListenerRegistry.removeListeners(script));
    }
}
