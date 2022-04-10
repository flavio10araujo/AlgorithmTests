package ObjectedOrientedDesign.parkingSpots;

import java.util.List;

public class ParkingLot {

	private ParkingSpot[] parkingSpots;
	
	public ParkingLot(int parkingSpotsQtd) {
		parkingSpots = new ParkingSpot[parkingSpotsQtd];
		this.initializeSpots();
	}
	
	public ParkingLot(List<String> parkingSpotsSize) {
		parkingSpots = new ParkingSpot[parkingSpotsSize.size()];
		this.initializeSpotsBySizes(parkingSpotsSize);
	}
	
	private void initializeSpots() {
		for (int i = 0; i < parkingSpots.length; i++) {
			parkingSpots[i] = new ParkingSpot();
		}
	}
	
	private void initializeSpotsBySizes(List<String> parkingSpotsSize) {
		for (int i = 0; i < parkingSpots.length; i++) {
			parkingSpots[i] = new ParkingSpot(parkingSpotsSize.get(i));
		}
	}
	
	public void parkVehiculeByDescription(int parkingSpotID, String size, String color, String brand) {
		Vehicule vehicule = new Car();
		((Car) vehicule).setParkingSpotSize(ParkingSpotSize.valueOf(size));
		((Car) vehicule).setColor(color);
		((Car) vehicule).setBrand(brand);
		
		this.park(parkingSpotID, vehicule);
	}
	
	public void park(int parkingSpotID, Vehicule vehicule) {
		if (parkingSpotID >= 0 && parkingSpotID < parkingSpots.length) {
			ParkingSpot parkingSpot = parkingSpots[parkingSpotID];
			int nextSpot = parkingSpotID;
			
			// while:
			// 		there is already a car at the parking spot
			//		OR the parking spot size is smaller than the vehicule size.
			while(parkingSpot.getVehicule() != null || parkingSpot.getSize().value < vehicule.getParkingSpotSize().value) {
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
			
			if (parkingSpot.getVehicule() == null || parkingSpot.getSize().value >= vehicule.getParkingSpotSize().value) {
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