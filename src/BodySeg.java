public class BodySeg extends Oval {

    double dx = 0;
    double dy = 0;

    final int maxSpeed = 40;

    public BodySeg(int x, int y, int width, int height)
    {
        super(x,y,width,height);
    }

    public synchronized void addSpeed(double ddx, double ddy)
    {
        if(Math.abs(dx+ddx)>maxSpeed)
            dx=(dx>0)? maxSpeed:-1*maxSpeed;
        else
            dx+=ddx;
        if(Math.abs(dy+ddy)>maxSpeed)
            dy=(dy>0)? maxSpeed:-1*maxSpeed;
        else
            dy+=ddy;
    }

    public synchronized void move()
    {
        setLocation(getX() + (int)dx, getY() + (int)dy);
        dx+=(dx>0)? -0.1:0.1;
        dy+=(dy>0)? -0.1:0.1;
        if(Math.abs(dx)<0.1)
            dx=0;
        if(Math.abs(dy)<0.1)
            dy=0;
    }
}
