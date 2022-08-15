package team06.main.Games.Game4.Elements;

public class FourElementBase {
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

    public int getWidth() {
        return (int) (getSizeX() * quantifier);
    }

    public int getHeight() {
        return (int) (getSizeY() * quantifier);
    }

    /**
     * ImageBase for elements displaying an image
     */
    public FourElementBase() {
        super();
    }
}
