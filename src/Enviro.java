import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Enviro extends JFrame implements MouseListener, MouseMotionListener{

    Rectangle map = new Rectangle(-500, -500, 5000, 5000);

    double mx = 500;
    double my = 375;
    final int middlex;
    final int middley;
    int middledist = 0;//distance from middle
    double mrad = 0;

    boolean mon = false;//mouse on screen
    boolean mhold = false;//mouse is held
    Timer t = new Timer();

    public Enviro()
    {
        super("Flow");
        setSize(1000, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        middlex = getWidth()/2;
        middley = getHeight()/2;

        map.setBackground(Color.BLACK);
        Random gen = new Random();
        for(int i = 0; i<1000; i++)
        {
            Rectangle rec = new Rectangle(gen.nextInt(5000), gen.nextInt(5000), gen.nextInt(10)+1, gen.nextInt(10)+1);
            rec.setBackground(Color.GRAY);
            map.add(rec);
        }
        add(map);

        Rectangle rec = new Rectangle(middlex-5+500, middley-5+500, 10, 10);
        rec.setBackground(Color.RED);
        map.add(rec, 0);
        repaint();

        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);

        setVisible(true);
        t.schedule(new MyTimerTask(this), 0, 100);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println(e.getX());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    class MyTimerTask extends TimerTask
    {
        Line line;
        Enviro win;

        public MyTimerTask(Enviro win)
        {
            this.win = win;
        }
        @Override
        public void run() {
            mrad = Math.atan2(my, mx);
            if(mhold)
            {
            }
        }
    }
}
