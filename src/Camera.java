

public class Camera extends GRectangle {

    double dx = 0;
    double dy = 0;

    public Camera(int x, int y, int w, int h)
    {
        super(x,y,w,h);
    }

    public void setSpeed(double dx, double dy)
    {
        this.dx = dx;
        this.dy = dy;
    }

    public void move()
    {
        setLocation(getX() + (int)dx, getY() + (int)dy);
    }

}
