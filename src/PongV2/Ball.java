package PongV2;

import java.awt.*;

public class Ball
{
    private int inv;
    Tennis Tennis = new Tennis();
    double xVel;
    double yVel;
    double x;
    double y;
    long resetTime;
    int newX;
    int newX2;

    public Ball()
    {
        this.reset();
    }

    public void reset() {
        inv = 0;
        x = 350;
        y = 250;
        xVel = getRandomSpeed() * getRandomDirection();
        yVel = getRandomSpeed() * getRandomDirection();
        resetTime = System.currentTimeMillis();
    }

    public double getRandomSpeed()
    {
        return (Math.random() * 3 + 2);
    }

    public int getRandomDirection()
    {
        int rand = (int)(Math.random() * 2);
        if (rand == 1)
            return 1;
        else
            return -1;
    }

    public void draw (Graphics g)
    {
        g.setColor(Color.white);
        g.fillOval((int)x - 10



                , (int)y - 10, 20, 20);

    }

    public void checkPaddleCollision(Paddle p1, Paddle p2)
    {
        if (inv > 0)
        {
            return;
        }
        if (x <= p1.getX() + 20 && x >= p1.getX() - 20)
        {
            if (y >= p1.getY() && y <= p1.getY() + 80)
            {
                xVel = -xVel;
                inv = 10;
            }
        }
        else if (x <= p2.getX() + 20 && x >= p2.getX() - 20)
        {
            if (y >= p2.getY() && y <= p2.getY() + 80)
            {
                xVel = -xVel;
                inv = 10;
            }
        }



        // TODO: if the ball hits front of p1, push ball's x to be p1.x + 20
        // TODO: if the ball hits front of p2, push ball's x to be p2.x - 20
    }

    public void checkEdge(Paddle p1, Paddle p2)
    {



    }

    public void move()
    {
        if(System.currentTimeMillis() >= resetTime + 2000) {

            x += xVel;
            y += yVel;

            if (y < 10) {
                yVel = -yVel;
            }
            if (y > 490) {
                yVel = -yVel;
            }
            inv--;
        }
    }

    public int getX()
    {
        return (int)x;
    }

    public int getY()
    {
        return (int)y;
    }


}
