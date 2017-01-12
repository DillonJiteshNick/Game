package gameProject;

public abstract class MovingObject
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

	public double getXSpeed()
	{
		return xSpeed;
	}

	public double getYSpeed()
	{
		return ySpeed;
	}

	public void setXSpeed(double xSpeed) 
	{
		this.xSpeed = xSpeed;
	}

	public void setYSpeed (double ySpeed)
	{
		this.ySpeed = ySpeed;	
	}
}
