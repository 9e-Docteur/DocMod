package be.ninedocteur.docmod.utils;

import be.ninedocteur.docteam.website.Database;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.List;

public class DatabaseUtils {

    @SerializedName("database")
    @Expose
    private List<Database> database = null;

    public List<Database> getDatabase() {
        return database;
    }

    public void setDatabase(List<Database> database) {
        this.database = database;
    }


}
