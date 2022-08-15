package team06.main.Games.Game2.Elements;

import team06.main.Games.Game2.GameUI.TwoFixedPositions;

public class Description extends Game2ImageBase{

    double Size;

    public Description(String imagePath, double size, TwoFixedPositions.PredefinedPosition fixedPosition) {
        super(imagePath);
        Size = size;
        sizeX = 350;
        sizeY = 50;
        position = fixedPosition;
    }

    public int getTruePositionY(int panelSizeX, int panelSizeY){
        var rescaledSizeY = (int)(sizeY * quantifier);
        var calculatedPosition = positionsObject.CalculatePosition(position, panelSizeX, panelSizeY);
        return calculatedPosition.y  - (rescaledSizeY / 2) + additionallyMovedY;
    }
}
