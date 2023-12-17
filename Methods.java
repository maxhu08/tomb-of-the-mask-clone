import java.awt.*;

public class Methods {

  /**********************************************
   ******** Methods to use in your code *********
   **********************************************/
  /*
   * List of methods
   *
   *      drawButtonPressed(Graphics g, int width, int height,int[] mouse)
   *      drawGrid(Graphics g, int width, int height)
   */

  //This shows all the buttons being pressed on the keyboard, mouse, and mouse position
  public static void drawButtonPressed(
    Graphics2D g2d,
    int width,
    int height,
    int mouseX,
    int mouseY
  ) {
    int startRow0 = 120;
    int startRow1 = 130;
    int startRow2 = 140;
    int startRow3 = 150;

    int spacing = 20;

    g2d.setColor(new Color(40, 40, 40));
    g2d.fillRect(0, height, width / 2, 125);
    g2d.setColor(Color.GRAY);
    //up
    g2d.fillRect(50, height + 20, 20, 20);
    //down
    g2d.fillRect(50, height + 60, 20, 20);
    //left
    g2d.fillRect(30, height + 40, 20, 20);
    //right
    g2d.fillRect(70, height + 40, 20, 20);
    //mouse button
    g2d.fillOval(20, height + 80, 15, 15);
    g2d.drawString("X:" + mouseX + " Y:" + mouseY, 40, height + 93);

    g2d.drawString("1", startRow0, height + 15);
    g2d.drawString("2", startRow0 + spacing, height + 15);
    g2d.drawString("3", startRow0 + 2 * spacing, height + 15);
    g2d.drawString("4", startRow0 + 3 * spacing, height + 15);
    g2d.drawString("5", startRow0 + 4 * spacing, height + 15);
    g2d.drawString("6", startRow0 + 5 * spacing, height + 15);
    g2d.drawString("7", startRow0 + 6 * spacing, height + 15);
    g2d.drawString("8", startRow0 + 7 * spacing, height + 15);
    g2d.drawString("9", startRow0 + 8 * spacing, height + 15);
    g2d.drawString("0", startRow0 + 9 * spacing, height + 15);

    g2d.drawString("Q", startRow1, height + 35);
    g2d.drawString("W", startRow1 + spacing, height + 35);
    g2d.drawString("E", startRow1 + 2 * spacing, height + 35);
    g2d.drawString("R", startRow1 + 3 * spacing, height + 35);
    g2d.drawString("T", startRow1 + 4 * spacing, height + 35);
    g2d.drawString("Y", startRow1 + 5 * spacing, height + 35);
    g2d.drawString("U", startRow1 + 6 * spacing, height + 35);
    g2d.drawString("I", startRow1 + 7 * spacing, height + 35);
    g2d.drawString("O", startRow1 + 8 * spacing, height + 35);
    g2d.drawString("P", startRow1 + 9 * spacing, height + 35);

    g2d.drawString("A", startRow2, height + 55);
    g2d.drawString("S", startRow2 + spacing, height + 55);
    g2d.drawString("D", startRow2 + 2 * spacing, height + 55);
    g2d.drawString("F", startRow2 + 3 * spacing, height + 55);
    g2d.drawString("G", startRow2 + 4 * spacing, height + 55);
    g2d.drawString("H", startRow2 + 5 * spacing, height + 55);
    g2d.drawString("J", startRow2 + 6 * spacing, height + 55);
    g2d.drawString("K", startRow2 + 7 * spacing, height + 55);
    g2d.drawString("L", startRow2 + 8 * spacing, height + 55);

    g2d.drawString("Z", startRow3, height + 75);
    g2d.drawString("X", startRow3 + spacing, height + 75);
    g2d.drawString("C", startRow3 + 2 * spacing, height + 75);
    g2d.drawString("V", startRow3 + 3 * spacing, height + 75);
    g2d.drawString("B", startRow3 + 4 * spacing, height + 75);
    g2d.drawString("N", startRow3 + 5 * spacing, height + 75);
    g2d.drawString("M", startRow3 + 6 * spacing, height + 75);

    //space
    g2d.fillRect(165, height + 85, 100, 10);

    g2d.setColor(new Color(216, 135, 237));
    if (Keys.up) g2d.fillRect(50, height + 20, 20, 20);
    if (Keys.down) g2d.fillRect(50, height + 60, 20, 20);
    if (Keys.left) g2d.fillRect(30, height + 40, 20, 20);
    if (Keys.right) g2d.fillRect(70, height + 40, 20, 20);
    if (Keys.space) g2d.fillRect(165, height + 85, 100, 10);

    if (Mouse.button) g2d.fillOval(20, height + 80, 15, 15);
    //Row 1
    if (Keys.num1) g2d.drawString("1", startRow0, height + 15);
    if (Keys.num2) g2d.drawString("2", startRow0 + spacing, height + 15);
    if (Keys.num3) g2d.drawString("3", startRow0 + 2 * spacing, height + 15);
    if (Keys.num4) g2d.drawString("4", startRow0 + 3 * spacing, height + 15);
    if (Keys.num5) g2d.drawString("5", startRow0 + 4 * spacing, height + 15);
    if (Keys.num6) g2d.drawString("6", startRow0 + 5 * spacing, height + 15);
    if (Keys.num7) g2d.drawString("7", startRow0 + 6 * spacing, height + 15);
    if (Keys.num8) g2d.drawString("8", startRow0 + 7 * spacing, height + 15);
    if (Keys.num9) g2d.drawString("9", startRow0 + 8 * spacing, height + 15);
    if (Keys.num0) g2d.drawString("0", startRow0 + 9 * spacing, height + 15);

    if (Keys.q) g2d.drawString("Q", startRow1, height + 35);
    if (Keys.w) g2d.drawString("W", startRow1 + spacing, height + 35);
    if (Keys.e) g2d.drawString("E", startRow1 + 2 * spacing, height + 35);
    if (Keys.r) g2d.drawString("R", startRow1 + 3 * spacing, height + 35);
    if (Keys.t) g2d.drawString("T", startRow1 + 4 * spacing, height + 35);
    if (Keys.y) g2d.drawString("Y", startRow1 + 5 * spacing, height + 35);
    if (Keys.u) g2d.drawString("U", startRow1 + 6 * spacing, height + 35);
    if (Keys.i) g2d.drawString("I", startRow1 + 7 * spacing, height + 35);
    if (Keys.o) g2d.drawString("O", startRow1 + 8 * spacing, height + 35);
    if (Keys.p) g2d.drawString("P", startRow1 + 9 * spacing, height + 35);

    if (Keys.a) g2d.drawString("A", startRow2, height + 55);
    if (Keys.s) g2d.drawString("S", startRow2 + spacing, height + 55);
    if (Keys.d) g2d.drawString("D", startRow2 + 2 * spacing, height + 55);
    if (Keys.f) g2d.drawString("F", startRow2 + 3 * spacing, height + 55);
    if (Keys.g) g2d.drawString("G", startRow2 + 4 * spacing, height + 55);
    if (Keys.h) g2d.drawString("H", startRow2 + 5 * spacing, height + 55);
    if (Keys.j) g2d.drawString("J", startRow2 + 6 * spacing, height + 55);
    if (Keys.k) g2d.drawString("K", startRow2 + 7 * spacing, height + 55);
    if (Keys.l) g2d.drawString("L", startRow2 + 8 * spacing, height + 55);

    if (Keys.z) g2d.drawString("Z", startRow3, height + 75);
    if (Keys.x) g2d.drawString("X", startRow3 + spacing, height + 75);
    if (Keys.c) g2d.drawString("C", startRow3 + 2 * spacing, height + 75);
    if (Keys.v) g2d.drawString("V", startRow3 + 3 * spacing, height + 75);
    if (Keys.b) g2d.drawString("B", startRow3 + 4 * spacing, height + 75);
    if (Keys.n) g2d.drawString("N", startRow3 + 5 * spacing, height + 75);
    if (Keys.m) g2d.drawString("M", startRow3 + 6 * spacing, height + 75);
  }

  //This draws the grid on the screen.  It is togglable in the student file.
  public static void drawGrid(Graphics2D g2d, int width, int height) {
    g2d.setStroke(new BasicStroke(1));
    g2d.setFont(new Font("Helvetica", Font.PLAIN, 10));
    g2d.setColor(new Color(75, 75, 75));

    for (int x = 0; x <= width; x += 20) {
      g2d.setColor(new Color(75, 75, 75));
      g2d.drawLine(x, 0, x, 600);
      g2d.rotate(Math.toRadians(90), x, 0);
      g2d.setColor(new Color(175, 175, 175));
      g2d.drawString("" + x, x, -1);
      g2d.rotate(Math.toRadians(-90), x, 0);
    }
    for (int y = 0; y <= height; y += 20) {
      g2d.setColor(new Color(75, 75, 75));
      g2d.drawLine(0, y, 800, y);
      g2d.setColor(new Color(175, 175, 175));
      g2d.drawString("" + y, 1, y - 1);
    }
    g2d.setColor(new Color(175, 175, 175));
    for (int x = 0; x <= 800; x += 100) g2d.drawLine(x, 0, x, 600);
    for (int y = 0; y <= 600; y += 100) g2d.drawLine(0, y, 800, y);
  }
}
