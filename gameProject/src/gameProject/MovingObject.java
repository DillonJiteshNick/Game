package gameProject;

public abstract class MovingObject implements Runnable
{
	private double x, y, xSpeed, ySpeed;
	private Boolean moving;

	public double getX() 
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public void setY (int y)
	{
		this.y = y;
	}

	public void setXSpeed(double xSpeed) 
	{
		this.xSpeed = xSpeed;
	}

	public void setYSpeed (double ySpeed)
	{
		this.ySpeed = ySpeed;	}
}
