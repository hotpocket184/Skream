package us.happypockets.skream.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("null")
@Name("On Velocity Change")
@Description({"Checks when a player's velocity changes."})
@Examples({"on velocity change:"})

public abstract class EvntVelocityChange extends SkriptEvent {

    static {
        Skript.registerEvent("Velocity Change", SimpleEvent.class, PlayerVelocityEvent.class, "[player] velocity (change|shift)");
        EventValues.registerEventValue(PlayerVelocityEvent.class, Vector.class, new Getter<Vector, PlayerVelocityEvent>() {
            @Override
            @Nullable
            public Vector get(PlayerVelocityEvent e) {
                return e.getVelocity();
            }

        }, 0);
        EventValues.registerEventValue(PlayerVelocityEvent.class, Player.class, new Getter<Player, PlayerVelocityEvent>() {
            @Override
            @Nullable
            public Player get(PlayerVelocityEvent e) {
                return e.getPlayer();
            }

        }, 0);
    }
}