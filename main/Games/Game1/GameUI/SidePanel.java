package team06.main.Games.Game1.GameUI;

import team06.main.Games.Game1.BackPackFrame;
import team06.main.Games.Shared.GameSideMenuBase;
import team06.main.Games.Shared.GameState;

public class SidePanel extends GameSideMenuBase {
    private BackPackFrame frame;

    public SidePanel(BackPackFrame frame){
        super();
        this.frame = frame;
    }

    public void PauseFromOutside(){
        super.PauseGameStart();
    }

    public void StopTimer(){
        timer.stop();
    }

    public void FinishFromOutside(boolean newLevel){
        if(newLevel)
            StartTimeHandling(true);
        else{
            EndGameStart();
        }
    }

    @Override
    protected void ShowGuide() {
        new BackpackGuide(this);
    }

    @Override
    protected void StartGame(GameState.Difficulty difficulty) {
        var quantifier = 0.0;
        var itemCount = 0;
        switch (difficulty){
            case Training:
                itemCount = 5;
                quantifier = 0.5;
                break;
            case Medium:
                itemCount = 7;
                quantifier = 0.7;
                break;
            case Hard:
                itemCount = 9;
                quantifier = 0.9;
                break;
        }

        frame.StartGame(itemCount, quantifier);
    }

    @Override
    protected void PauseGame() {

    }

    @Override
    protected void EndGame() {
        if(GameState.IsGameRunning){
            frame.FinishGameByLose();
        }
    }

    @Override
    protected void SubmitResult() {
        if(GameState.IsGameRunning)
            frame.FinishLevel();
    }

    @Override
    protected int GetMediumTimeLimit() {
        return 30000;
    }

    @Override
    protected int GetHardTimeLimit() {
        return 15000;
    }
}
