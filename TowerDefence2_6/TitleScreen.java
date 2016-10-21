import greenfoot.*;
import java.awt.*;
import java.util.*;

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */

    MainGame main;
    GreenfootSound soundTrack = new GreenfootSound("\\sounds\\music.mp3");
    GreenfootImage bg;//background;
    SimpleTimer timer = new SimpleTimer();
    ArrayList <Label> titles;
    ArrayList <Label> labels;
    ArrayList <AimingTower> towers = new ArrayList<AimingTower>();
    ArrayList <Enemy> enemies = new ArrayList<Enemy>();
    public int moneyAmount = 0;
    
    public TitleScreen(MainGame m)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1, false); 
        bg = getBackground();
        bg.clear();
        bg.setColor(getColor());
        bg.fill();
        //setBackground(new GreenfootImage("TitleScreen.png")) ;
        
        titles = new ArrayList<Label>();
        setLabels();
        for (int i = 0; i < 10; i ++){
            titles.add(new Label("CASTLE DEFENCE",80));
            shiftColors(titles.get(i));
            addObject(titles.get(i),500,150);
        }
        towers.add(new AimingTower());
        towers.add(new BlastTower());
        towers.add(new FlameTower());
        
        towers.add(new MissileTower());
        towers.add(new FreezeTower());

        
        for (int i = 1; i <= 4;i++){
            enemies.add(new Enemy(i*i*40));
            addObject(enemies.get(i-1), 1000, 230 + 70*i);

        }
        
        
        
        for (int i = 0; i < towers.size()-1;i++){
            towers.get(i).setImage();
            addObject(towers.get(i), 300, 300 + 70*i);

        }
        towers.get(towers.size()-1).setImage();
        addObject(towers.get(towers.size()-1), 500, 405);
        
        main = m;
    }

    private void shiftColors(Label l){
        l.setFillColor(getColor());
        l.setLineColor(getColor());
    }

    private void shiftPosition(Actor a){
        a.setLocation(a.getX()+(int)(10.*Math.random()-5),a.getY()+(int)(10.*Math.random()-5));
    }

    private Color getColor(){
        int r = (int)(200*Math.random()+50);
        int g = (int)(200*Math.random()+50);
        int b = (int)(200*Math.random()+50);

        return new Color(r,g,b);
    }
    
    private void setLabels(){
        labels = new ArrayList<Label>();
        labels.add(new Label("low price,good range", 40));
        labels.add(new Label("multifire with spread", 40));
        labels.add(new Label("rapid fire, wide attack", 40));
        labels.add(new Label("long range homing missiles", 40));
        labels.add(new Label("no damage but slows enemies", 40));
        labels.add(new Label("low health,fast", 40));
        labels.add(new Label("slower enemy with more health", 40));
        labels.add(new Label("slow but tough", 40));
        labels.add(new Label("Juggernaut says what?!", 40));      
        labels.add(new Label("Click to  start",60));
    }

    public void act(){
        if (!soundTrack.isPlaying()){
            soundTrack.play();
            
        }
          titles();
        if (main.score > main.hiScore){
            showText("NEW HIGH SCORE!!!", 500,300);

            main.hiScore=main.score;
        }
        showText("Level: " + main.level, 500,450);
        showText("High Score: " + main.hiScore, 500,350);
        showText("Score: " + main.score, 500,400);
        showText("Money: $" + main.moneyAmount, 500,500);
        if (Greenfoot.mouseClicked(null)){
            soundTrack.stop();
            if (main.player.health <= 0 ){
                main = new MainGame( main.hiScore);
            }
            main.waveTimer.mark();
            Greenfoot.setWorld(main);
        }
    }

    public void titles(){
        //Set the title
        for (Label l : titles){
            shiftColors(l);
            shiftPosition(l);
        }

        //change the color 
        //Set the title
        //spawn
    }

}
