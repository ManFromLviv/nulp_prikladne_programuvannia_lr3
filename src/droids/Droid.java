package droids;

import java.util.Random;

public abstract class Droid {

    private final String name;
    private double health;
    private final double damage;
    private final double maxHealth;

    public Droid(String name, double health, double damage, double maxHealth) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.maxHealth = maxHealth;
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getDamage() {
        return damage;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth() {
        health = maxHealth;
    }

    public double attack(Droid droid) {
        int typeDamage = new Random().nextInt(6);
        double nowAttackDamage = (typeDamage == 0) ? 0 : damage / typeDamage;
        droid.attackDamage(nowAttackDamage);
        return nowAttackDamage;
    }

    public void attackDamage(double damage) {
        health -= damage;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void restore() {
        health = maxHealth;
    }

    @Override
    public String toString() {
        return "'" + name + "' {" +
                "health=" + health +
                ", damage=" + damage +
                '}';
    }
}