package it.jakegblp.skriptdiscord.elements.types;

import ch.njol.skript.classes.EnumClassInfo;
import ch.njol.skript.registrations.Classes;
import it.jakegblp.skriptdiscord.api.Bot;
import it.jakegblp.skriptdiscord.api.jda.BotSessionState;
import it.jakegblp.skriptdiscord.api.jda.HypeSquad;
import it.jakegblp.skriptdiscord.api.skript.classes.SimpleClassInfo;
import it.jakegblp.skriptdiscord.api.skript.classes.SimpleDiscordClassInfo;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.CloseCode;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.FileProxy;
import net.dv8tion.jda.api.utils.ImageProxy;
import net.dv8tion.jda.internal.entities.ReceivedMessage;

public class Types {
    static {
        Classes.registerClass(new SimpleClassInfo<>(Bot.class, "discordbot")
                .toString(bot -> "Discord Bot named "+bot.getSelfUser().getName())
                .user("discord ?bots?")
                .name("Discord - Bot")
                .description("A discord Bot.")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(SelfUser.class, "selfuser")
                .description("A Discord Self User. Represents a currently logged in account.")
                .toString(User::getName)
                .user("(discord ?)?self ?users?")
                .name("Discord - Self User")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(User.class, "user", SelfUser.class)
                .description("A Discord User. Contains all publicly available information about a specific Discord User.")
                .toString(User::getName)
                .user("(discord ?)?users?")
                .name("Discord - User")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(Guild.class, "guild")
                .description("A Discord Guild. his should contain all information provided from Discord about a Guild.")
                .toString(Guild::getName)
                .user("(discord ?)?guilds?")
                .name("Discord - Guild")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(ReceivedMessage.class, "receiveddiscordmessage")
                .description("A received discord message.")
                .toString(Message::getContentDisplay)
                .user("received ?discord ?messages?")
                .name("Discord - Received Message")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(Message.class, "discordmessage", ReceivedMessage.class)
                .description("A discord message.")
                .toString(Message::getContentDisplay)
                .user("discord ?messages?")
                .name("Discord - Message")
                .since("1.0.0"));
        Classes.registerClass(new SimpleClassInfo<>(MessageEmbed.class, "embed")
                .toString(embed -> "Embed with title '"+embed.getType())
                .description("A discord embed.")
                .user("(discord ?)?embeds?")
                .name("Discord - Embed")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(FileProxy.class, "discordfile")
                .description("A discord file.")
                .toString(fileProxy -> "Discord File "+fileProxy.getUrl())
                .user("discord ?files?")
                .name("Discord - File")
                .since("1.0.0"));
        Classes.registerClass(new SimpleDiscordClassInfo<>(ImageProxy.class, "discordimage")
                .description("A discord image.")
                .toString(imageProxy -> "Discord Image "+imageProxy.getUrl())
                .user("discord ?images?")
                .name("Discord - Image")
                .since("1.0.0"));
        Classes.registerClass(new EnumClassInfo<>(BotSessionState.class, "sessionstate", "session states")
                .user("(discord ?)?session ?states?")
                .name("Discord - Session State")
                .description("A session state.")
                .since("1.0.0"));
        Classes.registerClass(new EnumClassInfo<>(GatewayIntent.class, "gatewayintent", "gateway intents")
                .user("(discord ?)?gateway ?intents?")
                .name("Discord - Gateway Intent")
                .description("A gateway intent.")
                .since("1.0.0"));
        Classes.registerClass(new EnumClassInfo<>(CloseCode.class, "closecode", "close codes")
                .user("(discord ?)?close ?codes?")
                .name("Discord - Close Code")
                .description("A close code.")
                .since("1.0.0"));
        Classes.registerClass(new EnumClassInfo<>(HypeSquad.class, "hypesquad", "hype squads")
                .user("(discord ?)?hype ?squads?")
                .name("Discord - Hype Squad")
                .description("A hype squad.")
                .since("1.0.0"));
    }
}
