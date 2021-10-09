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
import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

@Name("Color of Team")
@Description({"Sets/gets the color of the specified team.", "COLORS: red, dark_red, blue, dark_blue, aqua, dark_aqua, dark_purple, light_purple, black, white, yellow, gold, gray, dark_gray, green and dark_green"})
@Examples({"set team color of \"happypockets\" to \"red\"", "broadcast \"%team color of \"\"happypockets\"\"%Hello\" # Broadcasts \"Hello\" in the same color of the team specified in the expression"})
@Since("1.0")

public class ExprTeamColor extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprTeamColor.class, String.class, ExpressionType.COMBINED, "color of team %team%");
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
        return "Allows you to get the color of a team & set it.";
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        if (team.getSingle(event) != null) {
            return new String[] {String.valueOf(team.getSingle(event).getColor())};
        }
        return null;
    }
    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode){
        Team t = team.getSingle(event);
        if(t != null){
            if(mode == Changer.ChangeMode.SET) {
                String x = ((String) delta[0]).toUpperCase();
                ChatColor c = ChatColor.valueOf(x);
                t.setColor(c);
            }
            else if(mode == Changer.ChangeMode.RESET) {
                t.setColor(ChatColor.WHITE);
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
