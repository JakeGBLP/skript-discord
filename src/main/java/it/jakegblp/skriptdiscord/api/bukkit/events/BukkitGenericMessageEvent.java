package it.jakegblp.skriptdiscord.api.bukkit.events;

import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.unions.GuildMessageChannelUnion;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NullMarked;

@Getter
@NullMarked
public class BukkitGenericMessageEvent extends BukkitGenericGuildEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    private final MessageChannelUnion channel;
    private final GuildMessageChannelUnion guildChannel;
    private final ChannelType channelType;
    private final long channelIdLong, messageIdLong;
    private final String channelId, messageId, jumpURL;
    private final boolean fromGuild, fromThread;

    @SuppressWarnings("ConstantConditions")
    public BukkitGenericMessageEvent(GenericMessageEvent event) {
        super(event, event.isFromGuild() ? event.getGuild() : null);
        this.channel = event.getChannel();
        this.channelType = event.getChannelType();
        this.guildChannel = event.getGuildChannel();
        this.channelIdLong = getChannelIdLong();
        this.channelId = getChannelId();
        this.messageIdLong = getMessageIdLong();
        this.messageId = getMessageId();
        this.jumpURL = event.getJumpUrl();
        this.fromGuild = event.isFromGuild();
        this.fromThread = event.isFromThread();
    }
    @SuppressWarnings("NullableProblems")
    @Nullable
    @Override
    public Guild getGuild() {
        return super.getGuild();
    }
}
