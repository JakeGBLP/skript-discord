package it.jakegblp.skriptdiscord.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import net.dv8tion.jda.api.entities.User;

public class CondUserIsHttpInteractionsBot extends PropertyCondition<User> {

    static {
        register(CondUserIsHttpInteractionsBot.class, "users", "[a] [skript( |-)]discord http interaction[s] bot");
    }

    @Override
    public boolean check(User value) {
        return value.getFlags().contains(User.UserFlag.BOT_HTTP_INTERACTIONS);
    }

    @Override
    protected String getPropertyName() {
        return "a skript-discord http interaction bot";
    }
}
