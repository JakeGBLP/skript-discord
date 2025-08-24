package it.jakegblp.skriptdiscord.elements.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

public class ExprUserDiscriminator extends SimplePropertyExpression<User, String> {

    static {
        registerDefault(ExprUserDiscriminator.class, String.class, "[skript( |-)]discord [user] discriminator", "users");
    }

    @Override
    public @Nullable String convert(User from) {
        return from.getDiscriminator();
    }

    @Override
    protected String getPropertyName() {
        return "skript-discord user discriminator";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
