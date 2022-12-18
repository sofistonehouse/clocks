/* ClockTester.java */

package clock;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * Main program for testing a clock
 *
 * @author Russell C. Bjork
 *
 * STUDENTS SHOULD NOT MAKE ANY CHANGES TO THIS CLASS!
 */
public class ClockTester extends JFrame
{
    /** Main program for testing
     */
    public static void main(String [] args)
    {
        new ClockTester().setVisible(true);
    }

    /** Constructor
     */
    ClockTester()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the panel used to contain the clocks

        final JComponent clockPanel = new JPanel() {
//            public void paintComponent(Graphics graphics) {
//                for (int i = 0; i < numberOfClocks; i ++)
//                    clock[i].paint(graphics);
//            }
            public Dimension getPreferredSize() {
                // Will end up having same height as larger panel of controls
                return new Dimension(400, 0);
            }
        };
        clockPanel.setLayout(null);
        getContentPane().add(clockPanel, BorderLayout.CENTER);

        // Create the panel used for creating a clock

        final JPanel constructPanel = new JPanel();
        JPanel constructPanelWrapper = new JPanel();
        constructPanelWrapper.add(constructPanel);
        
        constructPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        constructPanelWrapper.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
            BorderFactory.createLineBorder(Color.BLACK)));
        getContentPane().add(constructPanelWrapper, BorderLayout.WEST);
        constructPanel.setLayout(new GridLayout(0, 2, 5, 5));

        // Labels and fields for position and diameter

        createLabel = new JLabel("Clock 0");
        constructPanel.add(createLabel);
        constructPanel.add(new JLabel(""));
        constructPanel.add(new JLabel("X position"));
        xField = new JTextField("50");
        constructPanel.add(xField);
        constructPanel.add(new JLabel("Y position"));
        yField = new JTextField("50");
        constructPanel.add(yField);
        constructPanel.add(new JLabel("Face diameter"));
        diameterField = new JTextField("100");
        constructPanel.add(diameterField);
        constructPanel.add(new JLabel("Face color"));
        faceColorSelector = createColorSelector(0);
        constructPanel.add(faceColorSelector);
        constructPanel.add(new JLabel("Hour hand color"));
        hourHandColorSelector = createColorSelector(1);
        constructPanel.add(hourHandColorSelector);
        constructPanel.add(new JLabel("Minute hand color"));
        minuteHandColorSelector = createColorSelector(2);
        constructPanel.add(minuteHandColorSelector);
        constructPanel.add(new JLabel("Second hand color"));
        secondHandColorSelector = createColorSelector(3);
        constructPanel.add(secondHandColorSelector);
        constructPanel.add(new JLabel("Tag color"));
        tagColorSelector = createColorSelector(4);
        constructPanel.add(tagColorSelector);
        constructPanel.add(new JLabel("Offset to GMT"));
        gmtOffsetField = new JTextField("GMT-05:00");
        constructPanel.add(gmtOffsetField);

        // Button to actually create the clock

        createButton = new JButton("Create clock");
        constructPanel.add(createButton);
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int x, y, diameter;
                String gmtOffset;
                try
                {
                    x = Integer.parseInt(xField.getText());
                    y = Integer.parseInt(yField.getText());
                    diameter = Integer.parseInt(diameterField.getText());
                    clock[numberOfClocks] = new Clock(x,
                                                      y,
                                                      diameter,
                                                      createLabel.getText(),
                                                      POSSIBLE_COLORS[
                                                        faceColorSelector.getSelectedIndex()],
                                                      POSSIBLE_COLORS[
                                                        hourHandColorSelector.getSelectedIndex()],
                                                      POSSIBLE_COLORS[
                                                        minuteHandColorSelector.getSelectedIndex()],
                                                      POSSIBLE_COLORS[
                                                        secondHandColorSelector.getSelectedIndex()],
                                                      POSSIBLE_COLORS[
                                                        tagColorSelector.getSelectedIndex()],
                                                      gmtOffsetField.getText());
                    clockPanel.add(clock[numberOfClocks]);
                    clockPanel.repaint();
                    numberOfClocks ++;
                    createButton.setEnabled(numberOfClocks < MAX_CLOCKS);
                    if (createButton.isEnabled())
                        createLabel.setText("Clock " + numberOfClocks);
                    else
                        createLabel.setText("Enough clocks");
                    startButton.setEnabled(true);
                    clearButton.setEnabled(true);
                    boolean clocksEnabled = clockToSet.getSelectedIndex() < numberOfClocks;
                    setButton.setEnabled(clocksEnabled);
                    showCurrentButton.setEnabled(clocksEnabled);
                }
                catch(NumberFormatException exception)
                {
                    JOptionPane.showMessageDialog(ClockTester.this,
                                                  "All values must be integers");
                }
            }
        });

        // Create the panel used for setting the clock
        
        final JPanel controlPanel = new JPanel();
        JPanel controlPanelWrapper = new JPanel();
        controlPanelWrapper.add(controlPanel);
        
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanelWrapper.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
            BorderFactory.createLineBorder(Color.BLACK)));
        controlPanel.setLayout(new GridLayout(0, 2, 5, 5));
        getContentPane().add(controlPanelWrapper, BorderLayout.EAST);
  
        // Combo box used for specifying which clock to set
        
        clockToSet = new JComboBox();
        for (int i = 0; i < MAX_CLOCKS; i ++)
            clockToSet.addItem(i);
        clockToSet.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    boolean clocksEnabled = clockToSet.getSelectedIndex() < numberOfClocks;
                    setButton.setEnabled(clocksEnabled);
                    showCurrentButton.setEnabled(clocksEnabled);
                }
            }
        });
        controlPanel.add(new JLabel("Clock to set"));
        controlPanel.add(clockToSet);
        
        // Fields and labels used for specifying a specific time to set

        controlPanel.add(new JLabel("Hour"));
        hourField = new JTextField("0");
        controlPanel.add(hourField);
        controlPanel.add(new JLabel("Minute"));
        minuteField = new JTextField("0");
        controlPanel.add(minuteField);
        controlPanel.add(new JLabel("Second"));
        secondField = new JTextField("0");
        controlPanel.add(secondField);

        // Button used for setting the clock to a specific time

        setButton = new JButton("Set time");
        controlPanel.add(setButton);
        setButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int hour, minute, second;
                try
                {
                    hour = Integer.parseInt(hourField.getText());
                    minute = Integer.parseInt(minuteField.getText());
                    second = Integer.parseInt(secondField.getText());
                    clock[clockToSet.getSelectedIndex()].setTime(
                            hour, minute, second);
                    clockPanel.repaint();
                }
                catch(NumberFormatException exception)
                {
                    JOptionPane.showMessageDialog(ClockTester.this,
                            "Hour, minute, second values must be integers");
                }
            }
        });
        setButton.setEnabled(false);

        // Button used for setting the clock to the current time

        showCurrentButton = new JButton("Show current");
        controlPanel.add(showCurrentButton);
        showCurrentButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               clock[clockToSet.getSelectedIndex()].setCurrentTime();
               clockPanel.repaint();
           }
        });
        showCurrentButton.setEnabled(false);

        JPanel bottomButtonsWrapper = new JPanel();
        getContentPane().add(bottomButtonsWrapper, BorderLayout.SOUTH);

        // Button for starting automatic operation of the clocks

        startButton = new JButton("Start");
        bottomButtonsWrapper.add(startButton);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                for (int i = 0; i < numberOfClocks; i ++)
                    clock[i].start();
                startButton.setEnabled(false);
            }
        });
        startButton.setEnabled(false);

        // Button for clearing the clocks

        clearButton = new JButton("Clear");
        bottomButtonsWrapper.add(clearButton);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                numberOfClocks = 0;
                clockPanel.removeAll();
                clockPanel.repaint();
                createButton.setEnabled(true);
                createLabel.setText("Clock " + numberOfClocks);
                setButton.setEnabled(false);
                showCurrentButton.setEnabled(false);
                startButton.setEnabled(false);
                clearButton.setEnabled(false);
             }

        });
        clearButton.setEnabled(false);

        // Make sure the canvas has enough blank space for drawing the clock

        JOptionPane.getFrameForComponent(this).setTitle("Clock Tester");
        JOptionPane.getFrameForComponent(this).pack();
    }
    
    /** Create a color selector combo box
     *
     *  @param initialSelection the value that should be selected initially
     */
    private JComboBox createColorSelector(int initialSelection) {
        JComboBox result = new JComboBox();
        for (int i = 0; i < COLOR_NAMES.length; i ++)
            result.addItem(COLOR_NAMES[i]);
        result.setSelectedIndex(initialSelection);
        return result;
    }
    // The maximum number of clocks, the number of clocks we are currently 
    // displaying and the actual clocks
    
    private static final int MAX_CLOCKS = 2;
    private int numberOfClocks = 0;
    private Clock [] clock = new Clock[MAX_CLOCKS];
    
    // Specifications for constructing a clock

    private JLabel createLabel;
    private JTextField xField, yField, diameterField, gmtOffsetField;
    private JComboBox faceColorSelector,
                      hourHandColorSelector,
                      minuteHandColorSelector,
                      secondHandColorSelector,
                      tagColorSelector;

    // Possible colors for various components of a clock; names for colors
    // listed in color select dropdowns
    
    private String [] COLOR_NAMES = {
        "Black",
        "Blue",
        "Green",
        "Red",
        "Orange"
    };
            
    private Color [] POSSIBLE_COLORS = {
        Color.BLACK,
        Color.BLUE,
        Color.GREEN,
        Color.RED,
        Color.ORANGE
    };
    
    // Button to construct a clock

    private JButton createButton;

    // Specifications for setting the time
    
    private JComboBox clockToSet;
    private JTextField hourField, minuteField, secondField;

    // Buttons for setting the clock

    private JButton setButton, showCurrentButton;

    // Buttons for starting and clearing the clocks

    private JButton startButton, clearButton;
}
