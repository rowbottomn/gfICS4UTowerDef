import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    GreenfootImage temp;
    Color color; 
    Enemy owner;
    int maxHealth=100;
    
    public HealthBar(Enemy o, int maxHeath){
      //healthFill = new GreenfootImage(20,5);  
     // color = Color(0, 255, 0);
      this.maxHealth = maxHealth;
      owner = o;
      temp = getImage();
      
    }
    
    
    public void update() 
    {
        temp.clear();
      //update the image
      color = new Color(255*(1 - owner.health/maxHealth), 255 *owner.health/maxHealth, 0);
      temp.setColor(color); 
      temp.fillRect(1,1,50,10);
      temp.scale(1+owner.health, 8);
      setImage(temp);
       //update the position
      setLocation(owner.x+temp.getWidth()/2, owner.y-30);
        // Add your action code here.
    }    
}
