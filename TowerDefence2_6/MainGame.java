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
    FileReader fileReader;
    TitleScreen title;
    ArrayList <Waypoint> path;
    int level = 1;
    int moneyAmount = 110;
    int hiScore = 0;
    int score = 0;
    int pathSize = 60;
    int health = 100;
    
    Home player = new Home(health);
    int numEnemies = 10;
    int enemyCount = 0;
    //timers
    SimpleTimer spawnTimer;
    SimpleTimer waveTimer;
    SimpleTimer mouseTimer;
    Label levelLabel;
    Label moneyLabel;
    MouseInfo mouse;    
    int mX;
    int mY;
    boolean ebool = false;
    boolean lbool = false;
    boolean obool = false;
    boolean nbool = false;
    
    int frameCount;
    //Testing of classes
    AimingTower startTower;
    UIMenuBackground menuBackground;
    ArrayList <AimingTower> towers; //used to get images for buttons
    ArrayList <UIBaseTower> buttons;
    AimingTower tower;
    UIButton button1;
    UICursor cursor;
    /**
     * Constructor for objects of class MainGame.
     * 
     */
    public MainGame()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1, false); 
        title = new TitleScreen(this);
        
        //startTower = new AimingTower();
        //addObject(startTower, 120,90);
        //frameCount = 0;
        levelLabel = new Label("Level "+level,40);
        addObject(levelLabel,200,50);
        moneyLabel = new Label("$ "+moneyAmount,40);
        addObject(moneyLabel,950,50);

        //constructor calls
        menuBackground = new UIMenuBackground();
        //button1 = new UIButton();
        spawnTimer = new SimpleTimer();
        waveTimer = new SimpleTimer();
        mouseTimer = new SimpleTimer();
        setPaintOrder(Label.class,UIMenuBackground.class,Bullet.class,Enemy.class, HealthBar.class, Money.class,Tower.class, Range.class);
        setActOrder(Tower.class,Bullet.class,Enemy.class);

        //addObject(button1, 960,100);
        //addObject(new UIButton(), 960,200);
        //addObject(new UIButton(), 960,300);
        //addObject(new UIButton(), 960,400);

        setupGameScreen();
        setupUI();
        Greenfoot.setWorld(title);

    }
    
    public MainGame(int hi_score)
    {    
        this();
        hiScore = hi_score;

    }


    private void setupGameScreen(){
        //set the path
        setPath(1);
        //setPath(true);
        // setPath(level);

        //setup the player base
        player = new Home(100);
        addObject(player, getWidth()-160,getHeight()-87);
        //call the constructors of the

    }

    private void setupUI(){
        //setup the side interface menu
        addObject(menuBackground, 945,300);
     
        towers = new ArrayList <AimingTower> (); //used to get images for buttons
        towers.add(new AimingTower());
        towers.add(new BlastTower());
        towers.add(new FlameTower());
        towers.add(new FreezeTower());
        towers.add(new MissileTower());
        buttons = new ArrayList <UIBaseTower>();
        for (int i = 0; i < towers.size(); i++){
            buttons.add(new UIBaseTower(towers.get(i)));
            addObject(buttons.get(i), 950, i*100+100);
            showText("$"+towers.get(i).cost, 950,i*100+150);
        }
        buttons.get(0).active = true;
        cursor = new UICursor(this,towers.get(0).img.getWidth());
        addObject(cursor, -1000,-1000);
    }

    public void act(){
        int waveDelay = 6;
        if (Greenfoot.isKeyDown("e")){ebool = true;}
        if (Greenfoot.isKeyDown("l")){lbool = true;}
        if (Greenfoot.isKeyDown("o")){obool = true;}
        if (Greenfoot.isKeyDown("n")){nbool = true;}
        if (ebool&&lbool&&obool&&nbool&&spawnTimer.millisElapsed() > 600){
            moneyAmount+= 1000;
            spawnTimer.mark();
            ebool= false;
            lbool = false;
            obool = false;
            nbool = false;
    }
        if (waveTimer.millisElapsed() < waveDelay*1000){
            showText("Get Ready" + (waveDelay-waveTimer.millisElapsed()/1000), 500,400);
        }
        else{
                        showText("", 500,400);
        if (enemyCount < numEnemies && spawnTimer.millisElapsed() > 3000/(1+0.1*level)){
            addObject(new Enemy(path, level), path.get(0).x -pathSize,path.get(0).y); 
            enemyCount++;
            spawnTimer.mark();
        }
        else if (enemyCount == numEnemies && spawnTimer.millisElapsed() > 5000){
            level ++;
            levelLabel.setValue("Level "+level);
            enemyCount = 0;
            numEnemies += 2;
            waveTimer.mark();

        }
    }
        mouse = Greenfoot.getMouseInfo();
        updateUI();

        spawnTower();
        checkPlayerHealth();
    } 

    private void checkPlayerHealth(){
                if (player.health <=0){
            title.setBackground(title.endScreen);
            Greenfoot.setWorld(title);
        }
        else{
            showText("HP: "+player.health, player.getX(), player.getY()-65);
        }

    }
    
    private void updateUI(){
        moneyLabel.setValue("$"+moneyAmount);
    }

    private void spawnEnemy(){
        if (enemyCount < numEnemies && spawnTimer.millisElapsed() > 3000./(1.+0.03*level)){
            addObject(new Enemy(path, level), path.get(0).x -pathSize,path.get(0).y); 
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
           // mX = mouse.getX();
           // mY = mouse.getY();
            //handle the tower selection
            //     UIButton buttonActive = mouse.getActor();
            //     buttonActive = 

            int activeIndex = 0;
            
           // if (mouse.getX()>=getWidth()-menuBackground.getImage().getWidth()){
            for (int i = 0; i < towers.size(); i++){
               if (buttons.get(i).active){
                  activeIndex = i; 
               }
            }
                tower = towers.get(activeIndex).clone();
           // }
            cursor.update(tower, mouse, mouseTimer);
                //mouseTimer.mark();
         //       if (mouse.getButton() == 1){

           //         if ((test != null)&&cursor.canSpawn){//handles if nothing is highlighted
             //           moneyAmount -= test.cost;
               //         addObject(test,(int) mX,mY);
                      //  mouseTimer.mark();
                        
                   // }

              //  }
               // else if (mouse.getButton()>1){
                    //test = new BlastTower();
                    //raze 
                    //tower = (Tower)mouse.getActor(); 
               // }
            
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
            //System.out.println(System.getProperty("user.dir"));
        }
        // fileReader.close();
        for (Waypoint w: path){
            addObject(w, w.x,w.y);
        }
    }

    public void setPath(boolean madePath){
        int x = 0;
        int y = 60;
        int i = 0;
        int j = 1;
        int k = 0;
        path = new ArrayList <Waypoint>();

        for (i = 0; i < 6; i ++){
            path.add(new Waypoint((i*pathSize)-30,(j )*pathSize)); 
        }
        for (j=1; j < 5; j++){
            path.add(new Waypoint(((i)*pathSize)-30,(j)*pathSize));    
        }
        for (i = 6; i < 10; i ++){
            path.add(new Waypoint((i*pathSize)-30,(j )*pathSize)); 
        }
        for (j=5; j > 1; j--){
            path.add(new Waypoint(((i)*pathSize)-30,(j)*pathSize));    
        }    
        for (i = 10; i < 13; i ++){
            path.add(new Waypoint((i*pathSize)-30,(j )*pathSize)); 
        }
        for (j=1; j < 7; j++){
            path.add(new Waypoint(((i)*pathSize)-30,(j)*pathSize));    
        }
        for (i = 13; i > 3; i --){
            path.add(new Waypoint((i*pathSize)-30,(j )*pathSize)); 
        }
        for (j=7; j < 9; j++){
            path.add(new Waypoint(((i)*pathSize)-30,(j)*pathSize));    
        }
        for (i = 3; i < 15; i ++){
            path.add(new Waypoint((i*pathSize)-30,(j )*pathSize)); 
        }  
        for (Waypoint w: path){
            addObject(w, w.x,w.y);
        }
    }

}
