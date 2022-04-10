package ObjectedOrientedDesign.parkingSpots;

public class Car extends Vehicule {

	private CarSize size;
	private ParkingSpotSize parkingSpotSize;
	private String color;
	private String brand;
	
	public CarSize getSize() {
		return size;
	}
	
	public void setSize(CarSize size) {
		this.size = size;
	}
	
	@Override
	public String getColor() {
		return color;
	}
	
	@Override
	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String getBrand() {
		return brand;
	}
	
	@Override
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String toString() {
		return String.format("%s %s %s", this.parkingSpotSize, this.color, this.brand);
	}

	public void setParkingSpotSize(ParkingSpotSize parkingSpotSize) {
		this.parkingSpotSize = parkingSpotSize;
	}
	
	@Override
	public ParkingSpotSize getParkingSpotSize() {
		return this.parkingSpotSize;
	}
}