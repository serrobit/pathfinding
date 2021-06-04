package pathfinder;

import java.awt.*;

public class Cell extends GameObject{

    private int size;
    private boolean highlighted;
    private int iX; // grid coordinates
    private int iY; // grid coordinates

    public Cell(ID id, int size, int iX, int iY) {
        super(iX*size, iY*size, id);
        this.setSize(size);
        this.setiX(iX);
        this.setiY(iY);
        this.setHighlighted(false);
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

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(Graphics g) {
        switch (this.id) {
            case Visited:
                g.setColor(Color.orange);
                break;
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

        if( this.highlighted ) g.setColor(Color.DARK_GRAY);

        g.fillRect(this.x, this.y, size, size);


        g.setColor(Color.black);
        g.drawRect(this.x, this.y, size, size);

    }
    
}
