package it.jakegblp.skriptdiscord.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import net.dv8tion.jda.api.entities.User;

public class CondUserIsEarlySupporter extends PropertyCondition<User> {

    static {
        register(CondUserIsEarlySupporter.class, "users", "[a] [skript( |-)]discord early supporter");
    }

    @Override
    public boolean check(User value) {
        return value.getFlags().contains(User.UserFlag.EARLY_SUPPORTER);
    }

    @Override
    protected String getPropertyName() {
        return "a skript-discord partner";
    }
}
