package pathfinder;

import java.awt.Graphics;

public abstract class GameObject {
    
    protected int x, y; // coordinates in screen space
    protected ID id;
    protected boolean changedState;

    public GameObject(int x, int y, ID id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public ID getId() {
        return id;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setId(ID id) {
        this.id = id;
    }
}
