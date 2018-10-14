import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Enviro extends JFrame implements MouseListener, MouseMotionListener{

    boolean mhold = false;//mouse is held
    Timer t = new Timer();


    Map map = new Map();

    Head body;
    MouseTracker mouse;

    BodySeg[] spine = new BodySeg[4];
    int bodySize = 50;


    public Enviro()
    {
        super("Flow");
        setSize(1200, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        GRectangle screen = new GRectangle(50, 50, 1000, 1000);
        screen.setBackground(Color.BLACK);
        add(screen, 0);


        body = new Head();
        mouse = new MouseTracker(body);
        screen.add(body, 0);
        body.setLocation(getWidth()/2, getHeight()/2);

        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);

        setVisible(true);
        int fps = 50;
        t.schedule(new MyTimerTask(), 0, 1000/fps);
    }

    public Point findOvalMid(Oval o)
    {
        return new Point(o.getX()+o.getWidth()/2, o.getY()+o.getHeight()/2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        mhold=true;
        mouse.setX(e.getX());
        mouse.setY(e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mhold=false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        mouse.setX(e.getX());
        mouse.setY(e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    class MyTimerTask extends TimerTask
    {
        @Override
        public void run() {
            if(mhold){
                mouse.update();
                body.updateSpeed();
            }
            body.move();
            repaint();
        }
    }
}
