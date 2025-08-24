package it.jakegblp.skriptdiscord.api.skript.expressions;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.LiteralUtils;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.skriptlang.skript.lang.converter.Converter;

public abstract class SimpleConvertExpression<F, T> extends PropertyExpression<F, T> implements Converter<F, T> {

    protected String rawExpr;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (LiteralUtils.hasUnparsedLiteral(expressions[0])) {
            setExpr(LiteralUtils.defendExpression(expressions[0]));
            return LiteralUtils.canInitSafely(getExpr());
        }
        setExpr((Expression<? extends F>) expressions[0]);
        rawExpr = parseResult.expr;
        return true;
    }

    @Override
    @Nullable
    public abstract T convert(F from);

    @Override
    protected T[] get(Event event, F[] source) {
        return super.get(source, this);
    }

}
