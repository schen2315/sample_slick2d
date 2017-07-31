package javagame;


import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;


public class Play extends BasicGameState {
	
	Animation bucky, movingUp, movingDown, movingLeft, movingRight;
	Animation buckyStill, stillUp, stillDown, stillLeft, stillRight;
	//Image worldMap;
	public TiledMap worldMap;
	Image buckyUp0, buckyUp1, buckyUp2, buckyUp3;
	Image buckyDown0, buckyDown1, buckyDown2, buckyDown3;
	Image buckyLeft0, buckyLeft1, buckyLeft2, buckyLeft3;
	Image buckyRight0, buckyRight1, buckyRight2, buckyRight3;
	
	boolean quit = false;
	int[] duration = {100,100,100,100};
	float buckyPositionX = 0;
	float buckyPositionY = 0;
	float shiftX = buckyPositionX + 320;
	float shiftY = buckyPositionY + 160;
	
	Rectangle r0, r1, r2, r3, r4;
	Rectangle player;
	
	Rectangle collisionBoxes[] = new Rectangle[5]; 
	public Play(int State) {
		super();
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//collision boxes
		r0 = new Rectangle((8*32) + 24, (8 * 32) + 24, (float)8, (float)8);
		r1 = new Rectangle((9*32), (8 * 32) + 24, 16, 8);
		r2 = new Rectangle((9*32), (9 * 32), 10, 30);
		r3 = new Rectangle((8*32), (9 * 32), 32, 10);
		r4 = new Rectangle((8*32) + 16, (9 * 32) + 10, 16, 16);
		
		collisionBoxes[0] = r0;
		collisionBoxes[1] = r1;
		collisionBoxes[2] = r2;
		collisionBoxes[3] = r3;
		collisionBoxes[4] = r4;
		player = new Rectangle(shiftX + 12, shiftY + 28, 24, 12);
		
		buckyPositionX = 0;
		buckyPositionY = 0;
		System.out.println("Initiation!");
		/*
		worldMap = new Image("res/world.png");
		Image[] walkUp = {new Image("res/buckysBack.png"), new Image("res/buckysBack.png")};
		Image[] walkDown = {new Image("res/buckysFront.png"), new Image("res/buckysFront.png")};
		Image[] walkLeft = {new Image("res/buckysLeft.png"), new Image("res/buckysLeft.png")};
		Image[] walkRight = {new Image("res/buckysRight.png"), new Image("res/buckysRight.png")};
		*/
		
		//worldMap = new Image("res/CastleExample.png");
		
		worldMap = new TiledMap("res/TestCollisions2.tmx", "res");
		
		buckyDown0 = new Image("res/george.png").getSubImage(0, 0, 48, 48);
		buckyDown1 = new Image("res/george.png").getSubImage(0, 48, 48, 48);
		buckyDown2 = new Image("res/george.png").getSubImage(0, 96, 48, 48);
		buckyDown3 = new Image("res/george.png").getSubImage(0, 144, 48, 48);
		Image[] walkDown = {buckyDown0, buckyDown1, buckyDown2, buckyDown3};
		
		buckyLeft0 = new Image("res/george.png").getSubImage(48, 0, 48, 48);
		buckyLeft1 = new Image("res/george.png").getSubImage(48, 48, 48, 48);
		buckyLeft2 = new Image("res/george.png").getSubImage(48, 96, 48, 48);
		buckyLeft3 = new Image("res/george.png").getSubImage(48, 144, 48, 48);
		Image[] walkLeft = {buckyLeft0, buckyLeft1, buckyLeft2, buckyLeft3};
		
		buckyUp0 = new Image("res/george.png").getSubImage(96, 0, 48, 48);
		buckyUp1 = new Image("res/george.png").getSubImage(96, 48, 48, 48);
		buckyUp2 = new Image("res/george.png").getSubImage(96, 96, 48, 48);
		buckyUp3 = new Image("res/george.png").getSubImage(96, 144, 48, 48);
		Image[] walkUp = {buckyUp0, buckyUp1, buckyUp2, buckyUp3};
		
		buckyRight0 = new Image("res/george.png").getSubImage(144, 0, 48, 48);
		buckyRight1 = new Image("res/george.png").getSubImage(144, 48, 48, 48);
		buckyRight2 = new Image("res/george.png").getSubImage(144, 96, 48, 48);
		buckyRight3 = new Image("res/george.png").getSubImage(144, 144, 48, 48);
		Image[] walkRight = {buckyRight0, buckyRight1, buckyRight2, buckyRight3};
		
//		movingUp = new Animation(walkUp, duration, false);
		movingDown = new Animation(walkDown, duration, true);
		movingLeft = new Animation(walkLeft, duration, true);
		movingUp = new Animation(walkUp, duration, true);
		movingRight = new Animation(walkRight, duration, true);
		
		stillDown = new Animation(walkDown, duration, false);
		stillLeft = new Animation(walkLeft, duration, false);
		stillUp = new Animation(walkUp, duration, false);
		stillRight = new Animation(walkRight, duration, false);
//		movingLeft = new Animation(walkLeft, duration, false);
//		movingRight = new Animation(walkRight, duration, false);
		bucky = stillDown;
		buckyStill = stillDown;
	}
	//draw stuff on screen
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//worldMap.render((int)buckyPositionX, (int)buckyPositionY, 16, 16, 17 ,11, 0, true);
		//worldMap.render((int)buckyPositionX, (int)buckyPositionY, 16, 16, 17 ,11, 1, true);
		
