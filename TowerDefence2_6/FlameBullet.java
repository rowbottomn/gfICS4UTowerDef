import greenfoot.*;

/**
 * Write a description of class FlameBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FlameBullet extends Bullet
{
    /**
     * Act - do whatever the FlameBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    double bulletWidth;
    boolean skip = true; 
    public FlameBullet(int a, int b, int c, int d, double width){
        super(a, b, c, d);
        bulletWidth = width;
        move(speed);
    }
    
    public void act(){
        super.act();
        if (skip){
            skip = false;
                GreenfootImage temp = getImage();
             temp.scale((int)(bulletWidth*temp.getWidth()),(int)(bulletWidth*temp.getHeight()));
             
        }
        else {
            skip = true;
        }
    }
}
