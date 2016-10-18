import greenfoot.*;
import java.awt.*;

/**
 * Write a description of class BlastTower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlastTower extends AimingTower
{
    /**
     * Act - do whatever the BlastTower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    int spread;//how bullets spread apart in degrees
    int numBullets;//how many bullets are fired at once
    public BlastTower(){
        super();
        range *= 0.6;
        bulletSpeed*=0.7;
        damage*=0.8;
        fireRate = 600;
        spread = 28;
        cost = 65;
        numBullets = 2;
        color = new Color(200,0,0);
    }
    
    protected void fire(int angle){
        for (int i = 0; i < numBullets; i ++){
            getWorld().addObject(new BlastBullet(angle+(int)(Math.random()*spread-spread/2), damage, (int)range, bulletSpeed), getX(), getY());
        }
       fireTimer.mark();//resets timer
    }
    
    protected BlastTower clone(){
       BlastTower clone = new BlastTower(); 
       
       return clone; 
    }
}
