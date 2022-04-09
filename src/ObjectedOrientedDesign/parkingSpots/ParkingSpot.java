package ObjectedOrientedDesign.parkingSpots;

public class ParkingSpot {

	private Vehicule vehicule;
	
	public ParkingSpot() {
		
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
}