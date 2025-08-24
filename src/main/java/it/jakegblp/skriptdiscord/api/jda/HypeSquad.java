package it.jakegblp.skriptdiscord.api.jda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.entities.User;

@AllArgsConstructor
@Getter
public enum HypeSquad {
    BRAVERY(User.UserFlag.HYPESQUAD_BRAVERY),
    BRILLIANCE(User.UserFlag.HYPESQUAD_BRILLIANCE),
    BALANCE(User.UserFlag.HYPESQUAD_BALANCE),;

    private final User.UserFlag flag;
}
