package us.happypockets.skream.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import org.bukkit.entity.Entity;

@Name("In Lava")
@Description({"Checks if the specified entity/entities is/are in lava."})
@Examples({"if player is in lava:"})

public class CondInLava extends PropertyCondition<Entity> {
    static {
        register(CondInLava.class, "in lava", "entities");
    }

    @Override
    public boolean check(final Entity e) {
        return e.isInLava();
    }

    @Override
    protected String getPropertyName() {
        return "in Lava";
    }
}
