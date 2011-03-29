all: MainApp.java Scheduler.java ShortestJobFirst.java RoundRobin.java Process.java PreemptivePriority.java MainApp.java FirstComeFirstServe.java CPUTimeComparator.java startTimeComparator.java 
	javac MainApp.java
	javac CPUTimeComparator.java
	javac startTimeComparator.java

clean:
	rm -f *.class