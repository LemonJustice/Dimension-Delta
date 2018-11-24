package Main;

import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import Display.Assets;
import Display.Display;
import Display.KeyManager;
import Entity.Block;
import Entity.Enemy;
import Entity.Player;
import Entity.Slime;
import Menu.Menu;
import Menu.UI;

import java.awt.*;

public class Game implements Runnable{
	private Display display;
	public int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy buffer;
	private Graphics g;
	Color Background = new Color(61, 61, 61);
	
	private KeyManager keyManager;
	public Player player;
	public Menu menu;
	public UI ui;
	
	public int level = 0;
	
	public ArrayList<ArrayList<Block>> blocks = new ArrayList<ArrayList<Block>>();
	public ArrayList<ArrayList<Enemy>> enemies = new ArrayList<ArrayList<Enemy>>();
	
	private float camX, camY;
	
	private String[][] levels = {
		{
			".............................................",
			".                                           .",
			".                                           .",
			".                                           .",
			".                                           .",
			".                                           .",
			".                                           .",
			".                                           .",
			".                                           .",
			".                                           .",
			".                                           .",
			".                                           .",
			".                                           .",
			".>>><<<.    .   S   .                       .",
			".      .    .........                       .",
			"........            .                       .",
			".                   ...........            l.",
			".                                          l.",
			".                                   ..     l.",
			".                                          l.",
			".                          .....           l.",
			".                                          l.",
			".                       S                  l.",
			"............................................."
		}   
	};
	
	public Game(String title, int width2, int height2){
		this.width = width2;
		this.height = height2;
		this.title = title;
		keyManager = new KeyManager();
		menu = new Menu(this);
		ui = new UI(this);
		player = new Player(this, 40, 690);
		camX = player.getX();
		camY = player.getY();

	}
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		for (int i = 0 ; i < levels.length ; i++) {
			blocks.add(new ArrayList<Block>());
			for (int t = 0 ; t < levels[i].length ; t++) {
				for (int j = 0 ; j < levels[i][t].length() ; j++) {
					String blockType = Character.toString(levels[i][t].charAt(j));
					switch(blockType) {
						case ".":
							blocks.get(i).add(new Block(j*40, t*40, 40, 40, "normal"));
							break;
						case "l":
							blocks.get(i).add(new Block(j*40, t*40, 40, 40, "ladder"));
							break;
						case "<":
							blocks.get(i).add(new Block(j*40, t*40, 40, 40, "lCon"));
							break;
						case ">":
							blocks.get(i).add(new Block(j*40, t*40, 40, 40, "rCon"));
							break;
					}
				}
			}
		}
		for (int i = 0 ; i < levels.length ; i++) {
			enemies.add(new ArrayList<Enemy>());
			for (int t = 0 ; t < levels[i].length ; t++) {
				for (int j = 0 ; j < levels[i][t].length() ; j++) {
					String blockType = Character.toString(levels[i][t].charAt(j));
					switch(blockType) {
						case "S":
							enemies.get(i).add(new Enemy(j*40, t*40, "slime", this));
							break;
					}
				}
			}
		}
		
	}

	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if(delta >= 1){
				update();
				render();
				delta--;
			}
		}
		stop();
	}
	private void render() {
		buffer = display.getCanvas().getBufferStrategy();
		if(buffer == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = buffer.getDrawGraphics();
		
		g.clearRect(0, 0, width, height);
	
		g.setColor(Background);
		g.fillRect(0, 0, 1280, 720);
		
		
		g.translate((int)-camX+width/2-player.getWidth()/2, (int)-camY+height/2-player.getHeight()/2);
		 
		for (int i = 0 ; i < blocks.get(level).size() ; i++) {
			blocks.get(level).get(i).render(g);
		}
		for (int i = 0 ; i < enemies.get(level).size() ; i++) {
			enemies.get(level).get(i).render(g);
		}
		
		player.render(g);
		
		ui.render();
		 
		buffer.show();
		g.dispose();
	}

	private void update() {
		keyManager.tick();
		menu.tick();
		player.tick();
		
		camX += (player.getX()-camX)/5;
		camY += (player.getY()-camY)/5;
		
		if (levels[level][0].length()*40 < width) {
			camX = (levels[level][0].length()*40)/2;
		} else if (camX < width/2-20) {
			camX = width/2-20;
		} else if (camX > levels[level][0].length()*40-width/2-20) {
			camX = levels[level][0].length()*40-width/2-20;
		}
		
		if (levels[level].length*40 < height/2) {
			camY = (levels[level].length*40)/2;
		} else if (camY < height/2-20) {
			camY = height/2-20;
		} else if (camY > levels[level].length*40-height/2-20) {
			camY = levels[level].length*40-height/2-20;
		}
		
		for (int i = 0 ; i < enemies.get(level).size() ; i++) {
			enemies.get(level).get(i).tick();
		}
		ui.update();
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	public Player getPlayer(){
		return player;
	}
	
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
