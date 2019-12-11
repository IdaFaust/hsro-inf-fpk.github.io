package futures;

interface Callable<T> {
	T call() throws InterruptedException;
}
