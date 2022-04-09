package ObjectedOrientedDesign.callCenter;

import java.util.List;

public class Manager extends Employee {

	public Manager(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("Manager %s", name);
    }

    @Override
    public int rank() {
        return 1;
    }
    
    @Override
    public void work(CallCenter callCenter, List<String> output) {
        Respondent topWorker = null;
        
        for (Employee employee : callCenter.employees.get(0)) {
            if (employee.talkingTo == null && employee instanceof Respondent) {
                Respondent respondent = (Respondent) employee;
                
                if (topWorker == null || respondent.performanceRating > topWorker.performanceRating) {
                    topWorker = respondent;
                }
            }
        }
        
        if (topWorker != null) {
            output.add(String.format("Respondent %s is promoted to Manager under the authority of Manager %s", topWorker.name, name));
            callCenter.promote(topWorker, "Manager");
        }
    }
}