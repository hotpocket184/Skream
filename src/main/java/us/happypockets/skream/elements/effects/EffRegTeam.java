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
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Register Team")
@Description({"Registers/creates a team"})
@Examples("register team \"red\"")
@Since("1.0")

public class EffRegTeam extends Effect {

    static {
        Skript.registerEffect(EffRegTeam.class, "(create|make|register) [a] [new] team %string%");
    }

    private Expression<String> team;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.team = (Expression<String>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Register team effect with expression team: " + team.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        if(team != null){
            if(!(team.getSingle(event).contains(" "))){
                Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(team.getSingle(event));
            }
        }
    }
}