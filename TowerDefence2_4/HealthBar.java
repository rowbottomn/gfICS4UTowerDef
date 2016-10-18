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
    int maxHealth;
    int size;
    
    public HealthBar(Enemy o, int maxHeath){
      //healthFill = new GreenfootImage(20,5);  
     // color = Color(0, 255, 0);
      this.maxHealth = o.health;
      owner = o;
      size = owner.getImage().getHeight()/2;
      temp = getImage();
      
    }
    
    
    public void update() 
    {
        //temp.clear();
        int health = owner.health;
      //update the image
      if (health == maxHealth){return;}
      color = new Color(230*(1 - health/maxHealth), 230 *health/maxHealth, 0);
      temp.setColor(color); 
      temp.fill();
      temp.scale(1+50*health/maxHealth, 8);
      //setImage(temp);
      //update the position
      //setLocation(owner.x+temp.getWidth()/2, owner.y-size);
      setLocation(owner.x, owner.y-size);
      // Add your action code here.
    }    
}
