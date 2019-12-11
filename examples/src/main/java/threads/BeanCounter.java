package threads;

import java.util.Arrays;

class BeanCounter implements Runnable {

	private final String name;
	private final double[] data;
	BeanCounter(String name, int n) {
		this.name = name;
		this.data = new double [n];
	}

	@Override
	public void run() {
		System.out.println(name + " is starting...");
		Arrays.sort(data);
		System.out.println(name + " is done!");
	}

	public static void main(String[] args) throws InterruptedException {
		BeanCounter b1 = new BeanCounter("Bureaucrat 1", 10000);
		BeanCounter b2 = new BeanCounter("Bureaucrat 2", 1000);

		new Thread(b1).start();
		new Thread(b2).start();

		//new Thread(() -> System.out.println("Hallo")).start();
		System.out.println("main() done!");
	}
}
