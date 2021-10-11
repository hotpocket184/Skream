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
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import us.happypockets.skream.elements.expressions.ExprLastNPC;
import us.happypockets.skream.util.ScrubEntityType;

@Name("Register Team")
@Description({"Registers/creates a team"})
@Examples("register team \"red\"")
@Since("1.0")

public class EffSpawnNPC extends Effect {

    static {
        Skript.registerEffect(EffSpawnNPC.class, "spawn [a] npc named %string% at %location% [as %entitytype%]");
    }

    private Expression<String> name;
    private Expression<Location> loc;
    private Expression<EntityType> type;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.name = (Expression<String>) expressions[0];
        this.loc = (Expression<Location>) expressions[1];
        this.type = (Expression<EntityType>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Spawn npc effect with expression string: " + name.toString(event, debug) + " and expression location: " + loc.toString(event, debug) + " and expression type: " + type.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        NPCRegistry reg = CitizensAPI.getNPCRegistry();
        EntityType t = ScrubEntityType.getType(type.toString());
        NPC npc = reg.createNPC(t, name.getSingle(event));
        npc.spawn(loc.getSingle(event));
        ExprLastNPC.lastNPCCreated = npc;
    }
}