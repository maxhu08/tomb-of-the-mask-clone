import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game_Main extends Drawing {

  static Game_Classes.Player player = new Game_Classes.Player(40, 40, 20, false);
  static Game_Classes.Difficulty difficulty = Game_Classes.Difficulty.EASY;
  static Game_Classes.WorldCollectionV2 worldCollection = new Game_World().worldCollectionV2;
  static int timeElapsed = 0;

  public static void main(String[] args) {
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    int initialRiseDelay = 1000;
    int riseInterval = 100;

    scheduler.scheduleAtFixedRate(
      () -> {
        if (player.hasMoved) {
          Game_Methods.updateRisingWater(worldCollection.getCurrentWorld());
        }
      },
      initialRiseDelay,
      riseInterval,
      TimeUnit.MILLISECONDS
    );

    scheduler.scheduleAtFixedRate(
      () -> {
        if (playing && worldCollection.getCurrentWorld().attribute != Game_Classes.WorldAttribute.FINAL) {
          timeElapsed += 1;
        }
      },
      0,
      1,
      TimeUnit.MILLISECONDS
    );

    player.setHealth(3);

    Runner.project = new Game_Main();
    Runner.project.drawGrid = drawGrid;
    Runner.project.directions = directions;
    Runner.main();
  }

  static boolean drawGrid = false;
  // prettier-ignore
  static String[] directions = { 
    "💡 use wasd or arrow keys to move", 
    "💡 collect key to open portal", 
    "💡 don't get hit by enemies", 
    "💡 p to pause" 
  };

  double lastAnimationTime = System.currentTimeMillis();
  boolean animate = false;
  double animateInterval = 500;

  double lastIgnoreInputTime = System.currentTimeMillis();
  boolean ignoreInput = false;
  double ignoreInputInterval = 500;

  double lastSpearShootTime = System.currentTimeMillis();
  boolean shootSpears = false;
  double ignoreShootSpearsInterval = 4000;

  double lastPufferExpandTime = System.currentTimeMillis();
  boolean pufferExpand = false;
  double pufferExpandInterval = 3000;

  static double lastRiseTime = System.currentTimeMillis();
  static boolean devShowEnemyHitbox = false;

  static boolean paused = false;
  static boolean playing = false;

  @Override
  public void drawPerFrame(Graphics2D g2d) {
    Game_Methods.handleDev(player, worldCollection);

    if (Keys.pJustPressed && Game_Classes.StateManager.getState() == Game_Classes.State.GAME) {
      Game_Methods.handlePause();
    }

    switch (Game_Classes.StateManager.getState()) {
      case MENU:
        Game_Methods.drawMenu(g2d, menu, "menu");
        Game_Methods.handleMenuSelection(menu);
        break;
      case GAME:
        Game_Classes.WorldV2 currentWorld = worldCollection.getCurrentWorld();

        // player movement
        Game_Methods.handlePlayerMovement(player);
        Game_Methods.updatePlayerMovement(player, currentWorld, worldCollection, pufferExpand);

        // world
        Game_Methods.drawKey(g2d, currentWorld);
        Game_Methods.drawCoins(g2d, currentWorld.coins, animate, currentWorld);
        Game_Methods.drawWalls(g2d, currentWorld);
        Game_Methods.drawPortal(g2d, currentWorld);
        Game_Methods.drawFinalWorldText(g2d, currentWorld);

        // player
        Game_Methods.drawPlayer(g2d, player);

        // enemies
        Game_Methods.drawSpearShooters(g2d, currentWorld.spearShooters);
        Game_Methods.shootSpearShooters(currentWorld.spearShooters, currentWorld, shootSpears);
        Game_Methods.drawSpearProjectiles(g2d, currentWorld.spears);
        Game_Methods.moveSpearProjectiles(currentWorld);
        Game_Methods.drawBats(g2d, currentWorld.bats, animate);
        Game_Methods.moveBats(currentWorld.bats, currentWorld);
        Game_Methods.drawPuffers(g2d, currentWorld.puffers, currentWorld.col, pufferExpand);

        // rising water
        Game_Methods.drawRisingWater(g2d, currentWorld);

        // ui
        Game_Methods.displayCurrentWorld(g2d, worldCollection.getWorldIndex());
        Game_Methods.displayPlayerScore(g2d, player.score);
        Game_Methods.displayPlayerHealth(g2d, player);
        Game_Methods.displayDifficultyText(g2d);
        Game_Methods.displayTimeElapsed(g2d, timeElapsed);

        double now = System.currentTimeMillis();
        if (now - lastAnimationTime >= animateInterval) {
          animate = !animate;
          lastAnimationTime = now;
        }
        if (now - lastPufferExpandTime >= pufferExpandInterval) {
          pufferExpand = !pufferExpand;
          lastPufferExpandTime = now;
        }

        if (now - lastSpearShootTime >= ignoreShootSpearsInterval) {
          shootSpears = true;
          lastSpearShootTime = now;
        } else {
          shootSpears = false;
        }

        if (now - player.lastImmuneTime > player.immuneTime) {
          player.immune = false;
        }

        break;
      case DIED:
        Game_Methods.drawMenu(g2d, diedMenu, "you died");
        Game_Methods.handleMenuSelection(diedMenu);
        break;
      case SETTINGS:
        Game_Methods.drawMenu(g2d, settingsMenu, "settings");
        Game_Methods.handleMenuSelection(settingsMenu);
        break;
      case OTHER:
        Game_Methods.drawMenu(g2d, otherMenu, "other");
        Game_Methods.handleMenuSelection(otherMenu);
        break;
      case PAUSED:
        Game_Methods.drawMenu(g2d, pausedMenu, "paused");
        Game_Methods.handleMenuSelection(pausedMenu);
        break;
    }
  }

  Game_Classes.Menu menu = new Game_Classes.Menu(
    new Game_Classes.MenuOption[] {
      new Game_Classes.MenuOption(
        () -> "start",
        () -> {
          timeElapsed = 0;
          player.reset();
          worldCollection = new Game_World().worldCollectionV2;
          Game_Classes.StateManager.setState(Game_Classes.State.GAME);
          playing = true;
        }
      ),
      new Game_Classes.MenuOption(() -> "settings", () -> Game_Classes.StateManager.setState(Game_Classes.State.SETTINGS)),
      new Game_Classes.MenuOption(() -> "other", () -> Game_Classes.StateManager.setState(Game_Classes.State.OTHER))
    }
  );

  String difficultyText = Game_Methods.getDifficultyText(difficulty);
  Game_Classes.Menu settingsMenu = new Game_Classes.Menu(
    new Game_Classes.MenuOption[] {
      new Game_Classes.MenuOption(() -> "back", () -> Game_Classes.StateManager.setState(Game_Classes.State.MENU)),
      new Game_Classes.MenuOption(
        () -> difficultyText,
        () -> {
          switch (difficulty) {
            case EASY:
              Game_Main.difficulty = Game_Classes.Difficulty.MEDIUM;
              player.setHealth(3);
              break;
            case MEDIUM:
              Game_Main.difficulty = Game_Classes.Difficulty.HARD;
              player.setHealth(1);
              break;
            case HARD:
              Game_Main.difficulty = Game_Classes.Difficulty.EASY;
              player.setHealth(3);
              break;
          }
          difficultyText = Game_Methods.getDifficultyText(difficulty);
        }
      )
    }
  );

  Game_Classes.Menu diedMenu = new Game_Classes.Menu(
    new Game_Classes.MenuOption[] {
      new Game_Classes.MenuOption(
        () -> "restart",
        () -> {
          player.reset();
          worldCollection = new Game_World().worldCollectionV2;
          Game_Classes.StateManager.setState(Game_Classes.State.GAME);
          timeElapsed = 0;
        }
      ),
      new Game_Classes.MenuOption(
        () -> "menu",
        () -> {
          player.reset();
          Game_Classes.StateManager.setState(Game_Classes.State.MENU);
          timeElapsed = 0;
        }
      )
    }
  );

  Game_Classes.Menu pausedMenu = new Game_Classes.Menu(
    new Game_Classes.MenuOption[] {
      new Game_Classes.MenuOption(
        () -> "unpause",
        () -> {
          Game_Classes.StateManager.setState(Game_Classes.State.GAME);
          playing = true;
        }
      ),
      new Game_Classes.MenuOption(
        () -> "menu",
        () -> {
          Game_Classes.StateManager.setState(Game_Classes.State.MENU);
          timeElapsed = 0;
        }
      )
    }
  );

  Game_Classes.Menu otherMenu = new Game_Classes.Menu(
    new Game_Classes.MenuOption[] {
      new Game_Classes.MenuOption(() -> "menu", () -> Game_Classes.StateManager.setState(Game_Classes.State.MENU)),
      new Game_Classes.MenuOption(() -> "made by Max Hu", () -> {}),
      new Game_Classes.MenuOption(() -> "version: pre-v1.0.0a", () -> {})
    }
  );
}
