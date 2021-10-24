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
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

@Name("Delete/Unregister Team")
@Description({"Unregisters/Deletes a team"})
@Examples("unregister team red")
@Since("1.0")

public class EffDelTeam extends Effect {

    static {
        Skript.registerEffect(EffDelTeam.class, "(delete|remove|unregister) [the] team %team%");
    }

    private Expression<Team> team;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.team = (Expression<Team>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Unregister team effect with expression team: " + team.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        if(team != null){
           team.getSingle(event).unregister();
        }
    }
}