
public class FirstComeFirstServe extends Scheduler {
	@Override
	public void Run(){
		checkNewProcesses();
		while(!queue.isEmpty() || !inactive.isEmpty()){
			if (!queue.isEmpty()){
				//new process is entering the system. declaring the wait time and outputting to screen.
				queue.get(0).waitTime = currentTime;
				waitTimes.add(queue.get(0).waitTime);
				System.out.println("[time " + currentTime + "ms] Process " + queue.get(0).ID + " accessed CPU for the first time (wait time  " + queue.get(0).waitTime + "ms)");
				
				//incrementing each millisecond at a time for the breadth of the current process
				while (queue.get(0).neededCPUTime > 0){
					queue.get(0).neededCPUTime--;
					currentTime++;
					checkNewProcesses();
				}
				
				//finishing up the process by defining the turnaround times and output
				queue.get(0).turnaroundTime = currentTime - queue.get(0).waitTime;
				turnaroundTimes.add(queue.get(0).turnaroundTime);
				System.out.println("[time " + currentTime + "ms] Process " + queue.get(0).ID + " terminated (turnaround time " + queue.get(0).turnaroundTime + "ms, wait time " + queue.get(0).waitTime + "ms)");
				
				//keeping track of context switches for all except the last transition.
				if(!(queue.get(0).ID == sizeOfQueue)){
					contextSwitch();
				}
				queue.remove(0);
			}
			else{
				currentTime++;
				checkNewProcesses();
			}
		}
	}
	
	@Override
	public void contextSwitch(){
		System.out.println("[time " + currentTime + "ms] Context switch (swapped out process) " + queue.get(0).ID + " for process " + queue.get(1).ID + ")");
		for (int j = 0; j < 9; j++){
			currentTime++;
			checkNewProcesses();
		}
	}

	@Override
	public void addProcesses(){
		
	}
}
