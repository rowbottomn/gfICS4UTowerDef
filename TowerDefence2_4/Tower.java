import greenfoot.*;
import java.awt.*;
/**
 * Basic tower to use a superclass
 * 
 * @author Rowbottom 
 * @version 1_0 Basic 
 */
public class Tower extends Actor
{
    /**
     * Tower has
     * location (int, int)<<==already inherited from the Actor super class
     * level (int)
     * range (double)
     * rate of fire (double)
     * cost (int)
     * image (GreenfootImage)
     * damage (int)
     * 
     * A tower does 
     * fires 
     *      void fire(int angle)
     *      void fire (int x, int y)
     * upgrades
     *      void upgrade()
     * aims 
     *      int aim()
     *      int aim(Enemy <enemies>)
     * spawns
     *      handled by placement
     * getsDestroyed
     *      raze()
     * 
     */
//timer for cool down
    protected SimpleTimer fireTimer;
    
    Color color;
    protected int x = 0, y = 0;
    protected int level;
    protected double range;
    protected double fireRate;
    protected int bulletSpeed;
    protected int cost;
    protected int damage;
    protected int numBullet;
    protected int turretSize = 54;
    Range ranger;
 //   protected Bullet bullet;
    protected int spread = 0;
    //constructors here
    public Tower(){
        level = 1;
        range = 200;
        fireRate = 500;
        bulletSpeed = 20;
        damage = 15;
        numBullet = 1;
        
        color = new Color(255);
       // bullet = new Bullet();
        fireTimer = new SimpleTimer();
        fireTimer.mark();
    }
    
    public void act() 
    {
        
        //this updates the location of the tower 
  
       
       //check to see if I can fire a bullet 
       if (fireTimer.millisElapsed()  >= fireRate){
           aim();
           fire(getX(),getY());
//           System.out.println("fire");
       }
       //if (Greenfoot.mouseClicked(this)){
       //    upgrade();
       // }
    }    
       
        //methods here
        private int aim(){
            return 0;
        }
   
    private void fire(int angle){
        //call constructor of bullet class that is appropriate
        //tick timer
    }

    private void fire (int x, int y){
           getWorld().addObject(new Bullet(getRotation(), damage, (int)range, bulletSpeed), x, y);
           fireTimer.mark();//resets timer
    }
    
    protected void upgrade(){
     
    }
    
    void remove(){
        
    }

}
