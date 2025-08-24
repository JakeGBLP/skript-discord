package it.jakegblp.skriptdiscord.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import net.dv8tion.jda.api.entities.User;

public class CondUserIsPartner extends PropertyCondition<User> {

    static {
        register(CondUserIsPartner.class, "users", "[a] [skript( |-)]discord partner");
    }

    @Override
    public boolean check(User value) {
        return value.getFlags().contains(User.UserFlag.PARTNER);
    }

    @Override
    protected String getPropertyName() {
        return "a skript-discord partner";
    }
}
