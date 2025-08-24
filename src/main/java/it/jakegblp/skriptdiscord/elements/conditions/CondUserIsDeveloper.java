package it.jakegblp.skriptdiscord.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.dv8tion.jda.api.entities.User;

import java.util.Set;

public class CondUserIsDeveloper extends PropertyCondition<User> {

    static {
        register(CondUserIsDeveloper.class, "users", "[a] [skript( |-)]discord (:active|verified) developer");
    }

    private boolean active;

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        active = parseResult.hasTag("active");
        return super.init(expressions, matchedPattern, isDelayed, parseResult);
    }

    @Override
    public boolean check(User value) {
        Set<User.UserFlag> flags = value.getFlags();
        return flags.contains(active
                ? User.UserFlag.ACTIVE_DEVELOPER
                : User.UserFlag.VERIFIED_DEVELOPER);
    }

    @Override
    protected String getPropertyName() {
        return "a skript-discord " + (active ? "active" : "verified") + " developer";
    }
}
