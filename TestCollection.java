import java.util.Collection;	//abstract//?Serializable//?Cloneable
import java.util.Queue;		//interface//Collection<E>
import java.util.PriorityQueue;	//Queue<E>
import java.util.Deque;		//interface//Queue<E>
import java.util.ArrayDeque;	//Deque<E>
import java.util.List;		//abstract//Collection<E>
import java.util.ArrayList;	//List<E>
import java.util.LinkedList;	//List<E>//Deque<E>
import java.util.Vector;	//List<E>
import java.util.Stack;		//Vector<E>
import java.util.Set;		//abstract//Collection<E>
import java.util.SortedSet;	//interface//Set<E>
import java.util.NavigableSet;	//interface//SortedSet<E>//?Iterable<E>
import java.util.TreeSet;	//O(log n)//NavigableSet<E>
import java.util.HashSet;	//O(1) if unique//O(N) if all in 1 bucket//req: Object.hashCode()
import java.util.LinkedHashSet;	//HashSet//ordered
import java.util.EnumSet;	//HashSet//optimized for enums-only
//import java.util.Concurrent.ConcurrentSkipListSet;	//thread-safe//NavigableSet<E>//Cloneable//Serializable
//import java.util.Concurrent.CopyOnWriteArraySet;	//thread-safe variant optimized for WORM
import java.util.Map;		//abstract
import java.util.SortedMap;	//interface//Map<E>
import java.util.NavigableMap;	//interface//SortedMap<E>//?Iterable<E>
import java.util.TreeMap;	//O(log n)//NavigableMap<E>
import java.util.AbstractMap;	//Map<E>
import java.util.HashMap;	//O(1) if unique//O(N) if all in 1 bucket//AbstractMap<E>
import java.util.EnumMap;	//O(1) if unique//O(N) if all in 1 bucket//AbstractMap<E>
public class TestCollection {
	static enum Random {
		ZERO,
		ONE
	}
	public static <T extends Collection> void testCollection(T t) {
		if(0 != t.size() || !t.isEmpty()) {System.out.println("E: new " + t.getClass() + " not empty");}
		t.add(1);
		if(1 != t.size() || !t.contains(1)) {System.out.println("E: " + t.getClass() + ".add fail");}
		t.add(1);
	}
	public static <T extends Collection<?>> void testCollectionCleanup(T t) {
		t.clear();
		if(0 != t.size()) {System.out.println("E: " + t.getClass() + ".clear fail");}
	}
	public static <T extends List<?>> void testCollectionList(T t) {
		testCollection(t);
		if(2 != t.size()) {System.out.println("E: failed to add dup key in " + t.getClass());}
		testCollectionCleanup(t);
	}
	public static <T extends Queue<?>> void testCollectionQueue(T t) {
		testCollection(t);
		if(2 != t.size()) {System.out.println("E: failed to add dup key in " + t.getClass());}
		testCollectionCleanup(t);
	}
	public static <T extends Deque<?>> void testCollectionDeque(T t) {
		testCollection(t);
		if(2 != t.size()) {System.out.println("E: failed to add dup key in " + t.getClass());}
		testCollectionCleanup(t);
	}
	public static <T extends Set<?>> void testCollectionSet(T t) {
		testCollection(t);
		if(1 != t.size()) {System.out.println("E: dup key in " + t.getClass());}
		testCollectionCleanup(t);
	}
	public static void testCollectionEnumSet(EnumSet<Random> enums) {
		testCollectionSet(enums);
	}
	public static <T extends SortedSet<?>> void testCollectionSortedSet(T t) {
		testCollectionSet(t);
	}
	public static <T extends NavigableSet<?>> void testCollectionNavigableSet(T t) {
		testCollectionSet(t);
	}
	public static <T extends Map> void testCollectionMap(T t) {
		if(0 != t.size() || !t.isEmpty()) {System.out.println("E: new " + t.getClass() + " not empty");}
		t.put(1, "one");
		if(1 != t.size() || t.isEmpty()) {System.out.println("E: " + t.getClass() + ".add fail");}
		if(!t.containsKey(1) || !t.containsValue("one") || "one" != t.get(1)) {System.out.println("E: t[1] = " + t.get(1));}
		t.put(1, "one");
		t.put(1, "newVal");
		if(1 != t.size()) {System.out.println("E: dup key in " + t.getClass());}
/*		t.merge(0, "newVal", 
				String::concat
//				(x, y) -> (String)x + (String)y
			);
*/		t.put(0, "newVal");
		if(2 != t.size() || "newVal" != t.get(0)) {System.out.println("E: " + t.getClass() + ".merge fail");}
		t.put(1, "newVal");
		if(2 != t.size()) {System.out.println("E: dup key in " + t.getClass());}
		t.clear();
		if(0 != t.size() || !t.isEmpty()) {System.out.println("E: " + t.getClass() + ".clear failed");}
	}
	public static <T extends AbstractMap<?, String>> void testCollectionAbstractMap(T t) {
		testCollectionMap(t);
	}
	public static <T extends SortedMap<?, String>> void testCollectionSortedMap(T t) {
		testCollectionMap(t);
	}
	public static <T extends NavigableMap<?, String>> void testCollectionNavigableMap(T t) {
		testCollectionMap(t);
	}
	public static void testCollectionEnumMap(EnumMap<Random, ?> enums) {
		testCollectionMap(enums);
	}
	public static <T> void testCollections_(Class<T> t) {
		testCollectionList(new ArrayList<T>());
		testCollectionList(new LinkedList<T>());
		testCollectionDeque(new LinkedList<T>());
		testCollectionList(new Vector<T>());
		testCollectionList(new Stack<T>());
		testCollectionQueue(new PriorityQueue<T>());
		testCollectionQueue(new ArrayDeque<T>());
//		testCollectionEnumSet(new <T>());
		testCollectionSortedSet(new TreeSet<T>());
		testCollectionNavigableSet(new TreeSet<T>());
		testCollectionSet(new HashSet<T>());
		testCollectionSet(new LinkedHashSet<T>());
//		testCollectionSet(new ConcurrentSkipListSet<T>());
		testCollectionSortedMap(new TreeMap<T, String>());
		testCollectionNavigableMap(new TreeMap<T, String>());
		testCollectionAbstractMap(new HashMap<T, String>());
//		testCollectionMap(new ConcurrentSkipListMap<T, String>());
	}
	public static void testCollections() {
		testCollections_(Integer.class);
		testCollections_(Long.class);
		testCollections_(Float.class);
		testCollections_(Double.class);
//		testCollectionEnumMap(new EnumMap<Enum<T>, String>());
	}
	public static void main(String args[]) {
		try {
			testCollections();
		} catch (NoClassDefFoundError e) {
			System.out.println("NoClassDefFoundError, check for NAME$*.class to link");
			e.printStackTrace();
		}
	}
}

