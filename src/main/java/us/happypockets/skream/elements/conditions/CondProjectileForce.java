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
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

@Name("Has Advancement")
@Description({"Checks if a player an advancement."})
@Examples({"if player has advancement \"adventure/shoot_arrow\":"})

public class CondProjectileForce extends Condition{

    static {
        Skript.registerCondition(CondProjectileForce.class, "force of %projectiles% (1¦is|2¦is(n't| not)) %number%");
    }

    Expression<Projectile> projectile;
    Expression<Number> number;

    @SuppressWarnings({ "unchecked", "null" })
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.projectile = (Expression<Projectile>) expressions[0];
        this.number = (Expression<Number>) expressions[1];
        setNegated(parser.mark  == 1);
        return true;
    }

    @Override
    public @NonNull String toString(@Nullable Event event, boolean debug) {
        return "Projectile expression: " + projectile.toString(event, debug) + " Number expression: " + number.toString(event, debug);
    }

    @Override
    public boolean check(@NonNull Event event) {
        Projectile p = projectile.getSingle(event);
        if (p == null) return isNegated();
        return p. ? isNegated() : !isNegated();
    }
}
