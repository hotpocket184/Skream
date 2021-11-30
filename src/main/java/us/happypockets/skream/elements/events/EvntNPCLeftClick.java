package us.happypockets.skream.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.citizensnpcs.api.event.NPCDespawnEvent;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import us.happypockets.skream.util.Citizens;

@Name("On NPC LeftClick")
@Description({"Checks when a player leftclicks an npc."})
@Examples({"npc leftclick:"})
@RequiredPlugins("Citizens")

public abstract class EvntNPCLeftClick extends SimpleEvent {

    static {
        if(Citizens.hasCitizens()) {
            Skript.registerEvent("NPC Leftclick", SimpleEvent.class, NPCLeftClickEvent.class, "npc leftclick");
            EventValues.registerEventValue(NPCLeftClickEvent.class, Integer.class, new Getter<Integer, NPCLeftClickEvent>() {
                @Override
                @Nullable
                public Integer get(NPCLeftClickEvent e) {
                    return e.getNPC().getId();
                }

            }, 0);
            EventValues.registerEventValue(NPCLeftClickEvent.class, Player.class, new Getter<Player, NPCLeftClickEvent>() {
                @Override
                @Nullable
                public Player get(NPCLeftClickEvent e) {
                    return e.getClicker();
                }

            }, 0);
        }

    }
}
