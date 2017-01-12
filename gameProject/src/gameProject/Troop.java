package gameProject;

public class Troop extends MovingObject {
	private int health;
	private int damage;
	private int cost;
	private double range;
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int decidedHealth) {
		health=decidedHealth;
	}
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int decidedDamage) {
		damage=decidedDamage;
	}
	public int getCost() {
		return cost;
	}
	
	public void setCost(int decidedCost) {
		cost=decidedCost;
	}
	public double getRange() {
		return range;
	}
	
	public void setRange(double decidedRange) {
		range=decidedRange;
	}
	
}
