package team06.main.Games.Game1.GameUI;

import team06.main.Games.Shared.AdditionalGuide;
import team06.main.Games.Shared.GameSideMenuBase;

public class BackpackGuide extends AdditionalGuide {

    public BackpackGuide(GameSideMenuBase sideMenuBase){
        super(sideMenuBase);
    }

    @Override
    protected String GetGuideText() {
        return "                              "
                + "Welcome to backpack game from Team06\n\n"
                + " - By default, all the controls are mapped on our keyboard to control the backpack:\n"
                + "    Up, Left, Down, Right by the arrow keys.\n\n"
                + " - To start/end/pause the game:\n"
                + "    click at the according button.\n\n"
                + " - To submit the level:\n"
                + "    click at the \"Submit\" button.\n\n"
                + " Add items to the backpack (by drag and drop) to add their weight and value\n"
                + " Try to reach the target value without exceeding the backpack weight limit.\n\n"
                + " The time limits are: \n"
                + "     Training: none; Medium: 30s; Hard: 15s.\n\n"
                + " Try to reach the target value without exceeding the backpack weight limit.\n\n"
                + "                                                Good Luck!!";
    }
}
