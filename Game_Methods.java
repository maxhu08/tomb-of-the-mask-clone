import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

public class Game_Methods {

  public static void displayPlayerHealth(Graphics2D g2d, Game_Classes.Player player) {
    int triangleSideLength = 20;
    int startX = 20;
    int startY = 570;

    for (int i = 0; i < player.getHealth(); i++) {
      g2d.setColor(Color.RED);

      int[] xPoints = { startX, startX + triangleSideLength, startX + triangleSideLength / 2 };

      int[] yPoints = { startY, startY, startY + triangleSideLength };

      Polygon triangle = new Polygon(xPoints, yPoints, 3);
      g2d.fillPolygon(triangle);

      startX += triangleSideLength + 5;
    }
  }

  public static void drawMenu(Graphics2D g2d, Game_Classes.Menu menu, String text) {
    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
    g2d.drawString(text, 20, 30);

    for (int i = 0; i < menu.options.length; i++) {
      Game_Classes.MenuOption option = menu.options[i];

      if (i == menu.index) {
        Color selectedColor = new Color(61, 168, 226);
        g2d.setColor(selectedColor);
      } else {
        g2d.setColor(Color.WHITE);
      }
      g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
      g2d.drawString(option.getText(), 400, 100 + i * 40);
    }
  }

  public static void handleMenuSelection(Game_Classes.Menu menu) {
    if (Keys.spaceJustPressed) {
      Game_Classes.MenuOption menuOption = menu.options[menu.index];
      menuOption.action.run();
    }
    if ((Keys.wJustPressed || Keys.upJustPressed) && menu.index > 0) {
      menu.index -= 1;
    } else if ((Keys.sJustPressed || Keys.downJustPressed) && menu.index < menu.options.length - 1) {
      menu.index += 1;
    }
  }

  public static void handlePlayerMovement(Game_Classes.Player player) {
    if ((Keys.w || Keys.up) && !player.isMoving) {
      player.isMoving = true;
      player.setHasMoved(true);
      player.yV = -player.speed;
      Game_Main.lastRiseTime = System.currentTimeMillis();

      player.setLastPosition(new Game_Classes.Coordinate(player.x, player.y));
    }
    if ((Keys.a || Keys.left) && !player.isMoving) {
      player.isMoving = true;
      player.setHasMoved(true);
      player.xV = -player.speed;
      Game_Main.lastRiseTime = System.currentTimeMillis();

      player.setLastPosition(new Game_Classes.Coordinate(player.x, player.y));
    }
    if ((Keys.s || Keys.down) && !player.isMoving) {
      player.isMoving = true;
      player.setHasMoved(true);
      player.yV = player.speed;
      Game_Main.lastRiseTime = System.currentTimeMillis();

      player.setLastPosition(new Game_Classes.Coordinate(player.x, player.y));
    }
    if ((Keys.d || Keys.right) && !player.isMoving) {
      player.isMoving = true;
      player.setHasMoved(true);
      player.xV = player.speed;
      Game_Main.lastRiseTime = System.currentTimeMillis();

      player.setLastPosition(new Game_Classes.Coordinate(player.x, player.y));
    }
  }

