package fr.ninedocteur.docmod.utils;

import net.minecraft.util.Formatting;

import java.util.HashMap;
import java.util.Map;

public class AwardUtils {


    private static final Map<String, Award> award = new HashMap<>();

    public static final Award NINEDOCTEUR = new Award("0f471df5e6e44bf39f2235c2d7e6a4bb", Awards.HUGE_DEV, Awards.NinetyConfirmed);
    public static final Award PANDAREBEL = new Award("45949380580d4dd385266ee05dd75c22", Awards.HUGE_CONTRIBUTOR);
    public static final Award JOSIA = new Award("a0b89882850942b2921b14facddfb5dc", Awards.HUGE_DEV, Awards.BEST_FRIEND);
    public static final Award KILLAR = new Award("5ff408b588514303afe8e14f3e91c613", Awards.HUGE_DEV);
    //BETA
    public static final Award ITSAYKOW = new Award("2ec82b3fdb754b95a79d47f6d51db13c", Awards.HUGE_CONTRIBUTOR);
    public static final Award BALISTO = new Award("03d113767fb442a89a7c8db28faaac3f", Awards.HUGE_CONTRIBUTOR);
    //FRIEND
    public static final Award ORTARKGENDAR = new Award("785d28c1-4a7e-4ebe-a6b6-7893e52691a6", Awards.BEST_FRIEND, Awards.NinetyConfirmed);

    //DEV
    public static final Award DEV = new Award("380df991-f603-344c-a090-369bad2a924a", Awards.HUGE_DEV, Awards.BEST_FRIEND, Awards.HUGE_CONTRIBUTOR, Awards.NinetyConfirmed);

    //CONTRIBUTOR
    public static final Award GARATIM = new Award("fa319239c24c46199666b324b7a7dfe0", Awards.HUGE_CONTRIBUTOR, Awards.NinetyConfirmed);
    public static final Award ROBAINKS = new Award("c2ad98c1636d4d71994d9878b939f816", Awards.HUGE_CONTRIBUTOR, Awards.NinetyConfirmed);


    public static class Award {
        private final String awardName;
        private final String uuid;

        public Award(String uuid, Awards... award) {
            this.uuid = uuid;

            StringBuilder awardsBuilder = new StringBuilder();
            for (int x = 0; x < award.length; x++)
                awardsBuilder.append(award[x].colorName).append(Formatting.WHITE).append((x + 1 != award.length) ? ", " : "");
            this.awardName = awardsBuilder.toString();

            AwardUtils.award.put(this.uuid, this);
        }

        public String getAwardName() {
            return awardName;
        }

        public String getUuid() {
            return uuid;
        }
    }

    public static Map<String, Award> getAward() {
        return award;
    }

    enum Awards {
        HUGE_DEV(Formatting.DARK_AQUA, "Huge Dev"), NO_AWARDS(Formatting.RED, "No Awards"), HUGE_CONTRIBUTOR(Formatting.AQUA, "Huge Contributor"), BEST_FRIEND(Formatting.BLUE, "Best Friend <3"),
        NinetyConfirmed(Formatting.GREEN, "Ninety-Confirmed");

        private final String colorName;

        Awards(Formatting Formatting, String defaultNaming) {
            this.colorName = Formatting + defaultNaming;
        }
    }
}
