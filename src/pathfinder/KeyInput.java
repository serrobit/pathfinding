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
            default:
                break;
        }

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
        int size = cell.getSize();
        int x = cell.getiX();
        int y = cell.getiY();
        int gridWidth = Game.WIDTH / size;
        int gridHeight = Game.HEIGHT / size;
        int newIndex;
        ID tempID = cell.id;
        Cell cellAtNewIndex;

        if(!(x + dx >= 0 && x + dx <= gridWidth - 1 && y - dy >= 0 && y - dy <= gridHeight - 1))
            return;
        
        newIndex = getShiftedIndex(dx, dy, cell);
        cellAtNewIndex = (Cell)handler.objects.get(newIndex);
        if(cellAtNewIndex.id == ID.StartPoint || cellAtNewIndex.id == ID.EndPoint || cellAtNewIndex.id == ID.Wall)
            return;

        cell.setId(cellAtNewIndex.id);
        cellAtNewIndex.setId(tempID);
    }

    public int getFirstIndexOfCellByType(ID id){
        for(int i = 0; i < handler.objects.size(); i++)
        {
            GameObject tempGameObject = handler.objects.get(i);
            if(tempGameObject.id == id)
            {
                return i;
            }
        }
        return -1;
    }


    public int getShiftedIndex(int dx, int dy, Cell cell)
    {
        int size = cell.getSize();
        int x = cell.getiX();
        int y = cell.getiY();
        int gridWidth = Game.WIDTH / size;
        return (y - dy)*gridWidth + (x + dx);
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

    }
    
}
