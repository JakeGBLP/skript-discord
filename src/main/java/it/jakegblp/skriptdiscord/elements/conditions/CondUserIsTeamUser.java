package it.jakegblp.skriptdiscord.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import net.dv8tion.jda.api.entities.User;

public class CondUserIsTeamUser extends PropertyCondition<User> {

    static {
        register(CondUserIsTeamUser.class, "users", "[a] [skript( |-)]discord team user");
    }

    @Override
    public boolean check(User value) {
        return value.getFlags().contains(User.UserFlag.TEAM_USER);
    }

    @Override
    protected String getPropertyName() {
        return "a skript-discord team user";
    }
}
