package team06.main.Games.Game3;
import team06.main.Games.Game1.GameUI.BackpackGuide;
import team06.main.Games.Shared.GameSideMenuBase;
import team06.main.Games.Shared.GameState;

public class Game3SideMenuPanel extends GameSideMenuBase {

	 private MSTFrame frame;
	 Game3GamePanel gamePanel = MSTFrame.getGame();
	 private static String diff;

	    public Game3SideMenuPanel(MSTFrame frame){
	        super();
	        this.frame = frame;
	    }

	    @Override
	    protected void ShowGuide() {
	        new BackpackGuide(this);
	    }

	    @Override
	    protected void StartGame(GameState.Difficulty difficulty) {
	        switch (difficulty){
	            case Training:
	            	Game3GamePanel.setStarted(true);
	            	Game3GamePanel.setTraining(true);
	            	diff = difficulty.toString();
	            	gamePanel.resetPipePosition();
		        	gamePanel.resetVentiPosition();
	                break;
	            case Medium:
	            	Game3GamePanel.setStarted(true);
	            	Game3GamePanel.setMittel(true);
					Game3GamePanel.setTraining(false);
					diff = difficulty.toString();
					gamePanel.resetPipePosition();
		        	gamePanel.resetVentiPosition();
	                break;
	            case Hard:
	            	Game3GamePanel.setStarted(true);
	            	Game3GamePanel.setSchwer(true);
					Game3GamePanel.setTraining(false);
					diff = difficulty.toString();
					gamePanel.resetPipePosition();
		        	gamePanel.resetVentiPosition();
	                break;
	        }

	        gamePanel.startGame(diff);
	    }

	    @Override
	    protected void PauseGame() {

	    }

	    @Override
	    protected void EndGame() {
	        if(GameState.IsGameRunning){
	            timer.stop();
	            frame.FinishGameByLose();
	        }
	    }

	    @Override
	    protected void SubmitResult() {
	        if(GameState.IsGameRunning)
	            frame.FinishGame();
	    }

		@Override
		protected int GetMediumTimeLimit() {
			return 30000;
		}

		@Override
		protected int GetHardTimeLimit() {
			return 15000;
		}

		public static String getDiff() {
			return diff;
		}

		public static void setDiff(String diff) {
			Game3SideMenuPanel.diff = diff;
		}
		
	}
