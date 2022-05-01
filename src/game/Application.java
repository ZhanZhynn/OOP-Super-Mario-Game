package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actor.Player;
import game.actor.Toad;
import game.ground.Dirt;
import game.ground.Floor;
import game.ground.Sprout;
import game.ground.Wall;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());


			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout());

			List<String> map = Arrays.asList(
					"..........................................##..........+.........................",
					"............+............+..................#...................................",
					"............................................#...................................",
					".............................................##......................+..........",
					"...............................................#................................",
					"................................................#...............................",
					".................+................................#.............................",
					".................................................##.............................",
					"................................................##..............................",
					".........+..............................+#____####.................+............",
					".......................................+#_____###++.............................",
					".......................................+#______###..............................",
					"........................................+#_____###..............................",
					"........................+........................##.............+...............",
					"...................................................#............................",
					"....................................................#...........................",
					"...................+.................................#..........................",
					"......................................................#.........................",
					".......................................................##.......................");

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			Actor mario = new Player("Player", 'm', 100);
			world.addPlayer(mario, gameMap.at(42, 10));

			Actor toad = new Toad();
			gameMap.addActor(toad, gameMap.at(42, 11));

			world.run();

	}
}
