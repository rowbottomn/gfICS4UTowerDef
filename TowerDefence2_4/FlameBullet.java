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
    public FlameBullet(int a, int b, int c, int d){
        super(a, b, c, d);
        move(speed);
    }
    
    public void act(){
        super.act();
                GreenfootImage temp = getImage();
        temp.scale((int)(1.1*temp.getWidth()),(int)(1.1*temp.getHeight()));

    }
}
