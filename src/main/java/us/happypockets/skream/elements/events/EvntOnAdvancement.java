package us.happypockets.skream.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("null")
@Name("On Advancement Complete")
@Description({"Checks when a player gets an advancement."})
@Examples({"on advancement complete:"})

public abstract class EvntOnAdvancement extends SkriptEvent {

    static {
        Skript.registerEvent("On Advancement", SimpleEvent.class, PlayerAdvancementDoneEvent.class, "[on] [player] advancement (complete|done)");

        EventValues.registerEventValue(PlayerAdvancementDoneEvent.class, String.class, new Getter<String, PlayerAdvancementDoneEvent>() {
            @Override
            @Nullable
            public String get(PlayerAdvancementDoneEvent e) {
                return e.getAdvancement().getKey().getKey();
            }

        }, 0);
    }
}
