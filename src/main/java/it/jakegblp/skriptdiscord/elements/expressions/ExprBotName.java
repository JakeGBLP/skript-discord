package it.jakegblp.skriptdiscord.elements.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import it.jakegblp.skriptdiscord.api.Bot;
import org.jetbrains.annotations.Nullable;

public class ExprBotName extends SimplePropertyExpression<Bot, String> {

    static {
        registerDefault(ExprBotName.class, String.class, "discord bot name", "discordbots");
    }

    @Override
    public @Nullable String convert(Bot from) {
        return from.name();
    }

    @Override
    protected String getPropertyName() {
        return "discord bot name";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
