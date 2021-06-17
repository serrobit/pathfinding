package pathfinder;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas
{
    
    public Window(int width, int height, String title, Game game, Menu menu)
    {
        JFrame frame = new JFrame(title);

        frame.getContentPane().setPreferredSize(new Dimension(width,height));
        frame.getContentPane().setMaximumSize(new Dimension(width,height));
        frame.getContentPane().setMinimumSize(new Dimension(width,height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(menu, BorderLayout.PAGE_END);
        frame.add(game,BorderLayout.CENTER);
        
        frame.pack();
        frame.setVisible(true);
        game.setFocusable(true);
        game.start();
    }
    
    
}
