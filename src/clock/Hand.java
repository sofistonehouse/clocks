package clock;
import java.awt.*;
/** 
 * Display a clock hand
 * 
 * @author Sofi Stonehouse
 */
public class Hand extends java.lang.Object {
    
    /** Constructor
     * 
     * @param centerX the X coordinate fo the center of the clock of which this 
     *      is a part relative to upper left corner of clock
     * @param centerY the Y coordinate fo the center of the clock of which this 
     *      is a part relative to upper left corner of clock
     * @param length the length of this hand
     * @param color the color of this hand
     */
    public Hand(int centerX,
                int centerY, 
                int length, 
                java.awt.Color color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.length = length;
        this.color = color;
    }
    
    
    /**
     * Set the angle for this hand
     * 
     * @param angle the angle (in degrees relative to vertical) for this hand
     */
    public void setAngle(long angle)
    {
        this.angle = angle;
    }
    
    
    /**
     * Draw this hand
     * 
     * @param graphics the graphics object on which to draw
     */
    public void draw(java.awt.Graphics graphics)
    {
        //Variables needed to draw the polygon hand shape
        int endX = centerX + (int)(length * Math.sin(Math.toRadians(angle)));
        int endY = centerY - (int)(length * Math.cos(Math.toRadians(angle)));
        int leftX = centerX + (int)(length * 0.8*Math.sin(Math.toRadians(angle-6)));
        int leftY = centerY - (int)(length * 0.8*Math.cos(Math.toRadians(angle-6)));
        int rightX = centerX + (int)(length * 0.8*Math.sin(Math.toRadians(angle+6)));
        int rightY = centerY - (int)(length * 0.8* Math.cos(Math.toRadians(angle+6)));
        
        //Creates the array of X points
        int xPoints [] = new int[4];
        xPoints[0] = centerX;
        xPoints[1] = leftX;
        xPoints[2] = endX;
        xPoints[3] = rightX;
        
        //Creates the array of Y points
        int yPoints [] = new int[4];
        yPoints[0] = centerY;
        yPoints[1] = leftY;
        yPoints[2] = endY;
        yPoints[3] = rightY;
        
        graphics.setColor(color);
        
        //Creates the shape of the hand based on the arrays of X and Y points
        graphics.fillPolygon(xPoints, yPoints, 4);
        
        //Creates the circle in the middle of each clock relative to the size
        //of the clock.
        graphics.fillOval(centerX-(length/16), centerY-(length/16), length/8, length/8);
        
    }
    
    
    //Constants
    private int centerX;
    private int centerY;
    private int length;
    private Color color;
    private long angle;
    
}
