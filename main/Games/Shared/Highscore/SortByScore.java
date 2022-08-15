package team06.main.Games.Shared.Highscore;

import java.util.Comparator;

public class SortByScore implements Comparator<HighScoreEntryObject> {
    // Used for sorting in ascending order of
    // roll number
    public int compare(HighScoreEntryObject a, HighScoreEntryObject b) {
        if(a == null)
            return -1;
        else if (b== null)
            return 1;
        return Integer.compare(b.Score, a.Score);
    }
}
