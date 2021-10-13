package us.happypockets.skream.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.MobType;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import us.happypockets.skream.util.ScrubEntityType;

@Name("Name of NPC")
@Description({"Sets/gets the name of the specifiednpc."})
@Examples({"set name of npc last spawned npc to \"happypockets\"", "broadcast \"%name of npc last spawned npc%\""})
@Since("1.0")

public class ExprTypeOfNPC extends SimpleExpression<EntityType> {

    static {
        Skript.registerExpression(ExprTypeOfNPC.class, EntityType.class, ExpressionType.COMBINED, "type of npc [with] [the] [id] %integer%");
    }

    private Expression<Integer> id;

    @Override
    public Class<? extends EntityType> getReturnType() {
        return EntityType.class;
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
        return "Type of npc expression with expression integer: " + id.toString(event, debug);
    }

    @Override
    @Nullable
    protected EntityType[] get(Event event) {
        if (id.getSingle(event) != null) {
            return new EntityType[] {CitizensAPI.getNPCRegistry().getById(id.getSingle(event)).getOrAddTrait(MobType.class).getType()};
        }
        return null;
    }
    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode){
        if(id != null){
            NPC npc = CitizensAPI.getNPCRegistry().getById(id.getSingle(event));
            EntityType type = ScrubEntityType.getType(delta[0].toString());
            if(mode == Changer.ChangeMode.SET) {
                npc.getOrAddTrait(MobType.class).setType(type);
            }
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(EntityType.class);
        }
        return null;
    }
}


