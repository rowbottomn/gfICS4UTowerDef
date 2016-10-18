import greenfoot.*;
import java.awt.*;
import java.util.*;

/**
 * Write a description of class UIBaseTower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UIBaseTower extends UIButton
{
    static Color hilight = new Color(225, 225, 100);
    protected ArrayList <UIBaseTower> towers; 
    /**
     * Act - do whatever the UIBaseTower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
   
    
    public UIBaseTower(AimingTower tower){
        tower.setImage();
        tower.range = 5;
        img = tower.getImage();
        setImage(img);
    }
    
    
    public UIBaseTower(BlastTower tower){
        tower.setImage();
        tower.range = 5;
        img = tower.getImage();
        setImage(img);
    }
    
    
    public UIBaseTower(FlameTower tower){
        tower.setImage();
        tower.range = 5;
        img = tower.getImage();
        setImage(img);
    }
    
     public UIBaseTower(FreezeTower tower){
         tower = (FreezeTower)tower;
        tower.setImage();
        tower.range = 5;
        img = tower.getImage();
        setImage(img);
    }
    
    public void act(){

        super.act();
        GreenfootImage temp = new GreenfootImage(getImage());
        temp.clear();

        if (active){
           temp.setColor(hilight);
           temp.fill();
        }
        temp.drawImage(img,0,0 );
        setImage(temp);
    }
    
    
}
