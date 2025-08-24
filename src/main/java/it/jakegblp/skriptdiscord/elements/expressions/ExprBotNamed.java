package it.jakegblp.skriptdiscord.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import it.jakegblp.skriptdiscord.api.Bot;
import it.jakegblp.skriptdiscord.api.BotRegistry;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprBotNamed extends SimpleExpression<Bot> {

    static {
        Skript.registerExpression(ExprBotNamed.class, Bot.class, ExpressionType.COMBINED, "[skript( |-)]discord bot[s] (named|from name[s]) %strings%");
    }

    private Expression<String> nameExpression;

    @Override
    protected Bot @Nullable [] get(Event event) {
        return nameExpression.stream(event).map(BotRegistry::getFromName).toArray(Bot[]::new);
    }

    @Override
    public boolean isSingle() {
        return nameExpression.isSingle();
    }

    @Override
    public Class<? extends Bot> getReturnType() {
        return Bot.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "skript-discord bots named "+nameExpression.toString(event, debug);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        nameExpression = (Expression<String>) expressions[0];
        return true;
    }
}
