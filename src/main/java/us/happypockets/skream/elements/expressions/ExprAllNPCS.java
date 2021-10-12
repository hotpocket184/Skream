package us.happypockets.skream.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

@Name("All NPCs")
@Description({"Returns a list containing the id of all npcs. (Loop-able)"})
@Examples({"broadcast \"%all npcs%\"", "set {_} to a random element out of all teams", "loop all teams:", "set glowing of npc all npcs to true"})
@Since("1.0")

public class ExprAllNPCS extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ExprAllNPCS.class, Integer.class, ExpressionType.COMBINED, "[all] npcs");
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Returns a list of NPC ids";
    }

    @Override
    @Nullable
    protected Integer[] get(Event event) {
        ArrayList<Integer> npcs = new ArrayList<>();
        for(NPC npc : CitizensAPI.getNPCRegistries().iterator().next().sorted()){
            npcs.add(npc.getId());
        }
        if(npcs.size() > 0){
            return npcs.toArray(new Integer[npcs.size()]);
        }
        else{
            return null;
        }
    }
}


