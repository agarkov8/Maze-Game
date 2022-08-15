package team06.main.Games.Game1.GameElements;

import team06.main.Games.Game1.GameUI.FixedPositions;
import team06.main.Games.Game1.GameUI.GamePanel;

import javax.swing.*;

public class Backpack extends ImageBase {
    public int Size;

    public Backpack(String imagePath, int size, FixedPositions.PredefinedPosition fixedPosition) {
        super(imagePath);
        Size = size;
        sizeX = 150;
        sizeY = 150;
        position = fixedPosition;
    }

    public int getTruePositionY(int panelSizeX, int panelSizeY){
        var rescaledSizeY = (int)(sizeY * quantifier);
        var calculatedPosition = positionsObject.CalculatePosition(position, panelSizeX, panelSizeY);
        return calculatedPosition.y  - (rescaledSizeY / 2) + additionallyMovedY;
    }

    /**
     * Allows the backpack to move left, checking for if the backpack's next
     * move hits a wall.
     */
    public Action moveLeft(int height){
        if(positionX-20 < 0
            || positionY + getHeight() > height && !GamePanel.CheckIfAbsoluteFullyBetweenExit(positionX -20, getWidth()))
            return null;

        additionallyMovedX -= 20;
        return null;
    }

    /**
     * Allows the backpack to move right, checking for if the backpack's next
     * move hits a wall.
     */
    public Action moveRight(int width, int height){
        if(positionX + getWidth() + 5 > width
            || positionY + getHeight() > height && !GamePanel.CheckIfAbsoluteFullyBetweenExit(positionX +20, getWidth()))
            return null;

        additionallyMovedX += 20;
        return null;
    }

    /**
     * Allows the backpack to move up, checking for if the backpack's next
     * move hits a wall.
     */
    public Action moveUp(){
        if(positionY-10 < 0)
            return null;

        additionallyMovedY -= 20;
        return null;
    }

    /**
     * Allows the backpack to move down, checking for if the backpacks next
     * move hits a wall.
     */
    public void moveDown(int height){
        if(positionY + getHeight()+ 20 > height && !GamePanel.CheckIfFullyBetweenExit(this))
        {
            return;
        }
        additionallyMovedY += 20;
    }
}
