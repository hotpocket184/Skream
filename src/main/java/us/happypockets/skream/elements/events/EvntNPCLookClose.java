package us.happypockets.skream.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCLookCloseChangeTargetEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public abstract class EvntNPCLookClose extends SimpleEvent {

    static {
        Skript.registerEvent("NPC Look Close", SimpleEvent.class, NPCLookCloseChangeTargetEvent.class, "npc lookclose");
        EventValues.registerEventValue(NPCLookCloseChangeTargetEvent.class, Integer.class, new Getter<Integer, NPCLookCloseChangeTargetEvent>() {
            @Override
            @Nullable
            public Integer get(NPCLookCloseChangeTargetEvent e) {
                return e.getNPC().getId();
            }

        }, 0);
        EventValues.registerEventValue(NPCLookCloseChangeTargetEvent.class, Player.class, new Getter<Player, NPCLookCloseChangeTargetEvent>() {
            @Override
            @Nullable
            public Player get(NPCLookCloseChangeTargetEvent e) {
                return e.getNewTarget();
            }

        }, 0);
    }
}