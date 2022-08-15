package team06.main.Games.Game2.GameUI;

import team06.main.Games.Game2.FishFrame;
import team06.main.Games.Shared.GameSideMenuBase;
import team06.main.Games.Shared.GameState;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends GameSideMenuBase {

    private FishFrame frame;


    public SidePanel(FishFrame frame) {
            super();
            setBackground(Color.ORANGE);
            this.frame = frame;
    }

    @Override
    protected void ShowGuide() {
        new Game2GuideFrame();
    }

    public void FinishFromOutside(boolean newLevel){
        if(newLevel)
            StartTimeHandling(true);
        else{
            EndGameStart();
        }
    }

    public void PauseFromOutside(){
        super.PauseGameStart();
    }

    public void StopTimer(){
        timer.stop();
    }

    @Override
    protected void StartGame(GameState.Difficulty difficulty) {
        var fishNumber = 0;
        int fishValue = 0;
        switch (difficulty){
            case Training:
                fishNumber = 4;
                fishValue = 1;
                break;
            case Medium:
                fishNumber = 6;
                fishValue = 2;
                break;
            case Hard:
                fishNumber = 8;
                fishValue = 3;
                break;
        }
        frame.StartGame(fishNumber, fishValue);
    }

    @Override
    protected void PauseGame() {

    }

    @Override
    protected void EndGame() {
        if (GameState.IsGameRunning){
            timer.stop();
            frame.FinishGameByLose();
        }
    }

    @Override
    protected void SubmitResult() {
        if (GameState.IsGameRunning){
            frame.FinishGame();
        }

    }


    protected int GetMediumTimeLimit() {
        return 90000;
    }


    protected int GetHardTimeLimit() {
        return 120000;
    }
}
