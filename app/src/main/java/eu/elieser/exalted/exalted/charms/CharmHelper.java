package eu.elieser.exalted.exalted.charms;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import eu.elieser.exalted.data.Aspect;
import eu.elieser.exalted.data.BundleKeys;
import eu.elieser.exalted.data.Charm;
import eu.elieser.exalted.navigation.Navigator;
import eu.elieser.exalted.scene.CharmDetailScene;

/**
 * Created by bjorn on 21/04/16.
 */
public class CharmHelper
{

    public static Spannable createCostSpannable(String string)
    {
        String text = "Cost: ";
        string = text + string;

        return createBoldSpannable(string, text.length());
    }

    public static Spannable createMinsSpannable(String string)
    {
        String text = "Mins: ";
        string = text + string;

        return createBoldSpannable(string, text.length());
    }

    public static Spannable createTypeSpannable(String string)
    {
        String text = "Type: ";
        string = text + string;

        return createBoldSpannable(string, text.length());
    }

    public static Spannable createKeywordsSpannable(final List<String> keywords, final IKeywordListener listener, final int color)
    {
        String text = "Keywords: ";
        final int length = text.length();
        Spannable spannable;

        if (keywords.isEmpty())
        {
            text += "None";
            spannable = createBoldSpannable(text, length);
        }
        else
        {
            String joiner = ", ";
            final int joinerLength = joiner.length();

            text += StringUtils.join(keywords, joiner);

            spannable = createBoldSpannable(text, length);
            int start = length;

            for (int i = 0; i < keywords.size(); i++)
            {
                final String kw = keywords.get(i);
                final int kwLength = kw.length();
                ClickableSpan clickableSpan = new ClickableSpan()
                {
                    @Override
                    public void onClick(View view)
                    {
                        listener.onClickKeyword(view, kw);
                    }

                    public void updateDrawState(TextPaint drawState)
                    {
                        drawState.setUnderlineText(false);
                        drawState.setColor(color);
                        drawState.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
                        drawState.bgColor = Color.TRANSPARENT;
                    }
                };

                spannable.setSpan(clickableSpan, start, start + kwLength, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

                Log.d("BEJ", kw);
                Log.d("BEJ", kwLength+"");
                Log.d("BEJ", start+"");

                start += kwLength + joinerLength;

                Log.d("BEJ", start+"");

            }
        }

        return spannable;
    }

    public static Spannable createKeywordsSpannable(String string)
    {
        String text = "Keywords: ";
        string = text + string;

        return createBoldSpannable(string, text.length());
    }

    public static Spannable createFlexibleSpannable(String string, String prefix)
    {
        string = prefix + string;

        return createBoldSpannable(string, prefix.length());
    }

    public static Spannable createMasterySpannable(String string)
    {
        String text = "Mastery: ";
        string = text + string;

        return createBoldSpannable(string, text.length());
    }

    public static Spannable createSpecialSpannable(String string)
    {
        String text = "Special activation rules: ";
        string = text + string;

        return createBoldSpannable(string, text.length());
    }

    public static Spannable createDurationSpannable(String string)
    {
        String text = "Duration: ";
        string = text + string;

        return createBoldSpannable(string, text.length());
    }

    public static Spannable createPrerequisiteCharmsSpannable(List<String> prerequisites, final int color)
    {
        String text = "Prerequisite Charms: ";

        return createLinkedCharmsSpannable(prerequisites, text, color);
    }

    public static Spannable createUnlocksCharmsSpannable(List<String> unlocks, int color)
    {
        String text = "Unlocks Charms: ";

        return createLinkedCharmsSpannable(unlocks, text, color);
    }

    public static Spannable createLinkedCharmsSpannable(List<String> prerequisites, String text, final int color)
    {
        final int prefixLength = text.length();

        String joiner = ", ";
        final int joinerLength = joiner.length();

        if (prerequisites.isEmpty())
        {
            text += "None";
        }
        else
        {
            text += StringUtils.join(prerequisites, joiner);

            int start = prefixLength;

            Spannable spannable = new SpannableString(text);

            for (int i = 0; i < prerequisites.size(); i++)
            {
                final String charmName = prerequisites.get(i);
                final int end = start + charmName.length();

                ClickableSpan clickableSpan = new ClickableSpan()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putString(BundleKeys.NAME, charmName);
                        Navigator.getNavigator().navigationEvent(CharmDetailScene.class, bundle);
                    }

                    public void updateDrawState(TextPaint drawState)
                    {
                        drawState.setUnderlineText(false);
                        drawState.setColor(color);
                        drawState.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                        drawState.bgColor = Color.TRANSPARENT;
                    }
                };

                spannable.setSpan(clickableSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

                start += charmName.length() + joinerLength;
            }

            StyleSpan prefixStyleSpan = new StyleSpan(Typeface.BOLD);
            spannable.setSpan(prefixStyleSpan, 0, prefixLength, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

            return spannable;
        }

        return createBoldSpannable(text, prefixLength);
    }

    public static Spannable createCostAndMinsSpannable(String cost, Aspect ability, Aspect essence)
    {
        String abilityString = ability.getName() + " " + ability.getValue();
        String essenceString = essence.getName() + " " + essence.getValue();

        return createCostAndMinsSpannable(cost, abilityString + " " + essenceString);
    }

    public static Spannable createCostAndMinsSpannable(String cost, String mins)
    {
        String costText = "Cost: ";
        String minsText = "Mins: ";

        int costStart = 0;
        int costEnd = costText.length();

        String theString = costText + cost + "; ";

        int minsStart = theString.length();
        int minsEnd = minsStart + minsText.length();

        theString += costText + mins;

        StyleSpan costSpan = new StyleSpan(Typeface.BOLD);
        StyleSpan minsSpan = new StyleSpan(Typeface.BOLD);

        Spannable spannable = new SpannableString(theString);
        spannable.setSpan(costSpan, costStart, costEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannable.setSpan(minsSpan, minsStart, minsEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        return spannable;
    }

    public static List<String> charmsToNames(List<Charm> charms)
    {
        List<String> strings = new ArrayList<>(charms.size());

        for (Charm charm :
                charms)
        {
            strings.add(charm.getName());
        }

        return strings;
    }

    public static Spannable createBoldSpannable(String cost, int end)
    {
        StyleSpan span = new StyleSpan(Typeface.BOLD);

        Spannable spannable = new SpannableString(cost);
        spannable.setSpan(span, 0, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        return spannable;
    }
}
