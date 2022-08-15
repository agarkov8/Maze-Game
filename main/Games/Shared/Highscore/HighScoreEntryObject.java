package team06.main.Games.Shared.Highscore;

import java.io.Serial;
import java.io.Serializable;

public class HighScoreEntryObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public HighScoreEntryObject(int score, String name) {
        Score = score;
        Name = name;
    }

    public int Score;
    public String Name;

}