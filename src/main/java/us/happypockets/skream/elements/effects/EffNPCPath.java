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
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import us.happypockets.skream.util.Citizens;

@Name("Make NPC Pathfind")
@Description({"Makes the specified NPC start pathfinding to the specified location.", "NOTE: If the NPC is unable to naturally walk to the location, it will teleport!"})
@Examples({"make npc last spawned npc pathfind to location of target block"})
@Since("1.0")
@RequiredPlugins("Citizens")

public class EffNPCPath extends Effect {

    static {
        if(Citizens.hasCitizens()) {
            Skript.registerEffect(EffNPCPath.class, "make npc %integers% path[ |-]find to [the] [location [at]] %location% [with speed %number%]");
        }

    }

    private Expression<Integer> id;
    private Expression<Location> loc;
    private Expression<Number> speed;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        this.loc = (Expression<Location>) expressions[1];
        this.speed = (Expression<Number>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Make npc path effect with expression integer: " + id.toString(event, debug) + " and expression location " + loc.toString(event, debug) + " and expression number " + speed.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        NPCRegistry reg = CitizensAPI.getNPCRegistry();
        NPC npc;
        Number n;
        if(speed!=null){
            n = speed.getSingle(event);
        }
        else{
            n = 1;
        }
        for(Integer i : id.getAll(event)){
            npc = reg.getById(i);
            npc.getNavigator().getDefaultParameters().baseSpeed(n.floatValue());
            npc.getNavigator().setTarget(loc.getSingle(event));
        }
    }
}
