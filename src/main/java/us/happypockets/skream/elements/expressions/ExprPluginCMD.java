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
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Plugin of Command")
@Description({"Returns the name of the plugin that created the specified command."})
@Examples("set {plugin} to plugin of command \"punish\"")
@Since("1.0")

public class ExprPluginCMD extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPluginCMD.class, String.class, ExpressionType.COMBINED, "[the] plugin of [the] command %string%");
    }

    private Expression<String> cmd;

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
        cmd = (Expression<String>) exprs[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Plugin of Command expression with expression string: " + cmd.toString(event, debug);
    }
    @Override
    @Nullable
    protected String[] get(Event event) {
        if(cmd != null){
            PluginCommand a = Bukkit.getServer().getPluginCommand(cmd.getSingle(event));
            if(a == null){
                return null;
            }
            else{
                return new String[]{a.getPlugin().getName()};
            }
        }
        return null;
    }
}
