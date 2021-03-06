package asteroids.model;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class of spaceships. Spaceships are represented as circles with radius r. 
 * @author Benson De Heel, Xander De Jaegere 
 *  
 */
public class Ship {
	/**
	 * Variable registering the constant speed of light.
	 */
   	static double c = 300000.0;
   	
   	/**
   	 * Initialize this new spaceship with the given parameters.
   	 * @param positionX
   	 * 			The x-component of the position of the spaceship.
   	 * @param positionY
   	 * 			The y-component of the position of the spaceship.
   	 * @param velocityX
   	 * 			The x-component of the velocity of the spaceship.
   	 * @param velocityY
   	 * 			The y-component of the velocity of the spaceship.
   	 * @param radius
   	 * 			The radius of the spaceship.
   	 * @param angle
   	 * 			The orientation angle of the spaceship.
   	 * @effect 	The x-component of the position of this ship will be set to the given positionX.
   	 * 			| setPositionX(positionX)
   	 * @effect	The y-component of the position of this ship will be set to the given positionY.
   	 * 			| setPositionY(positionY)
   	 * @effect	The x-component of the velocity of this ship will be set to the given VelocityX.
   	 * 			| setVelocityX(velocityX)
   	 * @effect	The y-component of the velocity of this ship will be set to the given VelocityY.
   	 * 			| setVelocityY(velocityY)
   	 * @effect	The radius of this ship will be set to the given radius.
   	 * 			| setRadius(radius)
   	 * @effect	The orientation angle of this ship will be set to the given angle.
   	 * 			| setAngle(angle)
   	 */
   	public Ship(double positionX, double positionY, double velocityX, 
   			double velocityY, double radius, double angle) {
   		setPositionX(positionX);
   		setPositionY(positionY);
   		setVelocityX(velocityX);
   		setVelocityY(velocityY);
   		setRadius(radius);
   		setAngle(angle);
   	}
	
   			
   	// Distance DEFENSIVE
   	
   	/**
   	 * Return the x-component of the position of this spaceship.
   	 */
	@Basic
	public double getPositionX() {
		return this.x;
	}
	
	/**
	 * Set the x-component of the position to the given value
	 * @param x
	 */
	@Basic
	public void setPositionX(double x) throws IllegalArgumentException{ 
		if (x == Double.NaN || x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY)
			throw new IllegalArgumentException("The given value is not valid.");
		this.x = x;
	}
	
	/**
	 * Variable registering the x-component of the position of 
	 * this spaceship.
	 */
	private double x;
	
   	/**
   	 * Return the y-component of the position of this spaceship.
   	 */
	@Basic
	public double getPositionY(){
		return this.y;
	}
	
	@Basic
	public void setPositionY(double y) throws IllegalArgumentException {
		if (y == Double.NaN || y == Double.POSITIVE_INFINITY || y == Double.NEGATIVE_INFINITY)
			throw new IllegalArgumentException("The given value is not valid.");

		this.y = y;
	}
	
	/**
	 * Variable registering the y-component of the position of 
	 * this spaceship.
	 */
	private double y;


	public void move(double duration){
		setPositionX(getPositionX()+getVelocityX()*duration);
		setPositionY(getPositionY()+getVelocityY()*duration);
	}
	// Velocity TOTAL
	
   	/**
   	 * Return the x-component of the velocity of this spaceship.
   	 */
	@Basic
	public double getVelocityX(){
		return this.velocityX;
	}
	
	@Basic
	public void setVelocityX(double velocityX){
		if (isValidVelocity(velocityX, getVelocityY()))
			this.velocityX = velocityX;
		else
			this.velocityX = Math.sqrt(Math.pow(c,2.0)-Math.pow(getVelocityY(), 2.0));
		
	}
	
	/**
	 * Variable registering the x-component of the speed vector of
	 * this spaceship.
	 */
	private double velocityX;
	
   	/**
   	 * Return the y-component of the velocity of this spaceship.
   	 */
	@Basic
	public double getVelocityY(){
		return this.velocityY;
	}
	
	/**
	 * Doet niks als de snelheid te hoog is.
	 * @param velocityY
	 */
	@Basic
	public void setVelocityY(double velocityY){
		if (isValidVelocity(getVelocityX(), velocityY))
			this.velocityY = velocityY;
	}
	
	/**
	 * Variable registering the y-component of the speed vector of
	 * this spaceship.
	 */
	private double velocityY;
	
   	/**
   	 * Return length of the velocity vector of this spaceship.
   	 * 
   	 * @return	The length of the velocity vector which is equal to sqrt(velocityX^2+velocityY^2)
   	 * 			| sqrt(getVelocityX()^2 + getVelocityY^2)
   	 */
	public double getTotalVelocity(){
		return Math.sqrt(Math.pow(getVelocityX(), 2.0)+Math.pow(getVelocityY(), 2.0));
	}
	
