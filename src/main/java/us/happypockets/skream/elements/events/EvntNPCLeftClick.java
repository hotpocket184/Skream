package us.happypockets.skream.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public abstract class EvntNPCLeftClick extends SimpleEvent {

    static {
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
