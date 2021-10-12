package us.happypockets.skream.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import us.happypockets.skream.util.ServerIdeas;

@Name("Random Server Idea")
@Description({"Returns a random server idea"})
@Examples("broadcast \"%random server idea%\"")
@Since("1.0")

public class ExprRandomIdea extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprRandomIdea.class, String.class, ExpressionType.COMBINED, "random server idea");
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Returns a random server idea";
    }
    @Override
    @Nullable
    protected String[] get(Event event) {
        return new String[]{ServerIdeas.getRandom()};
    }
}
