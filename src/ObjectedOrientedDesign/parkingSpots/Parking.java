package ObjectedOrientedDesign.parkingSpots;

public class Parking {

	private ParkingSpot[] parkingSpots;
	
	public Parking(int parkingSpotsQtd) {
		parkingSpots = new ParkingSpot[parkingSpotsQtd];
		this.initializeSpots();
	}
	
	private void initializeSpots() {
		for (int i = 0; i < parkingSpots.length; i++) {
			parkingSpots[i] = new ParkingSpot();
		}
	}
	
	public void parkVehiculeByDescription(int parkingSpotID, String size, String color, String brand) {
		Vehicule vehicule = new Car();
		((Car) vehicule).setSize(CarSize.valueOf(size));
		((Car) vehicule).setColor(color);
		((Car) vehicule).setBrand(brand);
		
		this.park(parkingSpotID, vehicule);
	}
	
	public void park(int parkingSpotID, Vehicule vehicule) {
		if (parkingSpotID >= 0 && parkingSpotID < parkingSpots.length) {
			ParkingSpot parkingSpot = parkingSpots[parkingSpotID];
			int nextSpot = parkingSpotID;
			
			while(parkingSpot.getVehicule() != null) {
				nextSpot++;
				
				if (nextSpot == parkingSpots.length) {
					nextSpot = 0;
				}
				
				// There is no empty place.
				if (nextSpot == parkingSpotID) {
					break;
				}
				
				parkingSpot = parkingSpots[nextSpot];
			}
			
			if (parkingSpot.getVehicule() == null) {
				parkingSpot.setVehicule(vehicule);
			}
		}
	}
	
	public void removeCar(int parkingSpotID) {
		ParkingSpot parkingSpot = parkingSpots[parkingSpotID];
		parkingSpot.setVehicule(null);
	}
	
	public String printSpot(int parkingSpotID) {
		ParkingSpot parkingSpot = parkingSpots[parkingSpotID];
		return parkingSpot.printVehicule();
	}
	
	public String printFreeSpots() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < parkingSpots.length; i++) {
			if (parkingSpots[i].getVehicule() == null) {
				sb.append(i + " ");
			}
		}
		
		return sb.toString();
	}
}