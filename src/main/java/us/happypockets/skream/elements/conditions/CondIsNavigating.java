package us.happypockets.skream.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

@Name("NPC Is Navigating")
@Description({"Checks if an npc is pathfinding/navigating."})
@Examples({"if npc last spawned npc is navigating:"})
@RequiredPlugins("Citizens")

public class CondIsNavigating extends Condition {

    static {
        Skript.registerCondition(CondIsNavigating.class, "npc %integer% (1¦is|2¦is(n't| not)) (navigating|pathfinding)");
    }

    Expression<Integer> id;

    @SuppressWarnings({ "unchecked", "null" })
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        setNegated(parser.mark  == 1);
        return true;
    }

    @Override
    public @NonNull String toString(@Nullable Event event, boolean debug) {
        return "Integer expression: " + id.toString(event, debug);
    }

    @Override
    public boolean check(@NonNull Event event) {
        return CitizensAPI.getNPCRegistry().getById(id.getSingle(event)).getNavigator().isNavigating() ? isNegated() : !isNegated();
    }
}
