package ObjectedOrientedDesign.vendingMachine;

public enum Coin {
	penny(1), nickel(5), dime(10), quarter(25);

    public final int value;

    Coin(int value) {
        this.value = value;
    }
}