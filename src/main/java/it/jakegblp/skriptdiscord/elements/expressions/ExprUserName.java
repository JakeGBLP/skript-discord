package it.jakegblp.skriptdiscord.elements.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

import static it.jakegblp.skriptdiscord.api.skript.utils.SkriptUtils.biTagKleenean;

public class ExprUserName extends SimplePropertyExpression<User, String> {

    static {
        registerDefault(ExprUserName.class, String.class, "[:effective|:global] [skript( |-)]discord name", "users");
    }

    private Kleenean isEffectiveOrGlobal;

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        isEffectiveOrGlobal = biTagKleenean(parseResult, "effective", "global");
        return super.init(expressions, matchedPattern, isDelayed, parseResult);
    }

    @Override
    public @Nullable String convert(User from) {
        return switch (isEffectiveOrGlobal) {
            case TRUE -> from.getEffectiveName();
            case FALSE -> from.getGlobalName();
            case UNKNOWN -> from.getName();
        };
    }

    @Override
    protected String getPropertyName() {
        return switch (isEffectiveOrGlobal) {
            case TRUE -> "effective ";
            case FALSE -> "global ";
            case UNKNOWN -> "";
        } + "skript-discord name";
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
