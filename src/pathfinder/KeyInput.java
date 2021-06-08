package pathfinder;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Function;

import pathfinder.Algorithms;

public class KeyInput extends KeyAdapter{

    private Handler handler;
    private Algorithms algos;

    public KeyInput(Handler handler) {
        super();
        this.handler = handler;

        this.algos = new Algorithms(handler);
    }


    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        switch (key) {

            // Start Point Moves
            case 65: // A
                moveStartCell(-1, 0);
                break;
            case 83: // S
                moveStartCell(0, -1);
                break;
            case 68: // D
                moveStartCell(1, 0);
                break;
            case 87: // W
                moveStartCell(0, 1);
                break;

            // End Point Moves
            case 37: // Left
                moveEndCell(-1, 0);
                break;
            case 40: // Down
                moveEndCell(0, -1);
                break;
            case 39: // Right
                moveEndCell(1, 0);
                break;
            case 38: // Up
                moveEndCell(0, 1);
                break;

            // Algorithms
            case 49: // Press 1 for Dijkstra
                this.handler.game.setGameState(GameState.ShowingDijkstra);
                break;

            case 48: // Press 0 to not show an algorithm illustration
                this.handler.game.setGameState(GameState.NoShow);
                break;

            case 57: // Press 9 to clear walls
                resetCellWalls();
                break;
            default:
                break;
        }

    }

    public void runAlgoBasedOnState(){
        resetCellStates();
        if(this.handler.game.getGameState() == GameState.ShowingDijkstra) algos.runDijkstra();

    }

    public void resetCellStates(){
        for (int i = 0; i < handler.objects.size(); i++) {
            Cell cell = (Cell)handler.objects.get(i);
            cell.setState(CellState.Unvisited);
        }
    }

    public void resetCellWalls(){
        for (int i = 0; i < handler.objects.size(); i++) {
            Cell cell = (Cell)handler.objects.get(i);
            if(cell.getId() == ID.Wall) cell.setId(ID.Open);
        }
    }

    // should make static somewhere
    public int getFirstIndexOfCellByType(ID id){
        for(int i = 0; i < handler.objects.size(); i++)
        {
            GameObject tempGameObject = handler.objects.get(i);
            if(tempGameObject.id == id) return i;

        }
        return -1;
    }
    public void moveStartCell(int dx, int dy)
    {
        Cell startCell = (Cell)handler.objects.get(getFirstIndexOfCellByType(ID.StartPoint)); 
        moveCell(dx,dy,startCell);
    }

    public void moveEndCell(int dx, int dy)
    {
        Cell endCell = (Cell)handler.objects.get(getFirstIndexOfCellByType(ID.EndPoint));
        moveCell(dx,dy,endCell);
    }

    public void moveCell(int dx, int dy, Cell cell)
    {

        int newIndex;
        ID tempID = cell.id;
        Cell cellAtNewIndex;
        
        newIndex = cell.getShiftedIndex(dx, dy);
        if(newIndex == -1) return; // ignore invalid moves

        cellAtNewIndex = (Cell)handler.objects.get(newIndex);
        if(cellAtNewIndex.id == ID.StartPoint || cellAtNewIndex.id == ID.EndPoint || cellAtNewIndex.id == ID.Wall)
            return;

        cell.setId(cellAtNewIndex.id);
        cellAtNewIndex.setId(tempID);
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        runAlgoBasedOnState();
    }
    
}
