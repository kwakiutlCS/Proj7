package Ex10;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Resource {
	private Mutex mutex = new Mutex();
	
	public void enter(Task task) {
		System.out.println(task+" asked permission to execute function");
		mutex.lock();
		System.out.println("\n"+task+" is accessing the resource\n");
		execute(task);
		leave(task);
	}
	
	
	private void leave(Task task) {
		System.out.println(task+" stopped execution on resource");
		mutex.unlock();
	}
	
	// critical region
	private void execute(Task task) {
		// simulating work
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
	}
}
