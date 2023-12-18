import java.awt.Color;
import java.util.ArrayList;
import java.util.function.Supplier;

public class Game_Classes {

  public static enum State {
    MENU,
    SETTINGS,
    GAME,
    DIED,
    OTHER,
    PAUSED
  }

  public class StateManager {

    private static State state = State.MENU;

    public static State getState() {
      return state;
    }

    public static void setState(State newState) {
      state = newState;
    }
  }

  public static class Menu {

    MenuOption[] options;
    int index;

    Menu(MenuOption[] options) {
      this.options = options;
      this.index = 0;
    }
  }

  public static class MenuOption {

    Supplier<String> textSupplier;
    Runnable action;

    MenuOption(Supplier<String> textSupplier, Runnable action) {
      this.textSupplier = textSupplier;
      this.action = action;
    }

    public String getText() {
      return textSupplier.get();
    }
  }

  public interface Collidable {
    int getX();
    int getY();
    int getW();
    int getH();
  }

  public static class Player implements Collidable {

    int defaultX;
    int defaultY;
    int x;
    int y;

    int w;
    int h;
    int xV;
    int yV;

    int speed;
    int score;
    private int health;
    boolean isMoving;
    Color col;
    double lastImmuneTime = System.currentTimeMillis();
    boolean immune = false;
    double immuneTime = 500;
    Coordinate lastPositionBeforeMoving;
    Direction movingDirection;
    boolean hasMoved = false;

    // double lastStunTime = System.currentTimeMillis();
    // boolean stunned = false;
    // double stunTime = 300;

    Player(int defaultX, int defaultY, int speed, boolean isMoving) {
      this.defaultX = defaultX;
      this.defaultY = defaultY;
      this.x = defaultX;
      this.y = defaultY;
      this.w = 40;
      this.h = 40;
      this.xV = 0;
      this.yV = 0;
      this.speed = speed;
      this.score = 0;
      // this.health = 3;
      this.isMoving = isMoving;
      this.col = new Color(247, 218, 69);
      this.hasMoved = false;
    }

    void reset() {
      this.x = defaultX;
      this.y = defaultY;
      this.w = 40;
      this.h = 40;
      this.xV = 0;
      this.yV = 0;
      this.score = 0;
      // this.health = 3;
      this.isMoving = false;
      this.col = new Color(247, 218, 69);
      this.lastImmuneTime = System.currentTimeMillis();
      this.immune = false;
      this.immuneTime = 500;
      this.hasMoved = false;
      // this.lastStunTime = System.currentTimeMillis();
      // this.stunned = false;
      // this.stunTime = 300;
    }

    void setHasMoved(boolean moved) {
      this.hasMoved = moved;
    }

    void setLastPosition(Coordinate position) {
      this.lastPositionBeforeMoving = position;
    }

    int getHealth() {
      return this.health;
    }

    void setHealth(int health) {
      this.health = health;
    }

    void subtractHealth(int dmg) {
      this.health -= dmg;
    }

    @Override
    public int getX() {
      return x;
    }

    @Override
    public int getY() {
      return y;
    }

    @Override
    public int getW() {
      return w;
    }

    @Override
    public int getH() {
      return h;
    }
  }

  public static class Coordinate {

    int x;
    int y;

    Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static class Wall implements Collidable {

    int x;
    int y;
    int w;
    int h;

    Wall(int x, int y, int w, int h) {
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
    }

    @Override
    public int getX() {
      return x;
    }

    @Override
    public int getY() {
      return y;
    }

    @Override
    public int getW() {
      return w;
    }

    @Override
    public int getH() {
      return h;
    }
  }

  public static class Portal implements Collidable {

    int x;
    int y;
    int w;
    int h;
    boolean open;
    Color col;

    Portal(int x, int y, int w, int h) {
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
      this.open = false;
      this.col = Color.WHITE;
    }

    @Override
    public int getX() {
      return x;
    }

    @Override
    public int getY() {
      return y;
    }

    @Override
    public int getW() {
      return w;
    }

    @Override
    public int getH() {
      return h;
    }
  }

  public static class WorldCollection {

    World[] worlds;
    private int worldIndex;

    WorldCollection(World[] worlds) {
      this.worlds = worlds;
      this.worldIndex = 0;
    }

    void setWorldIndex(int newWorldIndex) {
      worldIndex = newWorldIndex;
    }

    void setNextWorldIndex() {
      worldIndex += 1;
    }

    int getWorldIndex() {
      return worldIndex;
    }

    World getCurrentWorld() {
      return worlds[worldIndex];
    }
  }

