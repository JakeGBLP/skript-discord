package it.jakegblp.skriptdiscord.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import it.jakegblp.skriptdiscord.api.skript.expressions.SimpleConvertExpression;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprUserAsTag extends SimpleConvertExpression<User, String> {

    static {
        Skript.registerExpression(ExprUserAsTag.class, String.class, ExpressionType.PROPERTY,
                "%users% as [skript( |-)]discord tag");
    }

    @Override
    public @Nullable String convert(User from) {
        return from.getAsTag();
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return getExpr().toString(event, debug) + " as skript-discord tag";
    }
}