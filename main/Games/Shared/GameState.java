package team06.main.Games.Shared;

import team06.main.Games.Shared.Highscore.HighScoreModel;

public class GameState {
    public static boolean IsGameRunning;
    public static boolean IsGameLost;
    public static boolean IsGamePaused;
    public static boolean IsGuideOpen;
    public static Difficulty GameDifficulty;
    public static String CurrentGame;

    public static int Score;
    public static int Level;
    public static int TargetValue;

    public static void Reset(){
        IsGamePaused = false;
        IsGameRunning = false;
        IsGameLost = false;
        Score = 0;
        Level = 1;
        TargetValue = 0;
        GameDifficulty = Difficulty.Training;
        HighScoreModel.entries = null;
    }

    public enum Difficulty{
        Training,
        Medium,
        Hard
    }
}
