package us.happypockets.skream.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.citizensnpcs.api.event.NPCLookCloseChangeTargetEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import us.happypockets.skream.util.Citizens;

@Name("On NPC RightClick")
@Description({"Checks when a player rightclicks an npc."})
@Examples({"npc rightclick:"})
@RequiredPlugins("Citizens")

public abstract class EvntNPCRightClick extends SimpleEvent {

    static {
        if(Citizens.hasCitizens()) {
            Skript.registerEvent("PC Rightclick", SimpleEvent.class, NPCRightClickEvent.class, "npc rightclick");
            EventValues.registerEventValue(NPCRightClickEvent.class, Integer.class, new Getter<Integer, NPCRightClickEvent>() {
                @Override
                @Nullable
                public Integer get(NPCRightClickEvent e) {
                    return e.getNPC().getId();
                }

            }, 0);
            EventValues.registerEventValue(NPCRightClickEvent.class, Player.class, new Getter<Player, NPCRightClickEvent>() {
                @Override
                @Nullable
                public Player get(NPCRightClickEvent e) {
                    return e.getClicker();
                }

            }, 0);
        }

    }
}
