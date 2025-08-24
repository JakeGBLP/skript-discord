package it.jakegblp.skriptdiscord.api.bukkit.events;

import lombok.Getter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NullMarked;

@Getter
@NullMarked
public class BukkitMessageReceivedEvent extends BukkitGenericMessageEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    private final Message message;
    private final boolean webhookMessage;
    private final User author;
    private final @Nullable Member member;

    public BukkitMessageReceivedEvent(MessageReceivedEvent event) {
        super(event);
        this.message = event.getMessage();
        this.webhookMessage = event.isWebhookMessage();
        this.author = event.getAuthor();
        this.member = event.getMember();
    }
}
