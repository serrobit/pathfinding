package pathfinder;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

// 
// Resource: https://www.youtube.com/watch?v=1gir2R7G9ws&list=PLWms45O3n--6TvZmtFHaCWRZwEqnz2MHa&index=1
//
public class Game extends Canvas implements Runnable{
    
    public static final int WIDTH=800, HEIGHT = 600, CELL_SIZE = 20;

    private Thread thread;
    private boolean running = false;
    private Handler handler;

    public Game(){
        handler = new Handler(this);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput(handler));
        this.addMouseMotionListener(new MouseInput(handler));
        new Window(WIDTH, HEIGHT + HEIGHT/4, "Pathfinding", this, new Menu(WIDTH, HEIGHT / 4));

        for (int i = 0;i < HEIGHT / CELL_SIZE; i++) {
            for (int j = 0; j < WIDTH / CELL_SIZE; j++) {
                ID id = ID.Open;
                if(i == (HEIGHT / CELL_SIZE) / 2 && (j == (WIDTH / CELL_SIZE) / 4 || j == 3 * (WIDTH / CELL_SIZE) / 4))
                {
                    if(j == (WIDTH / CELL_SIZE) / 4)
                        id = ID.StartPoint;
                    else
                        id = ID.EndPoint;
                }
                handler.addObject(new Cell(id,CELL_SIZE, j, i));
            }
        }
        
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.print("\rFPS: "+frames);
                frames = 0;
            }
        }
        stop();
    }
    private void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    private void tick() {
        handler.tick();
    }

    public static void main(String[] args) {
        
        new Game();
    }
}
