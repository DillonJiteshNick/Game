package gameProject;

public class Troop extends MovingObject {
	private int health;
	private int damage;
	
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
}
