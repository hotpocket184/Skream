package us.happypockets.skream.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

@Name("Revoke Advancement")
@Description({"Gives a player an advancement."})
@Examples({"set delay of event-block to 1 second"})

public class EffSetDelay extends Effect {
    static {
        Skript.registerEffect(EffSetDelay.class, "set delay of %blocks% to %integer%");
    }

    private Expression<Block> block;
    private Expression<Integer> integer;

    @SuppressWarnings({ "unchecked", "null" })
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.block = (Expression<Block>) expressions[0];
        this.integer = (Expression<Integer>) expressions[1];
        return true;
    }

    @Override
    public @NonNull String toString(@Nullable Event event, boolean debug) {
        return "Set delay of block: " + block.toString(event, debug) + " and integer expression: " + integer.toString(event, debug);
    }

    @Override
    protected void execute(@NonNull Event event) {
        if (block == null) return;
        if (!block.equals(Material.SPAWNER)) {
            Skript.error("The block cannot be any block. To work, it must be a spawner.");
        }
        for (Block b : block.getAll(event)) {
           if (b.getType() == Material.SPAWNER) {
               String str = integer.toString();
               Integer i = Integer.parseInt(str);
               CreatureSpawner cs = (CreatureSpawner) b.getState();
               cs.setDelay(i);
               cs.update();

            }
        }
    }
}
