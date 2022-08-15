package team06.main.Games.Game1.GameElements;

import team06.main.Games.Game1.GameUI.FixedPositions;

import java.awt.*;

public class Item extends ImageBase {
    public int Weight; // 1 - 6
    public int Value; // 1 - 70

    public Item(String imagePath, int weight, int value, FixedPositions.PredefinedPosition fixedPosition) {
        super(imagePath);
        Weight = weight;
        Value = value;
        sizeX = 50;
        sizeY = 50;
        position = fixedPosition;
    }

    @Override
    protected void DrawAdditionalText(Graphics g, int rescaledSizeX, int rescaledSizeY){
        g.drawString("Weight: " + Weight, positionX - (rescaledSizeX/sizeX), positionY + rescaledSizeY+ 10);
        g.drawString("Value: " + Value, positionX - (rescaledSizeX/sizeY), positionY + rescaledSizeY + 25);
    }
}
