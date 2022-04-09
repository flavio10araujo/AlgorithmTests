package ObjectedOrientedDesign.callCenter;

import java.util.List;

public class Respondent extends Employee {

	public int performanceRating;
	
	public Respondent(String name) {
        super(name);
        performanceRating = 0;
    }

    @Override
    public String toString() {
        return String.format("Respondent %s", name);
    }

    @Override
    public int rank() {
        return 0;
    }
    
    @Override
    public void work(CallCenter callCenter, List<String> output) {
        performanceRating++;
    }
}