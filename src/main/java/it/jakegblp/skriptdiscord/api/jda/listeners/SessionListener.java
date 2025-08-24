package it.jakegblp.skriptdiscord.api.jda.listeners;

import it.jakegblp.skriptdiscord.api.bukkit.events.BukkitPostShutdownEvent;
import it.jakegblp.skriptdiscord.api.bukkit.events.BukkitReadyEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.events.session.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import static it.jakegblp.skriptdiscord.SkriptDiscord.callEventSync;

public class SessionListener extends ListenerAdapter {
    public static final SessionListener INSTANCE = new SessionListener();

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        callEventSync(new BukkitReadyEvent(event));
    }

    @Override
    public void onShutdown(@NotNull ShutdownEvent event) {
        callEventSync(new BukkitPostShutdownEvent(event));
    }
}
