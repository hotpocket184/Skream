package us.happypockets.skream.elements.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

public class TypTeam {
    static{
        Classes.registerClass(new ClassInfo<Team>(Team.class, "team")
                .name("Team")
                .description("Vanilla team")
                .parser(new Parser<Team>() {
                    @Override
                    @Nullable
                    public boolean canParse(ParseContext context) {
                        return true;
                    }
                    @Override
                    @Nullable
                    public Team parse(String s, ParseContext pc){
                        return Bukkit.getScoreboardManager().getMainScoreboard().getTeam(s);
                    }
                    @Override
                    public String toString(Team team, int flags) {
                        return team.toString();
                    }
                    @Override
                    public String toVariableNameString(Team team) {
                        return team.toString();
                    }
                    public String getVariableNamePattern() {
                        return ".*";
                    }}));
    }
}