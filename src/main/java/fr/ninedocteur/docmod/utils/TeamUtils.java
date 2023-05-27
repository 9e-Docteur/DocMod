package fr.ninedocteur.docmod.utils;

import net.minecraft.util.Formatting;

import java.util.HashMap;
import java.util.Map;

public class TeamUtils {

    private static final Map<String, TeamMember> teamMembers = new HashMap<>();

    public static final TeamMember NINEDOCTEUR = new TeamMember("0f471df5e6e44bf39f2235c2d7e6a4bb", Title.FOUNDER);
    public static final TeamMember PANDAREBEL = new TeamMember("45949380580d4dd385266ee05dd75c22", Title.ADMIN, Title.ASSETS_MANAGER);
    public static final TeamMember JOSIA = new TeamMember("a0b89882850942b2921b14facddfb5dc", Title.ADMIN, Title.DEVELOPER);
    public static final TeamMember KILLAR = new TeamMember("1ca7ce7d08b843b69df5d4752b81dfb4", Title.ADMIN, Title.DEVELOPER);
    //BETA
    public static final TeamMember ITSAYKOW = new TeamMember("2ec82b3fdb754b95a79d47f6d51db13c", Title.CONTRIBUTOR, Title.BETA);
    public static final TeamMember BALISTO = new TeamMember("03d113767fb442a89a7c8db28faaac3f", Title.CONTRIBUTOR, Title.BETA);

    //FRIEND
    public static final TeamMember ORTARKGENDAR = new TeamMember("785d28c1-4a7e-4ebe-a6b6-7893e52691a6", Title.FRIEND);

    //DEV
    public static final TeamMember DEV = new TeamMember("380df991-f603-344c-a090-369bad2a924a", Title.DEVELOPER);

    //CONTRIBUTOR
    public static final TeamMember GARATIM = new TeamMember("fa319239c24c46199666b324b7a7dfe0", Title.CONTRIBUTOR);
    public static final TeamMember ROBAINKS = new TeamMember("c2ad98c1636d4d71994d9878b939f816", Title.CONTRIBUTOR);


    public static class TeamMember {
        private final String titles;
        private final String uuid;

        public TeamMember(String uuid, Title... title) {
            this.uuid = uuid;

            StringBuilder titlesBuilder = new StringBuilder();
            for (int x = 0; x < title.length; x++)
                titlesBuilder.append(title[x].colorName).append(Formatting.WHITE).append((x + 1 != title.length) ? ", " : "");
            this.titles = titlesBuilder.toString();

            TeamUtils.teamMembers.put(this.uuid, this);
        }

        public String getTitles() {
            return titles;
        }

        public String getUuid() {
            return uuid;
        }
    }

    public static Map<String, TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public enum Title {
        FOUNDER(Formatting.RED, "Founder"), ADMIN(Formatting.RED, "Admin"),
        DEVELOPER(Formatting.AQUA, "Developer"), FRIEND(Formatting.GREEN, "Friend"),
        CONTRIBUTOR(Formatting.BLUE, "Contributor"), ASSETS_MANAGER(Formatting.DARK_PURPLE, "Assets Manager"),
        BETA(Formatting.DARK_BLUE, "Beta"), PLAYER(Formatting.WHITE, "Player");

        private final String colorName;

        Title(Formatting Formatting, String defaultNaming) {
            this.colorName = Formatting + defaultNaming;
        }
    }
}
