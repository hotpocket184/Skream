package us.happypockets.skream.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import us.happypockets.skream.util.Citizens;

@Name("Name of NPC")
@Description({"Sets/gets the name of the specified npc."})
@Examples({"set name of npc last spawned npc to \"happypockets\"", "broadcast \"%name of npc last spawned npc%\""})
@Since("1.0")
@RequiredPlugins("Citizens")

public class ExprNPCName extends SimpleExpression<String> {

    static {
        if(Citizens.hasCitizens()) {
            Skript.registerExpression(ExprNPCName.class, String.class, ExpressionType.COMBINED, "name of npc [with] [the] [id] %integer%");
        }

    }

    private Expression<Integer> id;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        id = (Expression<Integer>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Name of npc expression with expression integer: " + id.toString(event, debug);
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        if (id.getSingle(event) != null) {
            return new String[] {CitizensAPI.getNPCRegistry().getById(id.getSingle(event)).getName()};
        }
        return null;
    }
    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode){
        if(id != null){
            NPC npc = CitizensAPI.getNPCRegistry().getById(id.getSingle(event));
            if(mode == Changer.ChangeMode.SET) {
                npc.setName((String)delta[0]);
            }
            else if(mode == (Changer.ChangeMode.DELETE)) {
                npc.setName("");
            }
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }
}

