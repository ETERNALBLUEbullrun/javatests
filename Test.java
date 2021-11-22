class TestRunnable implements Runnable {
	private int index_;
	TestRunnable(int index) {
		index_ = index;
		System.out.println("E: this should not be printed");
	}
	@Override
	public void run() {
		System.out.printf("Thread[%d].start=%d", java.lang.Thread.currentThread().getId(), index_);
	}
}
public class Test {
	static long x_;
	public static void setX(int x) {
		x_ = x;
	}
	public static void setX(long x) {
		x_ = x;
	}
	public static int getX() {
		return (int)x_;
	}
	static enum Random {
		ZERO,
		ONE
	}
//	class InnerClass { };
	public static void testPrintf() {
		String differentAddress = "a";
		if(differentAddress == differentAddress + "") {
			System.out.println("E: + operator returned old address");
		}
		if(!differentAddress.equals("a" + "")) {
			System.out.println("E: .equals fail");
		}
		try {
//			String methodName = new Throwable().getStackTrace()[0].getMethodName();
//			String methodName = InnerClass.class.getEnclosingMethod().getName();
//			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
//			String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
			String methodName = "testPrintf";
			System.out.printf("%7.8s", methodName + " entered");
			System.out.printf("%7d", (int)01234.5);
			System.out.printf("%7.8f", (double)01234.5);
			System.out.printf("%b\n", (boolean)true);
		} catch(java.util.IllegalFormatPrecisionException e) {
			System.out.println("IllegalFormatPrecisionException ");
//			e.printStackTrace();
		}
	}
	public static void testArgs(String args[]) {
		for(int i = 0; args.length > i; ++i) {
			try {
				//setX(Integer.parseInt(args[i]));
				setX(Math.round(Double.parseDouble(args[i])));
				System.out.print(" round(arg1) = " + getX());
				setX((int)Double.parseDouble(args[i]));
				System.out.print(" (int)arg1 = " + getX());
			} catch(NumberFormatException e) {
//				System.out.print("NumberFormatException");
				System.out.printf("args[%d]=\"%s\"\n", i, args[i]);
			}
		}
	}
	public static void testMath() {
		int n = 3;
		double[] constant = {
			Math.PI,
			Math.E,
			Double.NEGATIVE_INFINITY
		};
		double[] rand = new double[n];
		for(int i = 0; n > i; ++i) {
			rand[i] = Math.random();
		}
		int x = 1, y = 2;
		x ^= y;
		y ^= x;
		x ^= y;
		if(x != 2 || y != 1) {
			System.out.println("mathSwap failed");
		}
		System.out.print("PI=" + constant[0]);
		System.out.print(" E=" + constant[1]);
		System.out.print(" -INF=" + constant[2]);
		System.out.printf(" [0,1)=" + rand[0]);
		System.out.printf(" [0,1)=%1.2f=%1.2e", rand[1], rand[1]);
		System.out.printf(" %b=", 0.5 > rand[2] ? false : true);
		switch((int)Math.round(rand[2])) {
			case 0: System.out.println("case 0"); break;
			case 1: System.out.println("case 1"); break;
		}
	}
	public static void testThread() {//throws InterruptedException {
		final int n = 8;
		Thread threads[] = new Thread[n];
		for(int i = 0; n > i; ++i) {
			try {
				final int threadIndex = i;
				threads[i] = new Thread(new TestRunnable(i));
				/*threads[i] = new Thread(new Runnable() {
					@Override
					public void run() {
						System.out.printf("threads[%d].start.getId=%d", threadIndex, java.lang.Thread.currentThread().getId());
					}
				});*/
				threads[i].start();
//				threads[i] = new Thread( () -> {
//					System.out.printf("threads[%i]", currentThread().getId());
//				});
				System.out.printf("threads[%d].getId=%d ", i, threads[i].getId());
			} catch (Exception e) {
				System.out.printf("E: threads[%d].start threw", i);
				e.printStackTrace();
			}
		}
		for(int i = n; 0 != i--; ) {
			long threadID = threads[i].getId();
			try {
				threads[i].join();
				System.out.printf(" %d->threads[%d].join", threadID, i);
			} catch (InterruptedException e) {
				System.out.println("E: {%d}.join=InterruptedException");
				e.printStackTrace();
			}
		}
	}
	public static void main(String args[]) {
		try {
			testPrintf();
			testArgs(args);
			testMath();
			testThread();
//			testThreadPool();
		} catch (NoClassDefFoundError e) {
			System.out.println("NoClassDefFoundError, check for NAME$*.class to link");
			e.printStackTrace();
		}
	}
}

