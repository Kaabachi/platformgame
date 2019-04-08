package platform.game.level;

import platform.game.*;
import platform.util.Box;
import platform.util.Vector;

public class SpaceLevel extends Level {

	@Override
	public void register(World world) {
		super.register(world);
		//register the components of the world
		world.setNextLevel(new SpaceLevel());
		world.register(new Background(new Box(Vector.ZERO,100,70),world.getLoader().getSprite("back_3"),-100));
		world.register(new Limits(new Box( Vector.ZERO,80,50)));
		world.register(new Floor(new Vector(-24,-5.5),new Vector (10,-4.5),"planet"));
		world.register(new Floor(new Vector(-24,-10.5),new Vector (10,-9.5),"planet"));
		world.register(new Floor(new Vector(17,-5.5),new Vector (35,-4.5),"planet"));
		world.register(new Floor(new Vector(17,-10.5),new Vector (35,-9.5),"planet"));;
		world.register(new Floor(new Vector(11,-10.5),new Vector (16,-9.5),"metal"));
		world.register(new Block(new Box(new Vector(13.5,0),1,1),world.getLoader().getSprite("metal")));
		Lever leverOne = new Lever(new Vector(13.5,1));
		world.register(leverOne);
		world.register(new Jumper(new Vector(9,-4)));
		Vector positionJoueur=new Vector(-15,-3);
		Vector velocityJoueur=new Vector(0,0);
		world.register(new Player(positionJoueur,velocityJoueur,world.getLoader().getSprite("blocker.happy.png"),5));
		world.register(new  Background(new Box(new Vector(-15,-3.5),2,4),getWorld().getLoader().getSprite("rocket"),-9));
		world.register(new SpaceInvader(new Vector(-6,0),1,1.5));
		world.register(new SpaceInvader(new Vector(0,0),1,1.5));
		world.register(new SpaceInvader(new Vector(6,0),1,1.5));
		world.register(new SpaceInvader(new Vector(-3,2),1,1.5));
		world.register(new SpaceInvader(new Vector(3,2),1,1.5));
		world.register(new  Background(new Box(new Vector(-7,3),5,5),getWorld().getLoader().getSprite("moon_full"),-1));
		Key yellowKey = new Key(new Vector(15.25,-4.5),world.getLoader().getSprite("key.yellow"));
		world.register(yellowKey);
		world.register(new Door(new Box(new Vector(12.5,1),1,1),world.getLoader().getSprite("lock.yellow"),yellowKey));
		world.register(new Door(new Box(new Vector(12.5,2),1,1),world.getLoader().getSprite("lock.yellow"),yellowKey));
		world.register(new Door(new Box(new Vector(12.5,-1),1,1),world.getLoader().getSprite("lock.yellow"),yellowKey));
		world.register(new Door(new Box(new Vector(12.5,0),1,1),world.getLoader().getSprite("lock.yellow"),yellowKey));
		world.register(new Door(new Box(new Vector(13.5,2),1,1),world.getLoader().getSprite("lock.yellow"),yellowKey));
		world.register(new Door(new Box(new Vector(14.5,1),1,1),world.getLoader().getSprite("lock.yellow"),yellowKey));
		world.register(new Door(new Box(new Vector(14.5,-1),1,1),world.getLoader().getSprite("lock.yellow"),yellowKey));
		world.register(new Door(new Box(new Vector(13.5,-1),1,1),world.getLoader().getSprite("lock.yellow"),yellowKey));
		world.register(new Door(new Box(new Vector(14.5,2),1,1),world.getLoader().getSprite("lock.yellow"),yellowKey));
		world.register(new Door(new Box(new Vector(14.5,0),1,1),world.getLoader().getSprite("lock.yellow"),yellowKey));
		Lever leverTwo = new Lever(new Vector(-16.25,-7.25));
		world.register(leverTwo);
		world.register(new Mover(new Box(new Vector(10.5,-7.5),1,6),world.getLoader().getSprite("metal"),new Vector(10.5,-12.5),leverOne));
		world.register(new Mover(new Box(new Vector(16.5,-7.5),1,6),world.getLoader().getSprite("metal"),new Vector(16.5,-12.5),leverTwo));

		world.register(new ArrowBlock(new Box(new Vector(-22,-6.5),1,1),world.getLoader().getSprite("spaceBuilding_007")));
		world.register(new ArrowBlock(new Box(new Vector(-22,-8.5),1,1),world.getLoader().getSprite("spaceBuilding_007")));		
		world.register(new Background(new Box(new Vector(-15,-6),1,1),world.getLoader().getSprite("wireBottomLeft"),-5));
		world.register(new Background(new Box(new Vector(-16,-6.88),1,1),world.getLoader().getSprite("wireRight"),-5));
		world.register(new Block(new Box(new Vector(35,-10.5),new Vector(36,-4.5)),world.getLoader().getSprite("stone.2")));
		world.register(new Exit(new Vector(30,-9), new Constant(true),new FirstLevel()));
		world.register(new Slime(new Vector(24,-9.25),1,1.5,true));
		world.register(new Background(new Box(new Vector(30,-8.5),3,3),world.getLoader().getSprite("castle_grey"),-5));
		world.register(new TurningSpike(new Vector(-15,-9.7)));
		world.register(new Spike(new Vector(-10,-9.2)));
		world.register(new TurningSpike(new Vector(-5,-9.7)));
		world.register(new Spike(new Vector(0,-9.2)));
		world.register(new TurningSpike(new Vector(5,-9.7)));
		world.register(new GroundEnemy(new Vector(-10,-5),2));
	}

}