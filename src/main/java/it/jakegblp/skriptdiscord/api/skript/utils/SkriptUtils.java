package it.jakegblp.skriptdiscord.api.skript.utils;

import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class SkriptUtils {

    public static Kleenean trueFalseOrUnknown(boolean first, boolean second) {
        return first ? Kleenean.TRUE : second ? Kleenean.FALSE : Kleenean.UNKNOWN;
    }

    public static Kleenean biTagKleenean(SkriptParser.ParseResult parseResult, String first, String second) {
        return trueFalseOrUnknown(parseResult.hasTag(first), parseResult.hasTag(second));
    }
}
