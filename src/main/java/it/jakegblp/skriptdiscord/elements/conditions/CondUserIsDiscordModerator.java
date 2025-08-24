package it.jakegblp.skriptdiscord.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import net.dv8tion.jda.api.entities.User;

public class CondUserIsDiscordModerator extends PropertyCondition<User> {

    static {
        register(CondUserIsDiscordModerator.class, "users", "[a] [certified] [skript( |-)]discord mod[erator]");
    }

    @Override
    public boolean check(User value) {
        return value.getFlags().contains(User.UserFlag.PARTNER);
    }

    @Override
    protected String getPropertyName() {
        return "a certified skript-discord moderator";
    }
}
