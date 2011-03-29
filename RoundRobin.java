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
					if (queue.get(0).turnAroundTime % timeSlice == 0) {
						preempt = true;
						break;
					}
					//decrement remaining time, increment elapsed time
					queue.get(0).neededCPUTime--;
					queue.get(0).turnaroundTime++;
					currentTime++;
					checkNewProcesses();
				}
				
				//finishing up the process by defining the turnaround times and output
				
				if (!preempt) {
					turnaroundTimes.add(queue.get(0).turnaroundTime);
					System.out.println("[time " + currentTime + "ms] Process " + queue.get(0).ID + " terminated (turnaround time " + queue.get(0).turnaroundTime + "ms, wait time " + queue.get(0).waitTime + "ms)");
				}
				
				//keeping track of context switches for all except the last transition.
				if(!(queue.isEmpty() && inactive.isEmpty())) 
					contextSwitch();
				
				Process p = queue.remove(0);
				if (preempt) {
					queue.add(p);
					preempt = false;
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
		while (inactive.get(0).startTime >= currentTime) {
		   queue.add(inactive.get(0));
		   System.out.println("[time " + currentTime + "ms] Process " + inactive.get(0).ID + " created (requiring " + inactive.get(0).neededCPUTime + "ms CPU time, priority " + inactive.get(0).priority + ")");
		   inactive.remove(0);
		}
	}
	
	
	
	@Override
	public void contextSwitch(){
		Process p = queue.remove(0);
		queue.add(p);
		println("Context switch (swapped out process) " + p.ID + " for process " + queue.get(0).ID + ")");
		currentTime += 9;
	}
	public void contextSwitchDone() {
		Process p = queue.remove(0);
		println("Context switch (swapped out process) " + p.ID + " for process " + queue.get(0).ID + ")");
	}
}