package it.jakegblp.skriptdiscord.elements.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.dv8tion.jda.api.entities.ISnowflake;
import org.jetbrains.annotations.Nullable;

public class ExprSnowflakeId extends SimplePropertyExpression<ISnowflake, Object> {

    static {
        registerDefault(ExprSnowflakeId.class, Object.class, "([discord] snowflake|discord) [:string] id", "snowflakes");
    }

    private boolean isString;

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        isString = parseResult.hasTag("string");
        return super.init(expressions, matchedPattern, isDelayed, parseResult);
    }

    @Override
    public @Nullable Object convert(ISnowflake snowflake) {
        return isString ? snowflake.getId() : snowflake.getIdLong();
    }

    @Override
    protected String getPropertyName() {
        return "discord snowflake " + (isString ? "string " : "") + "id";
    }

    @Override
    public Class<?> getReturnType() {
        return isString ? String.class : Long.class;
    }
}
