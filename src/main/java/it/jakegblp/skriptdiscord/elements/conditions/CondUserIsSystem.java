package it.jakegblp.skriptdiscord.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import net.dv8tion.jda.api.entities.User;

public class CondUserIsSystem extends PropertyCondition<User> {

    static {
        register(CondUserIsSystem.class, "users", "[a] [skript( |-)]discord system user");
    }

    @Override
    public boolean check(User value) {
        return value.isSystem();
    }

    @Override
    protected String getPropertyName() {
        return "a skript-discord system user";
    }
}
