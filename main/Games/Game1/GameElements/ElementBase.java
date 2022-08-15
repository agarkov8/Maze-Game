package team06.main.Games.Game1.GameElements;

import team06.main.Games.Game1.GameUI.FixedPositions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class ElementBase {
    public double quantifier;
    protected int positionX;
    protected int positionY;
    protected int sizeX;
    protected int sizeY;

    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }

    public int getSizeX() {
        return sizeX;
    }
    public int getSizeY() {
        return sizeY;
    }

    public int getWidth(){
        return (int) (getSizeX() * quantifier);
    }
    public int getHeight(){
        return (int) (getSizeY() * quantifier);
    }

    /**
     * ImageBase for elements displaying an image
     */
    public ElementBase(){
        super();
    }
}
