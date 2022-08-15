package team06.main.Games.Game2;

import team06.main.Games.AdditionalGame;
import team06.main.Games.Game2.GameUI.Game2FieldPanel;
import team06.main.Games.Game2.GameUI.SidePanel;
import team06.main.Games.Shared.GameState;

public class FishFrame extends AdditionalGame {

    private Game2FieldPanel game;

    private SidePanel sidePanel;

    public FishFrame() {
        super();
        GameState.Reset();
        game = new Game2FieldPanel(this);
        super.GamePanel = game;
        sidePanel = new SidePanel(this);
        super.SidePanel = sidePanel;
        super.SetupGame();
    }

    public void StartGame(int fishAmount, int fishValue) {
        game.CreateGame(fishAmount, fishValue);
    }

    public void FinishByGamePanel(boolean newLevel){
        sidePanel.FinishFromOutside(newLevel);
    }

    public void PauseByGamePanel(){
        sidePanel.PauseFromOutside();
    }

    public void FinishGameByLose(){
        game.FinishGame(false);
    }

    public void FinishGame(){
        game.EvaluateAndFinishGame();
    }

    public void StopTimerFromGame(){
        sidePanel.StopTimer();
    }
}
