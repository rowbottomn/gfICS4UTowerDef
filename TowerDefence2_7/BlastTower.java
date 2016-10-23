import greenfoot.*;
import java.awt.*;

/**
 * Write a description of class BlastTower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlastTower extends AimingTower
{
    /**
     * Act - do whatever the BlastTower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    int spread;//how bullets spread apart in degrees
    int numBullets;//how many bullets are fired at once
    public BlastTower(){
        super();
        range *= 0.6;
        bulletSpeed*=0.7;

        fireRate = 600;
        spread = 20;
        cost = 95;
        numBullets = 3;
        damage*=1.7/numBullets;
        color = new Color(150,150,255);
        sound = new GreenfootSound("turret_blast1.wav");
        sound.setVolume(65);

    }

    protected void fire(int angle){
        if (sound.isPlaying()){
            sound.stop();
        }
        this.sound.play();
        for (int i = 0; i < numBullets; i ++){
            getWorld().addObject(new BlastBullet(angle+spread/2*(i-numBullets/2), damage, (int)range, bulletSpeed), getX(), getY());
        }
        fireTimer.mark();//resets timer
    }

    protected BlastTower clone(){
        BlastTower clone = new BlastTower(); 

        return clone; 
    }

    protected void upgrade(){
        range *= 1.1;
        cost *= 1.8;
        damage*=numBullets;
        numBullets += 1;
        spread*=1.05;
        damage*=1.1/numBullets;
        tempWorld.removeObject(ranger);
        ranger= null;
    }
}
