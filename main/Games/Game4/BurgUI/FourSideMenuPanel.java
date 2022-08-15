package team06.main.Games.Game4.BurgUI;

import team06.main.Games.Game4.BurgFrame;
import team06.main.Games.Shared.GameSideMenuBase;
import team06.main.Games.Shared.GameState;


public class FourSideMenuPanel extends GameSideMenuBase {
    private BurgFrame frame4;

    public FourSideMenuPanel(BurgFrame frame) {
        super();
        this.frame4 = frame;

    }

    public void PauseFromOutside() {
        super.PauseGameStart();
    }

    public void StopTimer() {
        timer.stop();
    }

    public void FinishFromOutside(boolean newLevel) {
        if (newLevel)
            StartTimeHandling(true);
        else {
            EndGameStart();
        }
    }

    @Override
    protected void ShowGuide() {
        new FourGuideFrame(this);
    }

    @Override
    protected void StartGame(GameState.Difficulty difficulty) {
        var dorfCount = 0;
        var burgCount = 0;
        var nextBurg = 0.0;
        switch (difficulty) {
            case Training:
                dorfCount = 6;
                burgCount = 3;
                nextBurg = 0.38;
                break;
            case Medium:
                dorfCount = 10;
                burgCount = 6;
                nextBurg = 0.23;
                break;
            case Hard:
                dorfCount = 25;
                burgCount = 5;
                nextBurg = 0.29;
                break;
        }

        frame4.StartGame(dorfCount, burgCount, nextBurg);
    }

    @Override
    protected void PauseGame() {

    }

    protected void EndGame() {
        if (GameState.IsGameRunning){
            timer.stop();
            frame4.FinishGameByLose();
        }
    }

    @Override
    protected void SubmitResult() {
        if (GameState.IsGameRunning){
            frame4.FinishGame();
        }

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