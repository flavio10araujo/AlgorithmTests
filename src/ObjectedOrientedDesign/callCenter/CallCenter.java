package ObjectedOrientedDesign.callCenter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CallCenter {

	public final int MAX_RANKS = 3;
	public List<List<Employee>> employees;
	public Map<String, Employee> employeeMap;
    public List<Deque<CallInstance>> callQueue;
    public Map<String, CallInstance> callMap;
	
	public CallCenter() {
		employees = new ArrayList<>();
        callQueue = new ArrayList<>();
        
        for (int i = 0; i < MAX_RANKS; i++) {
            employees.add(new ArrayList<>());
            callQueue.add(new ArrayDeque<>());
        }
        
        employeeMap = new HashMap<>();
        callMap = new HashMap<>();
	}

	public void hire(Employee employee) {
        employees.get(employee.rank()).add(employee);
        employeeMap.put(employee.toString(), employee);
    }
	
	public void remove(Employee employee) {
        List<Employee> employeeList = employees.get(employee.rank());
        
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
            employeeMap.remove(employee.toString());
        }
    }
	
	public void promote(Employee employee, String newRole) {
        remove(employee);
        Employee newEmployee = Employee.createEmployee(newRole, employee.name);
        hire(newEmployee);
    }

	public void addNumberToQueue(String number) {
        if (!callMap.containsKey(number)) {
            callMap.put(number, new CallInstance(number));
            callQueue.get(0).offer(callMap.get(number));
        }
    }

	public void resolveQueue(List<String> output) {
        for (int i = MAX_RANKS - 1; i >= 0; i--) {
            Deque<CallInstance> currentQueue = callQueue.get(i);
            List<Employee> currentEmployees = employees.get(i);
            
            while (!currentQueue.isEmpty()) {
                CallInstance top = currentQueue.getFirst();
                boolean resolved = false;
                
                for (Employee employee : currentEmployees) {
                    if (top.startCall(employee, output)) {
                        resolved = true;
                        break;
                    }
                }
                
                if (resolved) {
                    currentQueue.poll();
                } else {
                    break;
                }
            }
        }
    }
	
	public boolean escalate(String phone, List<String> output) {
        if (callMap.containsKey(phone)) {
            CallInstance currentCall = callMap.get(phone);
            
            if (currentCall.escalate(output)) {
                callQueue.get(currentCall.rank).offer(currentCall);
                return true;
            }
        }
        
        return false;
    }
}