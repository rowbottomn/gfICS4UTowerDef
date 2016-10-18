import greenfoot.*;
import java.util.*;
import java.io.*;

/**
 * Write a description of class MainGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class MainGame extends World
{
    public static GreenfootSound largeExp = new GreenfootSound("explosion_large.mp3");
    public static GreenfootSound medExp = new GreenfootSound("explosion_medium.mp3");
    public static GreenfootSound smallExp = new GreenfootSound("explosion_small.mp3");
    public static GreenfootSound littlePop = new GreenfootSound("little_pop.mp3");
    FileReader fileReader;

    ArrayList <Waypoint> path;
    Home player;
    int level = 1;
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

    int frameCount;
    //Testing of classes
    AimingTower startTower;
    UIMenuBackground menuBackground;
    UIButton button1;
    /**
     * Constructor for objects of class MainGame.
     * 
     */
    public MainGame()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1, false); 
        //startTower = new AimingTower();
        //addObject(startTower, 120,90);
        //frameCount = 0;
        levelLabel = new Label("Level "+level,40);
        addObject(levelLabel,200,50);



        //constructor calls
        menuBackground = new UIMenuBackground();
        button1 = new UIButton();
        spawnTimer = new SimpleTimer();
        mouseTimer = new SimpleTimer();
        setPaintOrder(UIMenuBackground.class,Bullet.class,Enemy.class);
        setActOrder(Tower.class,Bullet.class,Enemy.class);

        
        addObject(button1, 960,100);
        addObject(new UIButton(), 960,200);
        addObject(new UIButton(), 960,300);
        addObject(new UIButton(), 960,400);
        
        setupGameScreen();
        setupUI();
    
    }
    
    private void setupGameScreen(){
        //set the path
        //setPath(level);
        setPath();
        
       //setup the player base
        player = new Home(100);
        addObject(player, getWidth()-160,getHeight()-87);
        //call the constructors of the
        
    }
    
    private void setupUI(){
       //setup the side interface menu
        addObject(menuBackground, 945,300);
    }

    public void act(){

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
        mouse = Greenfoot.getMouseInfo();
        spawnTower();
        
    } 
    
    private void spawnEnemy(){
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
    
    private void spawnTower(){
        if (mouse != null){
            mX = mouse.getX();
            mY = mouse.getY();
            //handle the tower selection
           //     UIButton buttonActive = mouse.getActor();
           //     buttonActive = 
                        
        Tower test;
            
            if (mX < 840 && mouseTimer.millisElapsed()> 1000){
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
    }
    
    //setpath is overloaded below to pull from files
    private void setPath(){
        path = new ArrayList <Waypoint>();
        
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
        path.add(new Waypoint(getWidth()-590,getHeight()-300));
        path.add(new Waypoint(getWidth()-590,getHeight()-360));
        path.add(new Waypoint(getWidth()-590,getHeight()-420));
        path.add(new Waypoint(getWidth()-590,getHeight()-480));
        path.add(new Waypoint(getWidth()-590,getHeight()-540));
        path.add(new Waypoint(getWidth()-530,getHeight()-540));
        path.add(new Waypoint(getWidth()-470,getHeight()-540));
        path.add(new Waypoint(getWidth()-410,getHeight()-540));
        path.add(new Waypoint(getWidth()-350,getHeight()-540));
        path.add(new Waypoint(getWidth()-290,getHeight()-540));
        path.add(new Waypoint(getWidth()-290,getHeight()-480));
        path.add(new Waypoint(getWidth()-290,getHeight()-420));
        path.add(new Waypoint(getWidth()-350,getHeight()-420));
        path.add(new Waypoint(getWidth()-410,getHeight()-420));
        path.add(new Waypoint(getWidth()-410,getHeight()-360));
        path.add(new Waypoint(getWidth()-410,getHeight()-300));
        path.add(new Waypoint(getWidth()-350,getHeight()-300));
        path.add(new Waypoint(getWidth()-350,getHeight()-240));
        path.add(new Waypoint(getWidth()-350,getHeight()-180));
        path.add(new Waypoint(getWidth()-290,getHeight()-180));
        path.add(new Waypoint(getWidth()-290,getHeight()-120));
        path.add(new Waypoint(getWidth()-290,getHeight()-60));
        path.add(new Waypoint(getWidth()-230,getHeight()-60));              

        for (Waypoint w: path){
            addObject(w, w.x,w.y);
        }
    }    
    
    public void setPath(int level){
       /* 
       path = new ArrayList <Waypoint>(); 
        if (path!=null){
            for (Waypoint w: path){
                removeObject(w);
            }
            path.clear();
        }
        try{
            InputStream is = getClass().getClassLoader().getResourceAsStream("level"+level+".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String s;
            while((s = br.readLine()) != null) { //
                String [] pos = s.split(",");
                path.add(new Waypoint(Integer.parseInt(pos[0]),Integer.parseInt(pos[1])));
            } 
        }
        catch(Exception e){
            System.out.println(System.getProperty("user.dir"));
        }
        // fileReader.close();
        for (Waypoint w: path){
            addObject(w, w.x,w.y);
        }*/
    }

}
