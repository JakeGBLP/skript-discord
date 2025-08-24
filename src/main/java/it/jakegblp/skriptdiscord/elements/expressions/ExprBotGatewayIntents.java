package it.jakegblp.skriptdiscord.elements.expressions;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import it.jakegblp.skriptdiscord.api.Bot;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class ExprBotGatewayIntents extends PropertyExpression<Bot, GatewayIntent> {

    static {
        registerDefault(ExprBotGatewayIntents.class, GatewayIntent.class, "gateway intents", "discordbots");
    }

    private Expression<Bot> botExpression;

    @Override
    public Class<? extends GatewayIntent> getReturnType() {
        return GatewayIntent.class;
    }

    @Override
    protected GatewayIntent[] get(Event event, Bot[] source) {
        return Arrays.stream(source).flatMap(bot -> bot.getGatewayIntents().stream()).toArray(GatewayIntent[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "gateway intents of "+botExpression.toString(event, debug);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        botExpression = (Expression<Bot>) expressions[0];
        return true;
    }
}
