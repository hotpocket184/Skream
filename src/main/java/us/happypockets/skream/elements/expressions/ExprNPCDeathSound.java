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
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import us.happypockets.skream.util.ScrubNPCSound;

@Name("Prefix of Team")
@Description({"Sets/gets the prefix of the specified team."})
@Examples({"set prefix of team red to \"happypockets\"", "broadcast \"%prefix of team happypockets%\""})
@Since("1.0")

public class ExprNPCDeathSound extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprNPCDeathSound.class, String.class, ExpressionType.COMBINED, "deathsound of npc [with] [the] [id] %integers%");
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
        return "Death sound of npc expression with expression integer: " + id.toString(event, debug);
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        if (id.getSingle(event) != null) {
            return new String[] {CitizensAPI.getNPCRegistry().getById(id.getSingle(event)).data().get(NPC.DEATH_SOUND_METADATA)};
        }
        return null;
    }
    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode){
        if(id != null){
            for(Integer ids : id.getAll(event)){
                NPC npc = CitizensAPI.getNPCRegistry().getById(ids);
                if(mode == Changer.ChangeMode.SET) {
                    String sound = ScrubNPCSound.getSound((String)delta[0]);
                    npc.data().setPersistent(NPC.DEATH_SOUND_METADATA, sound);
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
