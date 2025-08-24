package it.jakegblp.skriptdiscord.api.jda.listeners;

import it.jakegblp.skriptdiscord.api.bukkit.events.BukkitMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import static it.jakegblp.skriptdiscord.SkriptDiscord.callEventSync;

public class MessageListener extends ListenerAdapter {
    public static final MessageListener INSTANCE = new MessageListener();
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        callEventSync(new BukkitMessageReceivedEvent(event));
    }
}
