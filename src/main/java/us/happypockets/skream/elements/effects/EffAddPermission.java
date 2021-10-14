package us.happypockets.skream.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.npc.NPC;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.actionlog.ActionLogger;
import net.luckperms.api.context.ContextManager;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.LuckPermsEvent;
import net.luckperms.api.messaging.MessagingService;
import net.luckperms.api.messenger.MessengerProvider;
import net.luckperms.api.metastacking.MetaStackFactory;
import net.luckperms.api.model.group.GroupManager;
import net.luckperms.api.model.user.UserManager;
import net.luckperms.api.node.NodeBuilderRegistry;
import net.luckperms.api.node.matcher.NodeMatcherFactory;
import net.luckperms.api.platform.Platform;
import net.luckperms.api.platform.PlayerAdapter;
import net.luckperms.api.platform.PluginMetadata;
import net.luckperms.api.query.QueryOptionsRegistry;
import net.luckperms.api.track.TrackManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("null")
@Name("Add Permission")
@Description({"Gives a player a permission."})
@Examples({"add \"skript.admin\" to permissions of player"})

public class EffAddPermission extends Effect {
    LuckPerms api;

    static {
        Skript.registerEffect(EffAddAdvancement.class, "add [the [permission]] %string% to [the] permissions of %player%");
    }

    private Expression<Player> player;
    private Expression<String> permission;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[1];
        this.permission = (Expression<String>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Add permission effect with string expression: " + permission.toString(event, debug) + " and with player expression: " + player.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        if (player == null) return;
        for (Player p : player.getAll(event)) {
            LuckPerms.
        }

    }
}