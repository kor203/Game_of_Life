package Computation;

import GUI.GridButton;

import java.awt.*;
import java.util.ArrayList;

public class GameDynamics {
    public static boolean[][] timeStep(boolean[][] gridValues, ArrayList<GridButton> gridButtons, int gridSizeX, int gridSizeY){
        boolean[][] nextGridValues = new boolean[gridSizeY][gridSizeX];
        for (int y = 0; y < gridSizeY; y++)
            for (int x = 0; x < gridSizeX; x++)
                if (gridValues[y][x] == true)
                    for (int yIt = Math.max(0, y - 1); yIt < Math.min(gridSizeY, y + 2); yIt++)
                        for (int xIt = Math.max(0, x - 1); xIt < Math.min(gridSizeX, x + 2); xIt++)
                            nextGridValues[yIt][xIt] = true;
        for (int y = 0; y < gridSizeY; y++) {
            for (int x = 0; x < gridSizeX; x++) {
                if (nextGridValues[y][x] == true){
                    int sum = gridValues[y][x] ? -1 : 0;
                    for (int yIt = Math.max(0, y - 1); yIt < Math.min(gridSizeY, y + 2); yIt++)
                        for (int xIt = Math.max(0, x - 1); xIt < Math.min(gridSizeX, x + 2); xIt++)
                            if (gridValues[yIt][xIt])
                                sum++;
                    if (gridValues[y][x] && (sum == 2 || sum == 3))
                        nextGridValues[y][x] = true;
                    else if (!gridValues[y][x] && sum == 3)
                        nextGridValues[y][x] = true;
                    else nextGridValues[y][x] = false;
                    gridButtons.get(x + gridSizeX * y).setAlive(nextGridValues[y][x]);
                }
            }
        }
        return nextGridValues;
    }
}
