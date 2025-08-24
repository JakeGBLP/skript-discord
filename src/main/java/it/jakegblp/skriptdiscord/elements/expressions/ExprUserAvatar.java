package it.jakegblp.skriptdiscord.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.utils.ImageProxy;
import org.jetbrains.annotations.Nullable;

import static it.jakegblp.skriptdiscord.api.skript.utils.SkriptUtils.biTagKleenean;

public class ExprUserAvatar extends SimplePropertyExpression<User, Object> {

    static {
        registerDefault(ExprUserAvatar.class, Object.class, "[:effective|:default] [skript( |-)]discord user avatar [:url|:id]", "users");
    }

    private Kleenean isEffectiveOrDefault, isUrlOrId;

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        isEffectiveOrDefault = biTagKleenean(parseResult, "effective", "default");
        isUrlOrId = biTagKleenean(parseResult, "url", "id");
        if (isEffectiveOrDefault.isTrue() || isUrlOrId.isFalse()) {
            Skript.error("Effective avatar doesn't have an id.");
            return false;
        }
        return super.init(expressions, matchedPattern, isDelayed, parseResult);
    }

    @Override
    public @Nullable Object convert(User from) {
        return switch (isUrlOrId) {
            case TRUE -> switch (isEffectiveOrDefault) {
                case TRUE -> from.getEffectiveAvatarUrl();
                case FALSE -> from.getDefaultAvatarUrl();
                case UNKNOWN -> from.getAvatarUrl();
            };
            case FALSE -> switch (isEffectiveOrDefault) {
                case TRUE -> null;
                case FALSE -> from.getDefaultAvatarId();
                case UNKNOWN -> from.getAvatarId();
            };
            case UNKNOWN -> switch (isEffectiveOrDefault) {
                case TRUE -> from.getEffectiveAvatar();
                case FALSE -> from.getDefaultAvatar();
                case UNKNOWN -> from.getAvatar();
            };
        };
    }

    @Override
    protected String getPropertyName() {
        return switch (isEffectiveOrDefault) {
            case TRUE -> "effective ";
            case FALSE -> "default ";
            case UNKNOWN -> "";
        } + "skript-discord user avatar " + switch (isUrlOrId) {
            case TRUE -> "url ";
            case FALSE -> "id ";
            case UNKNOWN -> "";
        };
    }

    @Override
    public Class<?> getReturnType() {
        return switch (isUrlOrId) {
            case TRUE, FALSE -> String.class;
            case UNKNOWN -> ImageProxy.class;
        };
    }
}
