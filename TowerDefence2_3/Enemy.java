import greenfoot.*;
import java.util.*;
/**
 * Abst class Enemy here.
 * 
 * @author (your name) 
 * @version 1_0 moves and gets hit and removed
 * 1_1 uses waypoints to modify move method
 * 
 */
public class Enemy extends AbstEnemy
{
    ArrayList <Waypoint> path;
    Waypoint current;
    int step = 0;
    
    
    public Enemy(){
         health = 20;
         angle = 0;
         speed = 3;
         damage = 10;
         this.healthbar = new HealthBar(this,health);
    }

      public Enemy(ArrayList <Waypoint> p){
         health = 100;
         angle = 0;
         speed = 3;
         damage = 10;
         path = p;
         current = path.get(step);
         this.healthbar = new HealthBar(this, health);
         
      }
      
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();//just makes sure to get a reference to the world
        if (healthbar.getWorld() == null){
            getWorld().addObject(healthbar, 0,0);
        }
        
        //update position
        move();
        //check if hit
        
            takeDamage();
            //healthbar.update(getX(), getY(), health);
            healthbar.update();
            //check if dead
                //remove    
           
        
    }    

    
    public void nextWaypoint(){

    }
    //enemy moves
    protected void move(){
        move(speed);
        if ((Math.abs(getX()- current.x) <speed)&&(Math.abs(getY()- current.y) <speed)){
            step ++;
            if (step < path.size()){
                current = path.get(step);
                turnTowards(current.x, current.y);
            }
        }
    }
        
    protected boolean checkHit(){
        return isTouching(Bullet.class);
    }
    //enemy removes itself 
    //take damage
    protected void takeDamage(){
        bullets = new ArrayList<Bullet> (getIntersectingObjects(Bullet.class));
        //using a for loop 
        while (bullets.size() > 0){
            Bullet b = bullets.get(0); //local reference to the current bullet 
            health -= b.damage;//bullets.get(0).damage;//remove each damage from health
            bullets.remove(b);//remove the first bullet
            world.addObject(new Explosion(b),this.getX(),this.getY());
            world.removeObject(b);
//            System.out.println("HIT! "+health+" hitpoints remaining.");
        }
        //check to see if enemy is dead OR made it to the end
        if (health <=0){
            health = 0;
            world.removeObject(healthbar);
            world.addObject(new Explosion(this),this.getX(),this.getY());
            world.removeObject(this);            
        }
        //handled by the Home now
        //if (step >= path.size()){
        //    world.removeObject(this);
        //}
        
    }
    
    protected void betterTakeDamage(){
        //testing removing one at a time
        Bullet b = (Bullet)getOneIntersectingObject(Bullet.class);
        health -= b.damage;
        world.removeObject(b);
        if (health <= 0){
            world.addObject(new Explosion(this),this.getX(),this.getY());
            world.removeObject(this);
        }
    }
}