  public static void updatePlayerMovement(
    Game_Classes.Player player,
    Game_Classes.WorldV2 currentWorld,
    Game_Classes.WorldCollectionV2 worldCollection,
    boolean pufferExpand
  ) {
    player.x += player.xV;
    player.y += player.yV;
    // double now = System.currentTimeMillis();

    for (int i = 0; i < currentWorld.coins.size(); i++) {
      Game_Classes.Coin c = currentWorld.coins.get(i);
      if (isColliding(player, c) && !c.collected) {
        c.collected = true;
        player.score += c.points;
      }
    }
    if (currentWorld.attribute != Game_Classes.WorldAttribute.FINAL) {
      if (isColliding(player, currentWorld.key)) {
        currentWorld.key.collected = true;
        currentWorld.portal.open = true;
      }
      if (isColliding(player, currentWorld.portal) && currentWorld.portal.open) {
        worldCollection.setNextWorldIndex();

        handleTransferToLevel(player, worldCollection);
        return;
      }
    }
    if (currentWorld.attribute == Game_Classes.WorldAttribute.RISING) {
      if (isColliding(player, currentWorld.risingWater) && !player.immune) {
        player.lastImmuneTime = System.currentTimeMillis();
        player.immune = true;
        if (player.getHealth() == 1) {
          Game_Classes.StateManager.setState(Game_Classes.State.DIED);
          return;
        }

        player.subtractHealth(1);
      }
    }
    for (int i = 0; i < currentWorld.bats.length; i++) {
      Game_Classes.Bat b = currentWorld.bats[i];
      if (isColliding(player, b) && !player.immune) {
        player.lastImmuneTime = System.currentTimeMillis();
        player.immune = true;
        if (player.getHealth() == 1) {
          Game_Classes.StateManager.setState(Game_Classes.State.DIED);
          return;
        }

        player.subtractHealth(1);
      }
    }
    for (int i = 0; i < currentWorld.spearShooters.length; i++) {
      Game_Classes.SpearShooter shooter = currentWorld.spearShooters[i];
      if (isColliding(shooter, player)) {
        player.x -= player.xV;
        player.y -= player.yV;
        player.xV = 0;
        player.yV = 0;
        player.isMoving = false;
      }
    }
    for (int i = 0; i < currentWorld.spears.size(); i++) {
      Game_Classes.SpearProjectile spear = currentWorld.spears.get(i);
      if (isColliding(spear, player) && !player.immune) {
        player.lastImmuneTime = System.currentTimeMillis();
        player.immune = true;
        if (player.getHealth() == 1) {
          Game_Classes.StateManager.setState(Game_Classes.State.DIED);
          return;
        }

        player.subtractHealth(1);
      }
    }
    for (int i = 0; i < currentWorld.puffers.length; i++) {
      Game_Classes.Puffer puffer = currentWorld.puffers[i];

      if (!pufferExpand && isColliding(puffer, player)) {
        player.x -= player.xV;
        player.y -= player.yV;
        player.xV = 0;
        player.yV = 0;
        player.isMoving = false;
      } else if (pufferExpand && isCollidingRaw(puffer.x - 30, puffer.y - 30, puffer.w + 60, puffer.h + 60, player.x, player.y, player.w, player.h)) {
        if (!player.immune) {
          player.lastImmuneTime = System.currentTimeMillis();
          player.immune = true;
          if (player.getHealth() == 1) {
            Game_Classes.StateManager.setState(Game_Classes.State.DIED);
            return;
          }
          player.subtractHealth(1);
        }
      }
    }
    for (int i = 0; i < currentWorld.walls.size(); i++) {
      Game_Classes.Wall w = currentWorld.walls.get(i);
      if (isColliding(player, w)) {
        player.x -= player.xV;
        player.y -= player.yV;
        player.xV = 0;
        player.yV = 0;
        player.isMoving = false;
      }
    }
  }

  public static void drawPlayer(Graphics2D g2d, Game_Classes.Player p) {
    g2d.setColor(p.col);
    if (p.immune) g2d.setColor(Color.WHITE);
    g2d.fillRect(p.x, p.y, p.w, p.h);

    g2d.setColor(p.col);

    if (p.lastPositionBeforeMoving == null) {
      p.setLastPosition(new Game_Classes.Coordinate(p.x, p.y));
    }

    // trail
    g2d.setStroke(new BasicStroke(10));

    if (p.xV != 0 || p.yV != 0) g2d.drawLine(p.lastPositionBeforeMoving.x + p.w / 2, p.lastPositionBeforeMoving.y + p.h / 2, p.x + p.w / 2, p.y + p.h / 2);
  }

