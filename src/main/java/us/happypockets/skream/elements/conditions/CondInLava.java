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

@Name("In Lava")
@Description({"Checks if the specified entity/entities is/are in lava."})
@Examples({"if player is in lava:"})

public class CondInLava extends Condition {

    static {
        Skript.registerCondition(CondInLava.class, "%entity% (1¦is|2¦is(n't| not)) in lava");
    }

    Expression<Entity> entity;

    @SuppressWarnings({ "unchecked", "null" })
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.entity = (Expression<Entity>) expressions[0];
        setNegated(parser.mark  == 1);
        return true;
    }

    @Override
    public @NonNull String toString(@Nullable Event event, boolean debug) {
        return "Entity in lava " + entity.toString(event, debug);
    }

    @Override
    public boolean check(@NonNull Event event) {
        Entity e = entity.getSingle(event);
        if (e == null) return isNegated();
        return e.isInLava() ? isNegated() : !isNegated();
    }
}
