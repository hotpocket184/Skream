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

@Name("Suffix of Team")
@Description({"Sets/gets the prefix of the specified team."})
@Examples({"set suffix of team \"team\" to \"happypockets\"", "broadcast \"%suffix of team \"\"happypockets\"\"%\""})
@Since("1.0")

public class ExprTeamSuffix extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprTeamSuffix.class, String.class, ExpressionType.COMBINED, "suffix of team %team%");
    }

    private Expression<Team> team;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
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
        return "Allows you to get the suffix of a team & set it.";
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        if (team.getSingle(event) != null) {
            return new String[] {team.getSingle(event).getSuffix()};
        }
        return null;
    }
    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode){
        Team t = team.getSingle(event);
        if(t != null){
            if(mode == Changer.ChangeMode.SET) {
                String x = ((String) delta[0]);
                t.setSuffix(x);
            }
            else if(mode == Changer.ChangeMode.RESET) {
                t.setSuffix("");
            }
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.RESET) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }
}

