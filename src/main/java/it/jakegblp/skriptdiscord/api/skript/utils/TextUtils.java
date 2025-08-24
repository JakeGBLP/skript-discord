package it.jakegblp.skriptdiscord.api.skript.utils;

public class TextUtils {
    public static String prettyName(Class<?> cls) {
        String simple = cls.getSimpleName();
        if (simple.matches("I[A-Z].*"))
            simple = simple.substring(1);
        return simple.replaceAll("([a-z])([A-Z])", "$1 $2");
    }
}
