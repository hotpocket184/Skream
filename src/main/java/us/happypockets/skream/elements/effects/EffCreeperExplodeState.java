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

public class EffCreeperExplodeState extends Effect {
    static{
        PluginManager pm = Bukkit.getServer().getPluginManager();
        Plugin protocollib = pm.getPlugin("ProtocolLib");
        if(protocollib.isEnabled()){
            Skript.registerEffect(EffCreeperExplodeState.class, "set [creeper] explo(de|sion) state of %livingentity% to %boolean% for %players%");
        }
    }

    private Expression<LivingEntity> creeper;
    private Expression<Player> players;
    private Expression<Boolean> bool;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.creeper = (Expression<LivingEntity>) expressions[0];
        this.bool = (Expression<Boolean>) expressions[1];
        this.players = (Expression<Player>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Creeper Explode State effect with expression livingentity: " + creeper.toString(event, debug) + ", expression players: " + players.toString(event, debug) + " and expression boolean: " + bool.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        LivingEntity ent = creeper.getSingle(event);
        Integer val;
        if(ent == null){return;}
        if(ent.getType().equals(EntityType.CREEPER)){
            if(bool.getSingle(event).equals(true)){
                val = 1;
            }
            else{
                val = -1;
            }
            ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
            PacketContainer packetContainer = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
            WrappedDataWatcher watcher = new WrappedDataWatcher(ent);
            watcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(16,WrappedDataWatcher.Registry.get(Integer.class)), val);
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
            Skript.error("You may only use the CreeperExplodeState effect with creepers.");
        }

    }
}
