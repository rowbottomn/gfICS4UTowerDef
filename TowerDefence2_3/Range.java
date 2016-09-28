import greenfoot.*;

/**
 * Write a description of class Range here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Range extends Actor
{
    Enemy target;
    
    /**
     * Act - do whatever the Range wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }   
    
    public Enemy target(){
        return (Enemy) getOneIntersectingObject(Enemy.class);
    }
}
