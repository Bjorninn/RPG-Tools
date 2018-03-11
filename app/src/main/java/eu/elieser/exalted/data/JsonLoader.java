package eu.elieser.exalted.data;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;

import eu.elieser.exalted.data.genesys.Talents;

/**
 * Created by bjorn on 21/04/16.
 */
public class JsonLoader
{
    public static String loadJsonFromAsset(Context context, String fileName)
    {
        String json = null;

        try
        {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-16");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return json;
    }

    public static Talents genesysTalentsFromJson(Context context)
    {
        String json = loadJsonFromAsset(context, "genesys-talents.json");

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        return gson.fromJson(json, Talents.class);
    }

    public static Charms charmsFromJson(Context context)
    {
        String json = loadJsonFromAsset(context, "charms-json.json");

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        return gson.fromJson(json, Charms.class);
    }

    public static MartialArtsCharms maCharmsFromJson(Context context)
    {
        String json = loadJsonFromAsset(context, "ma-json.json");

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        return gson.fromJson(json, MartialArtsCharms.class);
    }

    public static Keywords keywordsFromJson(Context context)
    {
        String json = loadJsonFromAsset(context, "charm-keywords2.json");

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        return gson.fromJson(json, Keywords.class);
    }
}
