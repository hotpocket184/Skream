package us.happypockets.skream.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class ExprPoseOfEntity extends SimpleExpression<String> {
    static {
        Skript.registerExpression(ExprPoseOfEntity.class, String.class, ExpressionType.COMBINED, "pose of %entities%");
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
