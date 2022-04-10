package ObjectedOrientedDesign.parkingSpots;

public enum ParkingSpotSize {
	Small(1), Medium(2), Large(3);

    public final int value;

    ParkingSpotSize(int value) {
        this.value = value;
    }
}