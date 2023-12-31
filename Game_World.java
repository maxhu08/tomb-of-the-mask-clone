import java.awt.Color;

public class Game_World {

  Color wallLightPurple = new Color(181, 99, 201);
  Color wallDarkPurple = new Color(124, 86, 206);
  Color wallBlue = new Color(0, 70, 168);
  Color wallGreen = new Color(122, 175, 42);
  Color wallFinal = new Color(60, 196, 157);

  // template
  // "████████████████████",
  // "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
  // "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
  // "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
  // "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
  // "█¢¢¢¢¢¢K¢¢¢¢¢¢¢¢¢¢¢█",
  // "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
  // "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
  // "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
  // "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
  // "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
  // "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
  // "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
  // "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
  // "████████████████████"

  public Game_Classes.WorldCollectionV2 worldCollectionV2 = new Game_Classes.WorldCollectionV2(
    new Game_Classes.WorldV2[] {
      // * WORLD 1 *
      new Game_Classes.WorldV2()
        .setLayout(
          new String[] {
            "████████████████████",
            "█     █     ¢¢¢█¢¢¢█",
            "█     █     █¢¢¢¢¢¢█",
            "█████¢█████████¢¢█¢█",
            "█¢¢K█¢¢¢█¢¢¢¢¢█¢¢█¢█",
            "█¢███¢█¢█¢█   █¢██¢█",
            "█¢¢¢█¢█¢█¢█   █¢¢█¢█",
            "███¢█¢█¢█¢███¢██¢█¢█",
            "█¢¢¢█¢¢¢█¢█¢¢¢█¢¢█¢█",
            "█¢█████¢█¢¢¢¢¢█¢██¢█",
            "█¢  █¢¢¢█¢¢¢¢¢█¢¢█¢█",
            "█¢  █¢███¢¢█¢¢¢¢¢█¢█",
            "█¢  █¢¢¢¢¢¢█████¢█¢█",
            "█¢¢¢¢¢¢█¢¢¢█¢¢¢¢¢█¢█",
            "████████████████████"
          }
        )
        .setSpawn(new Game_Classes.Coordinate(40, 40))
        .setColor(wallLightPurple)
        .setPortal(new Game_Classes.Portal(340, 30, 120, 20)),
      // * WORLD 2 *
      new Game_Classes.WorldV2()
        .setLayout(
          new String[] {
            "████████████████████",
            "█¢¢¢¢¢¢¢█¢¢██¢¢¢█¢¢█",
            "█       █¢¢¢¢¢¢¢¢¢¢█",
            "█       ███████¢¢¢¢█",
            "█       █¢¢¢¢¢█¢█¢██",
            "███████¢█¢¢¢¢¢¢¢█¢ █",
            "█K¢¢¢¢¢¢█¢  █████¢ █",
            "█       █¢¢¢¢¢¢¢█¢ █",
            "█████¢█¢█      ¢█¢ █",
            "█¢¢¢¢¢█¢█████  ¢█¢ █",
            "█¢███¢█¢█¢¢¢█  ¢████",
            "█¢█ █¢█¢ ¢█¢¢¢¢¢¢¢¢█",
            "█¢███¢█¢ ¢█¢  █¢¢¢¢█",
            "█¢¢¢¢¢¢¢¢¢█¢¢¢¢¢¢¢██",
            "████████████████████"
          }
        )
        .setSpawn(new Game_Classes.Coordinate(160, 40))
        .setColor(wallLightPurple)
        .setPortal(new Game_Classes.Portal(750, 240, 20, 120))
        .setBats(new Game_Classes.Bat[] { new Game_Classes.Bat(40, 120), new Game_Classes.Bat(120, 280), new Game_Classes.Bat(360, 320) }),
      // * WORLD 3 *
      new Game_Classes.WorldV2()
        .setLayout(
          new String[] {
            "██████ █████████████",
            "█   ██    ███   ████",
            "█   █K¢¢¢¢¢¢¢¢¢¢¢¢¢█",
            "█   ¢¢¢¢█     █¢¢¢¢█",
            "██████████████████¢█",
            "█¢¢¢¢¢¢¢█¢¢¢¢¢¢¢¢¢¢ ",
            "█¢█████¢█¢█ ██████¢█",
            "█¢█¢¢¢█¢█¢█       ¢█",
            "█¢█¢█¢█¢¢¢██████████",
            "█¢█¢█¢█████¢¢¢¢█¢¢¢█",
            "█¢¢¢         █¢¢¢█¢█",
            "██████████████████¢█",
            "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
            "                  ██",
            "████████████████████"
          }
        )
        .setSpawn(new Game_Classes.Coordinate(40, 40))
        .setColor(wallDarkPurple)
        .setPortal(new Game_Classes.Portal(340, 550, 120, 20))
        .setSpearShooters(
          // prettier-ignore
          new Game_Classes.SpearShooter[] {
            new Game_Classes.SpearShooter(240, 0, Game_Classes.Direction.DOWN),
            new Game_Classes.SpearShooter(760, 200, Game_Classes.Direction.LEFT),
            new Game_Classes.SpearShooter(160, 400, Game_Classes.Direction.RIGHT),
            new Game_Classes.SpearShooter(0, 520, Game_Classes.Direction.RIGHT),
          }
        ),
      // * WORLD 4 *
      new Game_Classes.WorldV2()
        .setLayout(
          new String[] {
            "████████████████████",
            "█ ¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
            "█¢¢███████████████¢█",
            "█¢██ ¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
            "█¢¢¢█¢██████████████",
            "███¢█¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
            "█¢¢¢████████¢█████¢█",
            "█¢¢¢¢¢¢¢¢¢¢¢¢█¢¢¢█¢█",
            "██¢████      █¢█¢█¢█",
            "██¢███████████¢█¢█¢█",
            "██¢¢¢█         █¢ ¢ ",
            "█¢¢█¢¢¢█¢¢¢¢¢¢¢█¢¢¢ ",
            "█¢██████       █████",
            "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢K█",
            "████████████████████"
          }
        )
        .setSpawn(new Game_Classes.Coordinate(360, 280))
        .setPortal(new Game_Classes.Portal(340, 350, 120, 20))
        .setColor(wallDarkPurple)
        .setSpearShooters(
          new Game_Classes.SpearShooter[] {
            new Game_Classes.SpearShooter(40, 40, Game_Classes.Direction.DOWN),
            //
            new Game_Classes.SpearShooter(160, 120, Game_Classes.Direction.RIGHT),
            //
            new Game_Classes.SpearShooter(760, 400, Game_Classes.Direction.LEFT),
            new Game_Classes.SpearShooter(760, 440, Game_Classes.Direction.LEFT),
            //
            new Game_Classes.SpearShooter(480, 240, Game_Classes.Direction.UP),
            //
            new Game_Classes.SpearShooter(320, 480, Game_Classes.Direction.DOWN),
            new Game_Classes.SpearShooter(360, 480, Game_Classes.Direction.DOWN),
            new Game_Classes.SpearShooter(400, 480, Game_Classes.Direction.DOWN),
            new Game_Classes.SpearShooter(440, 480, Game_Classes.Direction.DOWN),
            new Game_Classes.SpearShooter(480, 480, Game_Classes.Direction.DOWN),
            new Game_Classes.SpearShooter(520, 480, Game_Classes.Direction.DOWN),
            new Game_Classes.SpearShooter(560, 480, Game_Classes.Direction.DOWN)
          }
        )
        .setBats(new Game_Classes.Bat[] { new Game_Classes.Bat(400, 400) }),
      // * WORLD 5 *
      new Game_Classes.WorldV2()
        .setLayout(
          new String[] {
            "████████████████████",
            "███████████       K█",
            "██████¢   ██████   █",
            "█¢¢¢¢¢¢ █ ██████   █",
            "█¢  ¢██   ¢¢¢¢¢¢ █ █",
            "█¢  ¢█████████     █",
            "█¢ █¢██¢¢¢¢¢¢█¢¢¢¢¢█",
            "█¢¢¢¢██¢████¢███████",
            "███¢███¢█   ¢█¢¢¢¢¢█",
            "█ █¢¢¢¢¢█ █ ¢█¢███¢█",
            "█████████   ¢█¢█¢¢¢█",
            "█¢¢¢¢¢¢¢█¢¢¢¢¢¢█¢███",
            "█¢█ █  ¢████████¢¢¢█",
            "███    ¢¢¢¢¢¢¢¢¢¢¢¢█",
            "████████████████████"
          }
        )
        .setSpawn(new Game_Classes.Coordinate(40, 480))
        .setColor(wallBlue)
        .setPortal(new Game_Classes.Portal(460, 30, 120, 20))
        .setPuffers(
          new Game_Classes.Puffer[] {
            new Game_Classes.Puffer(160, 480),
            new Game_Classes.Puffer(400, 360),
            new Game_Classes.Puffer(120, 240),
            new Game_Classes.Puffer(320, 120),
            new Game_Classes.Puffer(680, 160)
          }
        ),
      // * WORLD 6 *
      new Game_Classes.WorldV2()
        .setLayout(
          new String[] {
            "████████████████████",
            "█            █    ██",
            "█      █        ¢  █",
            "█ █K¢¢¢¢        ¢  █",
            "█  ¢   ¢    █   ¢  █",
            "█  ¢   ¢    ¢¢¢¢¢█ █",
            "█  ¢   ¢    ¢      █",
            "██ ¢   ¢    ¢      █",
            "█¢¢¢¢¢¢¢█   ¢  █   █",
            "█¢ ¢       █¢¢¢¢   █",
            "█¢ ¢           ¢   █",
            "█¢ ¢¢¢¢¢¢█     ¢   █",
            "█¢ █    ¢¢¢¢¢¢¢¢█  █",
            "█¢      █          █",
            "████████████████████"
          }
        )
        .setSpawn(new Game_Classes.Coordinate(40, 520))
        .setColor(wallBlue)
        .setPortal(new Game_Classes.Portal(580, 30, 120, 20))
        .setPuffers(
          new Game_Classes.Puffer[] {
            // essential
            new Game_Classes.Puffer(320, 320),
            new Game_Classes.Puffer(120, 480),
            new Game_Classes.Puffer(640, 480),
            new Game_Classes.Puffer(600, 320),
            new Game_Classes.Puffer(480, 160),
            // deco
            new Game_Classes.Puffer(200, 200)
          }
        ),
      // * WORLD 7 *
      new Game_Classes.WorldV2()
        .setLayout(
          new String[] {
            "████████████ ███████",
            "█     ¢¢█¢¢█¢¢¢¢¢¢K█",
            "█ █   █¢¢¢¢█¢█¢    █",
            "█   ██████¢█¢█¢    █",
            "█   █¢¢¢¢¢¢█¢█¢█████",
            "█████¢████ █¢█¢¢¢¢¢█",
            "█   █¢¢¢¢¢¢¢¢█    ¢█",
            "█ █ ██████████████¢█",
            " ¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
            "██¢████████████ ████",
            "██¢   █¢¢¢¢¢¢¢█¢¢¢¢█",
            "██¢¢¢¢¢¢¢¢█¢¢¢¢¢¢█¢█",
            "███████ ██████████¢█",
            "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
            "████████████████████"
          }
        )
        .setSpawn(new Game_Classes.Coordinate(40, 520))
        .setColor(wallGreen)
        .setPortal(new Game_Classes.Portal(40, 190, 120, 20))
        .setAttribute(Game_Classes.WorldAttribute.RISING)
        .setRisingWater(// prettier-ignore
          new Game_Classes.RisingWater()
            .setColor(new Color(55, 229, 220))
            .setSpeed(1))
        .setPuffers(new Game_Classes.Puffer[] { new Game_Classes.Puffer(80, 280), new Game_Classes.Puffer(80, 80) })
        .setSpearShooters(
          new Game_Classes.SpearShooter[] {
            new Game_Classes.SpearShooter(600, 360, Game_Classes.Direction.DOWN),
            new Game_Classes.SpearShooter(280, 480, Game_Classes.Direction.UP),
            new Game_Classes.SpearShooter(0, 320, Game_Classes.Direction.RIGHT),
            new Game_Classes.SpearShooter(480, 0, Game_Classes.Direction.DOWN),
            new Game_Classes.SpearShooter(400, 200, Game_Classes.Direction.UP)
          }
        )
        .setBats(new Game_Classes.Bat[] { new Game_Classes.Bat(600, 80), new Game_Classes.Bat(560, 240) }),
      // * WORLD 8 *
      new Game_Classes.WorldV2()
        .setLayout(
          new String[] {
            "████████████████████",
            "█¢¢¢█   ¢¢¢¢¢¢█    █",
            "█¢¢¢¢¢¢¢¢█   ¢¢¢¢¢¢█",
            "███¢████████████████",
            "█¢¢¢ ¢¢¢¢¢¢█¢¢¢█¢¢  ",
            "█¢¢¢¢¢¢¢K ¢¢¢█¢¢¢¢  ",
            "█████████████████¢██",
            "█¢¢¢¢¢¢¢¢¢¢¢█    ¢ █",
            "█¢  █    ██¢   █ ¢ █",
            "█¢█ █¢¢¢¢██¢¢¢¢¢¢¢██",
            "█¢  █¢██¢¢██████████",
            "█¢¢¢¢¢███¢¢█¢¢¢¢¢███",
            "███████ ██¢¢¢█ █¢¢¢█",
            "█        █████████¢█",
            "████████████████████"
          }
        )
        .setSpawn(new Game_Classes.Coordinate(720, 520))
        .setColor(wallGreen)
        .setPortal(new Game_Classes.Portal(620, 30, 120, 20))
        .setAttribute(Game_Classes.WorldAttribute.RISING)
        .setRisingWater(// prettier-ignore
          new Game_Classes.RisingWater()
            .setColor(new Color(55, 229, 220))
            .setSpeed(1))
        .setPuffers(new Game_Classes.Puffer[] { new Game_Classes.Puffer(80, 360), new Game_Classes.Puffer(600, 320) })
        .setSpearShooters(
          new Game_Classes.SpearShooter[] {
            new Game_Classes.SpearShooter(560, 480, Game_Classes.Direction.UP),
            //
            new Game_Classes.SpearShooter(760, 160, Game_Classes.Direction.LEFT),
            new Game_Classes.SpearShooter(760, 200, Game_Classes.Direction.LEFT),
            //
            new Game_Classes.SpearShooter(360, 200, Game_Classes.Direction.RIGHT),
            //
            new Game_Classes.SpearShooter(200, 320, Game_Classes.Direction.UP),
            new Game_Classes.SpearShooter(240, 320, Game_Classes.Direction.UP),
            new Game_Classes.SpearShooter(280, 320, Game_Classes.Direction.UP),
            new Game_Classes.SpearShooter(320, 320, Game_Classes.Direction.UP),
            //
            new Game_Classes.SpearShooter(160, 160, Game_Classes.Direction.LEFT)
          }
        )
        .setBats(
          new Game_Classes.Bat[] {
            new Game_Classes.Bat(80, 200),
            //
            new Game_Classes.Bat(280, 40)
          }
        ),
      // * WORLD 9 - FINAL *
      new Game_Classes.WorldV2()
        .setLayout(
          new String[] {
            "████████████████████",
            "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
            "█¢████████████████¢█",
            "█¢█              █¢█",
            "█¢█              █¢█",
            "█¢█              █¢█",
            "█¢█              █¢█",
            "█¢█              █¢█",
            "█¢█              █¢█",
            "█¢█              █¢█",
            "█¢█              █¢█",
            "█¢█              █¢█",
            "█¢████████████████¢█",
            "█¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢¢█",
            "████████████████████"
          }
        )
        .setSpawn(new Game_Classes.Coordinate(720, 520))
        .setColor(wallFinal)
        .setPortal(new Game_Classes.Portal(620, 30, 120, 20))
        .setAttribute(Game_Classes.WorldAttribute.FINAL)
        .setBats(
          new Game_Classes.Bat[] {
            new Game_Classes.Bat(120, 440),
            new Game_Classes.Bat(160, 400),
            new Game_Classes.Bat(200, 360),
            new Game_Classes.Bat(240, 320),
            new Game_Classes.Bat(280, 280),
            new Game_Classes.Bat(320, 240),
            new Game_Classes.Bat(360, 200),
            new Game_Classes.Bat(400, 160),
            new Game_Classes.Bat(440, 120)
          }
        )
    }
  );
}
