package Ex10;


public class Resource {
	private Mutex mutex = new Mutex();
	
	public void enter(Task task) {
		//System.out.println(task+" asked permission to execute function");
		mutex.lock();
		System.out.println(task+" is accessing the resource");
		execute(task);
		leave(task);
	}
	
	
	private void leave(Task task) {
		System.out.println(task+" stopped execution on resource\n");
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
