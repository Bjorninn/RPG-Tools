package eu.elieser.exalted.genesys.talents;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;

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
    private static List<String> symbols2 = new ArrayList<>(10);
    private static Map<String, String> letters = new HashMap<>();
    private static Map<String, String> eoteLetters = new HashMap<>();
    private static Typeface genesysTypeface;
    private static Typeface eoteTypeface;

    private static class SymbolIndex
    {
        public final int index;
        public final String symbol;

        public boolean needsColor = false;
        public int indexAddition = 0;

        private SymbolIndex(int index, String symbol)
        {
            this.index = index;
            this.symbol = symbol;
        }

        private SymbolIndex(int index, String symbol, int indexAddition, boolean needsColor)
        {
            this.index = index;
            this.symbol = symbol;
            this.indexAddition = indexAddition;
            this.needsColor = needsColor;
        }
    }

    public static void initialize(AssetManager assetManager)
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

        genesysTypeface = Typeface.createFromAsset(assetManager, "GenesysGlyphs.ttf");
        eoteTypeface = Typeface.createFromAsset(assetManager, "EotESymbol-Regular-PLUS.otf");


    }

    // 1. Find each item and mark it for font and possibly color and index addition
    // 2. Roll through the items and apply effects


    public static Spannable createDescriptionSpannable2(String text)
    {
        // Algorithm
        // 1. go through text and find the index of each symbol
        List<Integer> indices = new ArrayList<>();
        List<SymbolIndex> indexes = new ArrayList<>();

        for (String symbol : symbols)
        {
            int index = text.indexOf(symbol);

            while (index >= 0)
            {
                indexes.add(new SymbolIndex(index, symbol));
                index = text.indexOf(symbol, index + 1);
            }
        }

        // 2. replace each found symbol with its letter
        for (String symbol : symbols)
        {
            text = text.replace(symbol, letters.get(symbol));
        }

        Spannable spannable = new SpannableString(text);


        // 3. each index is now (index - (i * 3)) as we changed symbols like [BO] to b
        for (int i = 0; i < indices.size(); i++)
        {
            int index = indices.get(i) - (i * 3);

            CustomTypefaceSpan span = new CustomTypefaceSpan(eoteTypeface);
            spannable.setSpan(span, index, index + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }

        return spannable;
    }

    public static Spannable createDescriptionSpannable(String text)
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
        for (String symbol : symbols)
        {
            text = text.replace(symbol, letters.get(symbol));
        }

        Spannable spannable = new SpannableString(text);


        // 3. each index is now (index - (i * 3)) as we changed symbols like [BO] to b
        for (int i = 0; i < indices.size(); i++)
        {
            int index = indices.get(i) - (i * 3);

            CustomTypefaceSpan span = new CustomTypefaceSpan(genesysTypeface);
            spannable.setSpan(span, index, index + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
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
