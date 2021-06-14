package pathfinder;

import java.util.LinkedList;
import java.awt.Graphics;

public class Handler {
    protected Game game;
    protected Algorithms algos;

    public Handler(Game game) {
        this.game = game;
        this.algos = new Algorithms(this);
    }
    LinkedList<GameObject> objects = new LinkedList<GameObject>();

    public void tick(){
        for(int i =0; i < objects.size(); i++)
        {
            GameObject tempObject = objects.get(i);
            tempObject.tick();

        }
    }

    public void render(Graphics g){
        for(int i =0; i < objects.size(); i++)
        {
            GameObject tempObject = objects.get(i);
            tempObject.render(g);

        }
    }

    public void addObject(GameObject object){
        this.objects.add(object);
    }

    public void removeObject(GameObject object){
        this.objects.remove(object);
    }

    public void runAlgoBasedOnState(){
        resetCellStates();
        if(this.game.getGameState() == GameState.ShowingDijkstra) this.algos.runDijkstra();

    }

    public void resetCellStates(){
        for (int i = 0; i < this.objects.size(); i++) {
            Cell cell = (Cell)this.objects.get(i);
            cell.setState(CellState.Unvisited);
        }
    }

    public void resetCellWalls(){
        for (int i = 0; i < this.objects.size(); i++) {
            Cell cell = (Cell)this.objects.get(i);
            if(cell.getId() == ID.Wall) cell.setId(ID.Open);
        }
    }

    // should make static somewhere
    public int getFirstIndexOfCellByType(ID id){
        for(int i = 0; i < this.objects.size(); i++)
        {
            GameObject tempGameObject = this.objects.get(i);
            if(tempGameObject.id == id) return i;

        }
        return -1;
    }
    public void moveStartCell(int dx, int dy)
    {
        Cell startCell = (Cell)this.objects.get(getFirstIndexOfCellByType(ID.StartPoint)); 
        moveCell(dx,dy,startCell);
    }

    public void moveEndCell(int dx, int dy)
    {
        Cell endCell = (Cell)this.objects.get(getFirstIndexOfCellByType(ID.EndPoint));
        moveCell(dx,dy,endCell);
    }

    public void moveCell(int dx, int dy, Cell cell)
    {

        int newIndex;
        ID tempID = cell.id;
        Cell cellAtNewIndex;
        
        newIndex = cell.getShiftedIndex(dx, dy);
        if(newIndex == -1) return; // ignore invalid moves

        cellAtNewIndex = (Cell)this.objects.get(newIndex);
        if(cellAtNewIndex.id == ID.StartPoint || cellAtNewIndex.id == ID.EndPoint || cellAtNewIndex.id == ID.Wall)
            return;

        cell.setId(cellAtNewIndex.id);
        cellAtNewIndex.setId(tempID);
    }
}
