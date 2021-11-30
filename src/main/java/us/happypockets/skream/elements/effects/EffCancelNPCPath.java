package us.happypockets.skream.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import us.happypockets.skream.elements.conditions.CondIsNPC;
import us.happypockets.skream.util.Citizens;

@Name("Cancel NPC Pathfinding")
@Description({"Stops the NPC from completing its pathfinding goal.", "NOTE: This can also be used to stop an NPC from attacking."})
@Examples({"cancel pathfinding for npc last spawned npc"})
@Since("1.0")
@RequiredPlugins("Citizens")

public class EffCancelNPCPath extends Effect {

    static {
        if(Citizens.hasCitizens()){
            Skript.registerEffect(EffCancelNPCPath.class, "(cancel|delete) (path[ |-]finding|[the ]path) for npc %integers%");
        }

    }

    private Expression<Integer> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Cancel npc path with expression integer: " + id.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        NPCRegistry reg = CitizensAPI.getNPCRegistry();
        NPC npc;
        for(Integer i : id.getAll(event)){
            npc = reg.getById(i);
            npc.getNavigator().cancelNavigation();
        }
    }
}
