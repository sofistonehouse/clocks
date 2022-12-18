/* Project.java */

package project;

import clock.Clock;
import java.awt.*;
import javax.swing.*;

/** Main program for CPS122 Individual Project 2
 *
 *  @author Russell C. Bjork
 *  
 *  MODIFIED BY: Sofi Stonehouse
 */
public class Project extends JPanel {

    /** Start execution 
     */
    public void start() {

        setLayout(null);
        setBackground(BACKGROUND_COLOR);
       
        // Create and start the clocks

		Clock clock1 = new Clock(CLOCK1_LEFT_X,
		                       CLOCK1_UPPER_Y,
        		               CLOCK1_FACE_DIAMETER,
                		       CLOCK1_LABEL,
		                       CLOCK1_FACE_COLOR,
        		               CLOCK1_HOUR_HAND_COLOR,
                		       CLOCK1_MINUTE_HAND_COLOR,
		                       CLOCK1_SECOND_HAND_COLOR,
        		               CLOCK1_LABEL_LINE_COLOR,
                		       CLOCK1_TIMEZONE_OFFSET);
		add(clock1);
		clock1.start();
                
                
                Clock clock2 = new Clock(CLOCK2_LEFT_X,
		                       CLOCK2_UPPER_Y,
        		               CLOCK2_FACE_DIAMETER,
                		       CLOCK2_LABEL,
		                       CLOCK2_FACE_COLOR,
        		               CLOCK2_HOUR_HAND_COLOR,
                		       CLOCK2_MINUTE_HAND_COLOR,
		                       CLOCK2_SECOND_HAND_COLOR,
        		               CLOCK2_LABEL_LINE_COLOR,
                		       CLOCK2_TIMEZONE_OFFSET);
		add(clock2);
		clock2.start();
                
                
                Clock clock3 = new Clock(CLOCK3_LEFT_X,
		                       CLOCK3_UPPER_Y,
        		               CLOCK3_FACE_DIAMETER,
                		       CLOCK3_LABEL,
		                       CLOCK3_FACE_COLOR,
        		               CLOCK3_HOUR_HAND_COLOR,
                		       CLOCK3_MINUTE_HAND_COLOR,
		                       CLOCK3_SECOND_HAND_COLOR,
        		               CLOCK3_LABEL_LINE_COLOR,
                		       CLOCK3_TIMEZONE_OFFSET);
		add(clock3);
		clock3.start();
                
                
                Clock clock4 = new Clock(CLOCK4_LEFT_X,
		                       CLOCK4_UPPER_Y,
        		               CLOCK4_FACE_DIAMETER,
                		       CLOCK4_LABEL,
		                       CLOCK4_FACE_COLOR,
        		               CLOCK4_HOUR_HAND_COLOR,
                		       CLOCK4_MINUTE_HAND_COLOR,
		                       CLOCK4_SECOND_HAND_COLOR,
        		               CLOCK4_LABEL_LINE_COLOR,
                		       CLOCK4_TIMEZONE_OFFSET);
		add(clock4);
		clock4.start();
                
                
                Clock clock5 = new Clock(CLOCK5_LEFT_X,
		                       CLOCK5_UPPER_Y,
        		               CLOCK5_FACE_DIAMETER,
                		       CLOCK5_LABEL,
		                       CLOCK5_FACE_COLOR,
        		               CLOCK5_HOUR_HAND_COLOR,
                		       CLOCK5_MINUTE_HAND_COLOR,
		                       CLOCK5_SECOND_HAND_COLOR,
        		               CLOCK5_LABEL_LINE_COLOR,
                		       CLOCK5_TIMEZONE_OFFSET);
		add(clock5);
		clock5.start();
		
    }
    
    /** Main method for running this as an application
     */
    public static void main(String [] args) {
        JFrame frame = new JFrame();
        frame.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Project project = new Project();
        project.start();
        frame.add(project);
        frame.setVisible(true);
    }

//Application width and height

    private static final int APPLICATION_WIDTH = 1200;
    private static final int APPLICATION_HEIGHT = 700;

// COLORS FOR EACH CLOCK

    private static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
    private static final Color GRAY_BLUE = new Color(96,124,141);
    private static final Color GRAY_BLUE2 = new Color(83,104,114);
    private static final Color GRAY_BLUE3 = new Color(112,128,144);
    private static final Color GRAY_BLUE4 = new Color(83,104,120);
    private static final Color GRAY_BLUE5 = new Color(54,69,79);
    private static final Color HOUR_COLOR = new Color(107,67,33);
    private static final Color MINUTE_COLOR = new Color(184,151,120);
    private static final Color SECOND_COLOR = new Color(230,206,168);
    

//Clock 1
    
