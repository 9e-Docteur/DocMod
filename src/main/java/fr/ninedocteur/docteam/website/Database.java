package fr.ninedocteur.docteam.website;

import fr.ninedocteur.docmod.utils.IOUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * CREATED BY 9E_DOCTEUR
 * ON: 02-02-22
 */
public class Database {

    public static final String Test = IOUtils.readURLContent(WebsiteUtils.getWebsite() + "dev.index");

    public static final String DOCTEAM = IOUtils.readURLContent(WebsiteUtils.getWebsite() + "DocTeam.txt");
    public static final String COPYRIGHT = IOUtils.readURLContent(WebsiteUtils.getWebsite() + "copyright.txt");
    public static final String CHANGELOG = IOUtils.readURLContent(WebsiteUtils.getWebsite() + "changelog.txt");

    public static final String getBetaRankDir() {
        return IOUtils.readURLContent(WebsiteUtils.getWebsite() + "rank/beta/");
    }

    public static final String getRankDir() {
        return IOUtils.readURLContent(WebsiteUtils.getWebsite() + "rank/list/");
    }


    @SerializedName("database_version")
    @Expose
    private String databaseVersion;
    @SerializedName("made_by")
    @Expose
    private String madeBy;

    public String getDatabaseVersion() {
        return databaseVersion;
    }

    public void setDatabaseVersion(String databaseVersion) {
        this.databaseVersion = databaseVersion;
    }

    public String getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
    }

}
