package it.jakegblp.skriptdiscord.api;

import lombok.experimental.Delegate;
import net.dv8tion.jda.api.JDA;

public record Bot(@Delegate JDA jda, String name) {

    public Bot {
        if (name == null) name = jda.getSelfUser().getName();
    }

    public Bot(JDA jda) {
        this(jda, null);
    }
}
