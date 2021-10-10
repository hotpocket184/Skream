package us.happypockets.skream.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

@Name("Size of Team")
@Description({"Returns the integer value of the size of the specified team."})
@Examples("broadcast \"%size of team happypockets%\"")
@Since("1.0")

public class ExprTeamSize extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ExprTeamSize.class, Integer.class, ExpressionType.COMBINED, "size of team %team%");
    }

    private Expression<Team> team;

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        team = (Expression<Team>) exprs[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Size of team expression with expression team: " + team.toString(event, debug);
    }
    @Override
    @Nullable
    protected Integer[] get(Event event) {
        if (team.getSingle(event) != null) {
            return new Integer[]{team.getSingle(event).getSize()};
        }
        return null;
    }
}

