package us.happypockets.skream.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import us.happypockets.skream.elements.expressions.ExprLastNPC;

@Name("Spawn NPC")
@Description({"Creates an npc with the specified name and spawns it at the specified location as the specified type if it is set (default type is a player)."})
@Examples("spawn npc named \"hapily\" at player as player")
@Since("1.0")

public class EffSpawnNPC extends Effect {
    private static final BiMap<EntityData, EntityType> CACHE = HashBiMap.create();

    static {
        for (org.bukkit.entity.EntityType e : org.bukkit.entity.EntityType.values()) {
            Class<? extends Entity> c = e.getEntityClass();
            if (c != null)
                CACHE.put(EntityData.fromClass(c), e); // Cache Skript EntityData -> Bukkit EntityType
        }
        Skript.registerEffect(EffSpawnNPC.class, "(spawn|create) [a] npc named %string% at %location% as %entitydata%");
    }

    private Expression<String> name;
    private Expression<Location> loc;
    private Expression<EntityData> type;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.name = (Expression<String>) expressions[0];
        this.loc = (Expression<Location>) expressions[1];
        this.type = (Expression<EntityData>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Spawn npc effect with expression string: " + name.toString(event, debug) + " and expression location: " + loc.toString(event, debug) + " and expression type: " + type.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        NPCRegistry reg = CitizensAPI.getNPCRegistry();
        EntityType t = toBukkitEntityType(type.getSingle(event));
        NPC npc = reg.createNPC(t, name.getSingle(event));
        npc.spawn(loc.getSingle(event));
        ExprLastNPC.lastNPCCreated = npc;
    }
    private static org.bukkit.entity.EntityType toBukkitEntityType(EntityData e){
        return CACHE.get(EntityData.fromClass(e.getType())); // Fix Comparison Issues
    }
    private static EntityData toSkriptEntityData(org.bukkit.entity.EntityType e){
        return CACHE.inverse().get(e);
    }
}
