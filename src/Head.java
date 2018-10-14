import java.awt.*;

public class Head extends BodySeg {

    MouseTracker mouse;

    final double kMouse = 0.002;

    public Head(){
        super(0, 0, 50, 50);
    }

    public void setTracker(MouseTracker mouse){
        this.mouse = mouse;
    }

    /*
     * changes the speed depending on the distance from the mouse,
     * and time elapsed
     */
    @Override
    public void updateSpeed() {
        double x = mouse.getDistanceX();
        double y = mouse.getDistanceY();
        dx += x * kMouse;
        dy += y * kMouse;
    }

    /*
     * sets the location, and also tells the mouse tracker where the
     * center of this body segment is in relation to the screen
     */
    @Override
    public void setLocation(int x, int y) {
        super.setLocation(x, y);
        mouse.setMidX(x + 25 + getParent().getX());
        mouse.setMidY(y + 25 + getParent().getY());
    }
}
