package it.jakegblp.skriptdiscord.api.bukkit.events;

import lombok.Getter;
import net.dv8tion.jda.api.events.session.ShutdownEvent;
import net.dv8tion.jda.api.requests.CloseCode;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NullMarked;

import java.time.OffsetDateTime;

@Getter
@NullMarked
public class BukkitPostShutdownEvent extends BukkitGenericShutdownEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    private final OffsetDateTime timeShutdown;
    private final @Nullable CloseCode closeCode;
    private final int code;

    public BukkitPostShutdownEvent(ShutdownEvent event) {
        super(event);
        this.timeShutdown = event.getTimeShutdown();
        this.closeCode = event.getCloseCode();
        this.code = event.getCode();

    }
}
