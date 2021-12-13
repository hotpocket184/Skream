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

@Name("Make Horse Kick")
@Description({"Makes the specified horse do the kicking animation for the specified players (through packets).", "NOTE: You can stop this from occurring by using the StopHorseKick effect."})
@Examples({"make horse target kick for player"})
@Since("1.1")
@RequiredPlugins("ProtocolLib")

public class EffMakeHorseKick extends Effect {
    static{
        PluginManager pm = Bukkit.getServer().getPluginManager();
        Plugin protocollib = pm.getPlugin("ProtocolLib");
        if(protocollib.isEnabled()){
            Skript.registerEffect(EffMakeHorseKick.class, "make [horse] %livingentity% kick for %players%");
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
        return "Make Horse Kick effect with expression livingentity: " + horse.toString(event, debug) +  " and expression players " + players.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        LivingEntity ent = horse.getSingle(event);
        if(ent == null){return;}
        if(ent.getType().equals(EntityType.HORSE) || ent.getType().equals(EntityType.SKELETON_HORSE) || ent.getType().equals(EntityType.ZOMBIE_HORSE)){
            ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
            PacketContainer packetContainer = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
            WrappedDataWatcher watcher = new WrappedDataWatcher(ent);
            watcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(17,WrappedDataWatcher.Registry.get(Byte.class)), (byte) 32);
            packetContainer.getEntityModifier(ent.getWorld()).write(0, ent);
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
            Skript.error("You may only use the MakeHorseKick effect with horses (Zombie, Skeleton, or regular)");
        }

    }
}
