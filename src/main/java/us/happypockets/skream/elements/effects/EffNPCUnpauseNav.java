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
import us.happypockets.skream.util.Citizens;

@Name("Unpause NPC Pathfinding")
@Description({"Unpauses the NPC and allows them to complete any previously assigned pathfinding tasks."})
@Examples({"unpause pathfinding for npc last spawned npc"})
@Since("1.0")
@RequiredPlugins("Citizens")

public class EffNPCUnpauseNav extends Effect {

    static {
        if(Citizens.hasCitizens()) {
            Skript.registerEffect(EffNPCUnpauseNav.class, "(un[ |-]pause|continue) (navigation|path[ |-]finding) for npc %integers%");
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
        return "Unpause navigation for npc effect with expression integer: " + id.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        NPCRegistry reg = CitizensAPI.getNPCRegistry();
        NPC npc;
        for(Integer i : id.getAll(event)){
            npc = reg.getById(i);
            npc.getNavigator().setPaused(false);
        }
    }
}
