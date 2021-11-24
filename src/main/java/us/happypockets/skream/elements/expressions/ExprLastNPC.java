package us.happypockets.skream.elements.expressions;


import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Last Spawned NPC")
@Description({"Returns the id of the last spawned npc (that was spawned via this addon)"})
@Examples("set {id} to last spawned npc")
@Since("1.0")
@RequiredPlugins("Citizens")

public class ExprLastNPC extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ExprLastNPC.class, Integer.class, ExpressionType.COMBINED, "[id of] last (created|spawned) npc");
    }

    public static NPC lastNPCCreated;

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Returns the ID of the last created citizen";
    }
    @Override
    @Nullable
    protected Integer[] get(Event event) {
        if(lastNPCCreated != null) {
            return new Integer[]{lastNPCCreated.getId()};
        }
        return null;
    }
}


