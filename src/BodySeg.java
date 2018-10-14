import java.awt.*;

public abstract class BodySeg extends Oval {

    double dx = 0;
    double dy = 0;

    public BodySeg(int x, int y, int width, int height)
    {
        super(x,y,width,height);
        setBackground(Color.WHITE);
    }


    public abstract void updateSpeed();

    public synchronized void move()
    {
        setLocation(getX() + (int)dx, getY() + (int)dy);
        dx+=(dx>0)? -0.0001:0.0001;
        dy+=(dy>0)? -0.0001:0.0001;
        if(Math.abs(dx)<0.001)
            dx=0;
        if(Math.abs(dy)<0.001)
            dy=0;
    }
}
