package team06.main.Games.Game2.Elements;

import java.awt.*;

public class Exit extends ElementBase{

    public double relativePosition = -1;
    int side;

    public Exit(int x) {
        side = x;
        sizeX = 200;
        sizeY = 20;
    }

    public void DrawExit(Graphics g, int panelSizeX, int panelSizeY){
        if(relativePosition == -1)
            relativePosition = (double)positionX / (double)panelSizeX;

        quantifier = (double) panelSizeX / (double) 450;
        var rescaledSizeX = (int)(sizeX * quantifier);
        var rescaledSizeY = (int) (sizeY * quantifier);
//        positionX = (int)(relativePosition * panelSizeX);
//        positionY = panelSizeY - sizeY +5;

        if (side == 0) {
            positionX = 5;
            positionY = 450;
        } else {
            positionX = 360;
            positionY = 450;
        }


//        g.fillRect(positionX, positionY, rescaledSizeX, rescaledSizeY);
//        g.fillOval(positionX, positionY, rescaledSizeX, rescaledSizeY);
        g.fillOval(positionX,positionY,20,100);
    }
}
