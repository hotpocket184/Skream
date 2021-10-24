package us.happypockets.skream.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.citizensnpcs.api.event.NPCClickEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public abstract class EvntNPCClick extends SimpleEvent {

    static {
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
