package us.happypockets.skream.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("null")
@Name("Has Advancement")
@Description({"Checks if a player an advancement."})
@Examples({"if player has advancement \"adventure/shoot_arrow\":"})

public class CondHasAdvancement extends Condition{

    static {
        Skript.registerCondition(CondHasAdvancement.class, "%player% (1¦has|2¦does(n't| not) have) [the] advancement %string%");
    }

    Expression<Player> player;
    Expression<String> advancement;

    @SuppressWarnings({ "unchecked", "null" })
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.advancement = (Expression<String>) expressions[1];
        setNegated(parser.mark  == 1);
        return true;
    }

    @Override
    public @NonNull String toString(@Nullable Event event, boolean debug) {
        return "Player expression: " + player.toString(event, debug) + " String expression: " + advancement.toString(event, debug);
    }

    @Override
    public boolean check(@NonNull Event event) {
        Player p = player.getSingle(event);
        if (p == null) return isNegated();
        NamespacedKey key = NamespacedKey.minecraft(advancement.toString().replaceAll("[^a-zA-Z/:_]", ""));
        return p.getPlayer().getAdvancementProgress(Bukkit.getAdvancement(key)).isDone() ? isNegated() : !isNegated();
    }
}
