package be.ninedocteur.docmod;

import net.minecraftforge.common.ForgeConfigSpec;

public class DMConfig {

    public class Client {
        /*
      CLIENT
       */
        public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec CLIENT_SPEC;

        public static final ForgeConfigSpec.ConfigValue<Boolean> showVersion;
        public static final ForgeConfigSpec.ConfigValue<Boolean> showVersionInGame;
        public static final ForgeConfigSpec.ConfigValue<Boolean> customtitlescreen;
        public static final ForgeConfigSpec.ConfigValue<Boolean> showWIPTabs;
        public static final ForgeConfigSpec.ConfigValue<Boolean> showWidget;
        public static final ForgeConfigSpec.ConfigValue<Boolean> ThreeDHead;
        public static final ForgeConfigSpec.ConfigValue<Boolean> RPC;
        public static final ForgeConfigSpec.ConfigValue<Boolean> DEV_MODE;
        public static final ForgeConfigSpec.ConfigValue<Boolean> DEV_WARN;


        static {
            //CLIENT
            CLIENT_BUILDER.push("Config for DocMod Client");
            showVersion = CLIENT_BUILDER.comment("Show the version of DocMod. By Default is true").define("Show Version", false);
            showVersionInGame = CLIENT_BUILDER.comment("Show the version of DocMod in game. By Default is false").define("Show Version", false);
            customtitlescreen = CLIENT_BUILDER.comment("Show the custom title screen of DocMod. By Default is true").define("Show Version", true);
            showWIPTabs = CLIENT_BUILDER.comment("Show a new tab on the creative menu containing WIP stuff").define("Show WIP Stuff", false);
            showWidget = CLIENT_BUILDER.comment("Show the DocTeam's Widget, can't be showed if the custom title screen is disable").define("Show Widget", true);
            ThreeDHead = CLIENT_BUILDER.comment("Show in DocTeam Screen a 3D Head instead of a 2D").define("Show 3D Head", true);
            RPC = CLIENT_BUILDER.comment("If you have Discord, this option will if it's on true that you're playing our mod").define("Show DocMod RPC", true);
            DEV_MODE = CLIENT_BUILDER.comment("Public Dev mod does change things, not useful for everyone.").define("PUBLIC DEV MODE", false);
            DEV_WARN = CLIENT_BUILDER.define("Dev Warn", true);
            CLIENT_BUILDER.pop();
            CLIENT_SPEC = CLIENT_BUILDER.build();
        }
    }

    public class Server{
        public static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec SERVER_SPEC;

        public static final ForgeConfigSpec.ConfigValue<Boolean> GunExplosion;
        public static final ForgeConfigSpec.IntValue GunExplosionSize;
        static{
            SERVER_BUILDER.push("Config for DocMod Server");
            GunExplosion = SERVER_BUILDER.comment("If you want that RPG Gun do explosion. Also for the SW Dalek.").define("Gun Explosion", true);
            GunExplosionSize = SERVER_BUILDER.comment("If you want that RPG Gun do explosion").defineInRange("gun_explosion_size", 12, 1, Integer.MAX_VALUE);
            SERVER_BUILDER.pop();
            SERVER_SPEC = SERVER_BUILDER.build();
        }
    }

    public class Admin{
        /*
     CLIENT
      */
        public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec CLIENT_SPEC;

        public static final ForgeConfigSpec.ConfigValue<Boolean> gunCooldown;

        static{
            CLIENT_BUILDER.push("Config for DocMod Client (Admin)");
            gunCooldown = CLIENT_BUILDER.comment("Enable cooldown on the gun.").define("Enable cooldown on gun", true);
            CLIENT_BUILDER.pop();
            CLIENT_SPEC = CLIENT_BUILDER.build();
        }
    }

    private static Server serverConfig;

    public static Server getServerConfig(){
        return serverConfig;
    }
}
