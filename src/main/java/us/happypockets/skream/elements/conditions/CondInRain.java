package us.happypockets.skream.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import org.bukkit.entity.Entity;

@SuppressWarnings("null")
@Name("In Rain")
@Description({"Checks if the specified entity/entities is/are in rain."})
@Examples({"if player is in rain:"})

public class CondInRain extends PropertyCondition<Entity> {
    static {
        register(CondInRain.class, "in rain", "entities");
    }

    @Override
    public boolean check(final Entity e) {
        return e.isInRain();
    }

    @Override
    protected String getPropertyName() {
        return "in Rain";
    }
}

