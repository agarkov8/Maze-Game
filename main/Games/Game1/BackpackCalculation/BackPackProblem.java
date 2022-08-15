package team06.main.Games.Game1.BackpackCalculation;

import team06.main.Games.Game1.GameElements.Item;

import java.util.ArrayList;

public class BackPackProblem {
    public static int CalculateBackpackSolution(int capacity, ArrayList<Item> items)
    {
        var amount = items.size();
        int optimalBackpack[][] = new int[amount + 1][capacity + 1];

        int i, j;
        for (i = 0; i <= amount; i++)
        {
            for (j = 0; j <= capacity; j++)
            {
                if (i == 0 || j == 0)
                    optimalBackpack[i][j] = 0;
                else if (items.get(i - 1).Weight <= j)
                    optimalBackpack[i][j]
                            = Math.max(items.get(i - 1).Value
                                    + optimalBackpack[i - 1][j - items.get(i - 1).Weight],
                            optimalBackpack[i - 1][j]);
                else
                    optimalBackpack[i][j] = optimalBackpack[i - 1][j];
            }
        }

        return optimalBackpack[amount][capacity];
    }
}
