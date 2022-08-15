package team06.main.Games.Game2.Elements;
import team06.main.Games.Game1.GameUI.GamePanel;
import team06.main.Games.Game2.GameUI.Game2FieldPanel;
import team06.main.Games.Game2.GameUI.TwoFixedPositions;

import javax.swing.*;

public class Car extends Game2ImageBase{

    int Size;
    /**
     * ImageBase for elements displaying an image
     *
     * @param imagePath
     */
    public Car(String imagePath, int size, TwoFixedPositions.PredefinedPosition fixedPosition) {
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

    public void moveLeft(){
        if(positionX-20 < 0){
            if(!Game2FieldPanel.CheckIfFullyBetweenExit(this))
                return;
        }
        additionallyMovedX -= 20;
    }

    public void moveRight(int width){
        if(positionX + getWidth() + 20 > width){
            if(!Game2FieldPanel.CheckIfFullyBetweenExit(this))
                return;
        }
        additionallyMovedX += 20;
    }
}