  public static void drawWalls(Graphics2D g2d, Game_Classes.WorldV2 currentWorld) {
    g2d.setColor(currentWorld.col);
    for (int i = 0; i < currentWorld.walls.size(); i++) {
      Game_Classes.Wall w = currentWorld.walls.get(i);
      g2d.fillRect(w.x, w.y, w.w, w.h);
    }
  }

  public static void drawPortal(Graphics2D g2d, Game_Classes.WorldV2 currentWorld) {
    if (currentWorld.attribute == Game_Classes.WorldAttribute.FINAL) return;

    Game_Classes.Portal portal = currentWorld.portal;
    if (portal.open) {
      g2d.setColor(portal.col);
    } else {
      g2d.setColor(Color.GRAY);
    }
    g2d.fillRect(portal.x, portal.y, portal.w, portal.h);
  }

  public static void drawKey(Graphics2D g2d, Game_Classes.WorldV2 currentWorld) {
    if (currentWorld.attribute == Game_Classes.WorldAttribute.FINAL) return;

    Game_Classes.Key key = currentWorld.key;

    if (key.collected) return;

    g2d.setColor(key.col);

    // g2d.fillRect(key.x + 10, key.y + 10, key.w, key.h);

    // top
    g2d.fillRect(key.x + 12, key.y + 5, 16, 16);
    // stem
    g2d.fillRect(key.x + 17, key.y + 21, 6, 14);
    // ridges
    g2d.fillRect(key.x + 23, key.y + 24, 4, 4);
    g2d.fillRect(key.x + 23, key.y + 31, 4, 4);

    // hole
    g2d.setColor(Color.BLACK);
    g2d.fillRect(key.x + 16, key.y + 9, 8, 8);
  }

  public static void drawCoins(Graphics2D g2d, ArrayList<Game_Classes.Coin> coins, boolean animate, Game_Classes.WorldV2 currentWorld) {
    for (int i = 0; i < coins.size(); i++) {
      Game_Classes.Coin c = coins.get(i);
      if (!c.collected) {
        if (animate) {
          g2d.setColor(c.col);
        } else {
          g2d.setColor(currentWorld.col);
        }
        g2d.fillRect(c.x + 15, c.y + 15, c.w, c.h);
      }
    }
  }

  public static void displayPlayerScore(Graphics2D g2d, int score) {
    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
    g2d.drawString("score: " + score, 640, 30);
  }

  public static void drawSpearShooters(Graphics2D g2d, Game_Classes.SpearShooter[] spearShooters) {
    for (int i = 0; i < spearShooters.length; i++) {
      Game_Classes.SpearShooter shooter = spearShooters[i];
      g2d.setColor(Color.PINK);

      g2d.fillRect(shooter.x, shooter.y, shooter.w, shooter.h);

      g2d.setColor(shooter.col);
      switch (shooter.facing) {
        case LEFT:
          g2d.fillRect(shooter.x, shooter.y + 10, 10, 20);
          break;
        case RIGHT:
          g2d.fillRect(shooter.x + shooter.w - 10, shooter.y + 10, 10, 20);
          break;
        case UP:
          g2d.fillRect(shooter.x + 10, shooter.y, 20, 10);
          break;
        case DOWN:
          g2d.fillRect(shooter.x + 10, shooter.y + shooter.h - 10, 20, 10);
          break;
      }
    }
  }

  public static void shootSpearShooters(Game_Classes.SpearShooter[] spearShooters, Game_Classes.WorldV2 currentWorld, boolean shootSpears) {
    for (int i = 0; i < spearShooters.length; i++) {
      Game_Classes.SpearShooter shooter = spearShooters[i];

      if (shootSpears) if (shooter.facing == Game_Classes.Direction.LEFT || shooter.facing == Game_Classes.Direction.RIGHT) {
        currentWorld.addSpear(new Game_Classes.SpearProjectile(shooter.x, shooter.y + 15, 40, 10, shooter.facing));
      } else if (shooter.facing == Game_Classes.Direction.UP || shooter.facing == Game_Classes.Direction.DOWN) {
        currentWorld.addSpear(new Game_Classes.SpearProjectile(shooter.x + 15, shooter.y, 10, 40, shooter.facing));
      }
    }
  }

