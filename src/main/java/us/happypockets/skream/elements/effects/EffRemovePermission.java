package us.happypockets.skream.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("null")
@Name("Remove Permission")
@Description({"Removes a permission from a player."})
@Examples({"remove \"skript.admin\" to permissions of player"})

public class EffRemovePermission extends Effect {

    static {
        Skript.registerEffect(EffRemovePermission.class, "remove [the [permission]] %string% from [the] permissions of %player%");
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
        return "Remove permission effect with string expression: " + permission.toString(event, debug) + " and with player expression: " + player.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        if (player == null) return;
        for (Player p : player.getAll(event)) {
            p.getEffectivePermissions().remove((PermissionAttachmentInfo) permission);
        }
    }
}
