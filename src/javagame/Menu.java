package javagame;


import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {
	
	Image playNow;
	Image exitGame;
	
	public Menu(int State) {
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		playNow = new Image("res/playNow.png");
		exitGame = new Image("res/exitGame.png");
	}
	//draw stuff on screen
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString("Welcome to Bucky Land!", 100, 50);
		playNow.draw(100, 100);
		exitGame.draw(100, 200);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		//playNow button
		if((posX > 100 && posX < 311) && (posY > 209 && posY < 260)) {
			if(Mouse.isButtonDown(0)) {
				sbg.enterState(1);
			}
		}
		//exit button
		if((posX > 100 && posX < 311) && (posY > 109 && posY < 160)) {
			if(Mouse.isButtonDown(0)) {
				System.exit(0);
			}
		}
	}
	
	public int getID() {
		return 0;
	}
}
