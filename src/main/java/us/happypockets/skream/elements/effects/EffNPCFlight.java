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
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("NPC Flight")
@Description({"Allows you to set the flight mode of the specified npc to false/true"})
@Examples("set flight mode of npc last spawned npc to true")
@Since("1.0")

public class EffNPCFlight extends Effect {

    static {
        Skript.registerEffect(EffNPCFlight.class, "set flight [mode] of npc [with] [the] [id] %integer% to %boolean%");
    }

    private Expression<Integer> id;
    private Expression<Boolean> bool;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        this.bool = (Expression<Boolean>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "NPC flight effect with expression integer: " + id.toString(event, debug) + " and expression boolean: " + bool.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        NPCRegistry reg = CitizensAPI.getNPCRegistry();
        NPC npc = reg.getById(id.getSingle(event));
        npc.setFlyable(bool.getSingle(event));
    }
}
