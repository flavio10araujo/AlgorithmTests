package ObjectedOrientedDesign.callCenter;

import java.util.List;

public class Director extends Employee {

	public Director(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("Director %s", name);
    }

    @Override
    public int rank() {
        return 2;
    }
    
    @Override
    public void work(CallCenter callCenter, List<String> output) {
        output.add(String.format("%s holds a meeting", this.toString()));
    }
}