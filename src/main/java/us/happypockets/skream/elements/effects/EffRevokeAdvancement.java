package us.happypockets.skream.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("null")
@Name("Revoke Advancement")
@Description({"Gives a player an advancement."})
@Examples({"revoke \"adventure/shoot_arrow\" from advancements of player"})

public class EffRevokeAdvancement extends Effect{

    static {
        Skript.registerEffect(EffRevokeAdvancement.class, "(remove|revoke) %string% from [the] advancements of %player%");
    }

    private Expression<Player> player;
    private Expression<String> advancement;

    @SuppressWarnings({ "unchecked", "null" })
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        this.player = (Expression<Player>) expressions[1];
        this.advancement = (Expression<String>) expressions[0];
        return true;
    }

    @Override
    public @NonNull String toString(@Nullable Event event, boolean debug) {
        return "Remove advancement effect: " + advancement.toString(event, debug) + " and player expression: " + player.toString(event, debug);
    }

    @Override
    protected void execute(@NonNull Event event) {
        if (player == null) return;
        for (Player user : player.getAll(event)) {
            NamespacedKey key = NamespacedKey.minecraft(advancement.toString().replaceAll("[^a-zA-Z/:_]", ""));
            AdvancementProgress progress = user.getPlayer().getAdvancementProgress(Bukkit.getAdvancement(key));
            for (String criteria : progress.getAwardedCriteria())
                progress.revokeCriteria(criteria);
        }
    }
}