package ex08;

public class Task implements Runnable {
	private int id;
	private Resource resource;
	
	public Task(int id, Resource resource) {
		this.id = id;
		this.resource = resource;
	}
	
	@Override
	public String toString() {
		return "Task no: "+id;
	}

	@Override
	public void run() {
		while (true) {
			resource.enter(this);
		}
	}
}
