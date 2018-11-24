package Entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import Display.Assets;
import Main.Game;
import Misc.Functions;

public class Bullet extends Entity{
	
	float xDiff;
	float yDiff;
	private float speed, velx, vely;
	
	private Game game;
	private Functions functions = new Functions();
	private int width = 12;
	private int height = 15;
	private boolean dead = false;
	private int xDir;
	private double angle;
	private float xChange;

	public Bullet(float x, float y, float xDiff, float yDiff, double angle, Game game) {
		super(x, y);
		this.xDiff = xDiff;
		if(xDiff <= 0){
			xDir = 1;
		}else{
			xDir = -1;
		}
		this.yDiff = yDiff;
		speed = yDiff/xDiff;
		this.game = game;
		this.angle = angle;
	}

	@Override
	public void tick() {
		if(dead)
			return;
		if(speed != 6){
			xChange =Math.abs(6/speed);
			speed = 6;
		}
		velx = xDir*xChange;
		vely = speed;
		collisionCheck();
		x += velx;
		y += vely;
	}

	@Override
	public void render(Graphics g) {
		if(dead)
			return;
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform droneGun = g2d.getTransform();
		g2d.rotate(angle*-1, x+5, y+5);
		//g.drawImage(Assets.bullet,(int)x,(int)y, null);
		g2d.setTransform(droneGun);
	}
	
	public void collisionCheck() {
		for (int i = 0 ; i < game.blocks.get(game.level).size() ; i++) {
			 Block block = game.blocks.get(game.level).get(i);
			 if (functions.collide(block.getX(), block.getY(), block.getWidth(), block.getHeight(), x, y, width, height)) {
				 dead = true;
			 }
			 
		}
		if(functions.collide(game.player.getX(), game.player.getY(), game.player.getWidth(), game.player.getHeight(), x, y, width, height)){
			dead = true;
			System.out.println("You were shot! Pop pop!");
		}
	}
	public float GetX(){
		return x; 
	}
	public float GetY(){
		return y; 
	}
	public int GetWidth(){
		return width; 
	}
	public int GetHeight(){
		return height; 
	}
	public boolean GetState(){
		return dead;
	}
	public void SetState(boolean state){
		dead = state;
	}
	
}
