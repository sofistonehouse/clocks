/* Clock.java  */

package clock;

import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * Simulation of a clock
 * 
 * @author Russell C. Bjork
 *
 */
public class Clock extends JComponent implements Runnable
{
    /* The clock is laid out as follows:
     *
     *      -----------------------------------------
     *      |     (Blank border all around)         |
     *      |  -----------------------------------  |
     *      |  |      <- face diameter ->        |  |
     *      |  |                                 |  |
     *      |  |    Bounding rectangle for face  |  |
     *      |  |                                 |  |
     *      |  |  ^                              |  |
     *      |  |  |                              |  |
     *      |  | face                            |  |
     *      |  | diameter                        |  |
     *      |  |  |                              |  |
     *      |  |  v                              |  |
     *      |  |                                 |  |
     *      |  |                                 |  |
     *      |  |                                 |  |
     *      |  |                                 |  |
     *      |  |                                 |  |
     *      |  |                                 |  |
     *      |  |                                 |  |
     *      |  |                                 |  |
     *      |  |                                 |  |
     *      |  -----------------------------------  |
     *      |    ^ Space between face & label       |                            |
     *      |    |      2 x font height             |
     *      |    v                                  |
     *      |  -----------------------------------  |
     *      |  | Bounding rectangle for label    |  |  ^  font height
     *      |  -----------------------------------  |  v
     *      |                                       |
     *      -----------------------------------------
     */

    /** Constructor
     * 
     *  @param leftX X coordinate of the upper left corner of the bounding
     *         rectangle for the overall clock
     *  @param upperY Y coordinate of the upper left corner of the bounding
     *         rectangle for the overall clock
     *  @param faceDiameter diameter of the clock face
     *  @param label the label for this clock
     *  @param faceColor the color for the face of this clock
     *  @param hourHandColor the color for the hour hand of this clock;
     *  @param minuteHandColor the color for the minute hand of this clock;
     *  @param secondHandColor the color for the second hand of this clock;
     *  @param labelLineColor the color for the label line displayed below
     *         the clock
     *  @param timeZoneOffset the time zone for this clock
     */
    public Clock(int leftX,
                 int upperY,
                 int faceDiameter,
                 String label,
                 Color faceColor,
                 Color hourHandColor,
                 Color minuteHandColor,
                 Color secondHandColor,
                 Color labelLineColor,
                 String timeZoneOffset) {

        // The size of the font used for displaying various text depends on the
        // size of the clock

        font = new Font(Font.MONOSPACED, Font.BOLD, faceDiameter/FONT_RATIO);
        setFont(font);
        int fontHeight = getFontMetrics(font).getHeight();

        // Several values for constructing component parts are calculated based
        // on the radius of the clock
        
        int radius = faceDiameter/2;
        
        // Create the visible components of the clock
		
        face = new Face(BLANK_BORDER,
                        BLANK_BORDER,
                        faceDiameter,
                        faceColor);
        hourHand = new Hand(BLANK_BORDER + radius,
                            BLANK_BORDER + radius,
                            (int) (HOUR_HAND_RATIO * radius),
                            hourHandColor);
        minuteHand = new Hand(BLANK_BORDER + radius,
                              BLANK_BORDER + radius,
                              (int) (MINUTE_HAND_RATIO * radius),
                              minuteHandColor);
        secondHand = new Hand(BLANK_BORDER + radius,
                              BLANK_BORDER + radius,
                              (int) (SECOND_HAND_RATIO * radius),
                              secondHandColor);
        labelLine = new LabelLine(BLANK_BORDER,
                                  BLANK_BORDER + faceDiameter + 2 * fontHeight,
                                  faceDiameter,
                                  label,
                                  labelLineColor,
                                  timeZoneOffset);

        // Record time zone information

        timeZone = TimeZone.getTimeZone(timeZoneOffset);

        // Set the boundaries of the area on the screen where this clock will
        // be displayed

        setBounds(leftX,
                  upperY,
                  BLANK_BORDER + faceDiameter + BLANK_BORDER,
                  BLANK_BORDER + faceDiameter + 3 * fontHeight + BLANK_BORDER);
    }
    
    /** Set the time displayed by this clock
     *
     *  @param hour the hour to display
     *  @param minute the minute to display
     *  @param second the second to display
     */
    public void setTime(int hour, int minute, int second)
    {
        hourHand.setAngle(hour * DEGREES_PER_ROTATION / HOURS_PER_ROTATION +
                          minute * DEGREES_PER_ROTATION /
                            (MINUTES_PER_ROTATION * HOURS_PER_ROTATION));
        minuteHand.setAngle(minute * DEGREES_PER_ROTATION / MINUTES_PER_ROTATION +
                            second * DEGREES_PER_ROTATION /
                                (SECONDS_PER_ROTATION / MINUTES_PER_ROTATION));
        secondHand.setAngle(second * DEGREES_PER_ROTATION / SECONDS_PER_ROTATION);

        // Redraw this clock after changing the time setting
        
        repaint();
    }  

    /** Set this clock to display the current time in its time zone
     */
    public void setCurrentTime() {
        Calendar now = Calendar.getInstance(timeZone);
        setTime(now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                now.get(Calendar.SECOND));
    }

    /** Method executed when clock runs
     */
    public void run() {
        while(true) {
            try { Thread.sleep(TICK_INTERVAL); } catch(InterruptedException e) {}
            setCurrentTime();
        }
    }

    /** Start this clock
     */
    public void start() {
        new Thread(this).start();
    }

    /** Draw this clock on the screen
     *
     *  @param graphics the graphics object on which to draw
     */
    public void paint(Graphics graphics) {
        face.draw(graphics);
        hourHand.draw(graphics);
        minuteHand.draw(graphics);
        secondHand.draw(graphics);
        labelLine.draw(graphics);    
    }

    // Font to be used for this clock

    Font font;

    // Component parts of the clock

    private Face face;
    private Hand hourHand, minuteHand, secondHand;
    private LabelLine labelLine;

    // Time zone for this clock

    private TimeZone timeZone;

    // Constants controlling layout of the clock

    private static final int BLANK_BORDER = 10; // Blank border on all sides
    private static final int FONT_RATIO = 13; // Ratio between font size and diameter
    private static final double HOUR_HAND_RATIO = 0.6; // Ratio of hand length to radius
    private static final double MINUTE_HAND_RATIO = 0.8; // Ratio of hand length to radius
    private static final double SECOND_HAND_RATIO = 0.9; // Ratio of hand length to radius

    // Constants controlling the angle of the hands

    private static final int DEGREES_PER_ROTATION = 360;
    private static final int HOURS_PER_ROTATION = 12;
    private static final int MINUTES_PER_ROTATION = 60;
    private static final int SECONDS_PER_ROTATION = 60;

    // Length in milliseconds of the desired clock tick

    private static final int TICK_INTERVAL = 1000;
}
