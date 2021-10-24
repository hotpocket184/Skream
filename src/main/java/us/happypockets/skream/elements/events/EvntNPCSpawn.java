package us.happypockets.skream.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.citizensnpcs.api.event.NPCSpawnEvent;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

@Name("On NPC Spawn")
@Description({"Checks when an npc spawns.", "NOTE: This includes respawning."})
@Examples({"npc spawn:"})
@RequiredPlugins("Citizens")

public abstract class EvntNPCSpawn extends SimpleEvent {

    static {
        Skript.registerEvent("NPC Spawn", SimpleEvent.class, NPCSpawnEvent.class, "npc spawn");
        EventValues.registerEventValue(NPCSpawnEvent.class, Integer.class, new Getter<Integer, NPCSpawnEvent>() {
            @Override
            @Nullable
            public Integer get(NPCSpawnEvent e) {
                return e.getNPC().getId();
            }

        }, 0);
        EventValues.registerEventValue(NPCSpawnEvent.class, Location.class, new Getter<Location, NPCSpawnEvent>() {
            @Override
            @Nullable
            public Location get(NPCSpawnEvent e) {
                return e.getLocation();
            }

        }, 0);
    }
}
