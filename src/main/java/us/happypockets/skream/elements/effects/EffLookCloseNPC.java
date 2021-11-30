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
import net.citizensnpcs.trait.LookClose;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import us.happypockets.skream.util.Citizens;

@Name("Lookclose trait of NPC")
@Description({"Allows you to set the lookclose trait of the specified npc to true/false"})
@Examples({"set lookclose trait of npc last spawned npc to true"})
@Since("1.0")
@RequiredPlugins("Citizens")

public class EffLookCloseNPC extends Effect {

    static {
        if(Citizens.hasCitizens()){
            Skript.registerEffect(EffLookCloseNPC.class, "set look[ |-]close trait of npc %integers% to %boolean%");
        }

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
        return "Look-close trait of npc effect with expression integer: " + id.toString(event, debug) + " and expression boolean " + bool.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        NPCRegistry reg = CitizensAPI.getNPCRegistry();
        NPC npc;
        for(Integer i : id.getAll(event)){
            npc = reg.getById(i);
            npc.getOrAddTrait(LookClose.class).lookClose(bool.getSingle(event));
        }
    }
}
