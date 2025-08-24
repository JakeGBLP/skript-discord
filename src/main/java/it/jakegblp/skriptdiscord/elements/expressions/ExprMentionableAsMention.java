package it.jakegblp.skriptdiscord.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import it.jakegblp.skriptdiscord.api.skript.expressions.SimpleConvertExpression;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprMentionableAsMention extends SimpleConvertExpression<Message, User> {

    static {
        Skript.registerExpression(ExprMentionableAsMention.class, User.class, ExpressionType.PROPERTY,
                "%mentionables% as [skript( |-)]discord mention");
    }

    @Override
    public @Nullable User convert(Message from) {
        return from.getAuthor();
    }

    @Override
    public Class<? extends User> getReturnType() {
        return User.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return getExpr().toString(event, debug) + " as skript-discord mention";
    }
}