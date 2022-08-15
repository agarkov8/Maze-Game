package team06.main.Games.Game2.Elements;

import team06.main.Games.Game2.GameUI.TwoFixedPositions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game2ImageBase extends ElementBase{
    public TwoFixedPositions.PredefinedPosition position;

    BufferedImage Image;
    protected int additionallyMovedX;
    protected int additionallyMovedY;

    protected static TwoFixedPositions positionsObject = new TwoFixedPositions();

    public void Reposition(int x, int y){
        positionX = x;
        positionY = y;
    }

    /**
     * ImageBase for elements displaying an image
     */
    public Game2ImageBase(String imagePath){
        super();

        try {
            var filePath = getClass().getResource(imagePath);
            Image = ImageIO.read(new File(filePath.toURI()));
        } catch (IOException | URISyntaxException e) {
            System.out.println("Error during reading/writing: "+ e.getMessage());
        }
    }

    public void DrawElement(Graphics g, int panelSizeX, int panelSizeY, boolean isDragged){
        if (Image == null)
            return;

        quantifier = (double) panelSizeX / (double) 450;
        var rescaledSizeX = (int)(sizeX * quantifier);
        var rescaledSizeY = (int) (sizeY * quantifier);

        if(!isDragged){
            var calculatedPosition = positionsObject.CalculatePosition(position, panelSizeX, panelSizeY);
            positionX = calculatedPosition.x - (rescaledSizeX / 2) + additionallyMovedX;
            positionY = calculatedPosition.y  - (rescaledSizeY / 2) + additionallyMovedY;
        }

        g.drawImage(Image, positionX, positionY, rescaledSizeX, rescaledSizeY,null);
    }
}
