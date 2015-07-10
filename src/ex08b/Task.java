package ex08b;

public class Task implements Runnable {
	private int id;
	private int orderNo;
	private Resource resource;
	
	public Task(int id, Resource resource) {
		this.resource = resource;
		this.id = id;
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
	
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	public int getOrderNo() {
		return orderNo;
	}
}