  public static class World {

    Wall[] walls;
    Portal portal;
    Key key;
    ArrayList<Coin> coins = new ArrayList<>();
    Color col;
    Coordinate spawn;
    Bat[] bats = {};
    SpearShooter[] spearShooters = {};
    ArrayList<SpearProjectile> spears = new ArrayList<>();
    Puffer[] puffers = {};

    World(Wall[] walls, Portal portal, Key key, Color col, Coordinate spawn) {
      this.walls = walls;
      this.portal = portal;
      this.key = key;
      this.col = col;
      this.spawn = spawn;
    }

    World setBats(Bat[] bats) {
      this.bats = bats;
      return this;
    }

    World setSpearShooters(SpearShooter[] spearShooters) {
      this.spearShooters = spearShooters;
      return this;
    }

    void addSpear(SpearProjectile spear) {
      this.spears.add(spear);
    }

    World setPuffers(Puffer[] puffers) {
      this.puffers = puffers;
      return this;
    }

    World fillCoinExcept(Coordinate[] coordinates) {
      for (int x = 40; x < 760; x += 40) {
        for (int y = 40; y < 560; y += 40) {
          // except
          boolean shouldAddCoin = true;
          for (int i = 0; i < coordinates.length; i++) {
            if (coordinates[i].x == x && coordinates[i].y == y) {
              shouldAddCoin = false;
              break;
            }
          }
          if (shouldAddCoin) this.coins.add(new Coin(x, y));
        }
      }
      return this;
    }
  }

  public static class WorldCollectionV2 {

    WorldV2[] worlds;
    private int worldIndex;

    WorldCollectionV2(WorldV2[] worlds) {
      this.worlds = worlds;
      this.worldIndex = 0;
    }

    void setWorldIndex(int newWorldIndex) {
      worldIndex = newWorldIndex;
    }

    void setNextWorldIndex() {
      worldIndex += 1;
    }

    int getWorldIndex() {
      return worldIndex;
    }

    WorldV2 getCurrentWorld() {
      return worlds[worldIndex];
    }
  }

  public static class WorldV2 {

    ArrayList<Wall> walls = new ArrayList<>();
    Portal portal = null;
    Key key = null;
    ArrayList<Coin> coins = new ArrayList<>();
    Color col = null;
    Coordinate spawn = null;
    Bat[] bats = {};
    SpearShooter[] spearShooters = {};
    ArrayList<SpearProjectile> spears = new ArrayList<>();
    Puffer[] puffers = {};
    WorldAttribute attribute = WorldAttribute.NONE;
    RisingWater risingWater = null;

    WorldV2 WorldBuilder() {
      return this;
    }

    WorldV2 setLayout(String[] layout) {
      for (int i = 0; i < layout.length; i++) {
        int row = i;

        for (int j = 0; j < layout[i].length(); j++) {
          char character = layout[i].charAt(j);

          // tile is 40x40
          int x = j * 40;
          int y = row * 40;

          switch (character) {
            // empty space
            case ' ':
              break;
            // wall
            case '\u2588':
              walls.add(new Wall(x, y, 40, 40));
              break;
            // coin
            case '\u00a2':
              coins.add(new Coin(x, y));
              break;
            // key
            case 'K':
              this.key = new Key(x, y);
              break;
          }
        }
      }
      return this;
    }

    WorldV2 setAttribute(WorldAttribute attr) {
      this.attribute = attr;
      return this;
    }

    WorldV2 setRisingWater(RisingWater risingWater) {
      this.risingWater = risingWater;
      return this;
    }

    WorldV2 setSpawn(Coordinate spawn) {
      this.spawn = spawn;
      return this;
    }

    WorldV2 setColor(Color col) {
      this.col = col;
      return this;
    }

    WorldV2 setPortal(Portal portal) {
      this.portal = portal;
      return this;
    }

    WorldV2 setBats(Bat[] bats) {
      this.bats = bats;
      return this;
    }

    WorldV2 setSpearShooters(SpearShooter[] spearShooters) {
      this.spearShooters = spearShooters;
      return this;
    }

    void addSpear(SpearProjectile spear) {
      this.spears.add(spear);
    }

    WorldV2 setPuffers(Puffer[] puffers) {
      this.puffers = puffers;
      return this;
    }
  }

  public static enum WorldAttribute {
    NONE,
    RISING,
    FINAL
  }

  public static class RisingWater implements Collidable {

    int x;
    int y;
    int h;
    int w;
    int speed;
    Color col;

    RisingWater() {
      this.x = 0;
      this.y = 580;
      this.w = 800;
      this.h = 20;
    }

