package us.happypockets.skream.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.citizensnpcs.api.event.NPCCreateEvent;
import net.citizensnpcs.api.event.NPCDespawnEvent;
import org.jetbrains.annotations.Nullable;
import us.happypockets.skream.util.Citizens;

@Name("On NPC Despawn")
@Description({"Checks when an npc despawns."})
@Examples({"npc despawn:"})
@RequiredPlugins("Citizens")

public abstract class EvntNPCDespawn extends SimpleEvent {

    static {
        if(Citizens.hasCitizens()) {
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
}
