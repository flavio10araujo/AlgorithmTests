package ObjectedOrientedDesign.parkingSpots;

public class ParkingSpot {

	private ParkingSpotSize size;
	private Vehicule vehicule;
	
	public ParkingSpot() {
		
	}
	
	public ParkingSpot(String size) {
		this.size = ParkingSpotSize.valueOf(size);
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
	
	public String printVehicule() {
		if (vehicule == null) {
			return "Empty";
		} else {
			return vehicule.toString();
		}
	}

	public ParkingSpotSize getSize() {
		return size;
	}

	public void setSize(ParkingSpotSize size) {
		this.size = size;
	}
}