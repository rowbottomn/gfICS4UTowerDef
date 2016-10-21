import greenfoot.*;
import java.awt.*;

/**
 * Write a description of class FlameTower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FlameTower extends BlastTower
{

    double bulletWidth;
//    SimpleTimer soundTimer = new SimpleTimer;
    int soundDelay = 1000;
    GreenfootSound sound2; //used to make the sound a little better
    boolean delay = false;
    public FlameTower(){
        super();
        range *= 0.7;//0.42 of original tower
        bulletSpeed*=0.4;//is 0.4 of 0.7
        damage=3;//0.8 * 0.25 is 0.2
        fireRate = 50;
        //spread = 30;
        numBullets = 1;
        cost = 150;
        bulletWidth = 1.1;
        sound = new GreenfootSound("\\sounds\\flame_thrower.wav");
        sound.setVolume(70);
        color = new Color(255,170,0);
    }

    /*    protected void fire(int angle){
    getWorld().addObject(new FlameBullet(angle+(int)(Math.random()*spread-spread/2), damage, (int)range, bulletSpeed), x, y);
    getWorld().addObject(new FlameBullet(angle+(int)(Math.random()*spread-spread/2), damage, (int)range, bulletSpeed), x, y);
    getWorld().addObject(new FlameBullet(angle+(int)(Math.random()*spread-spread/2), damage, (int)range, bulletSpeed), x, y);
    fireTimer.mark();//resets timer
    } */ 
    
    public void act(){
        super.act();
        if (target == null){
            sound.stop();
        }
    }

    protected FlameTower clone(){
        FlameTower clone = new FlameTower(); 

        return clone; 
    }

    protected void fire(int angle){
        getWorld().addObject(new FlameBullet(angle, damage, (int)range, bulletSpeed, bulletWidth), getX(), getY());
        if (!sound.isPlaying()){
            this.sound.play();
        }

        fireTimer.mark();//resets timer
    }

    public void upgrade(){
        bulletWidth*=1.03;
        damage +=1;
        range *= 1.2;
        cost *= 2.3;
        tempWorld.removeObject(ranger);
        ranger= null;
    }

}
