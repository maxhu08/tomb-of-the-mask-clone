import java.awt.*;

public abstract class Drawing {

  public boolean drawGrid = true;
  public String[] directions;

  public abstract void drawPerFrame(Graphics2D g2d);
}
