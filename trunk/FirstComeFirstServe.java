
public class FirstComeFirstServe extends Scheduler {
	@Override
	public void Run(){
		while(stillRunning == true){
		if (!queue.isEmpty()){
			//new process is entering the system. declaring the wait time and outputting to screen.
			queue[0].waitTime = currentTime;
			waitTimes.add(queue[0].waitTime);
			println("Process " + queue[0].ID + " accessed CPU for the first time (wait time  " + queue[0].waitTime + "ms)");
			
			//incrementing each millisecond at a time for the breadth of the current process
			while (queue[0].neededCPUTime > 0){
				queue[0].neededCPUTime--;
				currentTime++;
			}
			
			//finishing up the process by defining the turnaround times and output
			queue[0].turnaroundTime = currentTime - queue[0].waitTime;
			turnaroundTimes.add(queue[0].turnaroundTime);
			println("Process " + queue[0].ID + " terminated (turnaround time " + queue[0].turnaroundTime + "ms, wait time " + queue[0].waitTime + "ms)");
						
			if(!(queue[0].ID == sizeOfQueue)){
				contextSwitch();
			}
			queue.remove(0);
		}
	}
	
	@Override
	public void contextSwitch(){
		println("Context switch (swapped out process) " + queue[0].ID + " for process " + queue[1].ID + ")");
		for(int j = 0; j < 9; j++)
			currentTime++;
	}

	@Override
	public void addProcesses(){
		for (int i = 0; i < sizeOfQueue; i++) {
			println("[time " + currentTime + "ms] Process " + queue[0].ID + " created (requiring " + queue[0].neededCPUTime + "ms CPU time, priority " + queue[0].priority + ")");
			queue.add(new Process(i));
		}
	}
}
