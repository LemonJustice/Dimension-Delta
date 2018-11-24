package Entity;

import java.awt.Graphics;

import Display.Assets;
import Main.Game;
import Misc.Functions;

public class Enemy extends Entity{	
	
public String type;
private Slime slime;
private Game game;

private int width, height;

public Enemy(float x, float y, String type, Game game) {
	super(x, y);
	this.type = type;
	this.game = game;
	switch(type){
	case "slime":
		slime = new Slime(game, x, y);
		width = 40;
		height = 30;
		break;
	}
}

@Override
public void tick() {
	switch(type) {
	case "slime":
	 slime.tick();
	 x = slime.x;
	 y = slime.y;
		break;
	}
}

@Override
public void render(Graphics g) {
	switch(type) {
		case "slime":
			slime.render(g);
			break;
	}
}
public int getX(){
	return (int)x;
}

public int getY(){
	return (int)y;
}

public int getWidth(){
	return width;
}

public int getHeight(){
	return height;
}

public String getType() {
	return type;
}

}
