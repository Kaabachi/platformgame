package platform.game.level;

import platform.game.*;
import platform.util.*;

public class Snow extends Level {

	@Override
	public void register(World world){
		super.register(world);
		world.setNextLevel(new Snow());
		world.register(new Limits(new Box( Vector.ZERO,100,100)));
		world.register(new Background(new Box(new Vector(0,0),70,70),getWorld().getLoader().getSprite("BG"),-100) );
		world.register(new Background(new Box(new Vector(-4,15),3,2),getWorld().getLoader().getSprite("Igloo"),-15) );
		world.register(new Floor(new Vector(-8.85,13),new Vector(3,14),"snow"));
		Vector positionJoueur=new Vector(-3,14);
		Vector velocityJoueur=new Vector(0,0);
		world.register(new Player(positionJoueur,velocityJoueur,world.getLoader().getSprite("blocker.happy"),5));
		world.register(new Floor(new Vector(2,14),new Vector(5,15),"snow"));
		world.register(new TurningSpike(new Vector(5,14.75)));
		world.register(new Block(new Box(new Vector(6,20),9,2),getWorld().getLoader().getSprite("stone.2")));
		world.register(new Block(new Box(new Vector(9.5,13),2,16),getWorld().getLoader().getSprite("stone.7")));
		world.register(new Floor(new Vector(0,5),new Vector(9,6),"snow"));
		world.register(new Jumper(new Vector(1,6.5)));
		world.register(new Floor(new Vector(-4,10),new Vector(-2,11),"snow"));//plate forme clef
		Key yellow = new Key(new Vector(-3,11.75),world.getLoader().getSprite("key.yellow"));//clef
		world.register(yellow);
		world.register(new Door(new Box(new Vector(0.75,4.25),1.5,1.5),getWorld().getLoader().getSprite("lock.yellow"),yellow));
		world.register(new Floor(new Vector(-6,2.5),new Vector(13,3.5),"snow"));
		world.register(new Floor(new Vector(-4,-0.5),new Vector(16,0.5),"snow"));
		world.register(new ArrowBlock(new Box(new Vector(-5.5,2),1,1),getWorld().getLoader().getSprite("iceBlockAlt")));//fleches
		world.register(new TurningSpike(new Vector(6,0)));
		world.register(new TurningSpike(new Vector(2,0)));
		world.register(new TurningSpike(new Vector(-2,0)));
		world.register(new Floor(new Vector(-6,-3.5),new Vector(20,-2.5),"tundra"));
		world.register(new Slime(new Vector(13,-2.25),2,0.7,true));
		world.register(new Heart(new Vector(-5.5,0)));
		world.register(new FlyingEnemy(new Vector(6,18),1,0.5));
		world.register(new Block(new Box(new Vector(-7,8),2,10),getWorld().getLoader().getSprite("stone.7")));
		world.register(new Block(new Box(new Vector(-7,-1),2,5),getWorld().getLoader().getSprite("stone.7")));
		world.register(new Floor(new Vector(16,12.5),new Vector(19,13.5),"snow"));
		world.register(new Block(new Box(new Vector(16.75,7),1.5,13),getWorld().getLoader().getSprite("stone.7")));
		world.register(new HeartBlock(new Box(new Vector(16.75,-1.75),1.5,1.5),getWorld().getLoader().getSprite("stone.1"),getWorld().getLoader().getSprite("stone.broken.1")));	
		world.register(new Destructible(new Box(new Vector(16.75,-0.25),1.5,1.5),getWorld().getLoader().getSprite("stone.1"),getWorld().getLoader().getSprite("stone.broken.1")));
		Lever lever = new Lever(new Vector(19,-2));
		world.register(new Mover(new Box(new Vector(21.5,-7),3,1),getWorld().getLoader().getSprite("iceBlock"),new Vector(21.5,13),lever));
		world.register(lever);
		world.register(new Floor(new Vector(16,12.5),new Vector(20,13.5),"snow"));
		world.register(new Floor(new Vector(23,12.5),new Vector(25,13.5),"snow"));
		world.register(new Background(new Box(new Vector(24,15.5),2,4),getWorld().getLoader().getSprite("tree04"),-10) );
		Torch torch= new Torch(new Vector(23.25,15.5),false);
		world.register(torch);
		world.register(new Mover(new Box(new Vector(13.25,5.5),5.5,1),getWorld().getLoader().getSprite("iceBlock"),new Vector(13.25,20.5),torch));
		Key blue = new Key(new Vector(3.5,22),world.getLoader().getSprite("key.blue"));//clef
		world.register(blue);
		world.register(new Frog(new Vector(5,21.5),2));
		world.register(new Floor(new Vector(16.5,20),new Vector(30,21),"sand"));
		world.register(new Background(new Box(new Vector(27,22),6,4),getWorld().getLoader().getSprite("piramid"),-15) );
		world.register(new Exit(new Vector(26.5,21.5),blue,new Sand()));


	}		
}	