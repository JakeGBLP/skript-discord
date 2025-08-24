package it.jakegblp.skriptdiscord.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import net.dv8tion.jda.api.entities.User;

public class CondUserIsDiscordEmployee extends PropertyCondition<User> {

    static {
        register(CondUserIsDiscordEmployee.class, "users", "[a] [skript( |-)]discord employee");
    }

    @Override
    public boolean check(User value) {
        return value.getFlags().contains(User.UserFlag.STAFF);
    }

    @Override
    protected String getPropertyName() {
        return "a skript-discord employee";
    }
}
