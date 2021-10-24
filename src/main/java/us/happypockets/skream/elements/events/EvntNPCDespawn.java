package us.happypockets.skream.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.citizensnpcs.api.event.NPCDespawnEvent;
import org.jetbrains.annotations.Nullable;

public abstract class EvntNPCDespawn extends SimpleEvent {

    static {
        Skript.registerEvent("NPC Despawn", SimpleEvent.class, NPCDespawnEvent.class, "npc de[ |-]spawn");
        EventValues.registerEventValue(NPCDespawnEvent.class, Integer.class, new Getter<Integer, NPCDespawnEvent>() {
            @Override
            @Nullable
            public Integer get(NPCDespawnEvent e) {
                return e.getNPC().getId();
            }

        }, 0);
    }
}
