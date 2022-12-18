package clock;
import java.awt.*;

/**
 * Display a clock face
 * 
 * @author Sofi Stonehouse
 */
public class Face
{
    /** Constructor
     * 
     * @param leftX the X coordinate of the upper left corner of the bounding 
     *      rectangle for the face relative to upper left corner of clock
     * @param upperY the Y coordinate of the upper left corner of the bounding
     *         rectangle for the face relative to upper left corner of clock
     * @param faceDiameter the diameter of the face
     * @param color color of the face
    */
    public Face(int leftX,
                int upperY,
                int faceDiameter,
                java.awt.Color color) {
        
        this.leftX = leftX;
        this.upperY = upperY;
        this.faceDiameter = faceDiameter;
        this.faceRadius = faceDiameter/2;
        this.middleX = leftX + (faceDiameter/2) ; 
        this.middleY = upperY + (faceDiameter/2); 
        this.color = color;
    }
    
    /**
     * Draw the face
     * 
     * @param graphics the object on which to draw 
     */
    public void draw(java.awt.Graphics graphics)
    {
        // Defines the current angle
        double angle; 
        
        //Creates the circle and border for each clock
        graphics.setColor(Color.WHITE);
        graphics.fillOval(leftX-5, upperY-5, faceDiameter+10, faceDiameter+10);
        graphics.setColor(color);
        graphics.fillOval(leftX, upperY, faceDiameter, faceDiameter);
        graphics.setColor(Color.WHITE);
        
        // Places Hashmarks around the clock
        for (int i=0; i<60; i++)
        {
            //Sets the current angle to i/60 times 360
            angle = i*6; 
            
            
            HASHMARK = faceDiameter/100; 
            
            //Finds the coordinates around the circle
            int outerX = (int)(faceRadius*Math.sin(Math.PI * 2.0 * angle/360.0))+middleX;
            int outerY = (int)(faceRadius*Math.cos(Math.PI * 2.0 * angle/360.0))+middleY;

            
             if (angle%10 ==0)
            {
                HASHMARK+=faceDiameter/50;
            }
            
            if (angle == 0.0 || angle==90.0 || angle == 180.0 || angle == 270.0)
            {
                HASHMARK += faceDiameter/25;
                
            }
            
            //Finds the coordinates of a smaller invisible circle
            int innerX = (int)((faceRadius-HASHMARK)*Math.sin(Math.PI * 2.0 * angle/360.0))+middleX;
            int innerY = (int)((faceRadius-HASHMARK)*Math.cos(Math.PI * 2.0 * angle/360.0))+middleY;
            
            
            //Color of the hash marks
            if (angle == 0.0 || angle==90.0 || angle == 180.0 || angle == 270.0)
            {
                graphics.setColor(PEACH);
            }
            else
                graphics.setColor(Color.WHITE);
            
            //Connects the outer circle point with the inner circle point to 
            //make a hash mark. 
            graphics.drawLine(outerX, outerY, innerX, innerY);
            
        }
        
        // Places roman numerals around the clock
        String[] numerals = {"XII", "I","II","III","IV","V","VI","VII","VIII","IX","X","XI"};
        for (int i=0; i<=11; i++)
        {
            angle = i*30; 
            
            int xPos = middleX + (int)(faceRadius*0.75 * Math.sin(Math.toRadians(angle)) - graphics.getFontMetrics().stringWidth(numerals[i])/2);
            int yPos = middleY - (int)(faceRadius*0.75 * Math.cos(Math.toRadians(angle))) + (graphics.getFontMetrics().getAscent()/4);
            graphics.drawString(numerals[i], xPos, yPos);
        }
        
    }
    
    //constants
    private int leftX;
    private int upperY;
    private int faceDiameter;
    private int faceRadius; 
    private int middleX;
    private int middleY;
    private Color color;
    private static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
    private double TOTAL_ANGLE = 360;
    private double TOTAL_ANGLE_INT = 360;
    private int HASHMARK; 
    private static final Color PEACH = new Color(255,203,164);
}
