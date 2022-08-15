package team06.main.Games.Game1.GameElements;

import team06.main.Games.Game1.GameUI.FixedPositions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class ImageBase extends ElementBase{
    public FixedPositions.PredefinedPosition position;

    BufferedImage Image;
    protected int additionallyMovedX;
    protected int additionallyMovedY;

    protected static FixedPositions positionsObject = new FixedPositions();

    public void Reposition(int x, int y){
        positionX = x;
        positionY = y;
    }

    /**
     * ImageBase for elements displaying an image
     */
    public ImageBase(String imagePath){
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

        quantifier = (double) Math.min(panelSizeX, panelSizeY) / (double) 600;
        var rescaledSizeX = (int)(sizeX * quantifier);
        var rescaledSizeY = (int) (sizeY * quantifier);

        if(!isDragged){
            var calculatedPosition = positionsObject.CalculatePosition(position, panelSizeX, panelSizeY);
            positionX = calculatedPosition.x - (rescaledSizeX / 2) + additionallyMovedX;
            positionY = calculatedPosition.y  - (rescaledSizeY / 2) + additionallyMovedY;
        }

        g.drawImage(Image, positionX, positionY, rescaledSizeX, rescaledSizeY,null);
        DrawAdditionalText(g,rescaledSizeX, rescaledSizeY);
    }

    protected void DrawAdditionalText(Graphics g, int rescaledSizeX, int rescaledSizeY){

    }
}
