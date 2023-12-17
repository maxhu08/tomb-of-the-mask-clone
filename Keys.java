import java.awt.event.*;

public class Keys implements KeyListener {

  static boolean up = false;
  static boolean down = false;
  static boolean right = false;
  static boolean left = false;
  static boolean space = false;

  static boolean a = false;
  static boolean b = false;
  static boolean c = false;
  static boolean d = false;
  static boolean e = false;
  static boolean f = false;
  static boolean g = false;
  static boolean h = false;
  static boolean i = false;
  static boolean j = false;
  static boolean k = false;
  static boolean l = false;
  static boolean m = false;
  static boolean n = false;
  static boolean o = false;
  static boolean p = false;
  static boolean q = false;
  static boolean r = false;
  static boolean s = false;
  static boolean t = false;
  static boolean u = false;
  static boolean v = false;
  static boolean w = false;
  static boolean x = false;
  static boolean y = false;
  static boolean z = false;

  static boolean num1 = false;
  static boolean num2 = false;
  static boolean num3 = false;
  static boolean num4 = false;
  static boolean num5 = false;
  static boolean num6 = false;
  static boolean num7 = false;
  static boolean num8 = false;
  static boolean num9 = false;
  static boolean num0 = false;
  //All these are true only for one frame.
  static boolean upJustPressed = false;
  static boolean downJustPressed = false;
  static boolean rightJustPressed = false;
  static boolean leftJustPressed = false;
  static boolean spaceJustPressed = false;

  static boolean aJustPressed = false;
  static boolean bJustPressed = false;
  static boolean cJustPressed = false;
  static boolean dJustPressed = false;
  static boolean eJustPressed = false;
  static boolean fJustPressed = false;
  static boolean gJustPressed = false;
  static boolean hJustPressed = false;
  static boolean iJustPressed = false;
  static boolean jJustPressed = false;
  static boolean kJustPressed = false;
  static boolean lJustPressed = false;
  static boolean mJustPressed = false;
  static boolean nJustPressed = false;
  static boolean oJustPressed = false;
  static boolean pJustPressed = false;
  static boolean qJustPressed = false;
  static boolean rJustPressed = false;
  static boolean sJustPressed = false;
  static boolean tJustPressed = false;
  static boolean uJustPressed = false;
  static boolean vJustPressed = false;
  static boolean wJustPressed = false;
  static boolean xJustPressed = false;
  static boolean yJustPressed = false;
  static boolean zJustPressed = false;

  static boolean num1JustPressed = false;
  static boolean num2JustPressed = false;
  static boolean num3JustPressed = false;
  static boolean num4JustPressed = false;
  static boolean num5JustPressed = false;
  static boolean num6JustPressed = false;
  static boolean num7JustPressed = false;
  static boolean num8JustPressed = false;
  static boolean num9JustPressed = false;
  static boolean num0JustPressed = false;

  @Override
  public void keyTyped(KeyEvent event) {}

