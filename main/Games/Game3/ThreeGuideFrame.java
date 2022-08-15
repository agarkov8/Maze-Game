package team06.main.Games.Game3;

import team06.main.Games.Shared.AdditionalGuide;
import team06.main.Games.Shared.GameSideMenuBase;

public class ThreeGuideFrame extends AdditionalGuide{

	    public ThreeGuideFrame(GameSideMenuBase sideMenuBase){
			super(sideMenuBase);
		}

		@Override
		protected String GetGuideText() {
			return "                              "
					+ "Welcome to minimum spanning tree game from Team06\n\n"
					+ " - By default, all the controls are mapped on our keyboard to control the ventilation system:\n"
					+ "    Up, Left, Down, Right by the arrow keys.\n\n"
					+ " - To start the game:\n"
					+ "    click at the \"Start\" button.\n\n"
					+ " - To pause the game:\n"
					+ "    click at the \"Pause\" button.\n\n"
					+ "    click at the \"Pause\" button.\n\n"
					+ "                                                Good Luck!!";
		}
	}
