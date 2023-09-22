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
public class DemoCollection {
	static enum Random {
		ZERO,
		ONE
	}
	public static <T extends Collection> void demoCollection(T t) {
		if(0 != t.size() || !t.isEmpty()) {System.out.println("E: new " + t.getClass() + " not empty");}
		t.add(1);
		if(1 != t.size() || !t.contains(1)) {System.out.println("E: " + t.getClass() + ".add fail");}
		t.add(1);
	}
	public static <T extends Collection<?>> void demoCollectionCleanup(T t) {
		t.clear();
		if(0 != t.size()) {System.out.println("E: " + t.getClass() + ".clear fail");}
	}
	public static <T extends List<?>> void demoCollectionList(T t) {
		demoCollection(t);
		if(2 != t.size()) {System.out.println("E: failed to add dup key in " + t.getClass());}
		demoCollectionCleanup(t);
	}
	public static <T extends Queue<?>> void demoCollectionQueue(T t) {
		demoCollection(t);
		if(2 != t.size()) {System.out.println("E: failed to add dup key in " + t.getClass());}
		demoCollectionCleanup(t);
	}
	public static <T extends Deque<?>> void demoCollectionDeque(T t) {
		demoCollection(t);
		if(2 != t.size()) {System.out.println("E: failed to add dup key in " + t.getClass());}
		demoCollectionCleanup(t);
	}
	public static <T extends Set<?>> void demoCollectionSet(T t) {
		demoCollection(t);
		if(1 != t.size()) {System.out.println("E: dup key in " + t.getClass());}
		demoCollectionCleanup(t);
	}
	public static void demoCollectionEnumSet(EnumSet<Random> enums) {
		demoCollectionSet(enums);
	}
	public static <T extends SortedSet<?>> void demoCollectionSortedSet(T t) {
		demoCollectionSet(t);
	}
	public static <T extends NavigableSet<?>> void demoCollectionNavigableSet(T t) {
		demoCollectionSet(t);
	}
	public static <T extends Map> void demoCollectionMap(T t) {
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
	public static <T extends AbstractMap<?, String>> void demoCollectionAbstractMap(T t) {
		demoCollectionMap(t);
	}
	public static <T extends SortedMap<?, String>> void demoCollectionSortedMap(T t) {
		demoCollectionMap(t);
	}
	public static <T extends NavigableMap<?, String>> void demoCollectionNavigableMap(T t) {
		demoCollectionMap(t);
	}
	public static void demoCollectionEnumMap(EnumMap<Random, ?> enums) {
		demoCollectionMap(enums);
	}
	public static <T> void demoCollections_(Class<T> t) {
		demoCollectionList(new ArrayList<T>());
		demoCollectionList(new LinkedList<T>());
		demoCollectionDeque(new LinkedList<T>());
		demoCollectionList(new Vector<T>());
		demoCollectionList(new Stack<T>());
		demoCollectionQueue(new PriorityQueue<T>());
		demoCollectionQueue(new ArrayDeque<T>());
//		demoCollectionEnumSet(new <T>());
		demoCollectionSortedSet(new TreeSet<T>());
		demoCollectionNavigableSet(new TreeSet<T>());
		demoCollectionSet(new HashSet<T>());
		demoCollectionSet(new LinkedHashSet<T>());
//		demoCollectionSet(new ConcurrentSkipListSet<T>());
		demoCollectionSortedMap(new TreeMap<T, String>());
		demoCollectionNavigableMap(new TreeMap<T, String>());
		demoCollectionAbstractMap(new HashMap<T, String>());
//		demoCollectionMap(new ConcurrentSkipListMap<T, String>());
	}
	public static void demoCollections() {
		demoCollections_(Integer.class);
		demoCollections_(Long.class);
		demoCollections_(Float.class);
		demoCollections_(Double.class);
//		demoCollectionEnumMap(new EnumMap<Enum<T>, String>());
	}
	public static void main(String args[]) {
		try {
			demoCollections();
		} catch (NoClassDefFoundError e) {
			System.out.println("NoClassDefFoundError, check for NAME$*.class to link");
			e.printStackTrace();
		}
	}
}

