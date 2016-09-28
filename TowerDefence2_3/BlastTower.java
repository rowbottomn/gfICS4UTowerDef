import greenfoot.*;

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
 
    
    protected void fire(int angle){
           getWorld().addObject(new BlastBullet(angle, 2*damage, (int)range, bulletSpeed/2), x, y);
           fireTimer.mark();//resets timer
    }
}
