package us.happypockets.skream.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Animals;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

@Name("In Breed Mode")
@Description({"Checks if an animal is in breeding mode."})
@Examples({"if event-entity is in breeding mode:"})

public class CondIsLoveMode extends Condition {

    static {
        Skript.registerCondition(CondIsLoveMode.class, "%livingentities% (1¦is|2¦is(n't| not)) in (love|breed[ing]) mode");
    }

    Expression<LivingEntity> entity;

    @SuppressWarnings({ "unchecked", "null" })
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.entity = (Expression<LivingEntity>) expressions[0];
        setNegated(parser.mark  == 1);
        return true;
    }

    @Override
    public @NonNull String toString(@Nullable Event event, boolean debug) {
        return "Livingentity expression: " + entity.toString(event, debug);
    }

    @Override
    public boolean check(@NonNull Event event) {
        LivingEntity e = entity.getSingle(event);
        if (e == null) return isNegated();
        if (e instanceof Animals) {
            return ((Animals) e).isLoveMode() ? isNegated() : !isNegated();
        } else {
            Skript.error("Only animals can be used in this condition.");
        }
        return true;
    }
}
