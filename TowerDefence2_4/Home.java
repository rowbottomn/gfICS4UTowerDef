import greenfoot.*;

/**
 * Write a description of class Home here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Home extends Actor
{

    int health;
    
    Enemy enemy;
    MainGame game;
//    HomeHealthBar healthbar;
    
    public Home(int health){
        this.health = health;
        GreenfootImage temp = getImage();
        temp.scale(temp.getWidth(),(int)(temp.getHeight()*1.2));
  //      healthbar = new HomeHealthBar(this,health);
    }
    
    public void act(){
        game = (MainGame)getWorld();
        enemy = (Enemy)getOneIntersectingObject(Enemy.class);
        if (enemy!=null){
            health -= enemy.damage;
            game.addObject(new Explosion(enemy), enemy.getX(),enemy.getY());
            game.removeObject(enemy.healthbar);
            game.removeObject(enemy);
        }
        if (health<1){
            game.addObject(new Explosion(this), getX(), getY());
            game.removeObject(this);
            return;
        }
    }
    
}
