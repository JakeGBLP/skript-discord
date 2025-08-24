package it.jakegblp.skriptdiscord.api.bukkit.events;

import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import org.jspecify.annotations.NullMarked;

@Getter
@NullMarked
public abstract class BukkitGenericGuildEvent extends BukkitGenericDiscordEvent {
    private final Guild guild;

    /**
     * Not all JDA events with a guild extend {@link GenericGuildEvent}.
     */
    protected BukkitGenericGuildEvent(GenericEvent event, Guild guild) {
        super(event);
        this.guild = guild;
    }

    protected BukkitGenericGuildEvent(GenericGuildEvent event) {
        super(event);
        this.guild = event.getGuild();
    }
}