    private static final int CLOCK1_LEFT_X = 400;				
    private static final int CLOCK1_UPPER_Y = 100;					
    private static final int CLOCK1_FACE_DIAMETER = 300;				
    private static final String CLOCK1_LABEL = "Baku";				
    private static final Color CLOCK1_FACE_COLOR = GRAY_BLUE;		
    private static final Color CLOCK1_HOUR_HAND_COLOR = HOUR_COLOR;	
    private static final Color CLOCK1_MINUTE_HAND_COLOR = MINUTE_COLOR;	
    private static final Color CLOCK1_SECOND_HAND_COLOR = SECOND_COLOR;	
    private static final Color CLOCK1_LABEL_LINE_COLOR = GRAY_BLUE4;	
    private static final String CLOCK1_TIMEZONE_OFFSET = "GMT+04:00";	

//Clock 2
    
    private static final int CLOCK2_LEFT_X = 800;				
    private static final int CLOCK2_UPPER_Y = 100;					
    private static final int CLOCK2_FACE_DIAMETER = 150;				
    private static final String CLOCK2_LABEL = "Philadelphia";				
    private static final Color CLOCK2_FACE_COLOR = GRAY_BLUE2;		
    private static final Color CLOCK2_HOUR_HAND_COLOR = HOUR_COLOR;	
    private static final Color CLOCK2_MINUTE_HAND_COLOR = MINUTE_COLOR;	
    private static final Color CLOCK2_SECOND_HAND_COLOR = SECOND_COLOR;	
    private static final Color CLOCK2_LABEL_LINE_COLOR = GRAY_BLUE4;	
    private static final String CLOCK2_TIMEZONE_OFFSET = "GMT-05:00";

//Clock 3
    
    private static final int CLOCK3_LEFT_X = 150;				
    private static final int CLOCK3_UPPER_Y = 400;					
    private static final int CLOCK3_FACE_DIAMETER = 150;				
    private static final String CLOCK3_LABEL = "Nashville";				
    private static final Color CLOCK3_FACE_COLOR = GRAY_BLUE3;		
    private static final Color CLOCK3_HOUR_HAND_COLOR = HOUR_COLOR;	
    private static final Color CLOCK3_MINUTE_HAND_COLOR = MINUTE_COLOR;	
    private static final Color CLOCK3_SECOND_HAND_COLOR = SECOND_COLOR;	
    private static final Color CLOCK3_LABEL_LINE_COLOR = GRAY_BLUE4;	
    private static final String CLOCK3_TIMEZONE_OFFSET = "GMT-06:00";

//Clock 4
    
    private static final int CLOCK4_LEFT_X = 800;				
    private static final int CLOCK4_UPPER_Y = 400;					
    private static final int CLOCK4_FACE_DIAMETER = 150;				
    private static final String CLOCK4_LABEL = "Bangkok";				
    private static final Color CLOCK4_FACE_COLOR = GRAY_BLUE4;		
    private static final Color CLOCK4_HOUR_HAND_COLOR = HOUR_COLOR;	
    private static final Color CLOCK4_MINUTE_HAND_COLOR = MINUTE_COLOR;	
    private static final Color CLOCK4_SECOND_HAND_COLOR = SECOND_COLOR;	
    private static final Color CLOCK4_LABEL_LINE_COLOR = GRAY_BLUE4;	
    private static final String CLOCK4_TIMEZONE_OFFSET = "GMT+07:00";

//Clock 5
    
    private static final int CLOCK5_LEFT_X = 150;				
    private static final int CLOCK5_UPPER_Y = 100;					
    private static final int CLOCK5_FACE_DIAMETER = 150;				
    private static final String CLOCK5_LABEL = "Budapest";				
    private static final Color CLOCK5_FACE_COLOR = GRAY_BLUE5;		
    private static final Color CLOCK5_HOUR_HAND_COLOR = HOUR_COLOR;	
    private static final Color CLOCK5_MINUTE_HAND_COLOR = MINUTE_COLOR;	
    private static final Color CLOCK5_SECOND_HAND_COLOR = SECOND_COLOR;	
    private static final Color CLOCK5_LABEL_LINE_COLOR = GRAY_BLUE4;	
    private static final String CLOCK5_TIMEZONE_OFFSET = "GMT+01:00";

}
