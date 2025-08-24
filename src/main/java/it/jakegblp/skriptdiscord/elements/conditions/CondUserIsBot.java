package it.jakegblp.skriptdiscord.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import net.dv8tion.jda.api.entities.User;

public class CondUserIsBot extends PropertyCondition<User> {

    static {
        register(CondUserIsBot.class, "users", "[a] [skript( |-)]discord bot [user]");
    }

    @Override
    public boolean check(User value) {
        return value.isBot();
    }

    @Override
    protected String getPropertyName() {
        return "a skript-discord bot";
    }
}
