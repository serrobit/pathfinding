package pathfinder;

import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseInputAdapter{
    
    private Handler handler;

    public MouseInput( Handler handler) {
        super();
        this.handler = handler;
    }

    public void resetCellHighlights(){
        for (int i = 0; i < handler.objects.size(); i++) {
            Cell cell = (Cell)handler.objects.get(i);
            cell.setHighlighted(false);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        resetCellHighlights();
        Cell cellToHighlight = (Cell)handler.objects.get(getCellIndexAtPoint(x,y));
        cellToHighlight.setHighlighted(true);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
        int x=e.getX();
        int y=e.getY();
        if( x <= 0 || y <= 0 || x>=Game.WIDTH || y>=Game.HEIGHT)
            return;
        Cell selectedCell = (Cell)handler.objects.get(getCellIndexAtPoint(x,y));
        resetCellHighlights();
        if(selectedCell.id != ID.StartPoint && selectedCell.id != ID.EndPoint)
        {
           if(SwingUtilities.isLeftMouseButton(e) ){
               selectedCell.setId(ID.Wall);
           }
           if(SwingUtilities.isRightMouseButton(e) ){
            selectedCell.setId(ID.Unvisited);
        }
            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        Cell selectedCell = (Cell)handler.objects.get(getCellIndexAtPoint(x,y));
        
        if(selectedCell.id != ID.StartPoint && selectedCell.id != ID.EndPoint)
        {
            if(selectedCell.id != ID.Wall)
                selectedCell.setId(ID.Wall);
            else
                selectedCell.setId(ID.Unvisited);
        }
    }
    

    int getCellIndexAtPoint(int x, int y){
        int iX = x / Game.CELL_SIZE;
        int iY = y / Game.CELL_SIZE;
        int index = iY*(Game.WIDTH/Game.CELL_SIZE) + iX;
        return index;
    }
}
