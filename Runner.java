import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Runner {

  //Creates an instance of the Drawing class
  static Drawing project;
  //Global variables for the width and height of the window
  int windowWidth = 800;
  int windowHeight = 600;

  class DrawPanel extends JPanel {

    public void paintComponent(Graphics g) {
      //Creates an upgraded graphics version
      Graphics2D g2d = (Graphics2D) g.create();
      //calls the drawPerFrame method which is where students code
      project.drawPerFrame(g2d);

      //This draws the grid if the variable drawGrid in the student
      //file is true
      if (project.drawGrid) Methods.drawGrid(g2d, windowWidth, windowHeight);

      //gets the new mouse position and updates Mouse.x and Mouse.y
      updateMousePosition();

      //This draws the text in the bottom right of the window
      drawDirections(g2d);

      //draws which buttons are being pressed
      Methods.drawButtonPressed(g2d, windowWidth, windowHeight, Mouse.x, Mouse.y);

      //This makes sure that Mouse.buttonJustPressed is only true for one frame
      Mouse.buttonJustPressed = false;
      Keys.resetJustPressed();

      //This redraws the screen
      try {
        Thread.sleep(10);
      } catch (Exception e) {
        e.printStackTrace();
      }
      frame.repaint();
    }

    public void drawDirections(Graphics2D g2d) {
      //draws the background of the directions
      g2d.setColor(new Color(40, 40, 40));
      g2d.fillRect(windowWidth / 2, windowHeight, windowWidth / 2, 125);
      //create the font for the directions
      Font hudFont = new Font("Helvetica", Font.PLAIN, 12);

      //This draws the directions to the screen.
      g2d.setColor(new Color(247, 218, 69));
      g2d.setFont(hudFont);
      int spacing = 110 / project.directions.length;
      for (int i = 0; i < project.directions.length; i++) {
        g2d.drawString(project.directions[i], 400, windowHeight + 12 + spacing * i);
      }
    }

    public void updateMousePosition() {
      Point p = MouseInfo.getPointerInfo().getLocation();
      SwingUtilities.convertPointFromScreen(p, frame);
      Mouse.x = p.x;
      Mouse.y = p.y - 30;
    }

    public DrawPanel() {
      KeyListener keys = new Keys();

      addKeyListener(keys);
      setFocusable(true);

      MouseListener mListener = new Mouse();
      addMouseListener(mListener);
      setFocusable(true);
    }
  }

  JFrame frame;
  DrawPanel drawPanel;

  public static void main(String... args) {
    new Runner().go();
  }

  private void go() {
    frame = new JFrame("Max Hu - tomb-of-the-mask-clone");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(windowWidth + 20, windowHeight + 140));
    frame.setBackground(Color.BLACK);
    frame.pack();
    drawPanel = new DrawPanel();
    frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
    frame.setResizable(true);

    frame.setLocation(0, 0);
    frame.setVisible(true);
  }
}
