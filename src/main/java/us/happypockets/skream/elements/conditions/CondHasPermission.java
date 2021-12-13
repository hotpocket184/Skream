package us.happypockets.skream.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.permissions.Permission;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

public class CondHasPermission extends Condition {

    static {
        Skript.registerCondition(CondHasPermission.class, "%players% (1¦has|2¦does(n't| not) have) permission %string%");
    }

    Expression<Player> player;
    Expression<String> permission;

    @SuppressWarnings({ "unchecked", "null" })
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.permission = (Expression<String>) expressions[1];
        setNegated(parser.mark  == 1);
        return true;
    }

    @Override
    public @NonNull String toString(@Nullable Event event, boolean debug) {
        return "Has permission condition with player expression: " + player.toString(event, debug) + " and string expression: " + permission.toString(event, debug);
    }

    @Override
    public boolean check(@NonNull Event event) {
        Player p = player.getSingle(event);
        Permission perm = (Permission) permission;
        if (p == null) return isNegated();
        return p.hasPermission(perm) ? isNegated() : !isNegated();
    }
}
