package eu.elieser.exalted.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bjorn on 16/11/2016.
 */

public class DiceRoller
{
    public boolean doubleNines;
    public boolean doubleEights;
    public boolean doubleSevens;

    public boolean clearOnes;


    public List<Integer> roll(int diceCount)
    {
        List<Integer> rolls = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < diceCount; i++)
        {
            int roll = random.nextInt(10) + 1;

            if (clearOnes && roll == 1)
            {
                while (roll == 1)
                {
                    roll = random.nextInt(10) + 1;
                }
            }

            rolls.add(roll);
        }

        return rolls;
    }

    public int calculateSuccesses(List<Integer> rolls)
    {
        List<Integer> successes = new ArrayList<>();
        int total = 0;

        for (Integer roll : rolls)
        {
            if (roll > 6)
            {
                successes.add(roll);
                total++;

                if (roll == 10 ||
                        roll == 9 && doubleNines ||
                        roll == 8 && doubleEights ||
                        roll == 7 && doubleSevens
                        )
                {
                    total++;
                }
            }
        }

        return total;
    }
}
