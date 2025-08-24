package it.jakegblp.skriptdiscord.elements.types;

import ch.njol.skript.registrations.Classes;
import it.jakegblp.skriptdiscord.api.skript.classes.SimpleDiscordClassInfo;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.attribute.ISlowmodeChannel;
import net.dv8tion.jda.api.entities.channel.concrete.NewsChannel;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.entities.channel.middleman.StandardGuildMessageChannel;

public class ChannelTypes {
    static {
        Classes.registerClass(new SimpleDiscordClassInfo<>(Channel.class, "channel")
                .description("A Discord Channel, this is the base type for all channels.")
                .toString(channel -> "Channel '"+channel.getName()+"'")
                .user("(discord ?)?channels?")
                .name("Discord - Channel")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(PrivateChannel.class, "privatechannel")
                .description("A Private Discord Channel.")
                .toString(channel -> "Private Channel '"+channel.getName()+"'")
                .user("private ?(discord ?)?channels?")
                .name("Discord - Private Channel")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(TextChannel.class, "textchannel")
                .description("A Discord Text Channel.")
                .toString(channel -> "Text Channel '"+channel.getName()+"'")
                .user("(discord ?)?text ?channels?")
                .name("Discord - Text Channel")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(NewsChannel.class, "newschannel")
                .description("""
                A Discord News Channel.
                
                The Discord client may refer to these as Announcement Channels.
                
                Members can subscribe channels in their own guilds to receive messages crossposted from this channel. This is referred to as following this channel.
                Messages sent in this channel can be crossposted, at which point they will be sent (via webhook) to all subscribed channels.
                """)
                .toString(channel -> "News Channel '"+channel.getName()+"'")
                .user("(discord ?)?news ?channels?")
                .name("Discord - News Channel")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(MessageChannel.class, "messagechannel")
                .description("A Discord Message Channel, can have messages and files sent to it.")
                .toString(channel -> "Message Channel '"+channel.getName()+"'")
                .user("(discord ?)?message ?channels?")
                .name("Discord - Message Channel")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(VoiceChannel.class, "voicechannel")
                .description("A Discord Voice Channel.")
                .toString(channel -> "Voice Channel '"+channel.getName()+"'")
                .user("(discord ?)?voice ?channels?")
                .name("Discord - Voice Channel")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(AudioChannel.class, "audiochannel")
                .description("A Discord Audio Channel, capable of handling audio")
                .toString(channel -> "Audio Channel '"+channel.getName()+"'")
                .user("(discord ?)?audio ?channels?")
                .name("Discord - Audio Channel")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(GuildMessageChannel.class, "guildmessagechannel")
                .description("A Discord Guild Message Channel, represents message channels present in guilds. Includes thread channels.")
                .toString(channel -> "Guild Message Channel '"+channel.getName()+"'")
                .user("(discord ?)?guild ?message ?channels?")
                .name("Discord - Guild Message Channel")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(StandardGuildMessageChannel.class, "standardguildmessagechannel")
                .description("A Standard Discord Guild Message Channel, similar to a Guild Message Channel but doesn't include thread channels.")
                .toString(channel -> "Standard Guild Message Channel '"+channel.getName()+"'")
                .user("(discord ?)?standard ?guild ?message ?channels?")
                .name("Discord - Standard Guild Message Channel")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(ISlowmodeChannel.class, "slowmodechannel")
                .description("A Slowmode Channel, supports slowmode.")
                .toString(channel -> "Slowmode channel '"+channel.getName()+"'")
                .user("(discord ?)?slow ?mode ?channels?")
                .name("Discord - Slowmode Channel")
                .since("1.0.0"));
    }
}
