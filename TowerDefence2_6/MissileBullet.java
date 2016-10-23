import greenfoot.*;
import java.util.*;
/**
 * Write a description of class MissileBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MissileBullet extends Bullet
{
    static ArrayList<Enemy> enemies;
    Enemy enemy;
    double bulletSpeed;
    public MissileBullet(int a, int d, int r, double s, Enemy e){
        super(a, d, r, (int)s);
        enemies = new ArrayList<Enemy>();
        this.bulletSpeed = s;
        enemy = e;
        enemies.add(enemy);
    }

    /**
     * Act - do whatever the MissileBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        speed = (int)this.bulletSpeed;
        // Add your action code here.
        super.act();
        if (enemy!=null&&enemy.getWorld()!=null){
            int oldAngle = getRotation();
            turnTowards(enemy.getX(), enemy.getY());
            setRotation(oldAngle+(int)((getRotation()-oldAngle)/3));
            bulletSpeed*=1.08;
        }
        else{
            enemy = enemies.get(0);//try to select another enemy in the list
               
            
            if (getWorld()!=null){
             
                getWorld().addObject(new Explosion(this), getX(),getY());
                getWorld().removeObject(this);
            }
        }

    }    
}
