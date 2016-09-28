// Austin Yen
// 4/28/15
// DictionaryTest.java
// Dictionary ADT test file, individually testing the 
// methods and also during different states of a 
// dictionary linked list
public class DictionaryTest {
	public static void main(String[] args){
		
		// create new Dictionary
		Dictionary dict = new Dictionary();
		
		
		// Check if isEmpty() returns true
		// System.out.println(dict.isEmpty()); 
		// It returned true
		
		// Check if size() returns 0
		// System.out.println(dict.size()); 
		// It returned 0
		
		// Try inserting some values
		dict.insert("1","a");
		dict.insert("2","b");
		dict.insert("3","c");
		dict.insert("4","d");
		dict.insert("5","e");
		dict.insert("6","f");
		// check if everything prints as specified in toString()
		// 1 a
		// 2 b
		// 3 c
		// ...
		// System.out.print(dict.toString());
		// everything printed correctly
		
		// Check if size() returns the proper size
		// System.out.println("size = "+dict.size()); 
		// It returned 6, which is correct
		

		
		
		// check if delete() removes the item specified by the key
		dict.delete("1"); 
		dict.delete("3");
		dict.delete("6");
		// check if the delete method works by printing
		System.out.print(dict.toString()); 
		// it printed everything except the specified items
		
		// Check again if size() returns the proper size
		System.out.println("size = "+dict.size()); 
		// It returned 3, which is correct
		
		// check if lookup finds the key and returns value
		// System.out.println("value of 1 is "+dict.lookup("1")); 
		// lookup() returned "a"
		
		
		// check if isEmpty() returns false
		// System.out.println(dict.isEmpty()); 
		// It returned false
		
		// try the makeEmpty() method
		// makeEmpty() works if isEmpty() returns true
		dict.makeEmpty(); 
		System.out.println(dict.isEmpty()); 
		// isEmpty returned true
		
		
		// try adding something to check again
		// should print out the key and value of only what was added
		dict.insert("1","a"); 
		System.out.print(dict.toString()); 
		// toString printed out "1 a"
	}
}
