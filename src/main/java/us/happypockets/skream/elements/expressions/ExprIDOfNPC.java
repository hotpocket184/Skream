package us.happypockets.skream.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("NPC Id of Entity")
@Description({"Returns the id of the specified entity if they are an npc."})
@Examples("set {id} to npc id of target")
@Since("1.0")
@RequiredPlugins("Citizens")

public class ExprIDOfNPC extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ExprIDOfNPC.class, Integer.class, ExpressionType.COMBINED, "npc id of %entity%");
    }

    private Expression<Entity> entities;

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
        entities = (Expression<Entity>) exprs[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "ID of NPC expression with expression entity: " + entities.toString(event, debug);
    }
    @Override
    @Nullable
    protected Integer[] get(Event event) {
        if(entities != null){
            Entity e = entities.getSingle(event);
            if(e.hasMetadata("NPC")){
                return new Integer[]{CitizensAPI.getNPCRegistry().getNPC(e).getId()};
            }
        }
        return null;
    }
}

