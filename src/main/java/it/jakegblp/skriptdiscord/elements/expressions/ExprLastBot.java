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

public class ExprLastBot extends SimpleExpression<Bot> {

    static {
        Skript.registerExpression(ExprLastBot.class, Bot.class, ExpressionType.SIMPLE, "[the] [last[ly]] registered [skript( |-)]discord bot");
    }

    @Override
    protected Bot @Nullable [] get(Event event) {
        return new Bot[] {BotRegistry.getLastBot()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Bot> getReturnType() {
        return Bot.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "the last registered discord bot";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
