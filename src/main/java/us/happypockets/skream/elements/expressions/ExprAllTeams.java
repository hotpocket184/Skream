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
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

@Name("All Teams")
@Description({"Returns a list containing the teams. (Loop-able)"})
@Examples({"broadcast \"%all teams%\"", "set {_} to a random element out of all teams", "loop all teams:"})
@Since("1.0")

public class ExprAllTeams extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprAllTeams.class, String.class, ExpressionType.COMBINED, "[all] teams");
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Returns a list of teams";
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        ArrayList<String> teams = new ArrayList<>();
        for(Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()){
            teams.add(team.getName());
        }
        if(teams.size() > 0){
            return teams.toArray(new String[teams.size()]);
        }
        else{
            return null;
        }
    }
}

