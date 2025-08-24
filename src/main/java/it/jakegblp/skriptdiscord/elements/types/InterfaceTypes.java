package it.jakegblp.skriptdiscord.elements.types;

import ch.njol.skript.registrations.Classes;
import it.jakegblp.skriptdiscord.api.skript.classes.SimpleDiscordClassInfo;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.emoji.CustomEmoji;
import net.dv8tion.jda.api.interactions.commands.ICommandReference;

public class InterfaceTypes {
    static {
        Classes.registerClass(new SimpleDiscordClassInfo<>(ISnowflake.class, "snowflake")
                .description("A snowflake, an object with a unique id.")
                .toString(snowflake -> "Snowflake with id "+snowflake.getId())
                .user("(discord ?)?snowflakes?")
                .name("Discord - Snowflake")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(IMentionable.class, "mentionable", ThreadMember.class, CustomEmoji.class, Role.class, Channel.class, UserSnowflake.class, Widget.Member.class, Member.class, ICommandReference.class)
                .description("A mentionable discord object.")
                .toString(IMentionable::getAsMention)
                .user("(discord ?)?mentionables?")
                .name("Discord - Mentionable")
                .since("1.0.0"));
    }
}
