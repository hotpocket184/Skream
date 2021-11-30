package us.happypockets.skream.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.citizensnpcs.api.event.NPCClickEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import us.happypockets.skream.elements.effects.EffSpawnNPC;
import us.happypockets.skream.util.Citizens;

@Name("On NPC Click")
@Description({"Checks when a player clicks an npc."})
@Examples({"npc click:"})
@RequiredPlugins("Citizens")

public abstract class EvntNPCClick extends SimpleEvent {

    static {
        if(Citizens.hasCitizens()) {
            Skript.registerEvent("NPC Click", SimpleEvent.class, NPCClickEvent.class, "npc click");
            EventValues.registerEventValue(NPCClickEvent.class, Integer.class, new Getter<Integer, NPCClickEvent>() {
                @Override
                @Nullable
                public Integer get(NPCClickEvent e) {
                    return e.getNPC().getId();
                }

            }, 0);
            EventValues.registerEventValue(NPCClickEvent.class, Player.class, new Getter<Player, NPCClickEvent>() {
                @Override
                @Nullable
                public Player get(NPCClickEvent e) {
                    return e.getClicker();
                }

            }, 0);
        }

    }
}