		int x = 0, y = 0;
		Image m;
		for(int i=16; i <= 26; i++) {
			for(int j=16; j <= 32; j++) {
				m = worldMap.getTileImage(j,i,0);
				if(m != null) m.draw(buckyPositionX + x, buckyPositionY + y);
				m = worldMap.getTileImage(j,i,1);
				if(m != null) m.draw(buckyPositionX + x, buckyPositionY + y);
				x += 32;
			}
			x = 0;
			y += 32;
		}
		bucky.draw(shiftX, shiftY);
		//m.draw(shiftX, shiftY);
		//player bounds
//		g.drawRect(shiftX + 12, shiftY + 28, 24, 12);
//		
//		g.drawRect(buckyPositionX + (8*32) + 24, buckyPositionY + (8 * 32) + 24, 8, 8);
//		g.drawRect(buckyPositionX + (9*32), buckyPositionY + (8 * 32) + 24, 16, 8);
//		g.drawRect(buckyPositionX + (9*32), buckyPositionY + (9 * 32), 10, 30);
//		g.drawRect(buckyPositionX + (8*32), buckyPositionY + (9 * 32), 32, 10);
//		g.drawRect(buckyPositionX + (8*32) + 16, buckyPositionY + (9 * 32) + 10, 16, 16);
		g.drawString( "Bucky's X: " + buckyPositionX + "\nBucky's Y:" + buckyPositionY, 400, 20);
		
		
		if(quit == true) {
			g.drawString( "Resume (R)", 250, 100);
			g.drawString( "Main Menu (M)", 250, 150);
			g.drawString( "Quit Game (Q)", 250, 200);
			if(quit == false) {
				g.clear();
			}
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//System.out.println(delta);
		//delta = 6;
		Input input = gc.getInput();
		bucky = buckyStill;
		//System.out.println(isCollision());
		//up
		if(input.isKeyDown(Input.KEY_W)) {
			bucky = movingUp;
			buckyStill = stillUp;
			buckyPositionY += delta * .2f;
			if(isCollision()) {
				buckyPositionY -= delta * .2f;
			}
		}
		//down
		if(input.isKeyDown(Input.KEY_S)) {
			bucky = movingDown;
			buckyStill = stillDown;
			buckyPositionY -= delta * .2f;
			if(isCollision()) {
				buckyPositionY += delta * .2f;
			}
 		}
		//left
		if(input.isKeyDown(Input.KEY_A)) {
			bucky = movingLeft;
			buckyStill = stillLeft;
			buckyPositionX += delta * .2f;
			if(isCollision()) {
				buckyPositionX -= delta * .2f;
			}
		}
		//right
		if(input.isKeyDown(Input.KEY_D)) {
			bucky = movingRight;
			buckyStill = stillRight;
			buckyPositionX -= delta * .2f;
			if(isCollision()) {
				buckyPositionX += delta * .2f;
			}
		}
		
		//escape
		if(input.isKeyDown(Input.KEY_ESCAPE)) {
			quit = true;
		}
		//when menu is up
		if(quit == true) {
			if(input.isKeyDown(Input.KEY_R)){
				quit = false;
			}
			if(input.isKeyDown(Input.KEY_M)){
				quit = false;
				sbg.enterState(0); 
			}
			if(input.isKeyDown(Input.KEY_Q)){
				System.exit(0);
			}
		}
	}
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) {
		buckyPositionX = 0;
		buckyPositionY = 0;
		buckyStill = stillDown;
		System.out.println("Entering Play State!");
	}
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) {
		System.out.println("Leaving Play State!");
	}
	public int getID() {
		return 1;
	}
	public boolean isCollision() {
		
		for(int i=0; i < collisionBoxes.length; i++) {
			
			if(player.intersects(
					new Rectangle(buckyPositionX + collisionBoxes[i].getX(), buckyPositionY + collisionBoxes[i].getY(), 
							collisionBoxes[i].getWidth(), collisionBoxes[i].getHeight()))) {
				return true;
			}
		}
		return false;
	}
}
