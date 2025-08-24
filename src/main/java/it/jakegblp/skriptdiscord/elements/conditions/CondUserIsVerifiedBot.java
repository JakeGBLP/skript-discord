package it.jakegblp.skriptdiscord.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import net.dv8tion.jda.api.entities.User;

public class CondUserIsVerifiedBot extends PropertyCondition<User> {

    static {
        register(CondUserIsVerifiedBot.class, "users", "[a] [skript( |-)]discord verified bot");
    }

    @Override
    public boolean check(User value) {
        return value.getFlags().contains(User.UserFlag.VERIFIED_BOT);
    }

    @Override
    protected String getPropertyName() {
        return "a skript-discord verified bot";
    }
}
