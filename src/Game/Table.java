package Game;

import javax.swing.*;
import java.awt.*;

public class Table {
    static void displayJFrame()
    {
        JFrame jframe = new JFrame("Chesticuffs Test");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set the JFrame size and location, and make it visible
        jframe.setPreferredSize(new Dimension(400, 300));
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);

    }

    public static void main(String[] args) {
        displayJFrame();
    }
}

