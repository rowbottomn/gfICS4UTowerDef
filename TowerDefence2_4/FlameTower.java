import greenfoot.*;
import java.awt.*;

/**
 * Write a description of class FlameTower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FlameTower extends BlastTower
{

    public FlameTower(){
        super();
        range *= 0.7;//0.42 of original tower
        bulletSpeed*=0.4;//is 0.4 of 0.7
        damage*=0.25;//0.8 * 0.25 is 0.2
        fireRate = 50;
        spread = 30;
        numBullets = 3;
        cost = 140;
        
        color = new Color(170,100,50);
    }
    
/*    protected void fire(int angle){
       getWorld().addObject(new FlameBullet(angle+(int)(Math.random()*spread-spread/2), damage, (int)range, bulletSpeed), x, y);
       getWorld().addObject(new FlameBullet(angle+(int)(Math.random()*spread-spread/2), damage, (int)range, bulletSpeed), x, y);
              getWorld().addObject(new FlameBullet(angle+(int)(Math.random()*spread-spread/2), damage, (int)range, bulletSpeed), x, y);
       fireTimer.mark();//resets timer
    } */ 
    
    protected FlameTower clone(){
       FlameTower clone = new FlameTower(); 
       
       return clone; 
    }
    
        protected void fire(int angle){
            int tempNum = (int)Math.random()*numBullets+1;
        for (int i = 0; i < tempNum; i ++){
            getWorld().addObject(new FlameBullet(angle+(int)(Math.random()*spread-spread/2), damage, (int)range, bulletSpeed), getX(), getY());
        }
       fireTimer.mark();//resets timer
    }
}
