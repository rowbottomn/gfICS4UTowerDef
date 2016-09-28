import greenfoot.*;
import java.util.*;
/**
 * Write a description of class AimingTower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AimingTower extends Tower
{
    Enemy target; //the local reference to the currently target 
    int firingAngle;
    Range ranger; 
    /**
     * Act - do whatever the AimingTower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public AimingTower(){
        ranger = new Range();
        
        
    }
    
    
    
    public void act() 
    {
        if (ranger.getWorld()==null){
            GreenfootImage temp = ranger.getImage();
            temp.scale((int)(range*2),(int)(range*2));
            temp.setTransparency(30);
     
            getWorld().addObject(ranger,this.getX(), this.getY());
        }
        //update position
        x = getX();
        y = getY();
        //aim();
        if (fireTimer.millisElapsed()  >= fireRate){
            target = ranger.target();
        if (target != null){
             
            turnTowards(target.getX(), target.getY());
            //firingAngle = this.getRotation();
            fire(this.getRotation());
            //target = null;
        }}
        
    //fire with angle
    
    }    
    
    protected void fire(int angle){
           getWorld().addObject(new Bullet(angle, damage, (int)range, bulletSpeed), x, y);
           fireTimer.mark();//resets timer
    }
    
    protected void aim(){
        //if target is null
        if (target == null){
           //get a target refernce
            int targetDist = 50;//this is the distance we are searching
            while(target == null && targetDist < range){
                //try to get an in range
                ArrayList <Enemy> enemies = new ArrayList<Enemy>(getNeighbours(targetDist, true, Enemy.class));
                if (enemies.size() > 0){
                    target = enemies.get(0);
                }
                //increase distance of search
                targetDist += 15;
            }
            if (target == null){
                return;
            }
            if (target.getWorld() != null){
                turnTowards(target.getX(), target.getY());
                firingAngle = this.getRotation();
            }
        }
    
    }

    protected void improvedAim(){
        //if the target is out of the world then set the target to null to find another target
        //if (target!=null && target.getWorld() == null){
        //   target = null;
        //}
        //if (target == null){//try to get another target
           
        //}       
        
        
        
    }
    
}
