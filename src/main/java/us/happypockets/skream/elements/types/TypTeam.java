package us.happypockets.skream.elements.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;

import ch.njol.skript.registrations.Classes;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Team;

public class TypTeam {
    static{
        Classes.registerClass(new ClassInfo<Team>(Team.class, "team")
                .user("teams?")
                .name("Team")
                .description("Vanilla team")
                .parser(new Parser<Team>() {
                    @Override
                    public Team parse(String s, ParseContext pc){
                        return Bukkit.getScoreboardManager().getMainScoreboard().getTeam(s);
                    }
                    @Override
                    public boolean canParse(ParseContext context) {
                        return true;
                    }
                    @Override
                    public String toString(Team team, int flags) {
                        return "" + team.getName();
                    }
                    @Override
                    public String toVariableNameString(Team team) {
                        return "" + team.getName();
                    }
                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }}));
    }
}
