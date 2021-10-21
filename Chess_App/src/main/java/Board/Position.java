package Board;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        setPosition(x, y);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position getPosition() {
        return new Position(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Position)) {
            return false;
        }
        return (x == ((Position) o).x && y == ((Position) o).y);
    }
}
