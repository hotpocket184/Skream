package us.happypockets.skream.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Sprint State of Player")
@Description({"Sets/gets the sprint state of the specified player(s)"})
@Examples({"set sprint state of player to true", "broadcast \"sprint state of player\""})
@Since("1.0")

public class ExprSprint extends SimpleExpression<Boolean> {

    static {
        Skript.registerExpression(ExprSprint.class, Boolean.class, ExpressionType.COMBINED, "(sprint|run)[ing] [state] of %players%");
    }

    private Expression<Player> player;

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        player = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Sprint state expression with expression player: " + player.toString(event, debug);
    }

    @Override
    @Nullable
    protected Boolean[] get(Event event) {
        if (player.getSingle(event) != null) {
            return new Boolean[] {player.getSingle(event).isSprinting()};
        }
        return null;
    }
    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode){
        if(player != null){
            for(Player p : player.getAll(event)){
                if(mode == Changer.ChangeMode.SET) {
                    Boolean x = ((Boolean) delta[0]);
                    p.setSprinting(x);
                }
            }

        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return null;
    }
}



