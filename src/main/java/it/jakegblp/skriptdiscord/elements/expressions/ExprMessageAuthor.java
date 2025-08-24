package it.jakegblp.skriptdiscord.elements.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

public class ExprMessageAuthor extends SimplePropertyExpression<Message, User> {

    static {
        registerDefault(ExprMessageAuthor.class, User.class, "[skript( |-)]discord message author", "discordmessages");
    }

    @Override
    public @Nullable User convert(Message from) {
        return from.getAuthor();
    }

    @Override
    protected String getPropertyName() {
        return "skript-discord message author";
    }

    @Override
    public Class<? extends User> getReturnType() {
        return User.class;
    }
}
