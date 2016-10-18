import greenfoot.*;
import java.awt.*;

/**
 * Write a description of class Range here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Range extends Actor
{
    Enemy target;
    int size;
    Color color; 
    
    public Range(){
        size = 400; 
   
    }
    
    public Range(int _size ){
        this.size = 2*_size;
    }

    public Range(int _size, Color c){
        this.size = 2*_size;
        color = c;
    }
    
    /**
     * Act - do whatever the Range wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
 
    
    public void setImage(){
       GreenfootImage temp = getImage();
       temp.scale(size,size);
       temp.clear();
       temp.setColor(color);
       temp.fillOval(0,0,size,size);
       temp.setTransparency(30);        
    }
        //used for freezetower
        public void setImage(double transparency){
       GreenfootImage temp = getImage();
       temp.scale(size,size);
       temp.clear();
       temp.setColor(color);
       temp.fillOval(0,0,size,size);
       temp.setTransparency((int)(80*transparency));        
    }    
        
    public Enemy target(){
        return (Enemy) getOneIntersectingObject(Enemy.class);
    }
}
