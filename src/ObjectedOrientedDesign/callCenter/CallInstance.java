package ObjectedOrientedDesign.callCenter;

import java.util.List;

public class CallInstance {

	public final int MAX_RANKS = 3;
	public String number;
	public Employee talkingTo;
	public int rank;

	public CallInstance(String number) {
		this.number = number;
		this.talkingTo = null;
		rank = 0;
	}

	public boolean startCall(Employee target, List<String> output) {
        if (talkingTo == null && target.talkingTo == null) {
            talkingTo = target;
            target.talkingTo = this;
            output.add(String.format("Connecting %s to %s", this.toString(), target.toString()));
            
            if (target instanceof Respondent) {
                ((Respondent) target).performanceRating++;
            }
            
            return true;
        }
        
        return false;
    }

	public boolean endCall(List<String> output) {
		if (talkingTo != null) {
			output.add(String.format("Call between %s and %s ended", this.toString(), this.talkingTo.toString()));
			talkingTo.talkingTo = null;
			talkingTo = null;
			return true;
		}
		
		return false;
	}
	
	public boolean escalate(List<String> output) {
        if (talkingTo != null && rank < MAX_RANKS - 1) {
            if (endCall(output)) {
                rank++;
                return true;
            }
        }
        
        return false;
    }

	@Override
	public String toString() {
		return number;
	}
}