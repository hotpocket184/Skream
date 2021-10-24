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
import org.jetbrains.annotations.Nullable;

@Name("On NPC Create")
@Description({"Checks when an npc object is created (not spawned)."})
@Examples({"npc create:"})
@RequiredPlugins("Citizens")

public abstract class EvntNPCCreate extends SimpleEvent {

    static {
        Skript.registerEvent("NPC Create", SimpleEvent.class, NPCCreateEvent.class, "npc create");
        EventValues.registerEventValue(NPCCreateEvent.class, Integer.class, new Getter<Integer, NPCCreateEvent>() {
            @Override
            @Nullable
            public Integer get(NPCCreateEvent e) {
                return e.getNPC().getId();
            }

        }, 0);
    }
}
