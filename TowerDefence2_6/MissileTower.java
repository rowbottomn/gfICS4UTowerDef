import greenfoot.*;
import java.awt.*;
/**
 * Write a description of class MissileTower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class MissileTower extends AimingTower
{
    double bulletSpeed;
    int numBullets;//how many bullets are fired at once
    public MissileTower(){
        super();
        range = 300;
        this.bulletSpeed=3.5;
        
        fireRate = 2400;

        cost = 320;
        numBullets = 1;
        damage*=4;
        color = new Color(150,0,150);
    }
    
    
        public void act() 
    {
        if (tempWorld ==null){
            tempWorld = (MainGame)getWorld();

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

                
                //firingAngle = this.getRotation();
                fire();
                //target = null;
            }
        }   
        //fire with angle
        if (Greenfoot.mouseClicked(this)){
            upgrade();
        }
    }    
    
    
    protected void fire(){
            getWorld().addObject(new MissileBullet((int)(Math.random()*360), damage, (int)(2*range), this.bulletSpeed, target), getX(), getY());
       fireTimer.mark();//resets timer
    }
    
    protected MissileTower clone(){
       MissileTower clone = new MissileTower(); 
       
       return clone; 
    }
    
    
    protected void upgrade(){
        range *= 1.1;
        cost *= 2.1;
        bulletSpeed*=1.2;
        fireRate *= 0.7;
        numBullets += 1;
        spread*=1.05;
        damage*=1.1;
        tempWorld.removeObject(ranger);
        ranger= null;
    }    
    
    protected void setImage(){
               this.img = new GreenfootImage("\\images\\space_base.png");
            
            //img.setColor(new Color(60,60,60));
            //img.fillOval(0,0,turretSize,turretSize);
            this.img.setColor(this.color);
            
          //  img.fillOval(-2,-1,turretSize,turretSize);
            
            this.img.scale(50,50);
            setImage(this.img);        
    }
}
