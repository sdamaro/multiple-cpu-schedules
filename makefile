all: main1 main2

main1: MainApp.java Scheduler.java ShortestJobFirst.java RoundRobin.java Process.java PreemptivePriority.java MainApp.java FirstComeFirstServe.java CPUTimeComparator.java startTimeComparator.java 
	javac MainApp.java
	javac CPUTimeComparator.java
	javac startTimeComparator.java
	javac priorityComparator.java

main2: MainApp2.java Scheduler.java ShortestJobFirst.java RoundRobin.java Process.java PreemptivePriority.java MainApp.java FirstComeFirstServe.java CPUTimeComparator.java startTimeComparator.java 
	javac MainApp2.java
	javac CPUTimeComparator.java
	javac startTimeComparator.java
	javac priorityComparator.java


clean:
	rm -f *.class