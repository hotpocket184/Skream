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
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import us.happypockets.skream.util.Citizens;

@Name("Make NPC Follow")
@Description({"Makes the npc continuously pathfind (follow) to the specified livingentity.", "NOTE: You can stop this from occurring by using the CancelNPCPath effect."})
@Examples({"make npc last spawned npc follow player"})
@Since("1.0")
@RequiredPlugins("Citizens")

public class EffNPCFollow extends Effect {

    static {
        if(Citizens.hasCitizens()) {
            Skript.registerEffect(EffNPCFollow.class, "make npc %integers% follow %livingentity% [with speed %number%]");
        }
    }

    private Expression<Integer> id;
    private Expression<LivingEntity> entity;
    private Expression<Number> speed;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        this.entity = (Expression<LivingEntity>) expressions[1];
        this.speed = (Expression<Number>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Make npc follow effect with expression integer: " + id.toString(event, debug) + " and expression livingentity " + entity.toString(event, debug) + " and expression number " + speed.toString(event, debug);
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
            npc.getNavigator().setTarget(entity.getSingle(event), false);
        }
    }
}
