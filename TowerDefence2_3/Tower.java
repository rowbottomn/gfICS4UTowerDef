import greenfoot.*;

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
    
    protected int x = 0, y = 0;
    protected int level;
    protected double range;
    protected double fireRate;
    protected int bulletSpeed;
    protected int cost;
    protected int damage;
    
    
    //constructors here
    public Tower(){
        level = 1;
        range = 200;
        fireRate = 500;
        bulletSpeed = 20;
        damage = 15;
        fireTimer = new SimpleTimer();
        fireTimer.mark();
        
    }
    
    private void drawTower(){
        GreenfootImage real = getImage();       
        GreenfootImage temp = new GreenfootImage(getImage(););
        
        temp.setColor( new Color(60,60
        //clear the old image
        temp.clear();
        
        //draw the base
        
        //
    }
    
    public void act() 
    {
        //this updates the location of the tower 
       x = getX();
        y = getY();
       
       //check to see if I can fire a bullet 
       if (fireTimer.millisElapsed()  >= fireRate){
           aim();
           fire(x,y);
//           System.out.println("fire");
       }
    }    
    
    
    
    
    public boolean canSpawn(){
        return !(isTouching(Waypoint.class) || isTouching(Tower.class));
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
           getWorld().addObject(new Bullet(45, damage, (int)range, bulletSpeed), x, y);
           fireTimer.mark();//resets timer
    }

}
