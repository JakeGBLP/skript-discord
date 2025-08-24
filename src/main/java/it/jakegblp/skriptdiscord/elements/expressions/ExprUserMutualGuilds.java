package it.jakegblp.skriptdiscord.elements.expressions;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class ExprUserMutualGuilds extends PropertyExpression<User, Guild> {

    static {
        registerDefault(ExprUserMutualGuilds.class, Guild.class, "mutual guilds", "users");
    }

    private Expression<User> userExpression;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        userExpression = (Expression<User>) expressions[0];
        return true;
    }

    @Override
    public Class<? extends Guild> getReturnType() {
        return Guild.class;
    }

    @Override
    protected Guild[] get(Event event, User[] source) {
        return Arrays.stream(source).flatMap(bot -> bot.getMutualGuilds().stream()).toArray(Guild[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "mutual guilds of "+ userExpression.toString(event, debug);
    }
}
