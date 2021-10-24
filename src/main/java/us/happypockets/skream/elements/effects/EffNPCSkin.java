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

@Name("NPC Skin")
@Description({"Allows you to set the skin of the specified npc to the specified player's name (the string value).", "NOTE: This only works if the npc's type is a player!"})
@Examples("set skin of npc last spawned npc to \"hapily\"")
@Since("1.0")
@RequiredPlugins("Citizens")

public class EffNPCSkin extends Effect {

    static {
        Skript.registerEffect(EffNPCSkin.class, "set skin of npc %integer% to %string%");
    }

    private Expression<Integer> id;
    private Expression<String> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        this.player = (Expression<String>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "NPC vulnerability effect with expression integer: " + id.toString(event, debug) + " and expression string: " + player.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        NPCRegistry reg = CitizensAPI.getNPCRegistry();
        NPC npc = reg.getById(id.getSingle(event));
        npc.data().setPersistent(NPC.PLAYER_SKIN_UUID_METADATA, player.getSingle(event));
        Location npcloc = npc.getStoredLocation();
        npc.despawn();
        npc.spawn(npcloc);
    }
}
