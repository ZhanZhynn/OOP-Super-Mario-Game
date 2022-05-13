package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.World;
import game.actor.Player;
import game.actor.Toad;
import game.ground.*;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

//			WarpPipe pipe = new WarpPipe();
			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout());

			FancyGroundFactory groundLavaFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(),new Lava());

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


			List<String> lavaZone = Arrays.asList(
					"......L.....................##..........+.....................",
					"...........+.......L..........#.....+.................+.......",
					"......L........................#..............LL..............",
					"......LLL.........L.................#.......L...........L.....",
					"............................##................................",
					"....+...........L...............................##............",
					".........+...........+..................+#____####....LLLL....",
					"..............................L........+#_____###++...L.......",
					"....L..........LL........L.............+#______###....L.......",
					"........................................+#_____###............",
					"...........L............+........................##.....LLL...",
					".....L.....L.....................+.................#..........",
					"....................................................#........."
);

			GameMap gameMap = new GameMap(groundFactory, map);
			GameMap lavaMap = new GameMap(groundLavaFactory, lavaZone);

			world.addGameMap(gameMap);
			world.addGameMap(lavaMap);

			Actor mario = new Player("Player", 'm', 100);
			world.addPlayer(mario, gameMap.at(42, 10));

			WarpPipe pipe = new WarpPipe();
//			pipe.allowableActions(mario, lavaMap.at(0,0), " to Lava Zone!!");
			pipe.addTeleportActions(mario, lavaMap.at(0,0), " to Lava Zone!!!");
			gameMap.at(40,13).setGround(pipe);
			gameMap.at(38,10).setGround(pipe);

			WarpPipe pipe2 = new WarpPipe();
//			pipe2.allowableActions(mario, gameMap.locationOf(mario)," back to normal world");
			pipe2.addTeleportActions(mario, gameMap.locationOf(mario)," back to normal world");
			lavaMap.at(0,0).setGround(pipe2);

//			world.addPlayer(mario, lavaMap.at(15, 10));

			Actor toad = new Toad();
			gameMap.addActor(toad, gameMap.at(42, 11));

			world.run();

	}
}
