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
    int numBullets;//how many bullets are fired at once
    public MissileTower(){
        super();
        range = 300;
        bulletSpeed=3;
        
        fireRate = 2600;

        cost = 350;
        numBullets = 1;
        damage*=2.7/numBullets;
        color = new Color(200,0,0);
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
        for (int i = 0; i < numBullets; i ++){
            getWorld().addObject(new MissileBullet((int)(Math.random()*360), damage, (int)range, bulletSpeed, target), getX(), getY());
        }
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
        damage*=numBullets;
        numBullets += 1;
        spread*=1.05;
        damage*=1.1/numBullets;
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
