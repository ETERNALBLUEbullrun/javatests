public class TestClass {
private String uuid = "983UH4K0VNMZ85";
private static String uuidStat = "963YGRJHSHKHET";
public static class NestedClass {
	public static void run() {
		System.out.println(uuidStat);
	}
}
public class InnerClass {
	InnerClass() {
		System.out.println(uuid);
	}
}
private static TestClass testClass = new TestClass();
public static void main(String args[]) {
	System.out.println(TestClass.uuidStat);
	InnerClass innerClass = testClass.new InnerClass();
	TestClass.NestedClass.run();
}
}

