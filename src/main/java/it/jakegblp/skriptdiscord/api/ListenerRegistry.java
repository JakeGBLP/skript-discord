package it.jakegblp.skriptdiscord.api;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.skriptlang.skript.lang.script.Script;

import java.util.Arrays;

public class ListenerRegistry {
    private static final Multimap<Script, ListenerAdapter> LISTENER_MAP = MultimapBuilder.hashKeys().arrayListValues().build();
    public static void addListeners(Script script, ListenerAdapter... listeners) {
        LISTENER_MAP.putAll(script, Arrays.asList(listeners));
    }
    public static void removeListeners(Script script) {
        LISTENER_MAP.removeAll(script);
    }
}
