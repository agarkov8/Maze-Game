package team06.main.Games.Game2.Elements;

import team06.main.Games.Game2.GameUI.TwoFixedPositions;


public class FishTank extends Game2ImageBase {
    int size;


    /**
     * ImageBase for elements displaying an image
     *
     * @param imagePath
     */
    public FishTank(String imagePath, int size, TwoFixedPositions.PredefinedPosition fixedPosition) {
        super(imagePath);
        this.size = size;
        sizeX = 100;
        sizeY = 100;
        position = fixedPosition;
    }

    public int getTruePositionY(int panelSizeX, int panelSizeY){
        var rescaledSizeY = (int)(sizeY * quantifier);
        var calculatedPosition = positionsObject.CalculatePosition(position, panelSizeX, panelSizeY);
        return calculatedPosition.y  - (rescaledSizeY / 2) + additionallyMovedY;
    }

}
