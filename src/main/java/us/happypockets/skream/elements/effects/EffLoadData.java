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
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

@Name("Load Data")
@Description({"Loads the saved data of a player."})
@Examples({"load player data of \"hotpocket184\" parsed as player"})

public class EffLoadData extends Effect {

    static {
        Skript.registerEffect(EffLoadData.class, "load [player] data (of|for) %players%");
    }

    private Expression<Player> player;

    @SuppressWarnings({ "unchecked" })
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        return true;
    }

    @Override
    public @NonNull String toString(@Nullable Event event, boolean debug) {
        return "Save data effect with player expression: " + player.toString(event, debug);
    }

    @Override
    protected void execute(@NonNull Event event) {
        if (player == null) return;
        for (Player user : player.getAll(event)) {
            user.loadData();
        }
    }
}
