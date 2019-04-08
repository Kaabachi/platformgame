package platform.game.level;

import platform.game.*;
import platform.util.*;

public class Select extends Level {

	@Override
	public void register(World world){
		super.register(world);

		world.register(new Background(new Box(new Vector(-10,0),3,5),getWorld().getLoader().getSprite("tree34"),-10) );
		world.register(new Background(new Box(new Vector(-4,-1),2,3),getWorld().getLoader().getSprite("SnowMan"),-10) );
		world.register(new Background(new Box(new Vector(2.5,-1),5,3),getWorld().getLoader().getSprite("piramid"),-10) );
		world.register(new Background(new Box(new Vector(10,-0.5),2,4),getWorld().getLoader().getSprite("rocket"),-10) );
		world.register(new Background(new Box(new Vector(0,0),30,20),getWorld().getLoader().getSprite("level.BG"),-100) );

		world.register(new Floor(new Vector(-12,-3.5),new Vector(-8,-2.5),"grass"));
		world.register(new Floor(new Vector(-6,-3.5),new Vector(-2,-2.5),"snow"));
		world.register(new Floor(new Vector(0,-3.5),new Vector(5,-2.5),"sand"));
		world.register(new Floor(new Vector(8,-3.5),new Vector(12,-2.5),"planet"));

		world.register(new LevelDoor(new Vector(-10,3),new FirstLevel(),getWorld().getLoader().getSprite("digit.1")));
		world.register(new LevelDoor(new Vector(-4,1),new Snow(),getWorld().getLoader().getSprite("digit.2")));
		world.register(new LevelDoor(new Vector(2.5,2),new Sand(),getWorld().getLoader().getSprite("digit.3")));
		world.register(new LevelDoor(new Vector(10,3),new SpaceLevel(),getWorld().getLoader().getSprite("digit.4")));
	}	

}
