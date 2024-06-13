package Computation;

import GUI.Frame;
import GUI.Grid;
import GUI.GridButton;

import javax.swing.*;
import java.util.ArrayList;

public class SimulationWorker extends SwingWorker<Void, Void> {
    boolean[][] gridValues;
    ArrayList<GridButton> gridButtons;
    int gridSizeX;
    int gridSizeY;
    Frame frame;
    Grid grid;

    public SimulationWorker(boolean[][] gridValues, ArrayList<GridButton> gridButtons, int gridSizeX, int gridSizeY, Frame frame, Grid grid) {
        this.gridValues = gridValues;
        this.gridButtons = gridButtons;
        this.gridSizeX = gridSizeX;
        this.gridSizeY = gridSizeY;
        this.frame = frame;
        this.grid = grid;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (true) {
            if (isCancelled())
                return null;
            gridValues = GameDynamics.timeStep(gridValues, gridButtons, gridSizeX, gridSizeY);
            grid.setGridValues(gridValues);
            Thread.sleep(frame.time);
        }
    }
}
