package pathfinder;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel{
    
    public Menu(int width, int height){
        JLabel menuLabel;

        setPreferredSize(new Dimension(width,height));
        setMaximumSize(new Dimension(width,height));
        setMinimumSize(new Dimension(width,height));
        setBorder(BorderFactory.createTitledBorder("Controls: "));
        String menuText = "<html>\n"+
                        "<ul>\n" +
                        "<li><font color=green>WASD Keys: Move Start Point</font>\n" +
                        "<li><font color=red>Arrow Keys: Move End Point</font>\n" +
                        "<li><font color=blue>Left Mouse: Draw Walls</font>\n" +
                        "<li>Right Mouse: Remove Walls\n" +
                        "</ul>\n" +
                        "<h4>Press 0 to turn off algorithm illustrations.\n"+
                        "<h4>Press 1 to Illustrate Dijkstra's Algorithm\n" +
                        "<h4>Press 2 to Illustrate A* Algorithm\n" +
                        "<h4>Press 8 to generate a depth-first random maze\n" +
                        "<h4>Press 9 to clear all walls\n";
         
        menuLabel = new JLabel(menuText);
        menuLabel.setOpaque(true);
        menuLabel.setHorizontalTextPosition(JLabel.LEFT);
        add(menuLabel);
    }
}
