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

@Name("Team of Entity")
@Description({"Gets the team of the specified entity."})
@Examples("broadcast \"%team of player%\"")
@Since("1.0")

public class ExprEntityTeam extends SimpleExpression<Team> {

    static {
        Skript.registerExpression(ExprEntityTeam.class, Team.class, ExpressionType.COMBINED, "team of %entities%");
    }

    private Expression<Entity> entity;

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
        entity = (Expression<Entity>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Team of entity expression with expression entity: " + entity.toString(event, debug);
    }

    @Override
    @Nullable
    protected Team[] get(Event event) {
        if (entity != null) {
            for(Entity e : entity.getAll(event)){
                for(Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()){
                    if(e.getType() == EntityType.PLAYER) {
                        if(team.hasEntry(e.getName())) {
                            return new Team[]{team};
                        }

                    }
                    if(!(e.getType() == EntityType.PLAYER)){
                        if(team.hasEntry(String.valueOf(e.getUniqueId()))) {
                            return new Team[]{team};
                        }
                    }
                }
            }
        }
        return null;
    }
}
