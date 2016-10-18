import greenfoot.*;

/**
 * Write a description of class Money here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class Money extends Explosion
{
    int value;
    public Money(int value, int size){
        super();
        this.value = value;
        duration = 8000;
        this.size = size;
        image = new GifImage("coin.gif");
        setImage(image.getCurrentImage());
        timer = new SimpleTimer();
    }
    /**
     * Act - do whatever the Money wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //if (world == null){
           world = (MainGame)getWorld();
        //}
        // Add your action code here.
         setImage(image.getCurrentImage());
         getImage().scale(size, size);
        if (timer.millisElapsed()>duration){
            world.removeObject(this);
        }  
     //   if (Greenfoot.mouseClicked(this)){
          //  world.moneyAmount+=value;
          //  world.moneyLabel.setValue("$"+world.moneyAmount+=value);
       //     world.removeObject(this);
            
       // }
    }    
}
