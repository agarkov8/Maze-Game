package team06.main.Games.Game1.GameElements;

import java.awt.*;

public class Exit extends ElementBase{
    public double relativePosition = -1;

    public Exit(int x) {
        positionX = x;
        sizeX = 140;
        sizeY = 20;
    }

    public void DrawExit(Graphics g, int panelSizeX, int panelSizeY){
        if(relativePosition == -1)
            relativePosition = (double)positionX / (double)panelSizeX;

        quantifier = (double) Math.min(panelSizeX, panelSizeY) / (double) 450;
        var rescaledSizeX = (int)(sizeX * quantifier);
        var rescaledSizeY = (int) (sizeY * quantifier);
        positionX = (int)(relativePosition * panelSizeX);
        positionY = panelSizeY - sizeY +5;

        g.fillRect(positionX, positionY, rescaledSizeX, rescaledSizeY);
    }
}
