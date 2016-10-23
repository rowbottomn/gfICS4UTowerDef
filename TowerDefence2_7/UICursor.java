import greenfoot.*;
import java.awt.*;

/**
 * Write a description of class UICursor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UICursor extends UIMenuBackground
{
    /**
     * Act - do whatever the UICursor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    MainGame tempWorld;
    greenfoot.MouseInfo mouse;
    GreenfootImage img;
    Money money;
    GreenfootSound pickup = new GreenfootSound("coins.wav");

    int size;
    boolean canSpawn;
    Tower tower;
    SimpleTimer timer;
    Label boost;
    Label raze;

    public UICursor(MainGame m,int s){
        tempWorld = m;
        size = s;
        img = getImage();
        setImage(new Color(200));
        img.scale(size,size);
        pickup.setVolume(75);
    }

    public void update(Tower t, greenfoot.MouseInfo m, SimpleTimer tim){
        mouse = m;
        timer = tim;

        if ((mouse == null) || (mouse.getX() > 900)){return;}
        setLocation(mouse.getX(),mouse.getY());
        if (boost ==null){
            boost = new Label("",24);
            tempWorld.addObject(boost,mouse.getX()-30,mouse.getY());
            raze = new Label("",24);
            tempWorld.addObject(raze,mouse.getX()+30,mouse.getY());
        }
        boost.setValue("");
        raze.setValue("");
        pickUpMoney();        
        tower = t;
        canSpawn = canSpawn();
        setImage(t.color);
        if (canSpawn&&mouse.getButton() ==1 && timer.millisElapsed()> 500){

            placeTower();
        }

        else{
            tower = (Tower)getOneIntersectingObject(Tower.class);
            if (tower == null){return;}
            boost.setLocation(mouse.getX()-30, mouse.getY());
            raze.setLocation(mouse.getX()+30, mouse.getY());
            boost.setValue(""+tower.cost);
            raze.setValue(""+tower.cost/2);

            if (mouse.getButton() ==1 && timer.millisElapsed()> 500){
                checkUpgrade();
            }

            else if (mouse.getButton() > 1){
                checkRaze();
            }

        }


    }

    private boolean canSpawn(){
        return !(isTouching(Waypoint.class) || isTouching(Tower.class));
    }

    private void setImage(Color c){
        img.clear();
        img.setColor(c);
        //img.fill(   );
        img.fillOval(0,0,size, size);
        if (canSpawn){
            img.setTransparency(100);
        }
        else {
            img.setTransparency(0);
        }

    }

    private boolean canBuy(){
        return tempWorld.moneyAmount >= tower.cost;
    }

    public void placeTower(){
        if (!canBuy()){
            boost.setLocation(mouse.getX(), mouse.getY());
            boost.setValue(""+tower.cost);
            return;
        }
        tempWorld.moneyAmount -= tower.cost;
        tempWorld.addObject(tower,mouse.getX(),mouse.getY());
        timer.mark();
    }

    public void pickUpMoney(){
        money = (Money)getOneIntersectingObject(Money.class);
        if (money ==null){return;}
        pickup.play();
        tempWorld.moneyAmount += money.value;
        tempWorld.score += money.value;
        tempWorld.removeObject(money);
    }

    public void checkUpgrade(){
        if (!canBuy()){return;}
        //tower.cost *=1.8;//moved it here to 
        tempWorld.moneyAmount -= tower.cost;
        tower.upgrade();  
        timer.mark();
    }

    protected void checkRaze(){
        if (tower ==null){return;}
        tempWorld.addObject(new Explosion(this), getX(), getY());
        tempWorld.moneyAmount += tower.cost/2;
        tower.remove();
        //tempWorld.moneyAmount += tower.cost/2;
        //tempWorld.removeObject(tower.ranger);
        //tempWorld.removeObject(tower); 
    }
}
