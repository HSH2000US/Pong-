package PongV2;

import java.awt.*;

public class Arena
{
    public void draw (Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(345, 0, 10, 500 );
        ((Graphics2D) g).setStroke(new BasicStroke(10));
        int r = 80;
        g.drawOval(700/2 - r/2, 500/2 - r/2, r, r);
    }


}
