public class Robot {

    private int x;
    private int y;
    private int dx;
    private int dy;
    private int speed;

    public Robot () {

    }

    public int getX () {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDX () {
        return dx;
    }

    public int getDY () {
        return dy;
    }

    public int getSpeed () {
        return speed;
    }

    public void setX (int x) {
        this.x = x;
    }

    public void setY (int y) {
        this.y=y;
    }

    public void setDX (int dx) {
        this.dx = dx;
    }

    public void setDY (int dy) {
        this.dy=dy;
    }

    public void setSpeed (int speed) {
        this.speed=speed;
    }

    public String toString () {
        return this.x+"&"+this.y+"&"+this.dx+"&"+this.dy+"&"+this.speed;
    }
}
