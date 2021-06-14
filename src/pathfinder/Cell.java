package pathfinder;

import java.awt.*;

public class Cell extends GameObject{

    private int size;
    private boolean highlighted;
    private int iX; // grid coordinates
    private int iY; // grid coordinates
    private CellState state;

    public Cell(ID id, int size, int iX, int iY) {
        super(iX*size, iY*size, id);
        this.setSize(size);
        this.setiX(iX);
        this.setiY(iY);
        this.setHighlighted(false);
        this.setState(CellState.Unvisited);
    }


    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }


    public boolean isHighlighted() {
        return highlighted;
    }


    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }


    public int getiY() {
        return iY;
    }

    public void setiY(int iY) {
        this.iY = iY;
    }

    public int getiX() {
        return iX;
    }

    public void setiX(int iX) {
        this.iX = iX;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getShiftedIndex(int dx, int dy)
    {
        int gridWidth = Game.WIDTH / this.size;
        int gridHeight = Game.HEIGHT / this.size;

        if(this.iY - dy < 0 || this.iY - dy >= gridHeight || this.iX + dx < 0 || this.iX + dx >= gridWidth) return -1;
        return (this.iY - dy)*gridWidth + (this.iX + dx);
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(Graphics g) {
        switch (this.id) {
            case StartPoint:
                g.setColor(Color.green);
                break;
            case EndPoint:
                g.setColor(Color.red);
                break;
            case Wall:
                g.setColor(Color.blue);
                break;       
            default:
                g.setColor(Color.white);
                break;
        }

        if(this.state == CellState.Visited && (this.id != ID.StartPoint && this.id != ID.EndPoint && this.id != ID.Wall))
        {
            g.setColor(Color.lightGray);
        }

        if(this.state == CellState.OnPath && (this.id != ID.StartPoint && this.id != ID.EndPoint && this.id != ID.Wall))
        {
            g.setColor(Color.yellow);
        }

        if( this.highlighted ) g.setColor(Color.DARK_GRAY);

        g.fillRect(this.x, this.y, this.size, this.size);
        

        g.setColor(Color.black);
        
        //String index = String.format("%d", (Game.WIDTH/this.size)*this.iY+this.iX);
        //g.drawString(index, this.x + this.size/2, this.y + this.size/2);
        
        g.drawRect(this.x, this.y, this.size, this.size);

    }
    
}
