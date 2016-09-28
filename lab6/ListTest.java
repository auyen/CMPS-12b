//-----------------------------------------------------------------------------
// Austin Yen
// 5/17/15
// ListTest.java
// Test of List
//-----------------------------------------------------------------------------
public class ListTest {
	public static void main(String[] args){
		
		// new List
		// one String
		List<String> one = new List<String>();
		// other one is Integer
		List<Integer> two = new List<Integer>();
		
		// test if it returns empty for a new list
		// test if it returns 0 for size
		// System.out.println("List is empty: "+one.isEmpty());
		// System.out.println("Items in list: "+one.size());
		
		// test adding nodes
		one.add(1, "heyo");
		one.add(2, "heya");
		
		two.add(1, 1);
		two.add(2, 2);
		
		// test if it returns false for empty now
		// test if size returns 2
		// System.out.println("List is empty: "+one.isEmpty());
		// System.out.println("Items in list: "+one.size());
		
		// same tests for "two"
		// System.out.println("List is empty: "+two.isEmpty());
		// System.out.println("Items in list: "+two.size());
		
		// test removing nodes
		one.remove(1);
		two.remove(1);
		// test size again to see
		// System.out.println("Items in one: "+one.size());
		// System.out.println("Items in two: "+two.size());
		
		// add a couple more
		one.add(1, "heyo");
		one.add(3, "heyayayayayaya");
		one.add(4, "heyaz");
		one.add(5, ">:V");
		
		two.add(1, 1);
		two.add(3, 3);
		two.add(4, 4);
		two.add(5, 5);
		// test size again to see
		System.out.println("Items in one: "+one.size());
		System.out.println("Items in two: "+two.size());
		
		
		System.out.println(one.toString());
		System.out.println(two.toString());
		// test removeAll
		one.removeAll();
		two.removeAll();
		// test size again to see
		System.out.println("Items in one: "+one.size());
		System.out.println("Items in two: "+two.size());
		
	}
}
