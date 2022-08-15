package team06.main.Games.Game2.Elements;

import team06.main.Games.Game2.GameUI.TwoFixedPositions;

import java.awt.*;

public class Fish extends Game2ImageBase {

    public int Value; // 1 - 70

    public Fish(String imagePath, int value, TwoFixedPositions.PredefinedPosition fixedPosition) {
        super(imagePath);
        Value = value;
        sizeX = 40;
        sizeY = 40;
        position = fixedPosition;
    }
}
