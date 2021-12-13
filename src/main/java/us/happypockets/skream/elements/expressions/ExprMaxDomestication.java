package us.happypockets.skream.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

@Name("Max Domestication")
@Description({"Sets/gets the domestication (temper) of a horse, skeleton horse, or zombie horse."})
@Examples("set the max domestication level of event-entity to 5")

public class ExprMaxDomestication extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ExprMaxDomestication.class, Integer.class, ExpressionType.COMBINED, "[the] max (temper|domestication) [level] of %livingentities%");
    }

    private Expression<LivingEntity> horse;

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        horse = (Expression<LivingEntity>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Max domestication level expression with expression livingentity: " + horse.toString(event, debug);
    }

    @Override
    @Nullable
    protected Integer[] get(Event event) {
        int i = 0;
        ArrayList<Integer> ints = new ArrayList<>();
        for(LivingEntity h : horse.getAll(event)) {
            if(h != null) {
                if(h.getType().equals(EntityType.HORSE) || h.getType().equals(EntityType.SKELETON_HORSE) || h.getType().equals(EntityType.ZOMBIE_HORSE)) {
                    Horse theHorse = (Horse) horse.getSingle(event);
                    ints.add(theHorse.getMaxDomestication());
                }else{
                    i = i + 1;
                }
            }
        }
        if(i > 0){
            Skript.error("You may only use the SetMaxDomestication effect with horses (Zombie, Skeleton, or Regular).");
        }
        return ints.toArray(new Integer[0]);
    }
    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode){
        if(horse != null){
            if(mode == Changer.ChangeMode.SET) {
                for(LivingEntity e : horse.getAll(event)){
                    Horse h = (Horse) e;
                    Integer x = ((Integer) delta[0]);
                    h.setMaxDomestication(x);
                }

            }
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Integer.class);
        }
        return null;
    }
}