  @Override
  public void keyPressed(KeyEvent event) {
    //System.out.println("keyPressed=" + KeyEvent.getKeyText(e.getKeyCode()));
    int key = event.getKeyCode();

    if (key == KeyEvent.VK_UP) {
      if (!up) upJustPressed = true;
      up = true;
    }
    if (key == KeyEvent.VK_DOWN) {
      if (!down) downJustPressed = true;
      down = true;
    }
    if (key == KeyEvent.VK_RIGHT) {
      if (!right) rightJustPressed = true;
      right = true;
    }
    if (key == KeyEvent.VK_LEFT) {
      if (!left) leftJustPressed = true;
      left = true;
    }
    if (key == KeyEvent.VK_A) {
      if (!a) aJustPressed = true;
      a = true;
    }
    if (key == KeyEvent.VK_B) {
      if (!b) bJustPressed = true;
      b = true;
    }
    if (key == KeyEvent.VK_C) {
      if (!c) cJustPressed = true;
      c = true;
    }
    if (key == KeyEvent.VK_D) {
      if (!d) dJustPressed = true;
      d = true;
    }
    if (key == KeyEvent.VK_E) {
      if (!e) eJustPressed = true;
      e = true;
    }
    if (key == KeyEvent.VK_F) {
      if (!f) fJustPressed = true;
      f = true;
    }
    if (key == KeyEvent.VK_G) {
      if (!g) gJustPressed = true;
      g = true;
    }
    if (key == KeyEvent.VK_H) {
      if (!h) hJustPressed = true;
      h = true;
    }
    if (key == KeyEvent.VK_I) {
      if (!i) iJustPressed = true;
      i = true;
    }
    if (key == KeyEvent.VK_J) {
      if (!j) jJustPressed = true;
      j = true;
    }
    if (key == KeyEvent.VK_K) {
      if (!k) kJustPressed = true;
      k = true;
    }
    if (key == KeyEvent.VK_L) {
      if (!l) lJustPressed = true;
      l = true;
    }
    if (key == KeyEvent.VK_M) {
      if (!m) mJustPressed = true;
      m = true;
    }
    if (key == KeyEvent.VK_N) {
      if (!n) nJustPressed = true;
      n = true;
    }
    if (key == KeyEvent.VK_O) {
      if (!o) oJustPressed = true;
      o = true;
    }
    if (key == KeyEvent.VK_P) {
      if (!p) pJustPressed = true;
      p = true;
    }
    if (key == KeyEvent.VK_Q) {
      if (!q) qJustPressed = true;
      q = true;
    }
    if (key == KeyEvent.VK_R) {
      if (!r) rJustPressed = true;
      r = true;
    }
    if (key == KeyEvent.VK_S) {
      if (!s) sJustPressed = true;
      s = true;
    }
    if (key == KeyEvent.VK_T) {
      if (!t) tJustPressed = true;
      t = true;
    }
    if (key == KeyEvent.VK_U) {
      if (!u) uJustPressed = true;
      u = true;
    }
    if (key == KeyEvent.VK_V) {
      if (!v) vJustPressed = true;
      v = true;
    }
    if (key == KeyEvent.VK_W) {
      if (!w) wJustPressed = true;
      w = true;
    }
    if (key == KeyEvent.VK_X) {
      if (!x) xJustPressed = true;
      x = true;
    }
    if (key == KeyEvent.VK_Y) {
      if (!y) yJustPressed = true;
      y = true;
    }
    if (key == KeyEvent.VK_Z) {
      if (!z) zJustPressed = true;
      z = true;
    }
    if (key == KeyEvent.VK_SPACE) {
      if (!space) spaceJustPressed = true;
      space = true;
    }

    if (key == KeyEvent.VK_1) {
      if (!num1) num1JustPressed = true;
      num1 = true;
    }
    if (key == KeyEvent.VK_2) {
      if (!num2) num2JustPressed = true;
      num2 = true;
    }
    if (key == KeyEvent.VK_3) {
      if (!num3) num3JustPressed = true;
      num3 = true;
    }
    if (key == KeyEvent.VK_4) {
      if (!num4) num4JustPressed = true;
      num4 = true;
    }
    if (key == KeyEvent.VK_5) {
      if (!num5) num5JustPressed = true;
      num5 = true;
    }
    if (key == KeyEvent.VK_6) {
      if (!num6) num6JustPressed = true;
      num6 = true;
    }
    if (key == KeyEvent.VK_7) {
      if (!num7) num7JustPressed = true;
      num7 = true;
    }
    if (key == KeyEvent.VK_8) {
      if (!num8) num8JustPressed = true;
      num8 = true;
    }
    if (key == KeyEvent.VK_9) {
      if (!num9) num9JustPressed = true;
      num9 = true;
    }
    if (key == KeyEvent.VK_0) {
      if (!num0) num0JustPressed = true;
      num0 = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent event) {
    int key = event.getKeyCode();

    if (key == KeyEvent.VK_UP) {
      up = false;
    }
    if (key == KeyEvent.VK_DOWN) {
      down = false;
    }
    if (key == KeyEvent.VK_RIGHT) {
      right = false;
    }
    if (key == KeyEvent.VK_LEFT) {
      left = false;
    }
    if (key == KeyEvent.VK_A) {
      a = false;
    }
    if (key == KeyEvent.VK_B) {
      b = false;
      bJustPressed = false;
    }
    if (key == KeyEvent.VK_C) {
      c = false;
    }
    if (key == KeyEvent.VK_D) {
      d = false;
    }
    if (key == KeyEvent.VK_E) {
      e = false;
    }
    if (key == KeyEvent.VK_F) {
      f = false;
    }
    if (key == KeyEvent.VK_G) {
      g = false;
    }
    if (key == KeyEvent.VK_H) {
      h = false;
    }
    if (key == KeyEvent.VK_I) {
      i = false;
    }
    if (key == KeyEvent.VK_J) {
      j = false;
    }
    if (key == KeyEvent.VK_K) {
      k = false;
    }
    if (key == KeyEvent.VK_L) {
      l = false;
    }
    if (key == KeyEvent.VK_M) {
      m = false;
    }
    if (key == KeyEvent.VK_N) {
      n = false;
    }
    if (key == KeyEvent.VK_O) {
      o = false;
    }
    if (key == KeyEvent.VK_P) {
      p = false;
    }
    if (key == KeyEvent.VK_Q) {
      q = false;
    }
    if (key == KeyEvent.VK_R) {
      r = false;
    }
    if (key == KeyEvent.VK_S) {
      s = false;
    }
    if (key == KeyEvent.VK_T) {
      t = false;
    }
    if (key == KeyEvent.VK_U) {
      u = false;
    }
    if (key == KeyEvent.VK_V) {
      v = false;
    }
    if (key == KeyEvent.VK_W) {
      w = false;
    }
    if (key == KeyEvent.VK_X) {
      x = false;
    }
    if (key == KeyEvent.VK_Y) {
      y = false;
    }
    if (key == KeyEvent.VK_Z) {
      z = false;
    }
    if (key == KeyEvent.VK_SPACE) {
      space = false;
    }
    if (key == KeyEvent.VK_1) {
      num1 = false;
    }
    if (key == KeyEvent.VK_2) {
      num2 = false;
    }
    if (key == KeyEvent.VK_3) {
      num3 = false;
    }
    if (key == KeyEvent.VK_4) {
      num4 = false;
    }
    if (key == KeyEvent.VK_5) {
      num5 = false;
    }
    if (key == KeyEvent.VK_6) {
      num6 = false;
    }
    if (key == KeyEvent.VK_7) {
      num7 = false;
    }
    if (key == KeyEvent.VK_8) {
      num8 = false;
    }
    if (key == KeyEvent.VK_9) {
      num9 = false;
    }
    if (key == KeyEvent.VK_0) {
      num0 = false;
    }
  }

  public static void resetJustPressed() {
    upJustPressed = false;
    downJustPressed = false;
    rightJustPressed = false;
    leftJustPressed = false;
    spaceJustPressed = false;

    aJustPressed = false;
    bJustPressed = false;
    cJustPressed = false;
    dJustPressed = false;
    eJustPressed = false;
    fJustPressed = false;
    gJustPressed = false;
    hJustPressed = false;
    iJustPressed = false;
    jJustPressed = false;
    kJustPressed = false;
    lJustPressed = false;
    mJustPressed = false;
    nJustPressed = false;
    oJustPressed = false;
    pJustPressed = false;
    qJustPressed = false;
    rJustPressed = false;
    sJustPressed = false;
    tJustPressed = false;
    uJustPressed = false;
    vJustPressed = false;
    wJustPressed = false;
    xJustPressed = false;
    yJustPressed = false;
    zJustPressed = false;

    num1JustPressed = false;
    num2JustPressed = false;
    num3JustPressed = false;
    num4JustPressed = false;
    num5JustPressed = false;
    num6JustPressed = false;
    num7JustPressed = false;
    num8JustPressed = false;
    num9JustPressed = false;
    num0JustPressed = false;
  }
}
