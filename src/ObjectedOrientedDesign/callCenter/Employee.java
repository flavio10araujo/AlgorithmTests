package ObjectedOrientedDesign.callCenter;

import java.util.List;

public abstract class Employee {

	public CallInstance talkingTo;
	public String name;

	public Employee(String name) {
		talkingTo = null;
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public static Employee createEmployee(String role, String name) {
        if (role.equals("Respondent")) {
            return new Respondent(name);
        } else if (role.equals("Manager")) {
            return new Manager(name);
        } else if (role.equals("Director")) {
            return new Director(name);
        }
        
        return null;
    }
	
	public abstract int rank();
	
	public abstract void work(CallCenter callCenter, List<String> output);
}