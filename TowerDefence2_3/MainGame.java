import greenfoot.*;
import java.util.*;

/**
 * Write a description of class MainGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainGame extends World
{
    ArrayList <Waypoint> path;
    int level = 0;
    int pathSize = 60;
    int numEnemies = 10;
    int enemyCount = 0;
    //timers
    SimpleTimer spawnTimer;
    SimpleTimer mouseTimer;
    Label levelLabel;
    MouseInfo mouse;
    int mX;
    int mY;
     ArrayList <Waypoint> enemyPath;
     int frameCount;
    AimingTower startTower;
    /**
     * Constructor for objects of class MainGame.
     * 
     */
    public MainGame()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, false); 
        //startTower = new AimingTower();
        //addObject(startTower, 120,90);
        //frameCount = 0;
        levelLabel = new Label("Level "+level,40);
        addObject(levelLabel,200,50);
        path = new ArrayList <Waypoint>();
        setPath(level);
        spawnTimer = new SimpleTimer();
        mouseTimer = new SimpleTimer();
        setPaintOrder(Bullet.class,Enemy.class);
        setActOrder(Tower.class,Bullet.class,Enemy.class);

    }
    
    public void act(){
        mouse = Greenfoot.getMouseInfo();
        if (mouse != null){
            mX = mouse.getX();
            mY = mouse.getY();
            Tower test;
            if (mouseTimer.millisElapsed()> 1000){
                if (mouse.getButton()==1){
                    test = new AimingTower();
                }
                else if (mouse.getButton()>1){
                    test = new BlastTower();
                }
                else{
                    return;
                }
                addObject(test, mX, mY);
                if (test.canSpawn()){
                    mouseTimer.mark();
                }
                else {
                    removeObject(test);
                }
            }
        }
        if (enemyCount < numEnemies && spawnTimer.millisElapsed() > 3000/(1+level)){
            addObject(new Enemy(path), path.get(0).x -pathSize,path.get(0).y); 
            enemyCount++;
            spawnTimer.mark();
        }
        else if (enemyCount == numEnemies && spawnTimer.millisElapsed() > 5000){
            level ++;
            levelLabel = new Label("Level "+level,40);
            enemyCount = 0;
            numEnemies += level;
            
        }
        
        

    }    
    public void setPath(int level){
        
        //these waypoints really should be based off the road tiles image size
        path.add(new Waypoint(30,60));
        path.add(new Waypoint(90,60));
        path.add(new Waypoint(90,120));
        path.add(new Waypoint(90,180));
        path.add(new Waypoint(150,180));
        path.add(new Waypoint(150,240));
        path.add(new Waypoint(150,300));
        path.add(new Waypoint(210,300));
        path.add(new Waypoint(210,360));
        path.add(new Waypoint(210,420));
        path.add(new Waypoint(150,420));
        path.add(new Waypoint(90,420));
        path.add(new Waypoint(90,480));
        path.add(new Waypoint(90,540));
        path.add(new Waypoint(150,540));
        path.add(new Waypoint(210,540));
        path.add(new Waypoint(270,540));
        path.add(new Waypoint(330,540));
        path.add(new Waypoint(390,540));
        path.add(new Waypoint(390,480));
        path.add(new Waypoint(390,420));
        path.add(new Waypoint(390,360));
        path.add(new Waypoint(390,300));
        path.add(new Waypoint(getWidth()-390,getHeight()-300));
        path.add(new Waypoint(getWidth()-390,getHeight()-360));
        path.add(new Waypoint(getWidth()-390,getHeight()-420));
        path.add(new Waypoint(getWidth()-390,getHeight()-480));
        path.add(new Waypoint(getWidth()-390,getHeight()-540));
        path.add(new Waypoint(getWidth()-330,getHeight()-540));
        path.add(new Waypoint(getWidth()-270,getHeight()-540));
        path.add(new Waypoint(getWidth()-210,getHeight()-540));
        path.add(new Waypoint(getWidth()-150,getHeight()-540));
        path.add(new Waypoint(getWidth()-90,getHeight()-540));
        path.add(new Waypoint(getWidth()-90,getHeight()-480));
        path.add(new Waypoint(getWidth()-90,getHeight()-420));
        path.add(new Waypoint(getWidth()-150,getHeight()-420));
        path.add(new Waypoint(getWidth()-210,getHeight()-420));
        path.add(new Waypoint(getWidth()-210,getHeight()-360));
        path.add(new Waypoint(getWidth()-210,getHeight()-300));
        path.add(new Waypoint(getWidth()-150,getHeight()-300));
        path.add(new Waypoint(getWidth()-150,getHeight()-240));
        path.add(new Waypoint(getWidth()-150,getHeight()-180));
        path.add(new Waypoint(getWidth()-90,getHeight()-180));
        path.add(new Waypoint(getWidth()-90,getHeight()-120));
        path.add(new Waypoint(getWidth()-90,getHeight()-60));
        path.add(new Waypoint(getWidth()-30,getHeight()-60));              
        
        for (Waypoint w: path){
            addObject(w, w.x,w.y);
        }
    }

    
}
