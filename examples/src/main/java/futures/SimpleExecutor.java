package futures;

import java.util.concurrent.ExecutionException;

public class SimpleExecutor implements Executor {
	@Override
	public <T> Future<T> async(Callable<T> task) {
		return new Future<T> () {
			Thread t;
			T result;
			ExecutionException e;

			// constructor block
			{
				t = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							result = task.call();
						} catch (Exception ex) {
							e = new ExecutionException(ex);
						}
					}
				});

				t.start();
			}

			@Override
			public T get() throws InterruptedException, ExecutionException {
				t.join();
				if (e != null)
					throw e;
				return result;
			}
		};
	}

	public static void main(String[] args) {
		SimpleExecutor ex = new SimpleExecutor();

		int a = 4, b = 12;
		Future<Integer> f1 = ex.async(() -> a / b);
		Future<Integer> f2 = ex.async(() -> a * b);
		Future<Integer> f3 = ex.async(() -> a + b);
		Future<Integer> f4 = ex.async(() -> a - b);

		// do other things if you like...
		try {
			System.out.println(f1.get());
			System.out.println(f4.get());System.out.println(f3.get());System.out.println(f2.get());
		} catch (ExecutionException | InterruptedException e) {
			System.out.println("The thread raised an exception: "
					+ e.getCause());
		}
	}
}
