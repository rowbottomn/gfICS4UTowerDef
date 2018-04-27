import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.io.*;
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
      
    }
    
    
    public void update() 
    {
        if (temp == null){
            temp = getImage();
            temp.clear();
        }
        //temp.clear();
        int health = owner.health;
      setLocation(owner.x, owner.y-size);
        //update the image
      if (health == maxHealth|| health <= 0){return;}
      int red = 0, green = 0;
      try{
          red = (int)(120+134*(1. - ((double)health/(double)maxHealth)));
          green = (int)( 254 *((double)health/(double)maxHealth));
      color = new Color(red,green, 0);
    }
    catch(Exception e){
       System.out.println(""+red+" , "+green);
    }
      temp.setColor(color); 
      temp.fill();
      temp.scale(1+50*health/maxHealth, 8);
      //setImage(temp);
      //update the position
      //setLocation(owner.x+temp.getWidth()/2, owner.y-size);
      
      // Add your action code here.
    }    
}
