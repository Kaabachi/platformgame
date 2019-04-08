package platform.game.level;

import platform.game.*;
import platform.util.*;

public class Sand extends Level {

	@Override
	public void register(World world){
		super.register(world);
		world.setNextLevel(new Sand());
		world.register(new Limits(new Box( Vector.ZERO,100,100)));
		world.register(new Background(new Box(new Vector(0,-10),70,70),getWorld().getLoader().getSprite("colored_desert"),-100) );
		world.register(new Background(new Box(new Vector(-4,-5),6,4),getWorld().getLoader().getSprite("piramid.flip"),-15) );
		world.register(new Background(new Box(new Vector(-3.25,-5.5),1,1.75),getWorld().getLoader().getSprite("door.open"),-10) );
		world.register(new Background(new Box(new Vector(2.5,-6.5),3,1),getWorld().getLoader().getSprite("sand.left"),-10) );
		world.register(new Background(new Box(new Vector(-2,-5.5),1,1.5),getWorld().getLoader().getSprite("signRight"),-5) );

		world.register(new Floor(new Vector(-8.85,-7),new Vector(1,-6),"sand"));
		Vector positionJoueur=new Vector(-3,-5.5);
		Vector velocityJoueur=new Vector(3,0);
		world.register(new Player(positionJoueur,velocityJoueur,world.getLoader().getSprite("blocker.happy"),5));
		Lever lever = new Lever(new Vector(3.5,-5.5));
		world.register(lever);
		world.register(new Mover(new Box(new Vector(2.5,-6.5),4,1),getWorld().getLoader().getSprite("sand.middle"),new Vector(20,-23),lever));
		Key star = new Key(new Vector(10,-12),world.getLoader().getSprite("star"));
		world.register(star);
		world.register(new Mover(new Box(new Vector(10,-16),3,1),getWorld().getLoader().getSprite("sand.middle"),new Vector(23.5,-16),star));
		world.register(new Floor(new Vector(25,-16.5),new Vector(32,-15.5),"sand"));
		world.register(new TurningSpike(new Vector(18,-16)));
		world.register(new ArrowBlock(new Box(new Vector(-2,-15),1,1),getWorld().getLoader().getSprite("box.warning.light")));
		world.register(new Heart(new Vector (29,-14)));
		world.register(new Floor(new Vector(29,1.5),new Vector(32,2.5),"sand"));
		world.register(new Floor(new Vector(10,1.5),new Vector(25,2.5),"planet"));
		world.register(new Block(new Box(new Vector(31,-7),2,19),getWorld().getLoader().getSprite("box.empty")));//3ridha
		world.register(new Floor(new Vector(22,-13),new Vector(28,-12),"sand"));
		world.register(new Block(new Box(new Vector(22.5,-5.25),1,15.5),getWorld().getLoader().getSprite("box.empty")));//jwayda
		world.register(new Jumper(new Vector(29,-15)));
		world.register(new Floor(new Vector(25,-10),new Vector(32,-9),"sand"));
		world.register(new HeartBlock(new Box(new Vector(24.5,-11.5),1,1),getWorld().getLoader().getSprite("box.item.alt"),getWorld().getLoader().getSprite("box.item")));
		world.register(new Destructible(new Box(new Vector(24.5,-10.5),1,1),getWorld().getLoader().getSprite("box.item.alt"),getWorld().getLoader().getSprite("box.item")));
		world.register(new Block(new Box(new Vector(23.5,-10.5),1,1),getWorld().getLoader().getSprite("box.double")));
		Key red = new Key(new Vector(23.5,-11.5),world.getLoader().getSprite("key.red"));
		world.register(red);
		world.register(new Slime(new Vector(29.5,-8.75),2,0.7,true));
		world.register(new Door(new Box(new Vector(27.5,-7.5),1,1),getWorld().getLoader().getSprite("lock.red"),red));
		world.register(new Door(new Box(new Vector(26.5,-7.5),1,1),getWorld().getLoader().getSprite("lock.red"),red));
		world.register(new Door(new Box(new Vector(27.5,-5.5),1,1),getWorld().getLoader().getSprite("lock.red"),red));
		world.register(new Door(new Box(new Vector(26.5,-6.5),1,1),getWorld().getLoader().getSprite("lock.red"),red));
		world.register(new Door(new Box(new Vector(26.5,-5.5),1,1),getWorld().getLoader().getSprite("lock.red"),red));
		world.register(new Door(new Box(new Vector(28.5,-6.5),1,1),getWorld().getLoader().getSprite("lock.red"),red));
		world.register(new Door(new Box(new Vector(28.5,-5.5),1,1),getWorld().getLoader().getSprite("lock.red"),red));
		world.register(new Door(new Box(new Vector(28.5,-7.5),1,1),getWorld().getLoader().getSprite("lock.red"),red));

		Torch torch = new Torch(new Vector(27.5,-6.5),false);
		world.register(torch);
		world.register(new Mover(new Box(new Vector(27,-9.5),4,1),getWorld().getLoader().getSprite("sand.middle"),new Vector(27,2),torch));
		world.register(new FlyingEnemy(new Vector(20,5),2,0.7));		
		world.register(new Frog(new Vector(15,3),1));
		world.register(new Background(new Box(new Vector(13,5),2,4),getWorld().getLoader().getSprite("rocket"),-15) );
		Key yellow = new Key(new Vector(17.5,4),world.getLoader().getSprite("key.yellow"));
		world.register(yellow);
		world.register(new Spike(new Vector(24.5,2.8)));
		world.register(new Spike(new Vector(23.5,2.8)));
		world.register(new Background(new Box(new Vector(27.5,-6.5),1,1),getWorld().getLoader().getSprite("box.warning.color"),-15) );
		world.register(new Exit(new Vector(13,3),yellow,new SpaceLevel()));
		world.register(new Background(new Box(new Vector(11,3.25),1,1.5),getWorld().getLoader().getSprite("exit"),-15));












	}
}
