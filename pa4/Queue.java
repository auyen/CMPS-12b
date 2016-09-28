// Austin Yen
// 5/18/15
// Queue.java
// Queue ADT, defines methods in QueueInterface.java


public class Queue {
	   private int numItems;      // number of items in this IntegerQueue

	   // private inner Node class
	   private class Node {
	      Object value;
	      Node next;

	      Node(Object y){
	         value = y;
	         next = null;
	      }
	   }

	   // Fields for the Queue class
	   private Node head;     // reference to first Node in List
	   private Node tail;     // reference to last Node in List

	   // Queue()
	   // constructor for the Queue class
	   public Queue(){
	      head = null;
	      tail = null;
	      numItems = 0;
	   }
	   
	   
	   
	   // isEmpty()
	   // pre: none
	   // post: returns true if this Queue is empty, false otherwise
	   public boolean isEmpty(){
		   return (numItems == 0);
	   }
	   
	   // length()
	   // pre: none
	   // post: returns the length of this Queue.
	   public int length(){
		   return numItems;
	   }
	   
	   // enqueue()
	   // adds newItem to back of this Queue
	   // pre: none
	   // post: !isEmpty()
	   public void enqueue(Object newItem){
		   Node N;
		   if(isEmpty()){
			   N = new Node(newItem);
			   head = N;
			   tail = head;
		   }
		   else{
			   N = tail;
			   Node O = new Node(newItem);
			   N.next = O;
			   tail = O;
		   }
		   numItems++;
	   }
	   
	   
	   // dequeue()
	   // deletes and returns item from front of this Queue
	   // pre: !isEmpty()
	   // post: this Queue will have one fewer element
	   public Object dequeue() throws QueueEmptyException{
		   if(isEmpty()){
			   throw new QueueEmptyException(
					   "Cannot delete from an empty queue.");
			   
		   }
		   Node N = new Node(head.value);
		   if(numItems == 0){
			   head = null;
		   }
		   else{
			   head = head.next;
		   }	
		   numItems--;
		   return N.value;
	   }
	   
	   // peek()
	   // pre: !isEmpty()
	   // post: returns item at front of Queue
	   public Object peek() throws QueueEmptyException{
		   if(isEmpty()){
			   throw new QueueEmptyException(
					   "Cannot return item from an empty queue.");
			   
		   }
		   return head.value;
	   }
	   
	   
	   // dequeueAll()
	   // sets this Queue to the empty state
	   // pre: !isEmpty()
	   // post: isEmpty()
	   public void dequeueAll() throws QueueEmptyException{
		   if(isEmpty()){
			   throw new QueueEmptyException(
					   "Cannot clear items from an empty queue.");
			   
		   }
		   head = null;
		   tail = null;
		   numItems = 0;
	   }
	   
	   
	   // toString()
	   // overrides Object's toString() method
	   public String toString(){
		   StringBuffer sb = new StringBuffer();
		      Node N = head;
		      for( ; N!=null; N=N.next) sb.append(N.value.toString()).append(" ");
		      return new String(sb);
	   }
}
