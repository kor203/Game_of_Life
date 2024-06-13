package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Grid extends JPanel{
    private int gridSizeX;
    private int gridSizeY;
    private boolean [][] gridValues;
    private ArrayList<GridButton> buttons;

    public Grid (int X, int Y){
        gridSizeX = X;
        gridSizeY = Y;
        initialize();
    }

    @Override
    public Dimension getPreferredSize(){
        Dimension dimension = super.getPreferredSize();
        Container container = getParent();
        if (container != null)
            dimension = container.getSize();
        double size = (dimension.getWidth() / ((double) gridSizeX) < dimension.getHeight() / ((double) gridSizeY) ? dimension.getWidth() / ((double) gridSizeX) : dimension.getHeight() / ((double) gridSizeY));
        return new Dimension ((int)(size * gridSizeX), (int)(size * gridSizeY));
    }

    public boolean[][] getGridValues() {
        return gridValues;
    }

    public void setGridValues(boolean[][] gridValues) {
        this.gridValues = gridValues;
    }

    public void setGridValue(int X, int Y, boolean val){
        gridValues[Y][X] = val;
    }

    public ArrayList<GridButton> getButtons() {
        return buttons;
    }

    public void updateSize (int X, int Y){
        gridSizeX = X;
        gridSizeY = Y;
    }

    public void initialize (){
        removeAll();
        setLayout(new GridLayout(gridSizeY, gridSizeX));
        buttons = new ArrayList<GridButton>(gridSizeX * gridSizeY);
        gridValues = new boolean[gridSizeY][gridSizeX];
        for (int y = 0; y < gridSizeY; y++) {
            for (int x = 0; x < gridSizeX; x++) {
                GridButton b = new GridButton(x, y, this);
                buttons.add(x + y * gridSizeX, b);
                this.add(b);
            }
        }
        revalidate();
        repaint();
    }
}
