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

import java.util.ArrayList;
@Name("Entries of Team")
@Description({"Gets all of the entries in the specified team."})
@Examples("broadcast \"%team entries of \"\"happypockets\"\"%\"")
@Since("1.0")

public class ExprTeamEntries extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprTeamEntries.class, String.class, ExpressionType.COMBINED, "entries of team %team%");
    }

    private Expression<Team> team;

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
        team = (Expression<Team>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Allows you to get the entries of a team.";
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        if (team.getSingle(event) != null) {
            ArrayList<String> entries = new ArrayList<>();
            for(String e : team.getSingle(event).getEntries()){
                entries.add(e);
            }
            return entries.toArray(new String[entries.size()]);
        }
        return null;
    }
}
