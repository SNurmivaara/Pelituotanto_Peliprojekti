package Weapons;

/**
 *
 * @author s1200481
 */
abstract public class Weapon {
    double weight, damage, accuracy;
    int bullets, capacity, rpt; // rpt = rounds per turn
    
    abstract public void Attack();

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public void setBullets(int bullets) {
        this.bullets = bullets;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRpt(int rpt) {
        this.rpt = rpt;
    }
    
    public double getWeight() {
        return weight;
    }

    public double getDamage() {
        return damage;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getBullets() {
        return bullets;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRpt() {
        return rpt;
    }
}
