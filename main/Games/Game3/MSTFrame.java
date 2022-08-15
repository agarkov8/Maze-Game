package team06.main.Games.Game3;

import team06.main.Games.AdditionalGame;
import team06.main.Games.Shared.GameState;
import team06.main.MazeGame.MainFrame;

public class MSTFrame extends AdditionalGame{

	
	    private static Game3GamePanel game;

	   

		public MSTFrame() {
	        super();
	        GameState.Reset();
	        game = new Game3GamePanel(this);
	        super.GamePanel = game;
	        super.SidePanel = new Game3SideMenuPanel(this);
	        super.SetupGame();
	    }

	    public void StartGame(int itemCount, double quantifier){

	        game.startGame(Game3SideMenuPanel.getDiff());
	    }

	    public void FinishGameByLose(){
	        //game.FinishGame(false);
	    }
	    public void FinishGame(){
	        //game.EvaluateAndFinishGame();
	    }

	     public static Game3GamePanel getGame() {
			return game;
		}

		public static void setGame(Game3GamePanel game) {
			MSTFrame.game = game;
		}

}
