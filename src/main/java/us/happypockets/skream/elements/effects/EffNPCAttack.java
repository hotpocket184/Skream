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

@Name("Make NPC Attack")
@Description({"Makes the npc start pathfinding to the specified livingentity & start attacking when they get close enough.", "NOTE: You can stop this from occurring by using the CancelNPCPath effect."})
@Examples({"make npc last spawned npc attack player"})
@Since("1.0")
@RequiredPlugins("Citizens")

public class EffNPCAttack extends Effect {

    static {
        Skript.registerEffect(EffNPCAttack.class, "make npc %integers% (hit|attack) %livingentity%");
    }

    private Expression<Integer> id;
    private Expression<LivingEntity> entity;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        this.entity = (Expression<LivingEntity>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Make npc attack effect with expression integer: " + id.toString(event, debug) + " and expression livingentity " + entity.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        NPCRegistry reg = CitizensAPI.getNPCRegistry();
        NPC npc;
        for(Integer i : id.getAll(event)){
            npc = reg.getById(i);
            npc.getNavigator().setTarget(entity.getSingle(event), true);
        }
    }
}
