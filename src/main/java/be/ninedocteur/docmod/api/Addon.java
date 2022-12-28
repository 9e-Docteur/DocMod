package be.ninedocteur.docmod.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;
import net.minecraftforge.forgespi.language.IConfigurable;
import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.language.IModInfo;
import net.minecraftforge.forgespi.locating.ForgeFeature;
import org.apache.maven.artifact.versioning.ArtifactVersion;

import java.net.URL;
import java.util.*;

public class Addon {
    private static Set<Addon> apiMods = new HashSet<>();
    private final String modName;
    private final String modId;
    private final String modVersion;
    private final String modWebsite;
    private final String modIssue;
    //private final Class<?> mainClass;
    //private final ResourceLocation logo;

    public Addon(String modName, String modId, String modVersion, String modWebsite, String modIssue /*Class<?> mainClass*/) {
//        if (mainClass == null) {
//            throw new IllegalArgumentException("Le paramètre mainClass ne peut pas être null");
//        }
        this.modName = modName;
        this.modId = modId;
        this.modVersion = modVersion;
        this.modWebsite = modWebsite;
        this.modIssue = modIssue;
        //this.mainClass = mainClass;
    }

    public String getModId() {
        return modId;
    }

    public String getModVersion() {
        return modVersion;
    }

    public String getModWebsite() {
        return modWebsite;
    }

    public String getModIssue() {
        return modIssue;
    }

    public static Set<Addon> getApiMods() {
        return apiMods;
    }

    public String getModName() {
        return modName;
    }

//    public Class<?> getMainClass() {
//        return mainClass;
//    }

    public IModInfo toModInfo(){
        return new IModInfo() {
            @Override
            public IModFileInfo getOwningFile() {
                return null;
            }

            @Override
            public String getModId() {
                return modId;
            }

            @Override
            public String getDisplayName() {
                return modName;
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public ArtifactVersion getVersion() {
                return null;
            }

            @Override
            public List<? extends ModVersion> getDependencies() {
                return null;
            }

            @Override
            public List<? extends ForgeFeature.Bound> getForgeFeatures() {
                return null;
            }

            @Override
            public String getNamespace() {
                return modName;
            }

            @Override
            public Map<String, Object> getModProperties() {
                return null;
            }

            @Override
            public Optional<URL> getUpdateURL() {
                return Optional.empty();
            }

            @Override
            public Optional<URL> getModURL() {
                return Optional.empty();
            }

            @Override
            public Optional<String> getLogoFile() {
                return Optional.empty();
            }

            @Override
            public boolean getLogoBlur() {
                return false;
            }

            @Override
            public IConfigurable getConfig() {
                return null;
            }
        };
    }


    public static void registerModAsAPI(Addon addon) {
        apiMods.add(addon);
    }
}
