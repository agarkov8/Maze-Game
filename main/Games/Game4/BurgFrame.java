package team06.main.Games.Game4;

import team06.main.Games.AdditionalGame;
import team06.main.Games.Game4.BurgUI.FourFieldPanel;
import team06.main.Games.Game4.BurgUI.FourSideMenuPanel;
import team06.main.Games.Shared.GameState;

public class BurgFrame extends AdditionalGame {
    private FourFieldPanel game4;
    private FourSideMenuPanel sidepanel4;

    public BurgFrame() {
        super();
        GameState.Reset();
        game4 = new FourFieldPanel(this);
        super.GamePanel = game4;
        sidepanel4 = new FourSideMenuPanel(this);
        super.SidePanel = sidepanel4;
        super.SetupGame();

    }

    public void StartGame(int dorfAmount, int burgAmount, double nextBurg){

        game4.CreateGame(dorfAmount, burgAmount, nextBurg);
    }

    public void PauseByGamePanel(){
        sidepanel4.PauseFromOutside();
    }
    public void StopTimerFromGame(){
        sidepanel4.StopTimer();
    }

    public void FinishByGamePanel(boolean newLevel){
        sidepanel4.FinishFromOutside(newLevel);
    }

    public void FinishGameByLose(){
        game4.FinishGame(false);
    }
    public void FinishGame(){
        game4.EvaluateAndFinishGame();
    }
    public void FinishLevel(){
        game4.EvaluateAndFinishLevel();
    }
}