    RisingWater setSpeed(int speed) {
      this.speed = speed;
      return this;
    }

    RisingWater setColor(Color col) {
      this.col = col;
      return this;
    }

    @Override
    public int getX() {
      return x;
    }

    @Override
    public int getY() {
      return y;
    }

    @Override
    public int getW() {
      return w;
    }

    @Override
    public int getH() {
      return h;
    }
  }

  public static class Coin implements Collidable {

    int x;
    int y;
    int h;
    int w;
    int points;
    Color col;
    boolean collected;

    Coin(int x, int y) {
      this.x = x;
      this.y = y;
      this.h = 10;
      this.w = 10;
      this.points = 1;
      this.col = new Color(247, 218, 69);
      this.collected = false;
    }

    @Override
    public int getX() {
      return x;
    }

    @Override
    public int getY() {
      return y;
    }

    @Override
    public int getW() {
      return w;
    }

    @Override
    public int getH() {
      return h;
    }
  }

  public static class Key implements Collidable {

    int x;
    int y;
    int h;
    int w;
    Color col;
    boolean collected;

    Key(int x, int y) {
      this.x = x;
      this.y = y;
      this.h = 20;
      this.w = 20;
      this.col = new Color(69, 247, 158);
      this.collected = false;
    }

    @Override
    public int getX() {
      return x;
    }

    @Override
    public int getY() {
      return y;
    }

    @Override
    public int getW() {
      return w;
    }

    @Override
    public int getH() {
      return h;
    }
  }

  public static class Bat implements Collidable {

    int x;
    int y;
    int h;
    int w;
    int fakeH;
    int fakeW;
    int speed;
    Color col;
    BatDirection direction;

    Bat(int x, int y) {
      this.x = x;
      this.y = y;
      this.h = 40;
      this.w = 60;
      this.fakeH = 20;
      this.fakeW = 20;
      this.speed = 5;
      this.col = new Color(232, 27, 133);
      this.direction = BatDirection.LEFT;
    }

    @Override
    public int getX() {
      return x;
    }

    @Override
    public int getY() {
      return y;
    }

    @Override
    public int getW() {
      return w;
    }

    @Override
    public int getH() {
      return h;
    }
  }

  public static enum BatDirection {
    LEFT,
    RIGHT
  }

  public static class SpearShooter implements Collidable {

    int x;
    int y;
    int h;
    int w;
    Color col;
    Direction facing;

    SpearShooter(int x, int y, Direction facing) {
      this.x = x;
      this.y = y;
      this.h = 40;
      this.w = 40;
      this.col = new Color(232, 27, 133);
      this.facing = facing;
    }

    @Override
    public int getX() {
      return x;
    }

    @Override
    public int getY() {
      return y;
    }

    @Override
    public int getW() {
      return w;
    }

    @Override
    public int getH() {
      return h;
    }
  }

  public static class SpearProjectile implements Collidable {

    int x;
    int y;
    int h;
    int w;
    int speed;
    Color col;
    Direction facing;

    SpearProjectile(int x, int y, int w, int h, Direction facing) {
      this.x = x;
      this.y = y;
      this.h = h;
      this.w = w;
      this.speed = 10;
      this.col = new Color(232, 27, 133);
      this.facing = facing;
    }

    @Override
    public int getX() {
      return x;
    }

    @Override
    public int getY() {
      return y;
    }

    @Override
    public int getW() {
      return w;
    }

    @Override
    public int getH() {
      return h;
    }
  }

  public static enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN
  }

  public static class Puffer implements Collidable {

    int x;
    int y;
    int h;
    int w;
    PufferState state;
    Color col;

    Puffer(int x, int y) {
      this.x = x;
      this.y = y;
      this.h = 40;
      this.w = 40;
      this.col = new Color(232, 27, 133);
    }

    // void explode() {
    //   this.x = this.x - 30;
    //   this.y = this.y - 30;
    //   this.h = 100;
    //   this.w = 100;
    // }

    // void passive() {
    //   this.x = this.x + 30;
    //   this.y = this.y + 30;
    //   this.h = 40;
    //   this.w = 40;
    // }

    @Override
    public int getX() {
      return x;
    }

    @Override
    public int getY() {
      return y;
    }

    @Override
    public int getW() {
      return w;
    }

    @Override
    public int getH() {
      return h;
    }
  }

  public static enum PufferState {
    PASSIVE,
    EXPLODED
  }

  public static enum Difficulty {
    EASY,
    MEDIUM,
    HARD
  }
}
