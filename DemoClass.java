public class DemoClass {
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
private static DemoClass demoClass = new DemoClass();
public static void main(String args[]) {
	System.out.println(DemoClass.uuidStat);
	InnerClass innerClass = demoClass.new InnerClass();
	DemoClass.NestedClass.run();
}
}

