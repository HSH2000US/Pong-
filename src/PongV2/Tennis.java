package PongV2;



import java.applet.Applet;
import java.awt.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;

// depreciated 
public class Tennis extends Applet implements Runnable, KeyListener
{
    final int WIDTH = 700, HEIGHT = 500;
    private int p1Score;
    private int p2Score;
    Arena ArenaObj = new Arena();
    Thread thread;
    HumanPaddle p1;
    HumanPaddle p2;
    Ball b1;
    boolean gameStarted;
    Graphics gfx;
    Image img;

    public void init()
    {
        this.addKeyListener(this);
        gameStarted = false;
        this.resize(WIDTH, HEIGHT);
        p1 = new HumanPaddle(2);
        p2 = new HumanPaddle(1);
        b1 =  new Ball();
        img = createImage(WIDTH, HEIGHT);
        gfx = img.getGraphics();
        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g)
    {
        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, WIDTH, HEIGHT);

        ArenaObj.draw(gfx);
        gfx.setColor(Color.white);
        gfx.drawString("Player One Score: " + p1Score, 5, 15);
        gfx.drawString("Player Two Score: " + p2Score, WIDTH - 130, 15);
        gfx.setColor(Color.white);


        if(b1.getX() < -10 || b1.getX() > 710)
        {
            if (b1.getX() < -10)
            {
                p2Score++;
            }
            else if (b1.getX() > 710)
            {
                p1Score++;
            }

            b1.reset();

            if (p1Score == 7)
            {
                gfx.setColor(Color.red);
                gfx.drawString("Game Over Player One Wins", 350, 250);
                gameStarted = !gameStarted;
            }
            else if (p2Score == 7)
            {
                gfx.setColor(Color.red);
                gfx.drawString("Game Over Player Two Wins", 350, 250);
                gameStarted = !gameStarted;
            }
        }

        p2.draw(gfx);
        p1.draw(gfx);
        b1.draw(gfx);

        if (!gameStarted)
        {
            gfx.setColor(Color.white);
            gfx.drawString("Tennis", 400, 100);
            gfx.drawString("Press Enter to Begin Game ", 390, 130);
        }
        g.drawImage(img, 0, 0, this);



    }

    public void update(Graphics g)
    {
        paint(g);
    }


    public void run()
    {
      for (;;)
      {
          if (gameStarted)
          {
              p2.move();

              p1.move();

              b1.move();

              b1.checkEdge(p1, p2);

              b1.checkPaddleCollision(p1, p2);


          }
          else {
              p1Score = 0;
              p2Score = 0;
              b1.reset();
          }

          repaint();
          try {
              Thread.sleep(10);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
    }




    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            p1.setUpAccel(true);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            p1.setDownAccel(true);
        }
        else if (e.getKeyCode() == KeyEvent.VK_W)
        {
            p2.setUpAccel(true);
        }
        else if (e.getKeyCode() == KeyEvent.VK_S)
        {
            p2.setDownAccel(true);
        }
        else if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            gameStarted = true;
        }
    }








    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            p1.setUpAccel(false);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            p1.setDownAccel(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_W)
        {
            p2.setUpAccel(false);
        }
        else if (e.getKeyCode() == KeyEvent.VK_S)
        {
            p2.setDownAccel(false);
        }
    }



    public void keyTyped(KeyEvent e)
    {

    }
}

