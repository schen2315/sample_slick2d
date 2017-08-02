package javagame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {

	public static final String gamename = "Ham Blaster!";
	public static final int menu = 0;
	public static final int play = 1;
	public static AppGameContainer appgc;
	public Game(String gamename) {
		super(gamename);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.enterState(menu);
	}
	public static void main(String[] args) {
		
		try {
			appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(640, 360, false);	//false for not full screen
			appgc.setTargetFrameRate(100);
			appgc.start();
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
