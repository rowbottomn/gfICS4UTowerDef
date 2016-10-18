import greenfoot.*;

/**
 * Write a description of class MissileBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MissileBullet extends Bullet
{
    Enemy enemy;
    
    public MissileBullet(int a, int d, int r, int s, Enemy e){
        super(a, d, r, s);
        enemy = e;
    }
    /**
     * Act - do whatever the MissileBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        super.act();
        if (enemy!=null){
        turnTowards(enemy.getX(), enemy.getY());
        speed*=1.4;
    }
    else{
        getWorld().addObject(new Explosion(this), getX(),getY());
        getWorld().removeObject(this);
    }
        
    }    
}
