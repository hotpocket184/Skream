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
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.Nullable;

@Name("Remove All Potion Effects")
@Description({"Removes/deletes all potion effects from an entity."})
@Examples("remove all potion effects from player")
@Since("1.0")

public class EffRemovePotions extends Effect {

    static {
        Skript.registerEffect(EffRemovePotions.class, "(remove|delete) all potion effects from %livingentities%");
    }

    private Expression<LivingEntity> entity;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.entity = (Expression<LivingEntity>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Removes all potion effects from living entities.";
    }

    @Nullable
    @Override
    protected void execute(Event event) {
        if (entity == null)  return;
        for(LivingEntity e : entity.getAll(event)){
            for(PotionEffect effect : e.getActivePotionEffects()){
                e.removePotionEffect(effect.getType());
            }
        }
    }
}