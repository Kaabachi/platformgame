package platform.game.level;


import platform.game.*;
import platform.util.*;

public class FirstLevel extends Level{
	@Override
	public void register(World world) {
		super.register(world);
		//register the components of the world
		world.setNextLevel(new FirstLevel());
		world.register(new Background(new Box(Vector.ZERO,100,70),world.getLoader().getSprite("colored_talltrees.png"),-100));
		world.register(new Limits(new Box( Vector.ZERO,80,50)));
		world.register(new Block(new Box(new Vector(0, 0), 10, 2), world.getLoader().getSprite("stone.2")));
		world.register(new Floor(new Vector(-20,-5.5),new Vector (10,-4.5),"grass"));
		world.register(new Floor(new Vector(16,-2.5),new Vector (21,-1.5),"grass"));
		world.register(new Floor(new Vector(11,1.5),new Vector (15,2.5),"grass"));
		world.register(new Floor(new Vector(8,3.5),new Vector (10,4.5),"grass"));
		world.register(new Floor(new Vector(20,3.5),new Vector (30,4.5),"grass"));
		world.register(new Floor(new Vector(20,6.5),new Vector (36,7.5),"grass"));
		world.register(new Floor(new Vector(33,3.5),new Vector (36,4.5),"grass"));
		world.register(new Background(new Box(new Vector(34.5,5.5),1,2),world.getLoader().getSprite("tree34"),-10));
		Torch torch = new Torch(new Vector(34.5,5.5),false);
		world.register(torch);
		world.register(new Mover(new Box(new Vector(31.5,4),3,1),world.getLoader().getSprite("stone.2"),new Vector(31.5,-8),torch));
		world.register(new Destructible(new Box(new Vector(24,5),1,1),world.getLoader().getSprite("stone.1"),world.getLoader().getSprite("stone.broken.1")));
		world.register(new Destructible(new Box(new Vector(24,6),1,1),world.getLoader().getSprite("stone.1"),world.getLoader().getSprite("stone.broken.1")));
		world.register(new HeartBlock(new Box(new Vector(25,5),1,1),world.getLoader().getSprite("stone.1"),world.getLoader().getSprite("stone.broken.1")));
		world.register(new Destructible(new Box(new Vector(25,6),1,1),world.getLoader().getSprite("stone.1"),world.getLoader().getSprite("stone.broken.1")));
		world.register(new Floor(new Vector (33,-8.5),new Vector(43,-7.5),"snow"));
		world.register(new Block(new Box(new Vector(29,-2),2,12),world.getLoader().getSprite("stone.7")));
		world.register(new Block(new Box(new Vector(34,-1),2,10),world.getLoader().getSprite("stone.7")));
		world.register(new TurningSpike(new Vector(29.75,-6)));
		world.register(new TurningSpike(new Vector(32.75,-2.5)));
		world.register(new TurningSpike(new Vector(28,4)));
		world.register(new TurningSpike(new Vector(29.75,0.5)));
		world.register(new  Background(new Box(new Vector(-15,-3),3,3),getWorld().getLoader().getSprite("castle_grey"),-9));
		world.register(new Block(new Box(new Vector(0, 5.5), 10 , 2), world.getLoader().getSprite("stone.2")));
		world.register(new ArrowBlock(new Box(new Vector(-4,-2),1,1),world.getLoader().getSprite("box.double")));
		world.register(new FlyingEnemy(new Vector(2,3),1,0.5));
		world.register(new Frog(new Vector(0,9),1));
		//register player
		Vector positionJoueur=new Vector(-15,-3);
		Vector velocityJoueur=new Vector(0,0);
		world.register(new Player(positionJoueur,velocityJoueur,world.getLoader().getSprite("blocker.happy"),5));
		world.register(new Slime(new Vector(-2,1.25),3,0.7,true));
		Lever leverOne = new Lever(new Vector(2,1.5));
		world.register(leverOne);
		Lever leverTwo= new Lever(new Vector(2,-4));
		world.register(leverTwo);
		world.register(new Mover(new Box(new Vector(-5.75,-1.75),1.5,5.5),world.getLoader().getSprite("stone.broken.7"),new Vector(-5.75,2),new Xor(leverOne,leverTwo)));
		world.register(new Mover(new Box(new Vector(5.75,2),1.5,5.5),world.getLoader().getSprite("stone.broken.7"),new Vector(5.75,-1.75),new Xor(leverOne,leverTwo)));
		world.register(new Heart(new Vector(-2,-4)));
		world.register(new Mover(new Box(new Vector(11,-12),2,1),world.getLoader().getSprite("stone.2"),new Vector(11,-5),leverTwo));
		world.register(new Mover(new Box(new Vector(13,-13),2,1),world.getLoader().getSprite("stone.2"),new Vector(13,-4),leverTwo));
		world.register(new Mover(new Box(new Vector(15,-14),2,1),world.getLoader().getSprite("stone.2"),new Vector(15,-3),leverTwo));
		world.register(new Jumper(new Vector(19,-1)));
		world.register(new Spike(new Vector(17.5,-1.2)));
		world.register(new Spike(new Vector(20.5,-1.2)));
		world.register(new Spike(new Vector(11.5,2.8)));
		world.register(new Spike(new Vector(14.5,2.8)));
		world.register(new Jumper(new Vector(13,3)));
		world.register(new Jumper(new Vector(9.5,5)));
		Key blueKey = new Key(new Vector(-2,7),world.getLoader().getSprite("key.blue"));
		world.register(blueKey);
		world.register(new Door(new Box(new Vector(23,5),1,1),world.getLoader().getSprite("lock.blue"),blueKey));
		world.register(new Door(new Box(new Vector(23,6),1,1),world.getLoader().getSprite("lock.blue"),blueKey));
		world.register(new Exit(new Vector(36,-7),new Constant(true),new Snow()));
		world.register(new Background(new Box(new Vector(34.5,-7),1,1),world.getLoader().getSprite("signExit"),-10));
		world.register(new Background(new Box(new Vector(37.5,-6),5,3),world.getLoader().getSprite("Igloo"),-20));
		world.register(new Background(new Box(new Vector(41,-6),1,3),world.getLoader().getSprite("tree22"),-15));
	}
}
