package Misc;

import java.util.Random;

public class Functions {
	
	public void Functions(){
		
	}
	
	public boolean collide(float x1, float y1, int w1, int h1, float x2, float y2, int w2, int h2) {
		return x1+w1 > x2 && y1+h1 > y2 && x1 < x2+w2 && y1 < y2+h2;    
	}
	
	public double rng(int min, int max, long seed) {
		Random random = new Random();
		random.setSeed(seed);
		int w = min-max;
		return random.nextFloat()*w-min;
	}
	
	public double dist(float x1, float y1, float x2, float y2) {
		return Math.sqrt( Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2) );
	}
	
}
