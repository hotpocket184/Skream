package us.happypockets.skream.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.papermc.paper.event.block.DragonEggFormEvent;
import org.bukkit.block.Block;
import org.bukkit.boss.DragonBattle;

public class EvntDragonEggForm extends SimpleEvent {
    static {
        Skript.registerEvent("Dragon Egg Form", SimpleEvent.class, DragonEggFormEvent.class, "dragon egg (form|make|create) [event]");
        EventValues.registerEventValue(DragonEggFormEvent.class, Block.class, new Getter<Block, DragonEggFormEvent>() {
            public Block get(DragonEggFormEvent e) {
                return e.getBlock();
            }

        }, 0);
        EventValues.registerEventValue(DragonEggFormEvent.class, DragonBattle.class, new Getter<DragonBattle, DragonEggFormEvent>() {
            public DragonBattle get(DragonEggFormEvent e) {
                return e.getDragonBattle();
            }

        }, 0);
    }
}
