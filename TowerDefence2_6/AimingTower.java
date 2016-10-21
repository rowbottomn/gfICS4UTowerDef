import greenfoot.*;
import java.util.*;
import java.awt.*;
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
    Color color;
    Range ranger; 
    World tempWorld;
    GreenfootImage img;
    GreenfootSound sound;
    /**
     * Act - do whatever the AimingTower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public AimingTower(){
        super();
        level = 1;
        range = 150;
        fireRate = 500;
        bulletSpeed = 20;
        damage = 10;
        numBullet = 1;
        cost = 50;
        color = new Color(0,180,0);//for ranger
        sound = new GreenfootSound("little_pop.wav");
        sound.setVolume(75);
    }

    public AimingTower(int _range){
        super();
        range = _range;
        color = new Color(0,180,0);

    }

    public void act() 
    {
        if (tempWorld ==null){

            tempWorld = getWorld();
        }
        if (img == null){
            setImage();
        }
        if (ranger==null){
            ranger = new Range((int)range, color);
            ranger.setImage();
            getWorld().addObject(ranger,this.getX(), this.getY());
        }
        //aim();
        if (fireTimer.millisElapsed()  >= fireRate){
            target = ranger.target();
            if (target != null){

                turnTowards(target.getX(), target.getY());
                //firingAngle = this.getRotation();
                fire(this.getRotation());
                //target = null;
            }
        }   
        //fire with angle
        if (Greenfoot.mouseClicked(this)){
            upgrade();
        }
    }    

    protected void setImage(){
        if (img == null){
            img = new GreenfootImage("\\images\\turret_green.png");}
        //img.setColor(new Color(60,60,60));
        //img.fillOval(0,0,turretSize,turretSize);
        img.setColor(this.color);

        img.fillOval(-2,-1,turretSize,turretSize);

        img.scale(turretSize-6,turretSize-6);
        setImage(img);        
    }

    protected void fire(int angle){
        this.sound.play();

        tempWorld.addObject(new Bullet(angle, damage, (int)range, bulletSpeed), getX(), getY());
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

    protected AimingTower clone(){
        AimingTower clone = new AimingTower(); 

        return clone; 
    }

    protected void upgrade(){
        range *= 1.2;
        cost *= 1.8;
        tempWorld.removeObject(ranger);
        ranger= null;
    }

    void remove(){
      
      //  tempWorld.moneyAmount += cost/2;
        tempWorld.removeObject(ranger);
        tempWorld.removeObject(this);

    }

}
