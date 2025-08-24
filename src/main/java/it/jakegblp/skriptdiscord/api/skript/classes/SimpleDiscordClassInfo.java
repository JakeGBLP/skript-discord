package it.jakegblp.skriptdiscord.api.skript.classes;

public class SimpleDiscordClassInfo<T, C extends SimpleDiscordClassInfo<T, C>> extends SimpleHierarchyClassInfo<T, C> {
    @SafeVarargs
    public SimpleDiscordClassInfo(Class<T> c, String codeName, boolean canParse, Class<? extends T>... subClasses) {
        super(c, codeName, canParse, "net.dv8tion.jda", subClasses);
    }

    @SafeVarargs
    public SimpleDiscordClassInfo(Class<T> c, String codeName, Class<? extends T>... subClasses) {
        this(c, codeName, false, subClasses);
    }

    public SimpleDiscordClassInfo(Class<T> c, String codeName) {
        this(c, codeName, false);
    }
}
