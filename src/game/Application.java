package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actor.Bowser;
import game.actor.Player;
import game.actor.PrincessPeach;
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
			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new PowerFountain()
			,new HealFountain());

			FancyGroundFactory groundLavaFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(),new Lava());

			List<String> map = Arrays.asList(
					"..........................................##..........+.........................",
					"............+............+..................#...................................",
					"............................................#...................................",
					".............................................##......................+..........",
					"...............................................#................................",
					"................................................#...............................",
					".................+................................#.............................",
					"...........................................A.H...##.............................",
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
					"...........L.....................+.................#..........",
					"....................................................#........."
);

			GameMap gameMap = new GameMap(groundFactory, map);
			GameMap lavaMap = new GameMap(groundLavaFactory, lavaZone);

			world.addGameMap(gameMap);
			world.addGameMap(lavaMap);

			Player mario = new Player("Player", 'm', 1000);
			world.addPlayer(mario, gameMap.at(42, 10));

			WarpPipe pipe = new WarpPipe(lavaMap.at(0,0),"to Lava Zone!!!");
			gameMap.at(40,13).setGround(pipe);

			WarpPipe pipe2 = new WarpPipe(lavaMap.at(0,0),"to Lava Zone!!!");
			gameMap.at(20,6).setGround(pipe2);

			WarpPipe pipe3 = new WarpPipe(lavaMap.at(0,0),"to Lava Zone!!!");
			gameMap.at(38,5).setGround(pipe3);

			WarpPipe pipe4 = new WarpPipe(lavaMap.at(0,0),"to Lava Zone!!!");
			gameMap.at(2,18).setGround(pipe4);

			WarpPipe pipe5 = new WarpPipe(lavaMap.at(0,0),"to Lava Zone!!!");
			gameMap.at(2,2).setGround(pipe5);

			WarpPipe pipe6 = new WarpPipe(lavaMap.at(0,0),"to Lava Zone!!!");
			gameMap.at(10,12).setGround(pipe6);

			WarpPipe pipeReturn = new WarpPipe(gameMap.locationOf(mario), "to Normal World");
//			pipe2.allowableActions(mario, gameMap.locationOf(mario)," back to normal world");
//			pipeReturn.addTeleportActions(mario, gameMap.at(mario.getOriLocation().x(), mario.getOriLocation().y())," back to normal world");
//			pipeReturn.addTeleportActionsReturn(mario, gameMap.at(0,0)," back to normal world");
			lavaMap.at(0,0).setGround(pipeReturn);


			Actor toad = new Toad();
			gameMap.addActor(toad, gameMap.at(42, 11));

			lavaMap.addActor(new Bowser(gameMap.at(6, 11)), lavaMap.at(6, 11));
			lavaMap.addActor(new PrincessPeach(), lavaMap.at(3, 11));

			world.run();

	}
}
