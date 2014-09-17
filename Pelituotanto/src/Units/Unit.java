package Units;

import Weapons.Weapon;
import java.util.Random;
/**
 *
 * @author s1200481
 */
public class Unit {
    private final Body body = new Body();
    private Weapon wpn;
    private double health, weight;

    public double Accuracy(){
        return (ACCURACY + wpn.getAccuracy()) * (1.00 / body.getArms());
    }
    
    public int Movement(){
        return (int)(MOVEMENT - (body.getLegs() * weight - WEIGHT_BREAKPOINT) * weight / WEIGHT_MODIFIER);
    }
    
    public double Damage(){
        return wpn.getDamage();
    }
    
    public void Damage_Taken(double dmg){
        Random rng = new Random();
        double chance = (rng.nextInt(100) + 1) / 100;
        double sum = 0.00, crt;
        int i = -1;
        
        do{
            i++;
            sum += HIT_CHANCE[i];
        }while(chance > sum);
        
        crt = MAX_CRITICAL - MIN_CRITICAL * 100;
        crt = (rng.nextInt((int)crt) + 1 / 100) + MIN_CRITICAL;
        
        health -= dmg * DMG_MODIFIER[i] * crt;
    }
    
    public void setWeapon(Weapon wpn){
        this.wpn = wpn;
    }
    
    private final static double 
            WEIGHT_MODIFIER = 50.00, 
            WEIGHT_BREAKPOINT = 10.00, 
            MOVEMENT = 10.00,
            ACCURACY = 1.00,
            MIN_CRITICAL = 0.75, 
            MAX_CRITICAL = 1.25;
    
    private final static double[] 
            DMG_MODIFIER = {2.00, 1.20, 0.80, 0.80}, // Head, Torso, Arms, Legs
            HIT_CHANCE = {0.10, 0.50, 0.20, 0.20}; // Head, Torso, Arms, Legs
}
