package it.jakegblp.skriptdiscord.api.skript.classes;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static it.jakegblp.skriptdiscord.api.skript.utils.TextUtils.prettyName;

@Getter
public class SimpleHierarchyClassInfo<T, C extends SimpleHierarchyClassInfo<T, C>> extends SimpleClassInfo<T, C> {

    protected final @Nullable String packagePrefix;
    protected final Class<? extends T>[] subClasses;

    /**
     * @param c             The class
     * @param codeName      The name used in patterns
     * @param canParse      Whether this classinfo can be parsed
     * @param packagePrefix The prefix of the package to scan for super types
     * @param subClasses    The list of subclasses
     */
    @SafeVarargs
    public SimpleHierarchyClassInfo(Class<T> c, String codeName, boolean canParse, @Nullable String packagePrefix, Class<? extends T>... subClasses) {
        super(c, codeName, canParse);
        this.packagePrefix = packagePrefix;
        this.subClasses = subClasses;
    }

    /**
     * @param c             The class
     * @param codeName      The name used in patterns
     * @param packagePrefix The prefix of the package to scan for super types
     * @param subClasses    The list of subclasses
     */
    @SafeVarargs
    public SimpleHierarchyClassInfo(Class<T> c, String codeName, @Nullable String packagePrefix, Class<? extends T>... subClasses) {
        this(c, codeName, false, packagePrefix, subClasses);
    }
    /**
     * @param c             The class
     * @param codeName      The name used in patterns
     * @param subClasses    The list of subclasses
     */
    @SafeVarargs
    public SimpleHierarchyClassInfo(Class<T> c, String codeName, Class<? extends T>... subClasses) {
        this(c, codeName, false, null, subClasses);
    }

    /**
     * @param c        The class
     * @param codeName The name used in patterns
     */
    public SimpleHierarchyClassInfo(Class<T> c, String codeName) {
        this(c, codeName, false, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public C description(String... description) {
        Set<String> superTypes = new LinkedHashSet<>();
        Class<?> base = getC();
        Class<?> current = base.getSuperclass();
        while (current != null && current != Object.class) {
            if (packagePrefix == null || current.getName().startsWith(packagePrefix)) {
                superTypes.add(prettyName(current));
            }
            current = current.getSuperclass();
        }

        collectInterfaces(base, superTypes);

        List<String> subTypeNames = new ArrayList<>();
        for (Class<?> cls : subClasses) {
            if (cls == null || cls == base) continue;
            if (packagePrefix != null && !cls.getName().startsWith(packagePrefix)) continue;

            if (base.isAssignableFrom(cls)) {
                subTypeNames.add(prettyName(cls));
            }
        }

        List<String> descriptionList = new ArrayList<>(Arrays.asList(description));
        descriptionList.add("");
        descriptionList.add("Extends: " + (superTypes.isEmpty() ? "(none)" : String.join(", ", superTypes)));

        if (!subTypeNames.isEmpty()) {
            descriptionList.add("Extended by: " + String.join(", ", subTypeNames));
        }

        return (C) super.description(descriptionList.toArray(new String[0]));
    }
    private void collectInterfaces(Class<?> type, Set<String> result) {
        for (Class<?> interfaceClass : type.getInterfaces()) {
            if (packagePrefix == null || interfaceClass.getName().startsWith(packagePrefix))
                result.add(prettyName(interfaceClass));
            collectInterfaces(interfaceClass, result);
        }

        Class<?> superclass = type.getSuperclass();
        if (superclass != null && superclass != Object.class) {
            collectInterfaces(superclass, result);
        }
    }

}