  public static void drawSpearProjectiles(Graphics2D g2d, ArrayList<Game_Classes.SpearProjectile> projs) {
    for (int i = 0; i < projs.size(); i++) {
      Game_Classes.SpearProjectile proj = projs.get(i);

      g2d.fillRect(proj.x, proj.y, proj.w, proj.h);
    }
  }

  public static void drawFinalWorldText(Graphics2D g2d, Game_Classes.WorldV2 currentWorld) {
    if (currentWorld.attribute != Game_Classes.WorldAttribute.FINAL) return;
    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));

    g2d.drawString("👍 you completed the game in " + formatTime(Game_Main.timeElapsed), 240, 260);
    g2d.drawString("thanks for playing", 340, 280);
  }

  public static void moveSpearProjectiles(Game_Classes.WorldV2 world) {
    for (int i = 0; i < world.spears.size(); i++) {
      Game_Classes.SpearProjectile spear = world.spears.get(i);

      for (int j = 0; j < world.walls.size(); j++) {
        if (isColliding(spear, world.walls.get(j))) {
          world.spears.remove(i);
        }
      }

      switch (spear.facing) {
        case LEFT:
          spear.x -= spear.speed;
          break;
        case RIGHT:
          spear.x += spear.speed;
          break;
        case UP:
          spear.y -= spear.speed;
          break;
        case DOWN:
          spear.y += spear.speed;
          break;
      }
    }
  }

  public static void drawBats(Graphics2D g2d, Game_Classes.Bat[] bats, boolean animate) {
    for (int i = 0; i < bats.length; i++) {
      Game_Classes.Bat bat = bats[i];

      if (Game_Main.devShowEnemyHitbox) {
        g2d.setColor(Color.GREEN);
        g2d.fillRect(bat.x, bat.y, bat.w, bat.h);
      }

      g2d.setColor(bat.col);

      int newY = bat.y + 10;
      int newX = bat.x + 20;

      Polygon wingLeft = new Polygon();
      Polygon wingRight = new Polygon();

      if (animate) {
        wingLeft.addPoint(newX, newY);
        wingLeft.addPoint(newX - 20, newY + bat.fakeH / 2);
        wingLeft.addPoint(newX, newY + bat.fakeH / 2);

        wingRight.addPoint(newX + bat.fakeW, newY);
        wingRight.addPoint(newX + bat.fakeW + 20, newY + bat.fakeH / 2);
        wingRight.addPoint(newX + bat.fakeW, newY + bat.fakeH / 2);
      } else {
        wingLeft.addPoint(newX, newY);
        wingLeft.addPoint(newX - 20, newY);
        wingLeft.addPoint(newX, newY + bat.fakeH / 2);

        wingRight.addPoint(newX + bat.fakeW, newY);
        wingRight.addPoint(newX + bat.fakeW + 20, newY);
        wingRight.addPoint(newX + bat.fakeW, newY + bat.fakeH / 2);
      }

      g2d.fillRect(newX, bat.y + 10, bat.fakeW, bat.fakeH);
      g2d.fillPolygon(wingLeft);
      g2d.fillPolygon(wingRight);
    }
  }

  public static void moveBats(Game_Classes.Bat[] bats, Game_Classes.WorldV2 currentWorld) {
    for (int i = 0; i < bats.length; i++) {
      Game_Classes.Bat bat = bats[i];

      for (int j = 0; j < currentWorld.walls.size(); j++) {
        Game_Classes.Wall w = currentWorld.walls.get(j);
        if (isColliding(bat, w)) {
          switch (bat.direction) {
            case LEFT:
              bat.direction = Game_Classes.BatDirection.RIGHT;
              break;
            case RIGHT:
              bat.direction = Game_Classes.BatDirection.LEFT;
              break;
          }
        }
      }

      for (int j = 0; j < currentWorld.spearShooters.length; j++) {
        Game_Classes.SpearShooter s = currentWorld.spearShooters[j];
        if (isColliding(bat, s)) {
          switch (bat.direction) {
            case LEFT:
              bat.direction = Game_Classes.BatDirection.RIGHT;
              break;
            case RIGHT:
              bat.direction = Game_Classes.BatDirection.LEFT;
              break;
          }
        }
      }

      switch (bat.direction) {
        case LEFT:
          bat.x += bat.speed;
          break;
        case RIGHT:
          bat.x -= bat.speed;
          break;
      }
    }
  }

  public static void displayCurrentWorld(Graphics2D g2d, int worldIndex) {
    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
    g2d.drawString("world-" + (worldIndex + 1), 20, 30);

    g2d.drawString("this is a secret", 1000, 800);
  }

  public static void drawPuffers(Graphics2D g2d, Game_Classes.Puffer[] puffers, Color wallColor, boolean pufferExpand) {
    for (int i = 0; i < puffers.length; i++) {
      Game_Classes.Puffer puffer = puffers[i];

      if (!pufferExpand) {
        g2d.setColor(wallColor);
        g2d.fillRect(puffer.x, puffer.y, puffer.w, puffer.h);

        g2d.setColor(Color.BLACK);

        int semicircleDiameter = 30;
        int semicircleX = puffer.x + 5;
        int semicircleY = puffer.y + 20 - semicircleDiameter / 2;

        // mouth
        g2d.fillArc(semicircleX, semicircleY, semicircleDiameter, semicircleDiameter, 180, 180);

        // eyes
        g2d.fillOval(puffer.x + 5, puffer.y + 5, 10, 10);
        g2d.fillOval(puffer.x + 25, puffer.y + 5, 10, 10);
      } else {
        g2d.setColor(puffer.col);

        // main body
        g2d.fillRect(puffer.x - 20, puffer.y - 20, 80, 80);
        g2d.fillRect(puffer.x, puffer.y - 30, 40, 100);
        g2d.fillRect(puffer.x - 30, puffer.y, 100, 40);

        // spikes
        g2d.fillRect(puffer.x - 30, puffer.y - 30, 20, 20);
        g2d.fillRect(puffer.x + 50, puffer.y - 30, 20, 20);
        g2d.fillRect(puffer.x + 50, puffer.y + 50, 20, 20);
        g2d.fillRect(puffer.x - 30, puffer.y + 50, 20, 20);

        g2d.setColor(Color.BLACK);

        int semicircleDiameter = 30;
        int semicircleX = puffer.x + 5;
        int semicircleY = puffer.y + 30 - semicircleDiameter / 2;

        // mouth
        g2d.fillArc(semicircleX, semicircleY, semicircleDiameter, semicircleDiameter, 180, -180);

        // eyes
        g2d.fillOval(puffer.x + 5, puffer.y + 5, 10, 10);
        g2d.fillOval(puffer.x + 25, puffer.y + 5, 10, 10);
      }
    }
  }

  public static void updateRisingWater(Game_Classes.WorldV2 world) {
    if (world.attribute != Game_Classes.WorldAttribute.RISING) return;
    if (world.risingWater.h > 1000) return;

    world.risingWater.h += world.risingWater.speed;
    world.risingWater.y -= world.risingWater.speed;
  }

  public static void drawRisingWater(Graphics2D g2d, Game_Classes.WorldV2 world) {
    if (world.attribute != Game_Classes.WorldAttribute.RISING) return;

    Game_Classes.RisingWater water = world.risingWater;

    g2d.setColor(world.risingWater.col);
    g2d.fillRect(water.x, water.y, water.w, water.h);
  }

  public static boolean isColliding(Game_Classes.Collidable obj1, Game_Classes.Collidable obj2) {
    return !(
      obj1.getX() + obj1.getW() <= obj2.getX() ||
      obj1.getY() + obj1.getH() <= obj2.getY() ||
      obj2.getY() + obj2.getH() <= obj1.getY() ||
      obj2.getX() + obj2.getW() <= obj1.getX()
    );
  }

  public static boolean isCollidingRaw(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
    return !(x1 + w1 <= x2 || y1 + h1 <= y2 || y2 + h2 <= y1 || x2 + w2 <= x1);
  }

  public static void displayDifficultyText(Graphics2D g2d) {
    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));

    int x = 700;
    int y = 580;

    switch (Game_Main.difficulty) {
      case EASY:
        g2d.drawString("EASY", x, y);
        break;
      case MEDIUM:
        g2d.drawString("MEDIUM", x, y);
        break;
      case HARD:
        g2d.drawString("HARD", x, y);
        break;
      default:
        g2d.drawString("", x, y);
        break;
    }
  }

  public static String getDifficultyText(Game_Classes.Difficulty diff) {
    switch (diff) {
      case EASY:
        return "[easy] - replenish health on new level";
      case MEDIUM:
        return "[medium] - don't replenish health on new level";
      case HARD:
        return "[hard] - nohit";
      default:
        return "default";
    }
  }

  public static void displayTimeElapsed(Graphics2D g2d, int time) {
    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));

    g2d.drawString(formatTime(time), 540, 580);
  }

  public static String formatTime(int time) {
    int minutes = (time / 1000) / 60;
    int seconds = (time / 1000) % 60;
    int milliseconds = time % 1000;

    return String.format("%02d:%02d.%03d", minutes, seconds, milliseconds);
  }

  public static void handleTransferToLevel(Game_Classes.Player player, Game_Classes.WorldCollectionV2 worldCollection) {
    if (Game_Main.difficulty == Game_Classes.Difficulty.EASY) {
      player.setHealth(3);
    }
    player.x = worldCollection.getCurrentWorld().spawn.x;
    player.y = worldCollection.getCurrentWorld().spawn.y;
    player.xV = 0;
    player.yV = 0;
    player.isMoving = false;
    player.setHasMoved(false);
  }

  public static void handlePause() {
    Game_Classes.StateManager.setState(Game_Classes.State.PAUSED);

    Game_Main.paused = !Game_Main.paused;
    Game_Main.playing = false;
  }

  public static void handleDev(Game_Classes.Player player, Game_Classes.WorldCollectionV2 worldCollection) {
    if (Keys.gJustPressed) {
      Runner.project.drawGrid = !Runner.project.drawGrid;
    }
    if (Keys.eJustPressed) {
      Game_Main.devShowEnemyHitbox = !Game_Main.devShowEnemyHitbox;
    }
    if (Keys.num1JustPressed) {
      worldCollection.setWorldIndex(0);
      handleTransferToLevel(player, worldCollection);
    }
    if (Keys.num2JustPressed) {
      worldCollection.setWorldIndex(1);
      handleTransferToLevel(player, worldCollection);
    }
    if (Keys.num3JustPressed) {
      worldCollection.setWorldIndex(2);
      handleTransferToLevel(player, worldCollection);
    }
    if (Keys.num4JustPressed) {
      worldCollection.setWorldIndex(3);
      handleTransferToLevel(player, worldCollection);
    }
    if (Keys.num5JustPressed) {
      worldCollection.setWorldIndex(4);
      handleTransferToLevel(player, worldCollection);
    }
    if (Keys.num6JustPressed) {
      worldCollection.setWorldIndex(5);
      handleTransferToLevel(player, worldCollection);
    }
    if (Keys.num7JustPressed) {
      worldCollection.setWorldIndex(6);
      Game_Classes.WorldV2 world = worldCollection.getCurrentWorld();
      world.risingWater.h = 20;
      handleTransferToLevel(player, worldCollection);
    }
    if (Keys.num8JustPressed) {
      worldCollection.setWorldIndex(7);
      handleTransferToLevel(player, worldCollection);
    }
    if (Keys.num9JustPressed) {
      worldCollection.setWorldIndex(8);
      handleTransferToLevel(player, worldCollection);
    }
  }
}
