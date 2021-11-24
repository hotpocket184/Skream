package us.happypockets.skream.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import org.bukkit.entity.Entity;

@SuppressWarnings("null")
@Name("In Water")
@Description({"Checks if the specified entity/entities is/are in water."})
@Examples({"if player is in water:"})

public class CondInWater extends PropertyCondition<Entity> {
    static {
        register(CondInWater.class, "in water", "entities");
    }

    @Override
    public boolean check(final Entity e) {
        return e.isInWater();
    }

    @Override
    protected String getPropertyName() {
        return "in Water";
    }
}
