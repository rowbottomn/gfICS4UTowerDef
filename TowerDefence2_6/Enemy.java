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
    GifImage gif;
    boolean gifActive = false;

    //not used
    public Enemy(){
        health = 20;
        angle = 0;
        speed = 3;
        damage = 10;
        value = 2+(health+damage)*speed/4;
        this.healthbar = new HealthBar(this,health);
    }

    public Enemy(ArrayList <Waypoint> p, int level){
        health = 80+(int)(Math.random()*level-level/2)*30;
        //health = 100+level/4*20;
        if (health<=80){
            health = 80;
            gif  = new GifImage("\\images\\fast_enemy.gif"); 
            setImage(gif.getCurrentImage());
            gifActive = true;        }
        else if (health <=160){
            gif  = new GifImage("\\images\\low_health_enemy.gif"); 
            setImage(gif.getCurrentImage());
            gifActive = true;
            
        }
        else if (health <= 340){
            setImage("\\images\\ladybug1.png");                        
        }
        else {
            gif  = new GifImage("\\images\\main_boss.gif");   
                       setImage(gif.getCurrentImage());
            gifActive = true;
        };
        angle = 0;
        speed = 2+(int)(level*0.3-health/100);
        if (speed<1){speed = 1;};
        damage = 8+(int)(level*0.2+health/150);
        path = p;
        value = 4+(health/40)*speed/3;

        current = path.get(step);

    }

    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();//just makes sure to get a reference to the world
        if (healthbar == null){
            healthbar = new HealthBar(this, health);
            getWorld().addObject(healthbar, 0,0);
        }

        //update position
        move();
        //check if hit

        //takeDamage();
        betterTakeDamage();
        //healthbar.update(getX(), getY(), health);
        healthbar.update();
        //check if dead
        //remove    

    }    

    //enemy moves
    protected void move(){
        move((int)(Math.round(speed*slowDown())));
        if ((Math.abs(getX()- current.x) <2*speed)&&(Math.abs(getY()- current.y) <2*speed)){
            step ++;
            if (step < path.size()){
                current = path.get(step);
                turnTowards(current.x, current.y);
            }
        }
        if (gifActive){
            setImage(gif.getCurrentImage());
        }
    }

    protected double slowDown(){
        if (isTouching(FreezeAttack.class)){
            return 0.5;
        }
        return 1.0;    

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
            world.addObject(new Money(value,50), this.getX(),this.getY());
            world.score+= value;
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
        if (b!= null){
            health -= b.damage;
            world.removeObject(b);
            if (health <= 0){
                health = 0;
                world.score+=value;
                world.addObject(new Explosion(this),this.getX(),this.getY());
                world.addObject(new Money(value,30), this.getX(),this.getY());
                world.removeObject(healthbar);
                world.removeObject(this);
            }
        }
    }
}
