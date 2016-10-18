import greenfoot.*;

/**
 * Write a description of class Assets here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public  class Assets {
    public static GreenfootSound largeExp = new GreenfootSound("\\sounds\\explosion_large.mp3");
    public static GreenfootSound medExp = new GreenfootSound("\\sounds\\explosion_medium.mp3");
    public static GreenfootSound smallExp = new GreenfootSound("\\sounds\\explosion_small.mp3");
    public static GreenfootSound littlePop = new GreenfootSound("\\sounds\\little_pop.mp3");

    public static GreenfootImage bullet = new GreenfootImage("\\images\\bullet.png");
    public static GreenfootImage blastBullet = new GreenfootImage("\\images\\blast.png");

    public static GreenfootImage aimingTower = new GreenfootImage("\\images\\green_turret.png");


    private static void fixImage(int x, int y){
        aimingTower.scale(x, y);    
    }

}