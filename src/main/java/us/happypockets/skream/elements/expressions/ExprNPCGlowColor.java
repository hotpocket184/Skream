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
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.ScoreboardTrait;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Glow Color of NPC")
@Description({"Sets/gets the glow color of the specified NPC.", "COLORS: red, dark_red, blue, dark_blue, aqua, dark_aqua, dark_purple, light_purple, black, white, yellow, gold, gray, dark_gray, green and dark_green"})
@Examples({"set glow color of npc last spawned npc to \"red\"", "broadcast \"%glow color of npc last spawned npc%Hello\" # Broadcasts \"Hello\" in the same color of the npc specified in the expression"})
@Since("1.0")

public class ExprNPCGlowColor extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprNPCGlowColor.class, String.class, ExpressionType.COMBINED, "glow[ing] color of npc [with] [the] [id] %integers%");
    }

    private Expression<Integer> id;

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
        id = (Expression<Integer>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Glowing color of npc expression with expression integer: " + id.toString(event, debug);
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        if (id.getSingle(event) != null) {
            return new String[] {String.valueOf(CitizensAPI.getNPCRegistry().getById(id.getSingle(event)).getOrAddTrait(ScoreboardTrait.class).getColor())};
        }
        return null;
    }
    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode){
        if(id != null){
            String x = ((String) delta[0]).toUpperCase();
            ChatColor c = ChatColor.valueOf(x);
            for(Integer ids : id.getAll(event)){
                NPC npc = CitizensAPI.getNPCRegistry().getById(ids);
                if(mode == Changer.ChangeMode.SET) {
                    npc.getOrAddTrait(ScoreboardTrait.class).setColor(c);
                }
            }
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }
}
