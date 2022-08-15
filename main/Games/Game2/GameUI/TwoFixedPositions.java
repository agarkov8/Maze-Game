package team06.main.Games.Game2.GameUI;

public class TwoFixedPositions {

    int offset = 60;

    public Position CalculatePosition(TwoFixedPositions.PredefinedPosition predefinedPosition, int width, int height){
        switch (predefinedPosition) {
            case Fish1:
                return new TwoFixedPositions.Position(offset,50);
            case Fish1d:
                return new TwoFixedPositions.Position(130,50);
            case Fish1c:
                return new TwoFixedPositions.Position(280,50);
            case Fish2:
                return new TwoFixedPositions.Position(offset,70);
            case Fish2d:
                return new TwoFixedPositions.Position(130,70);
            case Fish2c:
                return new TwoFixedPositions.Position(270,70);
            case Fish3:
                return new TwoFixedPositions.Position(offset,90);
            case Fish3d:
                return new TwoFixedPositions.Position(130,90);
            case Fish3c:
                return new TwoFixedPositions.Position(280,90);
            case Fish4:
                return new TwoFixedPositions.Position(offset,110);
            case Fish4d:
                return new TwoFixedPositions.Position(130,110);
            case Fish4c:
                return new TwoFixedPositions.Position(260,110);
            case Fish5:
                return new TwoFixedPositions.Position(offset,130);
            case Fish5d:
                return new TwoFixedPositions.Position(130,130);
            case Fish5c:
                return new TwoFixedPositions.Position(260,130);
            case Fish6:
                return new TwoFixedPositions.Position(offset,150);
            case Fish6d:
                return new TwoFixedPositions.Position(130,150);
            case Fish6c:
                return new TwoFixedPositions.Position(280,150);
            case Fish7:
                return new TwoFixedPositions.Position(offset,170);
            case Fish7d:
                return new TwoFixedPositions.Position(130,170);
            case Fish7c:
                return new TwoFixedPositions.Position(280,170);
            case Fish8:
                return new TwoFixedPositions.Position(offset,190);
            case Fish8d:
                return new TwoFixedPositions.Position(130,190);
            case Fish8c:
                return new TwoFixedPositions.Position(280,190);
            case Fish9:
                return new TwoFixedPositions.Position(offset,210);
            case Fish9d:
                return new TwoFixedPositions.Position(130,210);
            case Fish9c:
                return new TwoFixedPositions.Position(280,210);
            case Fish10:
                return new TwoFixedPositions.Position(offset,230);
            case Fish10d:
                return new TwoFixedPositions.Position(130,230);
            case Fish10c:
                return new TwoFixedPositions.Position(280,230);
            case Fish11:
                return new TwoFixedPositions.Position(offset,250);
            case Fish11d:
                return new TwoFixedPositions.Position(130,250);
            case Fish11c:
                return new TwoFixedPositions.Position(280,250);
            case Top:
                return new TwoFixedPositions.Position(offset,offset);
            case Wall1:
                return new TwoFixedPositions.Position(100,50);
            case TopLeft:
                return new TwoFixedPositions.Position(offset, offset);
            case TopMiddleLeft:
                return new TwoFixedPositions.Position((int) ((double) width / (double) 3), offset);
            case TopMiddleRight:
                return new TwoFixedPositions.Position((int) ((double) width / 1.5), offset);
            case TopRight:
                return new TwoFixedPositions.Position(width - offset, offset);
            case MiddleTopLeft:
                return new TwoFixedPositions.Position(offset, (int) ((double) height / (double) 3));
            case MiddleTopRight:
                return new TwoFixedPositions.Position(width - offset, (int) ((double) height / (double) 3));
            case MiddleBottomLeft:
                return new TwoFixedPositions.Position(offset, (int) ((double) height / 1.5));
            case Tank2:
                return new TwoFixedPositions.Position(width - 180, (int) ((double) height - 105));
            case Tank3:
                return new TwoFixedPositions.Position(width - 270, (int) ((double) height - 105));
            case Tank4:
                return new TwoFixedPositions.Position(width - 90, (int) ((double) height - 200));
            case Tank5:
                return new TwoFixedPositions.Position(width - 180, (int) ((double) height - 200));
            case Tank6:
                return new TwoFixedPositions.Position(width - 270, (int) ((double) height - 200));
            case MiddleBottomRight:
                return new TwoFixedPositions.Position(width - offset, (int) ((double) height / 1.5));
            case Tank1:
                return new TwoFixedPositions.Position(width - 90, (int) ((double) height - 105));
            case Fish1t1:
                return new TwoFixedPositions.Position(width - 90, (int) ((double) height - 105));
            case BottomLeft:
                return new TwoFixedPositions.Position(offset, height - offset);
            case BottomLeftB:
                return new TwoFixedPositions.Position(70, height -30);
            case BottomMiddleLeft:
                return new TwoFixedPositions.Position((int) ((double) width / (double) 3), height - offset);
            case BottomMiddleLeftC:
                return new TwoFixedPositions.Position((int) ((double) width / (double) 2), height - offset);
            case BottomMiddleRight:
                return new TwoFixedPositions.Position((int) ((double) width / 1.5), height - offset);
            case BottomRight:
                return new TwoFixedPositions.Position(width - offset, height - offset);
            case BottomRightB:
                return new TwoFixedPositions.Position(width - 80, height -30);
            case Center:
                return new TwoFixedPositions.Position(width / 2, height / 2);
            case Bottom:
                return new TwoFixedPositions.Position(width / 2, height);
            default:
                return null;
        }
    }

    public enum PredefinedPosition {
        Fish1,
        Fish1t1,
        Tank5,
        Tank6,
        Fish1d,
        Fish1c,
        Fish2c,
        Fish3c,
        Fish4c,
        Fish5c,
        Fish6c,
        Fish7c,
        Fish8c,
        Fish9c,
        Fish10c,
        Fish11c,
        Fish2d,
        Fish3d,
        Fish4d,
        Fish5d,
        Fish6d,
        Fish7d,
        Fish8d,
        Fish9d,
        Fish10d,
        Fish11d,
        Fish2,
        Fish3,
        Fish4,
        Fish5,
        Fish6,
        Tank4,
        Fish7,
        Fish8,
        Fish9,
        Fish10,
        Fish11,
        Top,
        Wall1,
        TopLeft,
        TopMiddleLeft,
        TopMiddleRight,
        TopRight,
        MiddleTopLeft,
        MiddleTopRight,
        MiddleBottomLeft,
        Tank2,
        Tank3,
        MiddleBottomRight,
        Tank1,
        BottomLeft,
        BottomLeftB,
        BottomMiddleLeft,
        BottomMiddleLeftC,
        BottomMiddleRight,
        BottomRight,
        BottomRightB,
        Center,
        Bottom
    }

    public class Position{
        public int x;
        public int y;

        public Position(int xPos, int yPos){
            x = xPos;
            y = yPos;
        }
    }
}
