package be.ninedocteur.docmod.utils;

import java.util.ArrayList;
import java.util.List;

public class TeamUUIDs {
    public static List<String> admin = new ArrayList<>();

    public class Team{

    public static String Ninedocteur = "0f471df5e6e44bf39f2235c2d7e6a4bb";
    public static String DEV = "380df991-f603-344c-a090-369bad2a924a";

    }

    public static void addAdmin(){
        admin.add(Team.DEV);
        admin.add(Team.Ninedocteur);
    }
}
