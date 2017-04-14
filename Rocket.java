/*
	Parent Class: Rocket Configurations
•	Subclasses: Missiles, Space rockets, Space Probes, Sleds, Rocket Trains
•	Propellant, Propellant Tank, Nozzle, Payload, Wheels.
•	Escape Velocity, Rocket Velocity, Navigation: Satellite
•	Escape System
•	Planetary Launch, Destination. 
•	Fuel Capacity, Launch Duration.
•	Rocket mass, final mass. 
•	Separation Execution, Twin Booster 
•	Increasing distance, decreases fuel.
•	If certain level is reached, refill permission.
•	Destination distance. Possible locations: Mars, Moon.
•	Moon Distance = x
•	Mars distance = y
•	Any level of distance, calculate fuel. 
•	1lbs Weight = 24lbs of fuel
•	Time elapsed on rocket, on person. 
•	Velocity calculate: using Tsiolkovsky Rocket Eq.

	The parent class is RocketParts since these are common in every rocket type.
	The subclass is RocketInt which contains the type of rocekts. 
	The code explains the basic parts of the rocket and what components are initially necessary. 
*/
import java.util.Scanner;
class RocketParts{
	private double FuelCapacity;
	private double payLoad;
	private double distanceToDest;
	private double rocketMass;
	private double initVelocity;
	private double escapeVelo;
	private String navigation;
	private int timeThrust;
	private double engineExhaust;
	public RocketParts(){
		
	}
	public RocketParts(double rocketMass, double initVelocity, double escapeVelo, String navigation, boolean fuelCheck, double payLoad, int timeThrust, double engineExhaust, double FuelCapacity){
		this.rocketMass = rocketMass;
		this.initVelocity = initVelocity;
		this.escapeVelo = escapeVelo;
		this.navigation = navigation;
		this.payLoad = payLoad;
		this.timeThrust = timeThrust;
		this.engineExhaust = engineExhaust;
		if (FuelCapacity <= 0.0){
			System.out.println("Please, refill!");
			System.exit(0); //If no fuel, then exit. 
		}
		else{
			this.FuelCapacity = FuelCapacity;
		}
	}
	public double getRocketMass() {
		return rocketMass;
	}
	public void setRocketMass(double rocketMass) {
		this.rocketMass = rocketMass;
	}
	public double getPayLoad() {
		return payLoad;
	}
	public void setPayLoad(double payLoad) {
		this.payLoad = payLoad;
	}
	public double getInitVelocity() {
		return initVelocity;
	}
	public void setInitVelocity(double initVelocity) {
		this.initVelocity = initVelocity;
	}
	public double getEscapeVelocity(){
		return escapeVelo;
	}
	public void setEscapeVelocity(double escapeVelo){
		this.escapeVelo = escapeVelo;
	}
	public double getDistanceToDest(){
		return distanceToDest;
	}
	public void setDistanceToDest(double distanceToDest){
		this.distanceToDest = distanceToDest;
	}
	public int getTimeThrust(){
		return timeThrust;
	}
	public void setTimeThrust(int timeThrust){
		this.timeThrust = timeThrust;
	}
	public double getEngineExhaust(){
		return engineExhaust;
	}
	public void setEngineExhaust(double engineExhaust){
		this.engineExhaust = engineExhaust;
	}
	public double getFuelCapacity(){
		return FuelCapacity;
	}
	public void setFuelCapacity(double FuelCapacity){
		this.FuelCapacity = FuelCapacity;
	}
	public String getNavigation(){
		return navigation;
	}
	public void setNavigation(String navigation){
		this.navigation = navigation;
	}
}
class RocketInt extends RocketParts{
	private String Rocket;
	
	public RocketInt(double rocketMass, double initVelocity, double escapeVelo, String navigation, boolean fuelCheck, double payLoad, int timeThrust, double engineExhaust, double FuelCapacity, String type){
		super(rocketMass, initVelocity, escapeVelo, navigation, fuelCheck, payLoad, timeThrust, engineExhaust, FuelCapacity);
		type = Rocket;
	}
	public double totalMass(){
		double totalMass;
		totalMass = getRocketMass() + getPayLoad();
		return totalMass;
	}
	public double calVelocity(){
		double velocity;
		velocity = getEngineExhaust()*Math.log(totalMass()/getRocketMass());
		return velocity;
	}
	public double distanceTravelled(){
		double distance = calVelocity()*getTimeThrust();
		return distance;
	}
	public double initfuel(){
		double Initialfuel = getFuelCapacity();
		return Initialfuel;
	}
	public double finalfuel(){ //per ride, 25% of the initial fuel remains.
		double finalFuel = 0.0;
		setFuelCapacity(getFuelCapacity()*0.25);
		finalFuel += getFuelCapacity();
		return finalFuel;
	}
	public void burstOff(){
		System.out.println("Burst Off!");
	}
	public void detach(){
		System.out.println("The shuttle is detached!");
	}
}
public class Rocket{
	public static void main(String[]args){
		int count = 10; 
		Scanner in = new Scanner(System.in);
		String destination;
		double payloadMass;
		double fuel;
		System.out.println("Enter the destination: ");
		destination = in.next();
		System.out.println("Enter the payload: ");
		payloadMass = in.nextDouble();
		System.out.println("Enter the fuel available: ");
		fuel = in.nextDouble();
		RocketInt inp = new RocketInt(500, 40, 7.9, destination, true, payloadMass, 7, 80, fuel, "Passenger Rocket");
		System.out.println("Initializing the rocket!");
			System.out.println("Fuel Requirements Checked.");
			System.out.println("Let the count down begin!");
			for(int i = 1; i <= count; i++){
				System.out.println("\t" + i);
			}
			System.out.println("The Rocket Journey has started towards " + destination + ":");
			System.out.println();
			System.out.println("The Mass of rocket is: " + inp.totalMass());
			System.out.println("The initial fuel is: " + inp.initfuel() + "lbs");
			System.out.println("The velocity of the rocket is: " + inp.calVelocity());
			System.out.println();
			inp.burstOff();
			System.out.println("Travelling...");
			System.out.println("After n hours...");
			inp.detach();
			System.out.println("The rocket moves further to destination.");
			System.out.println();
			System.out.println("The Rocket Journey has ended: ");
			System.out.println("The distance travelled is " + inp.distanceTravelled() + "km");
			System.out.println("The final fuel is: " + inp.finalfuel() + "lbs");
	}
}