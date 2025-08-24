package it.jakegblp.skriptdiscord.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import it.jakegblp.skriptdiscord.api.Bot;
import it.jakegblp.skriptdiscord.api.BotRegistry;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffShutdownBot extends Effect {

    static {
        Skript.registerEffect(EffShutdownBot.class,
                "[:force] shutdown [skript( |-)]discord bot[s] %discordbots%"
        );
    }

    private Expression<Bot> botExpression;
    private boolean force;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        botExpression = (Expression<Bot>) expressions[0];
        force = parseResult.hasTag("force");
        return true;
    }

    @Override
    protected void execute(Event event) {
        botExpression.stream(event).forEach(bot -> BotRegistry.unregisterBot(bot, force));
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return (force ? "force " : "") + "shutdown skript-discord bots " + botExpression.toString(event, b);
    }
}
