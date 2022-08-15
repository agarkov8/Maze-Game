package team06.main.Games.Game1.GameUI;

public class FixedPositions {
    int offset = 60;
    int offsetBottom = 90;

    public Position CalculatePosition(PredefinedPosition predefinedPosition, int width, int height){
        switch (predefinedPosition) {
            case TopLeft:
                return new Position(offset, offset);
            case TopMiddleLeft:
                return new Position((int) ((double) width / (double) 3), offset);
            case TopMiddleRight:
                return new Position((int) ((double) width / 1.5), offset);
            case TopRight:
                return new Position(width - offset, offset);
            case MiddleTopLeft:
                return new Position(offset, (int) ((double) height / (double) 3));
            case MiddleTopRight:
                return new Position(width - offset, (int) ((double) height / (double) 3));
            case MiddleBottomLeft:
                return new Position(offset, (int) ((double) height / 1.5));
            case MiddleBottomRight:
                return new Position(width - offset, (int) ((double) height / 1.5));
            case BottomLeft:
                return new Position(offset, height - offsetBottom);
            case BottomMiddleLeft:
                return new Position((int) ((double) width / (double) 3), height - offsetBottom);
            case BottomMiddleRight:
                return new Position((int) ((double) width / 1.5), height - offsetBottom);
            case BottomRight:
                return new Position(width - offset, height - offsetBottom);
            case Center:
                return new Position(width / 2, height / 2);
            case Bottom:
                return new Position(width / 2, height);
            default:
                return null;
        }
    }

    public enum PredefinedPosition {
        TopLeft,
        TopMiddleLeft,
        TopMiddleRight,
        TopRight,
        MiddleTopLeft,
        MiddleTopRight,
        MiddleBottomLeft,
        MiddleBottomRight,
        BottomLeft,
        BottomMiddleLeft,
        BottomMiddleRight,
        BottomRight,
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
