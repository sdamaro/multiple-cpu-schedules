import java.util.*;

public class RoundRobin extends Scheduler {
	int timeSlice = 100;
	boolean preempt = false;
	
	@Override
	public void Run() {
		checkNewProcesses();
		while (!queue.isEmpty() || !inactive.isEmpty()) {
			if (!queue.isEmpty()){
				//new process is entering the system. declaring the wait time and outputting to screen.
				queue.get(0).waitTime += currentTime - queue.get(0).lastTimeAccessed;
				waitTimes.add(queue.get(0).waitTime);
				if (queue.get(0).turnaroundTime == 0) System.out.println("[time " + currentTime + "ms] Process " + queue.get(0).ID + " accessed CPU for the first time (wait time  " + queue.get(0).waitTime + "ms)");
				
				//incrementing each millisecond at a time for the breadth of the current process
				while (queue.get(0).neededCPUTime > 0){
					if (queue.get(0).turnaroundTime % timeSlice == 0) {
						preempt = true;
						break;
					}
					//decrement remaining time, increment elapsed time
					queue.get(0).neededCPUTime--;
					queue.get(0).turnaroundTime++;
					currentTime++;
					checkNewProcesses();
					System.out.println("current: "+ currentTime + "\tturnaround: " + queue.get(0).turnaroundTime);
				}
				
				//finishing up the process by defining the turnaround times and output
				
				if (!preempt) {
					turnaroundTimes.add(queue.get(0).turnaroundTime);
					System.out.println("[time " + currentTime + "ms] Process " + queue.get(0).ID + " terminated (turnaround time " + queue.get(0).turnaroundTime + "ms, wait time " + queue.get(0).waitTime + "ms)");
					if(!(queue.size() <= 1 && inactive.isEmpty())) 
						contextSwitchDone();
					else
						return;
				}
				else {
					//keeping track of context switches for all except the last transition.
					if(!(queue.size() <= 1 && inactive.isEmpty())) 
						contextSwitch();
				}
				
			}
			else{
				currentTime++;
				checkNewProcesses();
			}
		}
	}
	
	@Override
	public void checkNewProcesses() {
		while (!inactive.isEmpty() && inactive.get(0).startTime >= currentTime) {
			queue.add(inactive.get(0));
			System.out.println("[time " + currentTime + "ms] Process " + inactive.get(0).ID + " created (requiring " + inactive.get(0).neededCPUTime + "ms CPU time, priority " + inactive.get(0).priority + ")");
			inactive.remove(0);
		}
	}
	
	
	
	@Override
	void contextSwitch(){
		Process p = queue.remove(0);
		queue.add(p);
		System.out.println("Context switch (swapped out process) " + p.ID + " for process " + queue.get(0).ID + ")");
		currentTime += 9;
	}
	void contextSwitchDone() {
		Process p = queue.remove(0);
		System.out.println("Context switch (swapped out process) " + p.ID + " for process " + queue.get(0).ID + ")");
		preempt = false;
	}
}