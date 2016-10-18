import greenfoot.*;

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    int x, y;
    int angle;//coming from the tower via constructor
    int damage;//coming from the tower via constructor
    int speed; //coming from the tower via constructor
    int range;//coming from the tower via constructor
    int distTravelled;//
    
    public Bullet(int a, int d, int r, int s){
        angle = a;
        damage = d;
        range = r;
        speed = s;
        distTravelled = 0;
        turn(angle);
    }
    
    public Bullet(){
        angle = 90;
        damage = 10;
        range = 300;
        speed = 15;
        distTravelled = 0;
        turn(angle);
    }
    
    public Bullet(int a){
        angle = a;
        damage = 10;
        range = 300;
        speed = 15;
        distTravelled = 0;
        turn(angle);
    }
    
    
    public void act() 
    {
        move(speed);
        distTravelled += speed;
        //check to see if its travelled past its range
        if (distTravelled >= range){
            getWorld().removeObject(this);//this removes the bullet
        }
    }    
    
    
}
