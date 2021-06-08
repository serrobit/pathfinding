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
        return Math.abs(c1.getiX() - c2.getiX()) + Math.abs(c1.getiY() - c2.getiY() );
    }

    public int getIndexByShortestDistance(LinkedList<Cell> checkList,HashMap<Cell,Integer> distanceMap)
    {
        int minDistance = Integer.MAX_VALUE;
        int cellDistance;
        int indexOfMinimum = 0;
        Cell c;
        for (int i = 0; i < checkList.size(); i++)
        {
            c = checkList.get(i);
            cellDistance = distanceMap.get(c);
            if(cellDistance < minDistance)
            {
                minDistance = cellDistance;
                indexOfMinimum = i;
            }
        }
        return indexOfMinimum;
    }

    public void runDijkstra(){
        int startIndex = getFirstIndexOfCellByType(ID.StartPoint);
        int endIndex = getFirstIndexOfCellByType(ID.EndPoint);
        int distanceCheck;
        int initialDistance;
        
        // indexing variables
        int currentIndex = 0;
        int nextIndex;

        int numCells = handler.objects.size();
        HashMap<Cell,Integer> distanceMap = new HashMap<Cell,Integer>();
        HashMap<Cell,Cell> previousMap = new HashMap<Cell,Cell>();
        LinkedList<Cell> checkList = new LinkedList<Cell>();
        Stack<Cell> path = new Stack<Cell>();
        
        Cell currentCell;
        Cell nextCell;

        Cell startCell = (Cell)handler.objects.get(startIndex);
        Cell endCell = (Cell)handler.objects.get(endIndex);
        
        
        // Set up for Dijkstra
        // Set each Cell with a distance of "infinity" from the start
        for(int i = 0; i < numCells; i++){
            
            if( i != startIndex) initialDistance = Integer.MAX_VALUE;
            else initialDistance = 0;

            currentCell = (Cell)handler.objects.get(i);
            distanceMap.put(currentCell, initialDistance);
        }

        checkList.add(startCell); // add the start cell to the check lsit

        while(!checkList.isEmpty())
        {

            currentIndex = getIndexByShortestDistance(checkList, distanceMap);
            currentCell = checkList.remove(currentIndex);
            
            if(currentCell == null || currentCell == endCell) break;


            if(currentCell.getId() == ID.Wall)
            {
                currentCell.setState(CellState.Visited);
                continue;
            }
                
            // check neighbors
            for(int i = -1; i <= 1; i++)
            {
                for (int j = -1; j <= 1; j++) {
                    if( (i + j != 1) && (i + j != -1) ) continue;

                        nextIndex = currentCell.getShiftedIndex(i, j);
                        if(nextIndex > 0 && nextIndex < numCells && nextIndex != -1)
                        {
                            nextCell = (Cell)handler.objects.get(nextIndex);
                            
                            int distanceBetween = distanceBetweenCells(currentCell, nextCell);
                            distanceCheck = distanceBetween + distanceMap.get(currentCell);
                            if( nextCell.getState() != CellState.Visited )
                            {
                                checkList.push(nextCell);
                                nextCell.setState(CellState.Visited);
                                if(distanceCheck < distanceMap.get(nextCell))
                                {
                                    distanceMap.put(nextCell, distanceCheck); // adjust distance to this cell to start
                                    previousMap.put(nextCell,currentCell);  
                                }

                            }
                            
                        }
                    
                    
                    
                }
            }
            
            
        }

        currentCell = endCell;
        while(currentCell != null)
        {
            path.push(currentCell);
            currentCell = previousMap.get(currentCell);
        }

        if(path.peek() == startCell)
        {
            for (Cell cell : path) {
                cell.setState(CellState.OnPath);
            }
        }

    }

}
