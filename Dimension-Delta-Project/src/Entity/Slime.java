package Entity;

import java.awt.Graphics;

import Display.Assets;
import Main.Game;
import Menu.Menu;
import Misc.Functions;

public class Slime extends Entity{

	private Functions functions = new Functions();
	private Game game;
	private Menu menu;
	
	private float health = 10;
	private int direction = 1;
	private float velx = 0;
	private float vely = 0;
	private int width = 26;
	private int height = 20;

	private boolean falling = true;
	
	public Slime(Game game,float x, float y) {
		super(x, y);
		this.game = game;
	}

	@Override
	public void tick() {
		if(game.menu.active)
			return;
		if(health <= 0)
			return;
		if(!falling){
			velx += .4 * direction;
		}
		x+=this.velx;
		this.collisionCheck(velx, 0);
		y+=this.vely;
		this.collisionCheck(0, vely);
		
		this.velx/=1.2;
		vely+=0.3;
	}

	@Override
	public void render(Graphics g) {
		if(health <= 0)
			return;
		g.drawImage(Assets.slime, (int) x, (int) y, null);
	}
	
	public void collisionCheck(float velX, float velY) {
		for (int i = 0 ; i < game.blocks.get(game.level).size() ; i++) {
			 Block block = game.blocks.get(game.level).get(i);
			 if (functions.collide(block.getX(), block.getY(), block.getWidth(), block.getHeight(), x, y, width, height) && block.type == "normal") {
				 if (velX > 0) {
					 direction *= -1;
					 x = block.getX()-width;
				 }
				 if (velX < 0) {
					 direction *= -1;
					 x = block.getX()+block.getWidth();
				 }
				 if (velY > 0) {
					 this.vely = 0;
					 y = block.getY()-height;
					 falling = false;
				 }
				 if (velY < 0) {
					 this.vely = 0;
					 y = block.getY()+block.getHeight();
					 falling = true;
				 }
			 }
			 //weapon collision here at some point
		}
	}
	public int getX(){
		return (int)x;
	}
	public int getY(){
		return (int)y;
	}
	public int getWidth(){
		return (int)width;
	}
	public int getHeight(){
		return (int)height;
	}
}
