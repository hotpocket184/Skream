package us.happypockets.skream.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

@Name("Remove Entity from Team")
@Description({"Removes an entity from a team.", "NOTE: Entities are removed from the team via their ID and players are removed via their name."})
@Examples("remove player from team red")
@Since("1.0")

public class EffRemoveTeam extends Effect {

    static {
        Skript.registerEffect(EffRemoveTeam.class, "(remove|delete) %entities% from team %team%");
    }

    private Expression<Entity> entity;
    private Expression<Team> team;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.entity = (Expression<Entity>) expressions[0];
        this.team = (Expression<Team>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Remove entity from team effect with expression entity: " + entity.toString(event, debug) + " and expression team: " + team.toString(event, debug);
    }

    @Nullable
    @Override
    protected void execute(Event event) {
        if(team != null){
            for(Entity e : entity.getAll(event)){
                if(e.getType() == EntityType.PLAYER) {
                    if(team.getSingle(event).hasEntry(e.getName())) {
                        team.getSingle(event).removeEntry(e.getName());
                    }
                }
                if(!(e.getType() == EntityType.PLAYER)){
                    if(team.getSingle(event).hasEntry(String.valueOf(e.getUniqueId()))) {
                        team.getSingle(event).removeEntry(String.valueOf(e.getUniqueId()));
                    }
                }
            }
        }
    }
}