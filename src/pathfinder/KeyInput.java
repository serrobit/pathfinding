package pathfinder;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

    private Handler handler;

    public KeyInput(Handler handler) {
        super();
        this.handler = handler;
    }


    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        switch (key) {

            // Start Point Moves
            case 65: // A
                this.handler.moveStartCell(-1, 0);
                break;
            case 83: // S
                this.handler.moveStartCell(0, -1);
                break;
            case 68: // D
                this.handler.moveStartCell(1, 0);
                break;
            case 87: // W
                this.handler.moveStartCell(0, 1);
                break;

            // End Point Moves
            case 37: // Left
                this.handler.moveEndCell(-1, 0);
                break;
            case 40: // Down
                this.handler.moveEndCell(0, -1);
                break;
            case 39: // Right
                this.handler.moveEndCell(1, 0);
                break;
            case 38: // Up
                this.handler.moveEndCell(0, 1);
                break;

            default:
                break;
        }

    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        // Algorithms
        switch(key)
        {
            case 49: // Press 1 for Dijkstra
                this.handler.game.setGameState(GameState.ShowingDijkstra);
                break;

            case 50: // Press 2 for A*
                this.handler.game.setGameState(GameState.ShowingAStar);
                break;

            case 48: // Press 0 to not show an algorithm illustration
                this.handler.game.setGameState(GameState.NoShow);
                break;

            case 56: // Press 8 to draw maze
                this.handler.algos.runDepthFirstRandomMaze();
                break;

            case 57: // Press 9 to clear walls
                this.handler.resetCellWalls();
                break;
                
            default:
                break;
        }
        this.handler.runAlgoBasedOnState();
    }
    
}
