package it.jakegblp.skriptdiscord.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.dv8tion.jda.api.entities.User;

import java.util.Set;

import static it.jakegblp.skriptdiscord.api.skript.utils.SkriptUtils.biTagKleenean;

public class CondUserIsBugHunter extends PropertyCondition<User> {

    static {
        register(CondUserIsBugHunter.class, "users", "[a] [skript( |-)]discord bug hunter [level (:1|:2)]");
    }

    private Kleenean level;

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        level = biTagKleenean(parseResult, "1", "2");
        return super.init(expressions, matchedPattern, isDelayed, parseResult);
    }

    @Override
    public boolean check(User value) {
        Set<User.UserFlag> flags = value.getFlags();
        return
                (!level.isFalse() && flags.contains(User.UserFlag.BUG_HUNTER_LEVEL_1)) ||
                (!level.isTrue() && flags.contains(User.UserFlag.BUG_HUNTER_LEVEL_2));
    }

    @Override
    protected String getPropertyName() {
        return "a skript-discord bug hunter" + switch (level) {
            case TRUE -> "level 1";
            case FALSE -> "level 2";
            case UNKNOWN -> "";
        };
    }
}
