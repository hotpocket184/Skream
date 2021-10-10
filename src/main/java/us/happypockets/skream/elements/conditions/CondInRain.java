package us.happypockets.skream.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("null")
@Name("In Rain")
@Description({"Checks if the specified entity/entities is/are in rain."})
@Examples({"if player is in rain:"})

public class CondInRain extends Condition {

    static {
        Skript.registerCondition(CondInRain.class, "%entities% (1¦is|2¦is(n't| not)) in rain");
    }

    Expression<Entity> entities;

    @SuppressWarnings({ "unchecked", "null" })
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.entities = (Expression<Entity>) expressions[0];
        setNegated(parser.mark  == 1);
        return true;
    }

    @Override
    public @NonNull String toString(@Nullable Event event, boolean debug) {
        return "Entity in rain " + entities.toString(event, debug);
    }

    @Override
    public boolean check(@NonNull Event event) {
        Entity e = entities.getSingle(event);
        if (e == null) return isNegated();
        return e.isInRain() ? isNegated() : !isNegated();
    }
}

