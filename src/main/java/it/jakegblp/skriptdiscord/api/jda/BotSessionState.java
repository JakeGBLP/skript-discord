package it.jakegblp.skriptdiscord.api.jda;

import it.jakegblp.skriptdiscord.api.bukkit.events.BukkitPostShutdownEvent;
import it.jakegblp.skriptdiscord.api.bukkit.events.BukkitPreShutdownEvent;
import it.jakegblp.skriptdiscord.api.bukkit.events.BukkitReadyEvent;

public enum BotSessionState
{
    /**
     * The session is fully loaded, including all guilds.
     *
     * @see BukkitReadyEvent
     */
    READY,

    /**
     * The session cache has been invalidated.
     *
     * @see SessionInvalidateEvent
     */
    INVALIDATED,

    /**
     * The session has disconnected, possibly to resume.
     *
     * @see SessionDisconnectEvent
     */
    DISCONNECTED,

    /**
     * The session has resumed successfully after disconnecting.
     *
     * @see SessionResumeEvent
     */
    RESUMED,

    /**
     * The session has been recreated after being {@link #INVALIDATED invalidated}.
     *
     * @see SessionRecreateEvent
     */
    RECREATED,

    /**
     * The session has been closed and will not be reconnected.
     *
     * @see BukkitPreShutdownEvent
     */
    SHUTTING_DOWN,

    /**
     * The session has been closed and will not be reconnected.
     *
     * @see BukkitPostShutdownEvent
     */
    SHUTDOWN
}
