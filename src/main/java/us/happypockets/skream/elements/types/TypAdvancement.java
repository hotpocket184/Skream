package us.happypockets.skream.elements.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.jetbrains.annotations.Nullable;

public class TypAdvancement {
    static{
        Classes.registerClass(new ClassInfo<Advancement>(Advancement.class, "advancement")
                .name("Advancement")
                .description("Vanilla Advancement")
                .parser(new Parser<Advancement>() {
                    @Override
                    @Nullable
                    public boolean canParse(ParseContext context) {
                        return true;
                    }
                    @Override
                    @Nullable
                    public Advancement parse(Advancement s, ParseContext pc){
                        return Bukkit.getAdvancement(s).getKey().getKey();
                    }
                    @Override
                    public String toString(Advancement advancement, int flags) {
                        return advancement.toString();
                    }
                    @Override
                    public String toVariableNameString(Advancement advancement) {
                        return advancement.toString();
                    }
                    public String getVariableNamePattern() {
                        return ".*";
                    }}));
    }
}