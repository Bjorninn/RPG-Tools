package eu.elieser.exalted.genesys.talents;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import eu.elieser.exalted.genesys.CustomTypefaceSpan;

import static eu.elieser.exalted.exalted.charms.CharmHelper.createBoldSpannable;

/**
 * Created by bjornjonsson on 11/03/2018.
 */

public class TalentHelper
{
    private static List<String> symbols = new ArrayList<>(10);

    public TalentHelper()
    {
        symbols.add("[BO]"); // Bonus Die
        symbols.add("[SE]"); // Setback Die
        symbols.add("[AD]"); // Advantage Symbol
        symbols.add("[TH]"); // Threat Symbol
        symbols.add("[SU]"); // Success Symbol
        symbols.add("[FA]"); // Failure Symbol
        symbols.add("[TR]"); // Triumph Symbol
        symbols.add("[DE]"); // Despair Symbol
    }

    public static Spannable createDescriptionSpannable(AssetManager assetManager, String text)
    {
        Spannable spannable = new SpannableString(text);
        Typeface font = Typeface.createFromAsset(assetManager, "GenesysGlyphs.ttf");

        for (String symbol :
                symbols)
        {
            int index = text.indexOf(symbol);

            while (index >= 0)
            {
                System.out.println(index);
                CustomTypefaceSpan span = new CustomTypefaceSpan(font);

                spannable.setSpan(span, index, index + 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

                index = text.indexOf(symbol, index + 1);
            }
        }

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
        String string = TextUtils.join(", ", strings);

        return createBoldSpannable(string, text.length());
    }

    public static Spannable createSourceSpannable(List<String> strings)
    {
        String text = "Source: ";
        String string = TextUtils.join(", ", strings);

        return createBoldSpannable(string, text.length());
    }
}
