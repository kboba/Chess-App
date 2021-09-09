package Game;

public class Position {
    private short x;
    private short y;

    public Position(short x, short y) {
        setPosition(x, y);
    }

    public void setPosition(short x, short y) {
        this.x = x;
        this.y = y;
    }

    public Position getPosition() {
        return new Position(x, y);
    }
}
