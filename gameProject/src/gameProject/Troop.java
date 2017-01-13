package gameProject;

public class Troop extends MovingObject {
	private int health;
	private int damage;
	private int cost;
	private int range;
	private int reward;
	
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
	
	public void setCost(int decidedDamage) {
		damage=decidedDamage;
	}
	public int getRange() {
		return range;
	}
	
	public void setRange(int decidedDamage) {
		damage=decidedDamage;
	}
	
	public int getReward()
	{
		return reward;
	}
	
	public void setReward(int decidedReward)
	{
		this.reward = decidedReward;
	}
	
	
}
