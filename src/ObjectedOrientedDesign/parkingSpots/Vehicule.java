package ObjectedOrientedDesign.parkingSpots;

public abstract class Vehicule {

	public abstract String getColor();
	
	public abstract void setColor(String color);
	
	public abstract String getBrand();
	
	public abstract void setBrand(String brand);
	
	public abstract ParkingSpotSize getParkingSpotSize();
}