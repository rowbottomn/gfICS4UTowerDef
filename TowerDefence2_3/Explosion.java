import greenfoot.*;

public class Explosion extends Actor
{
    World world;
    SimpleTimer timer;
    GreenfootSound sound;
    int duration = 1000;
    int size;
    int imageCtr;
    GifImage image;
    
    public Explosion (Actor a){
        setLocation(a.getX(), a.getY());
        image = new GifImage("explosion1.gif");
        setImage(image.getCurrentImage());
        size = (int)(a.getImage().getWidth()*0.9);
        getImage().scale(size, size);
        if (size > 150){
            sound = new GreenfootSound("explosion_large.mp3");
        }
        else if (size > 75){
            sound = new GreenfootSound("explosion_medium.mp3");
        }
        else {
            sound = new GreenfootSound("explosion_small.mp3");
        }
        sound.play();
      
        world = a.getWorld();
        //world.removeObject(a);
        timer = new SimpleTimer();
        
       imageCtr = 0;
    }
    
    
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
       
    public void act() 
    {
        imageCtr ++;
        // Add your action code here.
        //slow down the animation by a factor of two
        // if (imageCtr %2 ==0){
            // if (image.isRunning()){
                // image.pause();
            // }
            // else {
                // image.resume();
                // setImage(image.getCurrentImage());
                // getImage().scale(size, size);
            // }
        // }
         setImage(image.getCurrentImage());
         getImage().scale(size, size);
        if (timer.millisElapsed()>duration){
            world.removeObject(this);
        }
            
        
    }    
    
}
