package us.happypockets.skream.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Set Domestication")
@Description({"Sets the domestication level of a horse, skeleton horse, or zombie horse."})
@Examples("set domestication level of event-entity to 5")

public class EffSetDomestication extends Effect {

    static {
        Skript.registerEffect(EffSetDomestication.class, "set [the] (temper|domestication) [level] of [horse] %livingentity% to %integer%");
    }

    private Expression<LivingEntity> horse;
    private Expression<Integer> integer;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.horse = (Expression<LivingEntity>) expressions[0];
        this.integer = (Expression<Integer>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Set domestication level effect with expression livingentity: " + horse.toString(event, debug) +  " and expression integer " + integer.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        if(horse == null){return;}
        for (LivingEntity h : horse.getAll(event)) {
            if(h.getType().equals(EntityType.HORSE) || h.getType().equals(EntityType.SKELETON_HORSE) || h.getType().equals(EntityType.ZOMBIE_HORSE)){
                Integer i = Integer.parseInt(integer.toString());
                Horse theHorse = (Horse) h;
                theHorse.setDomestication(i);
            }
            else{
                Skript.error("You may only use the SetDomestication effect with horses (Zombie, Skeleton, or Regular).");
            }

        }

    }
}
