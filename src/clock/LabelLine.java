/* LabelLine.java */

package clock;

import java.awt.*;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Display of the label line beneath a clock
 *
 * @author Russell C. Bjork
 *
 * MODIFIED BY: 
 */
public class LabelLine {

    /** Constructor
     *
     *  @param leftX the X coordinate of the upper left corner of the bounding
     *         rectangle for the line relative to upper left corner of clock
     *  @param upperY the Y coordinate of the upper left corner of the bounding
     *         rectangle for the line relative to upper left corner of clock
     *  @param lineWidth the width available for the line - displayed line will
     *         be centered within this width
     *  @param label the descriptive text to be displayed in this line
     *  @param color the color of the line
     *  @param timeZoneOffset the time zone for the clock
     */
    public LabelLine(int leftX, 
                     int upperY,
                     int lineWidth,
                     String label,
                     Color color,
                     String timeZoneOffset) {

        this.leftX = leftX;
        this.upperY = upperY;
        this.lineWidth = lineWidth;
        this.label = label;
        this.color = color;
        this.timeZone = TimeZone.getTimeZone(timeZoneOffset);
    }
    
    

    /** Draw this line and the digital clock
     *
     *  @param graphics the graphics object on which to draw
     */
    public void draw(Graphics graphics) {

        graphics.setColor(color);
        
        //Creates the string "digitalTime" and sets it equal to the digital time
        String digitalTime; 
        Calendar now = Calendar.getInstance(timeZone);
        
        //Converts the clock to a 12 hour clock instead of a 24 hour clock
        if (now.get(Calendar.HOUR_OF_DAY)>12)
        {
            digitalTime = Integer.toString(now.get(Calendar.HOUR_OF_DAY)-12) + ":";
        }
        else
            digitalTime=Integer.toString(now.get(Calendar.HOUR_OF_DAY)) + ":";
        
        //Supplies leading 0's for minutes less than 10
        if(now.get(Calendar.MINUTE)<10)
            digitalTime+="0" + Integer.toString(now.get(Calendar.MINUTE))+ ":";
        else
            digitalTime+=Integer.toString(now.get(Calendar.MINUTE))+ ":";
        
        //Supplies leading 0's for seconds less than 10
        if(now.get(Calendar.SECOND)<10)
            digitalTime+="0" + Integer.toString(now.get(Calendar.SECOND));
        else
            digitalTime+=Integer.toString(now.get(Calendar.SECOND));
        
        //Adds "AM" or "PM" to the end of the digital time depending on what 
        //time it is. 
        if (now.get(Calendar.HOUR_OF_DAY)>12)
        {
            digitalTime+=" PM";
        }
        else
            digitalTime+=" AM";
        
        String content = label;
        
        // Calculate padding needed to center content if possible
        // Calculate padding needed to center digitalTime

        int contentWidth = graphics.getFontMetrics().stringWidth(content);
        int stringWidth = graphics.getFontMetrics().stringWidth(digitalTime);
        int paddingToCenter;
        if (contentWidth < lineWidth)
            paddingToCenter = (lineWidth - contentWidth) / 2;
        else
            // Not enough room to center
            paddingToCenter = 0;

        // Draw the content - note that, for drawString(), the method requires
        // the Y parameter to be the font baseline.  The getAscent() method
        // of the font metrics gives the distance from the baseline to the
        // height of the tallest character, which is what upperY specifies

        graphics.drawString(content,
                            leftX + paddingToCenter,
                            upperY - graphics.getFontMetrics().getAscent());
        
        //Draw the digitalTime
        graphics.drawString(digitalTime,
                            leftX + ((lineWidth - stringWidth) / 2),
                            upperY + graphics.getFontMetrics().getAscent());
    }

    // Values passed to the constructor

    private int leftX;
    private int upperY;
    private int lineWidth;
    private String label;
    private Color color;
    
    // Time zone for this clock

    private TimeZone timeZone;
}
