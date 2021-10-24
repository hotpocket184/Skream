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
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.trait.trait.MobType;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@Name("Name of NPC")
@Description({"Sets/gets the name of the specifiednpc."})
@Examples({"set name of npc last spawned npc to \"happypockets\"", "broadcast \"%name of npc last spawned npc%\""})
@Since("1.0")

public class ExprToolNPC extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(ExprToolNPC.class, ItemStack.class, ExpressionType.COMBINED, "(tool|held item) of npc [with] [the] [id] %integers%");
    }

    private Expression<Integer> id;

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
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
        return "Tool of npc expression with expression integer: " + id.toString(event, debug);
    }

    @Override
    @Nullable
    protected ItemStack[] get(Event event) {
        if (id.getSingle(event) != null) {
            return new ItemStack[] {CitizensAPI.getNPCRegistry().getById(id.getSingle(event)).getOrAddTrait(Equipment.class).get(Equipment.EquipmentSlot.HAND)};
        }
        return null;
    }
    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode){
        if(mode == Changer.ChangeMode.SET){
            for(Integer ids : id.getAll(event)){
                NPC npc = CitizensAPI.getNPCRegistry().getById(ids);
                npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, (ItemStack)delta[0]);
            }
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(ItemStack.class);
        }
        return null;
    }
}
