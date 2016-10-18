import greenfoot.*;

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
    GreenfootImage endScreen = new GreenfootImage("EndScreen.png");
    public TitleScreen(MainGame m)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        //setBackground(new GreenfootImage("TitleScreen.png")) ;
        main = m;
    }
    
    public void act(){
        if (main.score > main.hiScore){
                    showText("NEW HIGH SCORE!!!", 500,300);
            main.hiScore=main.score;
        }
        showText("Level: " + main.level, 500,450);
        showText("Score: " + main.hiScore, 500,350);
        showText("Score: " + main.score, 500,400);
        showText("Money: $" + main.moneyAmount, 500,500);
        if (Greenfoot.mouseClicked(null)){
            
            if (main.player.health <= 0 ){
                main = new MainGame( main.hiScore);
            }
            main.waveTimer.mark();
            Greenfoot.setWorld(main);
        }
    }
}
