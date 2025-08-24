package it.jakegblp.skriptdiscord.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.google.common.collect.ObjectArrays;
import it.jakegblp.skriptdiscord.api.jda.HypeSquad;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

import static ch.njol.skript.conditions.base.PropertyCondition.getPatterns;

public class CondUserIsInHypeSquad extends Condition {

    static {
        Skript.registerCondition(CondUserIsInHypeSquad.class,
                ObjectArrays.concat(
                        ObjectArrays.concat(
                                getPatterns(PropertyCondition.PropertyType.BE, "in [the] [[skript( |-)]discord hype[ ]squad[s] %hypesquad%", "users"),
                                getPatterns(PropertyCondition.PropertyType.BE, "in any [of the] [[skript( |-)]discord hype[ ]squad[s]", "users"),
                                String.class
                        ), getPatterns(PropertyCondition.PropertyType.BE, "in [the] [[skript( |-)]discord hype[ ]squad events program", "users"),
                        String.class
                )
        );
    }

    private boolean program = false;
    private Expression<User> userExpression;
    private Expression<HypeSquad> hypeSquadExpression = null;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        userExpression = (Expression<User>) expressions[0];
        if (matchedPattern > 3) {
            program = true;
        } else if (matchedPattern < 2) {
            hypeSquadExpression = (Expression<HypeSquad>) expressions[1];
        }
        setNegated(matchedPattern % 2 == 1);
        return true;
    }

    @Override
    public boolean check(Event event) {
        HypeSquad hypeSquad = hypeSquadExpression != null ? hypeSquadExpression.getSingle(event) : null;
        return userExpression.check(event, user -> {
            EnumSet<User.UserFlag> flags = user.getFlags();
            if (program)
                return flags.contains(User.UserFlag.HYPESQUAD);
            else if (hypeSquadExpression != null)
                return hypeSquad != null && flags.contains(hypeSquad.getFlag());
            for (HypeSquad house : HypeSquad.values())
                if (flags.contains(house.getFlag()))
                    return true;
            return false;
        }, isNegated());
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return userExpression.toString(event, debug) + " are " + (isNegated() ? "not " : "") + "in " +
                (program ? "the skript-discord hypesquad event program" :
                        hypeSquadExpression != null ? "in the skript-discord hypesquads " + hypeSquadExpression.toString(event, debug) :
                                "in any of the skript-discord hypesquads"
                        );
    }
}
