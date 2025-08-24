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

public class ExprRegisteredBots extends SimpleExpression<Bot> {

    static {
        Skript.registerExpression(ExprRegisteredBots.class, Bot.class, ExpressionType.SIMPLE, "[all [[of] the]|the] registered [skript( |-)]discord bots");
    }

    @Override
    protected Bot @Nullable [] get(Event event) {
        return BotRegistry.getBots().toArray(new Bot[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Bot> getReturnType() {
        return Bot.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "all of the registered skript-discord bots";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
