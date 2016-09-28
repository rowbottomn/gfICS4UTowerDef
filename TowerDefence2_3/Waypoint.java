import greenfoot.*;

/**
 * Write a description of class Waypoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Waypoint extends Actor
{
    int x, y;
    int size = 60;
    public Waypoint(int x, int y){
        GreenfootImage img = getImage();
        img.scale(size,size);
        setImage(img);
        this.x = x;
        this.y = y;
    }  
    
    /**
     * Act - do whatever the Waypoint wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
