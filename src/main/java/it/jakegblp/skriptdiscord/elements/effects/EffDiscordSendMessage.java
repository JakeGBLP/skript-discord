package it.jakegblp.skriptdiscord.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffDiscordSendMessage extends Effect {

    static {
        Skript.registerEffect(EffDiscordSendMessage.class, "send [skript( |-)]discord %strings% to %messagechannels%");
    }

    private Expression<String> messageExpression;
    private Expression<MessageChannel> channelExpression;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        messageExpression  = (Expression<String>) expressions[0];
        channelExpression = (Expression<MessageChannel>) expressions[1];
        return false;
    }

    @Override
    protected void execute(Event event) {
        if (messageExpression == null || channelExpression == null) return;
        String[] strings = messageExpression.getAll(event);
        for (MessageChannel messageChannel : channelExpression.getAll(event)) {
            for (String string : strings) {
                messageChannel.sendMessage(string).queue();
            }
        }

    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "send skript-discord "+messageExpression.toString(event, debug)+" to "+channelExpression.toString(event, debug);
    }
}
