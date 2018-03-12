package eu.elieser.exalted.genesys.talents;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.elieser.exalted.genesys.CustomTypefaceSpan;

import static eu.elieser.exalted.exalted.charms.CharmHelper.createBoldSpannable;
import static eu.elieser.exalted.exalted.charms.CharmHelper.createUnderlineSpannable;

/**
 * Created by bjornjonsson on 11/03/2018.
 */

public class TalentHelper
{
    private static List<String> symbols = new ArrayList<>(10);
    private static Map<String, String> letters = new HashMap<>();

    public static void initalize()
    {
        symbols.add("[BO]"); // Bonus Die
        symbols.add("[SE]"); // Setback Die
        symbols.add("[AD]"); // Advantage Symbol
        symbols.add("[TH]"); // Threat Symbol
        symbols.add("[SU]"); // Success Symbol
        symbols.add("[FA]"); // Failure Symbol
        symbols.add("[TR]"); // Triumph Symbol
        symbols.add("[DE]"); // Despair Symbol

        letters.put("[BO]", "j");
        letters.put("[SE]", "j");
        letters.put("[AD]", "a");
        letters.put("[TH]", "h");
        letters.put("[SU]", "s");
        letters.put("[FA]", "f");
        letters.put("[TR]", "t");
        letters.put("[DE]", "d");
    }

    public static Spannable createDescriptionSpannable(AssetManager assetManager, String text)
    {
        // Algorithm
        // 1. go through text and find the index of each symbol
        List<Integer> indices = new ArrayList<>();

        for (String symbol : symbols)
        {
            int index = text.indexOf(symbol);

            while (index >= 0)
            {
                indices.add(index);
                index = text.indexOf(symbol, index + 1);
            }
        }

        // 2. replace each found symbol with its letter
//        for (String symbol : symbols)
//        {
//            Log.d("RAG", symbol);
//
//            text = text.replaceAll(symbol, letters.get(symbol));
//
//            Log.d("TAG", text);
//        }
//
       Spannable spannable = new SpannableString(text);
//        Typeface font = Typeface.createFromAsset(assetManager, "GenesysGlyphs.ttf");
//
//        // 3. each index is now (index - (i * 3))
//        for (int i = 0; i < indices.size(); i++)
//        {
//            int index = indices.get(i);
//
//            CustomTypefaceSpan span = new CustomTypefaceSpan(font);
//            spannable.setSpan(span, (index - (i * 3)), index + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//        }


//        for (String symbol : symbols)
//        {
//            int index = text.indexOf(symbol);
//
//            Log.d("TAG", index + "");
//
//            while (index >= 0)
//            {
//                System.out.println(index);
//                CustomTypefaceSpan span = new CustomTypefaceSpan(font);
//
//
//                spannable.setSpan(span, index, index + 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//
//                index = text.indexOf(symbol, index + 1);
//
//                Log.d("TAG", index + "");
//
//            }
//        }

        return spannable;
    }

    public static Spannable testFont(AssetManager assetManager, String text)
    {
        Spannable spannable = new SpannableString(text);
        Typeface font = Typeface.createFromAsset(assetManager, "GenesysGlyphs.ttf");
        CustomTypefaceSpan span = new CustomTypefaceSpan(font);

        spannable.setSpan(span, 0, text.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        return spannable;
    }

    public static Spannable createTierSpannable(String string)
    {
        String text = "Tier: ";
        string = text + string;

        return createBoldSpannable(string, text.length());
    }

    public static Spannable createActivationSpannable(String string)
    {
        String text = "Activation: ";
        string = text + string;

        return createBoldSpannable(string, text.length());
    }

    public static Spannable createRankedSpannable(String string)
    {
        String text = "Ranked: ";
        string = text + string;

        return createBoldSpannable(string, text.length());
    }

    public static Spannable createPrerequisiteSpannable(String string)
    {
        String text = "Prerequisite: ";
        string = text + string;

        return createBoldSpannable(string, text.length());
    }

    public static Spannable createKeywordsSpannable(List<String> strings)
    {
        String text = "Keywords: ";
        String string = text + TextUtils.join(", ", strings);

        return createBoldSpannable(string, text.length());
    }

    public static Spannable createSourceSpannable(List<String> strings)
    {
        String text = "Source: ";
        String string = text + TextUtils.join(", ", strings);

        return createBoldSpannable(string, text.length());
    }

    public static Spannable createNameSpannable(String name)
    {
        return createUnderlineSpannable(name, name.length());
    }
}
