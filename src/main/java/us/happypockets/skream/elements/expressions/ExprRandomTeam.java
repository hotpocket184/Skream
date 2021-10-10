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
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

@Name("Entries of Team")
@Description({"Returns a list containing the entries in the specified team. (Loop-able)"})
@Examples("broadcast \"%entries of team happypockets%\"")
@Since("1.0")

public class ExprRandomTeam extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprRandomTeam.class, String.class, ExpressionType.COMBINED, "(random team|random element [out] of [all] teams)");
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
        return "Returns a random team";
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        ArrayList<String> teams = new ArrayList<>();
        for(Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()){
            teams.add(team.getName());
        }
        if(teams.size() > 0){
            int num = (int)(Math.random() * teams.size());
            return new String[]{teams.get(num)};
        }
        else{
            return null;
        }
    }
}
