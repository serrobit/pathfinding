package pathfinder;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Algorithms {
    protected Handler handler;

    public Algorithms(Handler handler) {
        this.handler = handler;
    }

    static class CellComparator implements Comparator<Cell> {
        private HashMap<Cell,Integer> distanceMap;

        public CellComparator(HashMap<Cell,Integer> distanceMap)
        {
            this.distanceMap = distanceMap;
        }

        @Override
        public int compare(Cell c1, Cell c2) {
            return distanceMap.get(c1) >= distanceMap.get(c2) ? 1 : -1;
        }
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

    public int distanceToStart(Cell c)
    {
        Cell start = (Cell)handler.objects.get(getFirstIndexOfCellByType(ID.StartPoint));
        return distanceBetweenCells(start, c);
    }

    public int distanceBetweenCells(Cell c1, Cell c2)
    {
        return Math.abs(c1.getiX() - c2.getiX()) + Math.abs(c1.getiY() + c2.getiY() );
    }

    public int getShiftedIndex(int dx, int dy, Cell cell)
    {
        int size = cell.getSize();
        int x = cell.getiX();
        int y = cell.getiY();
        int gridWidth = Game.WIDTH / size;
        return (y - dy)*gridWidth + (x + dx);
    }

    public void runDijkstra(){
        int startIndex = getFirstIndexOfCellByType(ID.StartPoint);
        int endIndex = getFirstIndexOfCellByType(ID.EndPoint);
        int minDist = Integer.MAX_VALUE;
        int currentDistance;
        
        // index variables
        int currentIndex = startIndex;
        int nextIndex;

        int numCells = handler.objects.size();
        boolean done = false;
        HashMap<Cell,Integer> distanceMap = new HashMap<Cell,Integer>();
        LinkedList<Cell> checkList = new LinkedList<Cell>();
        Stack<Cell> path = new Stack<Cell>();
        
        Cell currentCell;
        Cell nextCell;
        
        
        for(int i = 0; i < numCells; i++){
            int distance;
            if( i != startIndex)
                distance = Integer.MAX_VALUE;
            else distance = 0;
            currentCell = (Cell)handler.objects.get(i);
            distanceMap.put(currentCell, distance);
            checkList.add(currentCell);
        }
        checkList.add((Cell)handler.objects.get(startIndex));
        while(!checkList.isEmpty())
        {
            minDist = Integer.MAX_VALUE;
            currentIndex = 0;
            for (int i = 0; i < checkList.size(); i++)
            {
                currentCell = checkList.get(i);
                currentDistance = distanceMap.get(currentCell);
                if(currentDistance < minDist)
                {
                    minDist = currentDistance;
                    currentIndex = i;
                }
            }

            currentCell = checkList.remove(currentIndex);
            if(currentCell == null || getShiftedIndex(0, 0, currentCell) == endIndex)
            {
                done = true;
                break;
            }
            
            // check neighbors
            for(int i = -1; i <= 1; i++)
            {
                for (int j = -1; j <= 1; j++) {
                    if( i == 0 && j == 0 ) continue;
                    
                        nextIndex = getShiftedIndex(i, j, currentCell);
                        if(nextIndex > 0 && nextIndex < numCells)
                        {
                            nextCell = (Cell)handler.objects.get(nextIndex);
                            int distanceBetween = distanceBetweenCells(currentCell, nextCell);
                            if( nextCell.getId() != ID.Wall && nextCell.getState() != CellState.Visited )
                            {
                                distanceMap.put(nextCell, distanceToStart(nextCell)); // adjust distance to this cell to start
                                if(distanceBetween + distanceMap.get(currentCell) < distanceMap.get(nextCell))
                                {
                                    path.push(nextCell);
                                    nextCell.setState(CellState.OnPath);
                                    continue;
                                }
                                nextCell.setState(CellState.Visited);
                                // checkList.add(nextCell);
                            }
                        }
                    
                    
                    
                }
            }
            
        }

        for (int i = 0; i < path.size(); i++) {
            path.get(i).setState(CellState.OnPath);
        }
    }

}
