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
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

@Name("Is Anchor Spawn")
@Description({"Checks if the respawn location of a player is a respawn anchor."})
@Examples({"if respawn location is respawn anchor:"})

public class CondIsAnchorSpawn extends Condition {

    static {
        Skript.registerCondition(us.happypockets.skream.elements.conditions.CondInWater.class, "[the] respawn location (1¦is|2¦is(n't| not)) (a|an) [respawn] anchor");
    }

    Expression<Player> player;

    @SuppressWarnings({ "unchecked", "null" })
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        if (!getParser().isCurrentEvent(PlayerRespawnEvent.class)) {
            Skript.error("The condition of 'is anchor spawn' can only be used in a respawn event.");
        }
        this.player = (Expression<Player>) expressions[0];
        setNegated(parser.mark == 1);
        return true;
    }

    @Override
    public @NonNull String toString(@Nullable Event event, boolean debug) {
        return "Player from respawn anchor " + player.toString(event, debug);
    }

    @Override
    public boolean check(@NonNull Event event) {
        return ((PlayerRespawnEvent)event).isAnchorSpawn() ? isNegated() : !isNegated();
    }
}
