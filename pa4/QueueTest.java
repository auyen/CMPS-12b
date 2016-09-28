// Austin Yen
// 5/18/15
// QueueTest.java
// Testing the methods of Queue.java


public class QueueTest {
	public static void main(String[] args){
		Queue que = new Queue();
		
		// see if isEmpty() returns false
		// System.out.println("empty? "+que.isEmpty());
		
		// see if length() returns 0
		// System.out.println("size: "+ que.length());
		
		// enqueue some job objects
		que.enqueue(new Job(1,3));
		que.enqueue(new Job(2,4));
		que.enqueue(new Job(3,5));
		que.enqueue(new Job(4,6));
		
		// try isEmpty() again (should return false)
		// System.out.println("empty? "+que.isEmpty());
		
		// try length() again (should return 4)
		// System.out.println("size: "+ que.length());
		
		// try dequeue
		que.dequeue();
		
		// try isEmpty() again (should return false)
		System.out.println("empty? "+que.isEmpty());
		
		// try length() again (should return 3)
		System.out.println("size: "+ que.length());
		
		// try printing with toString()
		System.out.println(que.toString());
		
		// try peek()
		System.out.println(que.peek().toString());
		
		// try dequeueAll()
		que.dequeueAll();
		
		// try isEmpty() again (should return true)
		System.out.println("empty? "+que.isEmpty());
		
		// try length() again (should return 0)
		System.out.println("size: "+ que.length());
		
	}
}
