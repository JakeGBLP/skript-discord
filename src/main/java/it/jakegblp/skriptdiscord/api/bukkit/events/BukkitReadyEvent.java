package it.jakegblp.skriptdiscord.api.bukkit.events;

import lombok.Getter;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import org.bukkit.event.HandlerList;
import org.jspecify.annotations.NullMarked;

@Getter
@NullMarked
public class BukkitReadyEvent extends BukkitGenericSessionEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    private final int
            availableGuildCount,
            unavailableGuildCount,
            totalGuildCount;

    public BukkitReadyEvent(ReadyEvent event) {
        super(event);
        availableGuildCount = event.getGuildAvailableCount();
        unavailableGuildCount = event.getGuildUnavailableCount();
        totalGuildCount = event.getGuildTotalCount();
    }
}
