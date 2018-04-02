import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Enviro extends JFrame implements MouseListener, MouseMotionListener{

    double mx = 500;//mouse pos
    double my = 375;
    final int middlex;//middle of screen
    final int middley;
    double mrad = 0;//angle of mouse in radians

    boolean mhold = false;//mouse is held
    Timer t = new Timer();

    final int screenWidth = 1400;
    final int screenHeight = 1000;

    GRectangle camera;
    int cameraMidX;
    int cameraMidY;
    GRectangle map = new GRectangle(screenWidth*9/-2, screenHeight*9/-2, screenWidth*10, screenHeight*10);///WORK ON CHUNKS, CHUNK LOADING, AND RENDER DISTANCE

    Oval body;
    Oval[] spine = new Oval[4];
    int bodySize = 50;
    double bodySpeed = 0.00;

    public Enviro()
    {
        super("Flow");
        setSize(1400, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        middlex = getWidth()/2;
        middley = getHeight()/2;

        map.setBackground(Color.BLACK);
        Random gen = new Random();
        for(int i = 0; i<2000; i++)
        {
            GRectangle rec = new GRectangle(gen.nextInt(map.getWidth()), gen.nextInt(map.getHeight()), gen.nextInt(10)+1, gen.nextInt(10)+1);
            rec.setBackground(Color.GRAY);
            map.add(rec);
        }
        add(map);

        camera = new GRectangle(0,0,getWidth(), getHeight());
        camera.setVisible(false);
        map.add(camera);
        camera.setLocation((map.getWidth()-getWidth())/2, (map.getHeight()-getHeight())/2);
        findCameraMiddle();

        body = new Oval(camera.getX()+(camera.getWidth()-bodySize)/2, camera.getY()+(camera.getHeight()-bodySize)/2, bodySize, bodySize);
        body.setBackground(Color.WHITE);
        map.add(body, 0);

        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);

        setVisible(true);
        int fps = 50;
        t.schedule(new MyTimerTask(), 0, 1000/fps);
    }

    public void findCameraMiddle()
    {
        cameraMidX = camera.getX() + camera.getWidth()/2;
        cameraMidY = camera.getY() + camera.getHeight()/2;
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
        mx=e.getX();
        my=e.getY();
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
        mx=e.getX();
        my=e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    class MyTimerTask extends TimerTask
    {
        @Override
        public void run() {
            Point bodyMid = findOvalMid(body);
            int bmx = (int)bodyMid.getX()-camera.getX();//finds pos of body on screen
            int bmy = (int)bodyMid.getY()-camera.getY();

            if(mhold)//find movement of body in relation to mouse
            {
                double a = Math.sqrt(Math.pow(mx-bmx,2)+Math.pow(my-bmy,2));
                bodySpeed += (a<bodySize)? 0:a/1000.0000000;
            }
            if(bodySpeed!=0)
                bodySpeed-=0.01;
            if(bodySpeed<0)
            {
                bodySpeed=0;
            }
            mrad = Math.atan2(mx-bmx, my-bmy);
            body.setLocation((int)Math.round(bodySpeed*Math.sin(mrad))+body.getX(), (int)Math.round(bodySpeed*Math.cos(mrad))+body.getY());

            //find movement of camera in relation to body
            double speed = Math.sqrt(Math.pow(bodyMid.getX()-cameraMidX,2)+Math.pow(bodyMid.getY()-cameraMidY,2))/15;
            double brad = Math.atan2(bodyMid.getX()-cameraMidX, bodyMid.getY()-cameraMidY);
            camera.setLocation((int)Math.round(speed*Math.sin(brad))+camera.getX(), (int)Math.round(speed*Math.cos(brad))+camera.getY());

            findCameraMiddle();
            map.setLocation(camera.getX()*(-1), camera.getY()*(-1));
            repaint();
        }
    }
}
