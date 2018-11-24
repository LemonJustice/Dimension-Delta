package Entity;

import java.awt.Color;
import java.awt.Graphics;

import Display.Assets;
import Main.Game;
import Menu.Menu;
import Misc.Functions;

public class Player extends Entity {

	private Game game;
	private Functions functions = new Functions();
	
	private float velx = 0, vely = 0;
	private int width = 30;
	private int height = 60;
	private Color blue = new Color(0, 0, 255);
	
	private boolean falling = false;
	
	public Player(Game game, int x, int y) {
		super(x, y);
		this.game = game;
	}

	@Override
	public void tick() {	
		if(game.menu.active)
			return;
		if((game.getKeyManager().up || game.getKeyManager().space) && !falling){
			vely-=9;
		}
		this.falling = true;
		
		if (game.getKeyManager().left) {
			velx-=1.4;
		}	
		
		if (game.getKeyManager().right) {
			velx+=1.4;
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
		g.setColor(blue);
		g.fillRect((int) x, (int) y, width, height);
	}
	public void collisionCheck(float velX, float velY) {
		for (int i = 0 ; i < game.blocks.get(game.level).size() ; i++) {
			 Block block = game.blocks.get(game.level).get(i);
			 if (functions.collide(block.getX(), block.getY(), block.getWidth(), block.getHeight(), x, y, width, height) && block.type == "normal") {
				 if (velX > 0) {
					 this.velx = 0;
					 x = block.getX()-width;
				 }
				 if (velX < 0) {
					 this.velx = 0;
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
			 if (functions.collide(block.getX(), block.getY(), block.getWidth(), block.getHeight(), x, y, width, height) && block.type == "spike") {
				 if (velX > 0) {
					 this.velx = 0;
					 x = block.getX()-width;
				 }
				 if (velX < 0) {
					 this.velx = 0;
					 x = block.getX()+block.getWidth();
				 }
				 if (velY > 0) {
					 this.vely = 0;
					 y = block.getY()-height;
					 falling = false;
					 System.out.println("Get spiked!");
					 //INSERT DEATH CODE HERE PROBABLY
				 }
				 if (velY < 0) {
					 this.vely = 0;
					 y = block.getY()+block.getHeight();
					 falling = true;
				 }
			 }
			 if (functions.collide(block.getX(), block.getY(), block.getWidth(), block.getHeight(), x, y, width, height) && block.type == "ladder") {
				 if (game.getKeyManager().up) {
					 vely = -4;
				 } else {
					 vely = 2;
				 }
			 }
			 if (functions.collide(block.getX(), block.getY(), block.getWidth(), block.getHeight(), x, y, width, height) && block.type == "lCon") {
				 if (velX > 0) {
					 this.velx = 0;
					 x = block.getX()-width;
				 }
				 if (velX < 0) {
					 this.velx = 0;
					 x = block.getX()+block.getWidth();
				 }
				 if (velY > 0) {
					 this.vely = 0;
					 y = block.getY()-height;
					 falling = false;
					 this.velx -= 1.5;
				 }
				 if (velY < 0) {
					 this.vely = 0;
					 y = block.getY()+block.getHeight();
					 falling = true;
					 this.velx -= 1.5;
				 }
			 }
			 if (functions.collide(block.getX(), block.getY(), block.getWidth(), block.getHeight(), x, y, width, height) && block.type == "rCon") {
				 if (velX > 0) {
					 this.velx = 0;
					 x = block.getX()-width;
				 }
				 if (velX < 0) {
					 this.velx = 0;
					 x = block.getX()+block.getWidth();
				 }
				 if (velY > 0) {
					 this.vely = 0;
					 y = block.getY()-height;
					 falling = false;
					 this.velx += 1.5;
				 }
				 if (velY < 0) {
					 this.vely = 0;
					 y = block.getY()+block.getHeight();
					 falling = true;
					 this.velx += 1.5;
				 }
			 }
			
		}
		 for (int o = 0 ; o < game.enemies.get(game.level).size() ; o++) {
			 Enemy enemy = game.enemies.get(game.level).get(o);
			 if (functions.collide(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight(), x, y, width, height) && enemy.type == "slime") {
				 System.out.println("You took Damage");
			 }
			 if (functions.collide(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight(), x, y, width, height) && enemy.type == "bird") {
				 System.out.println("You took Damage");
			 }
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
//Hello
