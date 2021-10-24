package us.happypockets.skream.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.trait.LookClose;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Despawn NPC")
@Description({"Removes the specified NPC from the world (doesn't delete data completely) but allows it to be respawned if needed (See Respawn NPC effect)"})
@Examples({"set {id} to last spawned npc", "despawn npc {id}"})
@Since("1.0")

public class EffNPCAttack extends Effect {

    static {
        Skript.registerEffect(EffNPCAttack.class, "make npc [with] [the] [id] %integers% (hit|attack) %livingentity%");
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
