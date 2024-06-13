package GUI;

import Computation.GameDynamics;
import Computation.SimulationWorker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    private int gridSizeX = 10;
    private int gridSizeY = 10;
    public volatile int time = 1000;
    SimulationWorker worker;
    public Frame(){
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setMinimumSize(new Dimension(620, 700));
        Dimension textOrButtonDim = new Dimension(100, 30);
        Dimension sliderDim = new Dimension(300, 80);
        Dimension stepSliderDim = new Dimension(300, 80);
        Dimension timeIntervalDim = new Dimension(130, 30);

        //central grid
        JPanel gridPanel = new JPanel(new GridBagLayout());
        Grid grid = new Grid(gridSizeX, gridSizeY);
        gridPanel.add(grid);
        getContentPane().add(BorderLayout.CENTER, gridPanel);

        //menu
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(new EmptyBorder(new Insets(10, 0, 0, 0)));
        getContentPane().add(BorderLayout.WEST, menuPanel);

        menuPanel.add(Box.createGlue());

        JLabel label = new JLabel();
        label.setText("Grid: " + gridSizeX + "x" + gridSizeY);
        label.setPreferredSize(textOrButtonDim);
        label.setMaximumSize(textOrButtonDim);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(label);

        JPanel xSizeSliderPanel = new JPanel();
        JSlider xSizeSlider = new JSlider(10, 200, gridSizeX);
        xSizeSliderPanel.setPreferredSize(sliderDim);
        xSizeSliderPanel.setMaximumSize(sliderDim);
        xSizeSlider.setPaintTrack(true);
        xSizeSlider.setPaintTicks(true);
        xSizeSlider.setPaintLabels(true);
        xSizeSlider.setMajorTickSpacing(40);
        xSizeSlider.setMinorTickSpacing(5);
        xSizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gridSizeX = xSizeSlider.getValue();
                label.setText("Grid: " + gridSizeX + "x" + gridSizeY);
                grid.updateSize(gridSizeX, gridSizeY);
                //grid.initialize();
            }
        });
        xSizeSliderPanel.add(BorderLayout.WEST, new JLabel("10"));
        xSizeSliderPanel.add(BorderLayout.CENTER, xSizeSlider);
        xSizeSliderPanel.add(BorderLayout.EAST, new JLabel("200"));
        menuPanel.add(xSizeSliderPanel);

        JPanel ySizeSliderPanel = new JPanel();
        JSlider ySizeSlider = new JSlider(10, 200, gridSizeY);
        ySizeSliderPanel.setPreferredSize(sliderDim);
        ySizeSliderPanel.setMaximumSize(sliderDim);
        ySizeSlider.setPaintTrack(true);
        ySizeSlider.setPaintTicks(true);
        ySizeSlider.setPaintLabels(true);
        ySizeSlider.setMajorTickSpacing(40);
        ySizeSlider.setMinorTickSpacing(5);
        ySizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gridSizeY = ySizeSlider.getValue();
                label.setText("Grid: " + gridSizeX + "x" + gridSizeY);
                grid.updateSize(gridSizeX, gridSizeY);
            }
        });
        ySizeSliderPanel.add(BorderLayout.WEST, new JLabel("10"));
        ySizeSliderPanel.add(BorderLayout.CENTER, ySizeSlider);
        ySizeSliderPanel.add(BorderLayout.EAST, new JLabel("200"));
        menuPanel.add(ySizeSliderPanel);

        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton resizeButton = new JButton("Resize");
        resizeButton.setPreferredSize(textOrButtonDim);
        resizeButton.setMaximumSize(textOrButtonDim);
        resizeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.initialize();
            }
        });
        menuPanel.add(resizeButton);

        JButton timeStepButton = new JButton("Step");
        timeStepButton.setPreferredSize(textOrButtonDim);
        timeStepButton.setMaximumSize(textOrButtonDim);
        timeStepButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeStepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.setGridValues(GameDynamics.timeStep(grid.getGridValues(), grid.getButtons(), gridSizeX, gridSizeY));
            }
        });
        menuPanel.add(timeStepButton);

        menuPanel.add(Box.createGlue());


        //step menu
        JPanel stepMenuPanel = new JPanel();
        stepMenuPanel.setLayout(new BoxLayout(stepMenuPanel, BoxLayout.X_AXIS));
        stepMenuPanel.setBorder(new EmptyBorder(new Insets(10, 0, 10, 0)));
        getContentPane().add(BorderLayout.SOUTH, stepMenuPanel);

        stepMenuPanel.add(Box.createGlue());

        JPanel timeSliderPanel = new JPanel();
        JLabel timeLabel = new JLabel();
        timeLabel.setPreferredSize(timeIntervalDim);
        timeLabel.setMaximumSize(timeIntervalDim);
        timeLabel.setText("Time interval: " + time + " ms");
        stepMenuPanel.add(timeLabel);

        JSlider timeSlider = new JSlider(100, 5000, time);
        timeSlider.setPreferredSize(stepSliderDim);
        timeSlider.setMaximumSize(stepSliderDim);
        timeSlider.setPaintTrack(true);
        timeSlider.setPaintTicks(true);
        timeSlider.setPaintLabels(true);
        timeSlider.setMajorTickSpacing(600);
        timeSlider.setMinorTickSpacing(200);
        timeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                time = timeSlider.getValue();
                timeLabel.setText("Time interval: " + time + " ms");
            }
        });
        timeSliderPanel.add(BorderLayout.WEST, new JLabel("100"));
        timeSliderPanel.add(BorderLayout.CENTER, timeSlider);
        timeSliderPanel.add(BorderLayout.EAST, new JLabel("5000"));
        stepMenuPanel.add(timeSliderPanel);

        JPanel startStopPanel = new JPanel();
        startStopPanel.setLayout(new BoxLayout(startStopPanel, BoxLayout.Y_AXIS));
        startStopPanel.setBorder(new EmptyBorder(new Insets(10, 0, 10, 0)));
        JButton timeStartButton = new JButton("Start");
        timeStartButton.setPreferredSize(textOrButtonDim);
        timeStartButton.setMaximumSize(textOrButtonDim);
        Frame thisFrame = this;
        JButton timeStopButton = new JButton("Stop");
        timeStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xSizeSlider.setEnabled(false);
                ySizeSlider.setEnabled(false);
                resizeButton.setEnabled(false);
                timeStepButton.setEnabled(false);
                timeStartButton.setEnabled(false);
                timeStopButton.setEnabled(true);
                worker = new SimulationWorker(grid.getGridValues(), grid.getButtons(), gridSizeX, gridSizeY, thisFrame, grid);
                worker.execute();
            }
        });
        startStopPanel.add(timeStartButton);
        timeStopButton.setEnabled(false);
        timeStopButton.setPreferredSize(textOrButtonDim);
        timeStopButton.setMaximumSize(textOrButtonDim);
        timeStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worker.cancel(true);
                xSizeSlider.setEnabled(true);
                ySizeSlider.setEnabled(true);
                resizeButton.setEnabled(true);
                timeStepButton.setEnabled(true);
                timeStartButton.setEnabled(true);
                timeStopButton.setEnabled(false);
            }
        });
        startStopPanel.add(timeStopButton);
        stepMenuPanel.add(startStopPanel);

        stepMenuPanel.add(Box.createGlue());
    }
}
