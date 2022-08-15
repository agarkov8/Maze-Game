package team06.main.Games.Game4.BurgUI;

import team06.main.Games.Shared.AdditionalGuide;
import team06.main.Games.Shared.GameSideMenuBase;

public class FourGuideFrame extends AdditionalGuide {
    public FourGuideFrame(GameSideMenuBase sideMenuBase) {
        super(sideMenuBase);
    }

    @Override
    protected String GetGuideText() {
        return "                              "
                + "Welcome to castle game from Team06\n\n"
                + " - By default, all the controls are mapped on our keyboard to move the thron:\n"
                + "    Up, Left, Down, Right by the arrow keys.\n\n"
                + " - To start the game:\n"
                + "    click at the \"Start\" button.\n\n"
                + " - To pause the game:\n"
                + "    click at the \"Pause\" button.\n\n"
                + " Set castle per drag and drop to the village to create shelter when attacks occur\n\n"
                + " The village will turn into light grey if a castle is set\n\n"
                + " Only if a castle is set, the throne can be moved to claim the territory\n\n"
                + " After a throne is set, the village will turn into yellow\n\n"
                + " You can check the next nearest castle by clicking on the village\n\n"
                + " after setting all the castle, you can press s to see the answer\n\n"
                + " Try to set the castle as near as possible to the all village.\n\n"
                + "                                                Good Luck!!";
    }
}
