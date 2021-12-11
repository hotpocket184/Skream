package us.happypockets.skream.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Delay of Block")
@Description({"Returns the delay of a block. (Must be a spawner)"})
@Examples({"broadcast \"%delay of event-block%\""})

public class ExprDelay extends SimpleExpression {

    static {
        Skript.registerExpression(ExprDelay.class, String.class, ExpressionType.COMBINED, "[spawn[ing]] delay of %blocks%");
    }

    private Expression<Block> block;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        block = (Expression<Block>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Block expression: " + block.toString(event, debug);
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        if (block.getSingle(event) != null) {
            if (!block.equals(Material.SPAWNER)) {
                Skript.error("The block cannot be any block. To work, it must be a spawner.");
            }
            for (Block b : block.getAll(event)) {
                if (b.getType() == Material.SPAWNER) {
                    return new String[]{String.valueOf(((CreatureSpawner) b.getState()).getDelay())};
                }
            }
        }
        return null;
    }
}
