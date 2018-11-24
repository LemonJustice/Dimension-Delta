package Menu;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Main.Game;

public class UI {

	public boolean active = false; 
	private Game game;

	public UI(Game game){
	this.game = game;
	}
	
	public void  tick(){
	if(game.getKeyManager().keyJustPressed(KeyEvent.VK_E))
		active = !active;
		if(active)
			return;
	}
	
	public void render(Graphics g){
	}
}
