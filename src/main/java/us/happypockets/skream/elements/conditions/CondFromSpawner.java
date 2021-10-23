package us.happypockets.skream.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import org.bukkit.entity.Entity;

@Name("From Spawner")
@Description({"Checks if an entity is from a spawner."})
@Examples({"if event-entity is from a spawner:"})

public class CondFromSpawner extends PropertyCondition<Entity> {
    static {
        register(CondFromSpawner.class, "from a spawner", "entities");
    }

    @Override
    public boolean check(final Entity e) {
        return e.fromMobSpawner();
    }

    @Override
    protected String getPropertyName() {
        return "from a spawner";
    }
}
