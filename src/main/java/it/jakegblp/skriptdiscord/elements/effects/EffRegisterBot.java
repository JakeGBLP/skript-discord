package it.jakegblp.skriptdiscord.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import it.jakegblp.skriptdiscord.api.BotRegistry;
import it.jakegblp.skriptdiscord.api.ListenerRegistry;
import it.jakegblp.skriptdiscord.api.jda.listeners.MessageListener;
import it.jakegblp.skriptdiscord.api.jda.listeners.SessionListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.skriptlang.skript.lang.script.Script;

import java.util.Arrays;
import java.util.List;

public class EffRegisterBot extends Effect {

    static {
        Skript.registerEffect(EffRegisterBot.class,
                "register [new] [:default] [skript( |-)]discord bot [(named|with [custom] name) %-string%[,|[,] and]] with token %string%[[,|[,] and] with [gateway] intents %-gatewayintents%]"
        );
    }

    private Expression<String> nameExpression, tokenExpression;
    private Expression<GatewayIntent> gatewayIntentExpression;
    private Script script;
    private boolean createDefault;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        nameExpression = (Expression<String>) expressions[0];
        tokenExpression = (Expression<String>) expressions[1];
        gatewayIntentExpression = (Expression<GatewayIntent>) expressions[2];
        script = getParser().getCurrentScript();
        createDefault = parseResult.hasTag("default");
        return true;
    }

    @Override
    protected void execute(Event event) {
        String token = tokenExpression.getSingle(event);
        if (token == null) return;
        String id = nameExpression != null ? nameExpression.getSingle(event) : null;
        JDABuilder builder;
        if (gatewayIntentExpression != null) {
            List<GatewayIntent> gatewayIntentList = Arrays.asList(gatewayIntentExpression.getAll(event));
            if (createDefault)
                builder = JDABuilder.createDefault(token, gatewayIntentList);
            else
                builder = JDABuilder.create(token, gatewayIntentList);
        } else if (createDefault)
            builder = JDABuilder.createDefault(token);
        else
            builder = JDABuilder.create(token, List.of());
        JDA bot = builder.addEventListeners(SessionListener.INSTANCE, MessageListener.INSTANCE).build();
        ListenerRegistry.addListeners(script, SessionListener.INSTANCE, MessageListener.INSTANCE);
        BotRegistry.registerBot(id, bot);
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "register new "+(createDefault ? "default ": "")+"skript-discord bot "+ (nameExpression == null ? "" : "named "+nameExpression.toString(event, b)+", ")+"with token "+tokenExpression.toString(event, b) + (gatewayIntentExpression == null ? "" : ", and with gateway intents "+gatewayIntentExpression.toString(event, b));
    }
}
