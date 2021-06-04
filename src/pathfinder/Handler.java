package pathfinder;

import java.util.LinkedList;
import java.awt.Graphics;

public class Handler {
    protected Game game;

    public Handler(Game game) {
        this.game = game;
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
}
