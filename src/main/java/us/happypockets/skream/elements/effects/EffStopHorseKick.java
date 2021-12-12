package us.happypockets.skream.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;

@Name("Stop Horse Kick")
@Description({"Makes the specified horse stop doing any animation (even if they're not currently doing the kick animation) for the specified players (through packets)."})
@Examples({"stop horse kick from target for player"})
@Since("1.1")
@RequiredPlugins("ProtocolLib")

public class EffStopHorseKick extends Effect {
    static{
        PluginManager pm = Bukkit.getServer().getPluginManager();
        Plugin protocollib = pm.getPlugin("ProtocolLib");
        if(protocollib.isEnabled()){
            Skript.registerEffect(EffStopHorseKick.class, "stop horse kick[ing] from %livingentity% for %players%");
        }
    }

    private Expression<LivingEntity> horse;
    private Expression<Player> players;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.horse = (Expression<LivingEntity>) expressions[0];
        this.players = (Expression<Player>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Stop Horse Kick effect with expression livingentity: " + horse.toString(event, debug) +  " and expression players " + players.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        LivingEntity ent = horse.getSingle(event);
        if(ent == null){return;}
        if(ent.getType().equals(EntityType.HORSE) || ent.getType().equals(EntityType.SKELETON_HORSE) || ent.getType().equals(EntityType.ZOMBIE_HORSE)){
            ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
            PacketContainer packetContainer = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
            WrappedDataWatcher watcher = new WrappedDataWatcher(horse.getSingle(event));
            watcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(17,WrappedDataWatcher.Registry.get(Byte.class)), (byte) 0);
            packetContainer.getEntityModifier(horse.getSingle(event).getWorld()).write(0, horse.getSingle(event));
            packetContainer.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
            for(Player p : players.getAll(event)){
                try {
                    protocolManager.sendServerPacket(p, packetContainer);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            Skript.error("You may only use the StopHorseKick effect with horses (Zombie, Skeleton, or regular)");
        }

    }
}
