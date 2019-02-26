import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.io.*;//for the colors instead of java.AWT.*
/**
 * A pretty cool health bar with a low computational footprint, and autoupdating
 * spawn it in and then forget it
 * 
 * @author Nathan Rowbottom
 * @version Feb 26, 2019
 */
public class HealthBar extends Actor
{
    protected GreenfootImage temp;
    protected Color color; 
    protected Enemy owner;
    protected int maxHealth;
    protected int size;
    protected int health
    protected int red = 0, green = 0;
    
    public HealthBar(Enemy o, int maxHeath){
      //healthFill = new GreenfootImage(20,5);  
     // color = Color(0, 255, 0);
      this.maxHealth = o.health;
      owner = o;
      size = owner.getImage().getHeight()/2;
      
    }
    
    public void act(){
        //check to see if our owner is gone
        if (owner == null){
            kys();
            return;
        }
        //check to see if the object is no longer in the world 
        //should not have to do this if the world specified the act order reasonably 
        //but...
        if (owner.getWorld() == null){
            kys();
        }
    }
    
    private void kys(){
        getWorld().removeObject(this);
    }
    
    public void update() 
    {
       //first update then set up some stuff
        if (temp == null){
            temp = getImage();
            temp.clear();
        }
      //update health   
       health = owner.health;
      //move the bar to the right spot
      setLocation(owner.x, owner.y-size);
      //if we are full health or dead then no update required
      if (health == maxHealth|| health <= 0){return;}
      //update the color
      try{//not really needed anymore
          red = (int)(120+134*(1. - ((double)health/(double)maxHealth)));
          green = (int)( 254 *((double)health/(double)maxHealth));
       color = new Color(red,green, 0);
    }
    catch(Exception e){
        //let me know if we divided by zero somehow
       System.out.println(""+red+" , "+green);
    }
      temp.setColor(color); 
      temp.fill();
      temp.scale(1+50*health/maxHealth, 8);

    }    
}
