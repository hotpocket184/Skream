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
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

@Name("Team by Name")
@Description({"Allows you to get the team type via an expression."})
@Examples("set {team} to team \"hello\"")
@Since("1.0")

public class ExprTeamByName extends SimpleExpression<Team> {

    static {
        Skript.registerExpression(ExprTeamByName.class, Team.class, ExpressionType.COMBINED, "team %string%");
    }

    private Expression<String> name;

    @Override
    public Class<? extends Team> getReturnType() {
        return Team.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        name = (Expression<String>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Team expression with expression string: " + name.toString(event, debug);
    }

    @Override
    @Nullable
    protected Team[] get(Event event) {
        if (name.getSingle(event) != null) {
            return new Team[] {Bukkit.getServer().getScoreboardManager().getMainScoreboard().getTeam(name.getSingle(event))};
        }
        return null;
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return null;
    }
}