	/**
	 * Checks whether the total velocity is smaller than or equal to the speed of light (300 000km/s).
	 * 
	 * @param velocityX 
	 * 		  The x-component of the velocity vector.
	 * @param velocityY
	 * 		  The y-component of the velocity vector.
	 * @return True if and only if the velocity is smaller than or equal to the speed of light.
	 * 		   | result == sqrt(velocityY^2 + velocityX^2) <= 300 000
	 */
	private boolean isValidVelocity(double velocityX, double velocityY){
		return Math.sqrt(Math.pow(velocityY, 2.0)+Math.pow(velocityX, 2.0))<=c;
	}
	

	
	public void thrust(double amount){
		if (amount < 0){
			return;
		}
		double newVelocityX = getVelocityX()+amount*Math.cos(getAngle());
		double newVelocityY = getVelocityY()+amount*Math.cos(getAngle());
		
		if (isValidVelocity(newVelocityX, newVelocityY)){
			setVelocityX(newVelocityX);
			setVelocityY(newVelocityY);
		} else {
			double newTotalVelocity = Math.sqrt(Math.pow(newVelocityX, 2.0)+Math.pow(newVelocityY, 2.0));
			setVelocityX(c*newVelocityX/newTotalVelocity);
			setVelocityY(c*newVelocityY/newTotalVelocity);
		}
	}
	
	// Orientation NOMINAL
	/**
	 * Return the orientation angle of this ship, expressed in radians.
	 */
	@Basic
	public double getAngle(){
		return this.angle;
	}
	
	/**
	 * Set the orientation angle of this spaceship, expressed in radians.
	 * @param angle
	 * 		  The new orientation angle of this spaceship
	 * @pre	  The given angle must be between 0 and 2*PI
	 * 		  | 0 < angle < 2*PI
	 * @post  The orientation angle is equal to the given angle.
	 * 		  | new.getAngle() == angle
	 */
	@Basic
	public void setAngle(double angle){
		assert isValidAngle(angle);
		this.angle = angle;
	}
	
	/**
	 * Checks whether the given angle is a valid angle.
	 * @param currentAngle		The current angle of the spaceship.
	 * @return					True if and only if the given angle is between 0 and 2*PI
	 * 							| result == (0<= angle) && (angle<=2*PI)
	 */
	public static boolean isValidAngle(double angle){
		return (0 <= angle) && (angle <= 2*Math.PI);
	}
	
	/**
	 * Turn the spaceship with the given angle.
	 * 	A negative angle will result in an anti-clockwise rotation.
	 *  A positive angle will result in a clockwise rotation.
	 * @param angle
	 * 		  The size of change of rotation the angle.
	 * @effect 	The angle of the ship is incremented by the given angle.
	 * 			|setAngle(getAngle()+angle)
	 */
	public void turn(double angle){
		setAngle(getAngle()+angle);
	}

	/**
	 * Variable registering the orientation angle of the spaceship, expressed in radians.
	 */
	private double angle;
	
	// Radius DEFENSIVE
	/**
	 * return the radius of this spaceship, expressed in kilometers.
	 */
	@Basic
	public double getRadius(){
		return this.radius;
	}
	
	@Basic
	public void setRadius(double radius) throws IllegalArgumentException {
		if (radius == Double.NaN || radius == Double.POSITIVE_INFINITY || radius == Double.NEGATIVE_INFINITY)
			throw new IllegalArgumentException("The given value is not valid.");
		else if (radius < 10)
			throw new IllegalArgumentException("The given value must be larger than 10 km.");
		this.radius = radius;
	}
	
	/**
	 * Variable registering the radius of the spaceship, expressed in kilometers.
	 */
	private double radius;
	
	
	public double getDistanceBetween(Ship otherShip){
		if (this == otherShip)
			return 0;
		
		double deltaX = getPositionX()- otherShip.getPositionX();
		double deltaY = getPositionY()- otherShip.getPositionY();
		double distanceBetweenMid = Math.sqrt(Math.pow(deltaX, 2.0)+Math.pow(deltaY, 2.0)); 
		double minRadius = Math.min(getRadius(), otherShip.getRadius());
		
		// Het middelpunt van een van de twee schepen ligt in de cirkel.

		if (distanceBetweenMid < minRadius){
			return distanceBetweenMid-minRadius;
		}
			
		return distanceBetweenMid-(getRadius()+otherShip.getRadius());
	}
	
	
	public boolean overlap(Ship otherShip){
		return getDistanceBetween(otherShip) < 0;
	}
	
	public double getTimeToCollision(Ship otherShip){
		double deltaPositionX = otherShip.getPositionX()-getPositionX();
		double deltaPositionY = otherShip.getPositionY()-getPositionY();
		
		double deltaVelocityX = otherShip.getVelocityX()-getVelocityX();
		double deltaVelocityY = otherShip.getVelocityY()-getVelocityY();
		
		double d = 
	}
}

