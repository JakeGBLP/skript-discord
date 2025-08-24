package it.jakegblp.skriptdiscord.api.bukkit.events;

import com.google.common.base.Preconditions;
import it.jakegblp.skriptdiscord.api.Bot;
import it.jakegblp.skriptdiscord.api.BotRegistry;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import org.bukkit.event.Event;
import org.jspecify.annotations.NullMarked;

@Getter
@NullMarked
public abstract class BukkitCustomDiscordEvent extends Event {
    private final Bot bot;

    protected BukkitCustomDiscordEvent(JDA jda) {
        Bot bot = BotRegistry.getFromInstance(jda);
        if (!(this instanceof BukkitPostShutdownEvent)) {
            Preconditions.checkNotNull(bot, "Unknown bot obtained.");
        }
        assert bot != null;
        this.bot = bot;
    }
    protected BukkitCustomDiscordEvent(Bot bot) {
        this.bot = bot;
    }
    protected BukkitCustomDiscordEvent(GenericEvent event) {
        this(event.getJDA());
    }
}
