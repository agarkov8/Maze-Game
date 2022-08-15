package team06.main.Games.Game1;

import team06.main.Games.AdditionalGame;
import team06.main.Games.Game1.GameUI.GamePanel;
import team06.main.Games.Game1.GameUI.SidePanel;
import team06.main.Games.Shared.GameState;

public class BackPackFrame extends AdditionalGame {
    private GamePanel game;
    private SidePanel sidePanel;

    public BackPackFrame() {
        super();
        GameState.Reset();
        game = new GamePanel(this);
        super.GamePanel = game;
        sidePanel = new SidePanel(this);
        super.SidePanel = sidePanel;
        super.SetupGame();
    }

    public void StartGame(int itemCount, double quantifier){

        game.CreateGame(quantifier, itemCount);
    }

    public void PauseByGamePanel(){
        sidePanel.PauseFromOutside();
    }
    public void StopTimerFromGame(){
        sidePanel.StopTimer();
    }

    public void FinishByGamePanel(boolean newLevel){
        sidePanel.FinishFromOutside(newLevel);
    }

    public void FinishGameByLose(){
        game.FinishGame(false);
    }
    public void FinishGame(){
        game.EvaluateAndFinishGame();
    }
    public void FinishLevel(){
        game.EvaluateAndFinishLevel();
    }
}
