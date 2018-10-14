import java.awt.event.MouseEvent;

public class MouseTracker {

    double midX, midY;//head pos

    double x = 0;//mouse pos
    double y = 0;
    double rad = 0;//angle of mouse in radians

    double distanceX, distanceY = 0;

    final int xOffSet = 2;
    final int yOffSet = 25;

    Head head;

    public MouseTracker(Head head){
        this.head = head;
        head.setTracker(this);
    }

    public void setX(double x){
        this.x = x - 2;
    }

    public void setY(double y){
        this.y = y - 25;
    }

    public void setMidX(double x){
        midX = x;
    }

    public void setMidY(double midY) {
        this.midY = midY;
    }

    public double getDistanceX() {
        return distanceX;
    }

    public double getDistanceY() {
        return distanceY;
    }

    /*
     * updates the distance between the mouse and the head
     */
    public void update(){
        distanceX = x - midX;
        distanceY = y - midY;
    }
}
