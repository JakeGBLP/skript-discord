package it.jakegblp.skriptdiscord.api.bukkit.events;

import lombok.Getter;
import net.dv8tion.jda.api.events.GenericEvent;
import org.jspecify.annotations.NullMarked;

@Getter
@NullMarked
public abstract class BukkitGenericDiscordEvent extends BukkitCustomDiscordEvent implements GenuineDiscordEvent {
    private final long responseNumber;

    protected BukkitGenericDiscordEvent(GenericEvent event) {
        super(event);
        this.responseNumber = event.getResponseNumber();
    }
}
