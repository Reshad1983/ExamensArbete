/**
 * 
 */
package dv606.lecture2;

import java.util.Random;

import android.graphics.Color;

/**
 * @author jonasl
 *
 */
public class MyColor {
    private static final Random random = new Random();
    
	private String color_string;
	private int hex_color;
	
	MyColor(String s, int c) {
		color_string = s;
		hex_color = c;
	}
	
	public String getColorString() {
		return "Color: "+ color_string;
	}
	
	public int getColorCode() {
		return hex_color;
	}
	
    public static MyColor randomColor() {
    	int min = 100;
    	int r = min+random.nextInt(155);
    	int g = min+random.nextInt(155);
    	int b = min+random.nextInt(155);
    	int col = Color.argb(255,r,g,b);
    	String str = Integer.toHexString(col);
    	return new MyColor(str,col);
    }
	
	@Override
	public String toString() {return color_string;}
}
