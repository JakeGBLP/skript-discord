package it.jakegblp.skriptdiscord.api.bukkit.events;

import it.jakegblp.skriptdiscord.api.jda.BotSessionState;

public interface GenericSessionEvent {
    BotSessionState getSessionState();
}
