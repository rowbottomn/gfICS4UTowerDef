import greenfoot.*;
import java.awt.*;
/**
 * Write a description of class FreezeTower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FreezeTower extends AimingTower
{
    /**
     * Act - do whatever the FreezeTower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean active;
    
    public FreezeTower(){
        super();
        range = 160;
        cost = 200;
        fireRate = 2500;
        color = new Color(0,0,180);//for ranger 
        active = false;
        img = null;
    }

    public void act(){
        if (tempWorld == null){
            tempWorld = (MainGame)getWorld();
        }
        if (img == null){
            setImage();
        }
        if (active){
            if (ranger == null){
                ranger = new FreezeAttack((int)range, color);
                ranger.setImage();
                getWorld().addObject(ranger,this.getX(), this.getY()); 
            }
            
                ranger.setImage((double)(0.1+fireTimer.millisElapsed()/fireRate));            
        }
        else{
            tempWorld.removeObject(ranger);
            ranger=null;
        }

        //aim();
        //fire with angle
        if (Greenfoot.mouseClicked(this)){
            upgrade();
        }

        if (fireTimer.millisElapsed()  >= fireRate){
            active = !active;
            fireTimer.mark();
        }

    }

    protected FreezeTower clone(){
        FreezeTower clone = new FreezeTower(); 
        return clone; 
    }
    
    protected void setImage(){
               this.img = new GreenfootImage("\\images\\button-blue.png");
            
            //img.setColor(new Color(60,60,60));
            //img.fillOval(0,0,turretSize,turretSize);
            this.img.setColor(this.color);
            
          //  img.fillOval(-2,-1,turretSize,turretSize);
            
            this.img.scale(50,50);
            setImage(this.img);        
    }
}
