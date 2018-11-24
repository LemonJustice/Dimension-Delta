package Display;

import java.awt.image.BufferedImage;


public class Assets {

	private static final int  Playwidth = 60, Playheight = 120;
	
	
	//Add images to the "res" folder and initiate a variable on the line below as a BufferedImage
	
	public static BufferedImage icon, ladder, lCon, rCon, slime, vial;
	
	public static BufferedImage alex, heart;
	
	public static void init(){
		
		// exampleImage = ImageLoader.loadImage("/textures/exampleImage.png");
		 icon = ImageLoader.loadImage("/textures/DD_Icon.png");
		 ladder = ImageLoader.loadImage("/textures/Ladder.png");
		 lCon = ImageLoader.loadImage("/textures/LCon.png");
		 rCon = ImageLoader.loadImage("/textures/RCon.png");
		 slime = ImageLoader.loadImage("/textures/Slime.png");
		 vial = ImageLoader.loadImage("/textures/Health.png");
	}
}