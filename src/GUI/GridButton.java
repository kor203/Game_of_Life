package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridButton extends JButton implements ActionListener {
    boolean alive = false;
    int X;
    int Y;
    Grid grid;

    public GridButton(int X, int Y, Grid grid) {
        super();
        this.X = X;
        this.Y = Y;
        this.grid = grid;
        this.setBackground(Color.WHITE);
        this.addActionListener(this);
        this.setBorder(new LineBorder(Color.BLACK, 1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        alive = !alive;
        setGridValue();
        setColor();
    }

    private void setGridValue() {
        grid.setGridValue(X, Y, alive);
    }

    private void setColor() {
        if (alive)
            this.setBackground(Color.BLACK);
        else
            this.setBackground(Color.WHITE);
    }

    public void setAlive(Boolean value){
        alive = value;
        setColor();
    }
}
