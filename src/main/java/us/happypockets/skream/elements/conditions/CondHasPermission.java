package us.happypockets.skream.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

@Name("Has Permission")
@Description({"Checks if a player a permission."})
@Examples({"if player has permission \"skript.admin\":"})

public class CondHasPermission extends Condition {

    static {
        Skript.registerCondition(CondHasAdvancement.class, "%player% (1¦has|2¦does(n't| not) have) [the] permission %string%");
    }

    Expression<Player> player;
    Expression<String> permission;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.permission = (Expression<String>) expressions[1];
        setNegated(parser.mark == 1);
        return true;
    }

    @Override
    public @NonNull String toString(@Nullable Event event, boolean debug) {
        return "Player expression: " + player.toString(event, debug) + " String expression: " + permission.toString(event, debug);
    }

    @Override
    public boolean check(@NonNull Event event) {
        Player p = player.getSingle(event);
        if (p == null) return isNegated();
        return p.hasPermission(permission.toString());
    }
}
