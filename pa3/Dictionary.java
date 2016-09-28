// Austin Yen
// 4/28/15
// Dictionary.java
// Dictionary ADT, provides methods for use
// in creating a list of keys and their values
public class Dictionary implements DictionaryInterface {

   // private inner Node class
   private class Node {
      String key;
      String value;
      Node next;

      Node(String x, String y){
         key = x;
         value = y;
         next = null;
      }
   }

   // Fields for the Dictionary class
   private Node head;     // reference to first Node in List
   private Node tail;     // reference to last Node in List
   private int numItems;  // number of items in this Dictionary

   // IntegerList()
   // constructor for the Dictionary class
   public Dictionary(){
      head = null;
      tail = null;
      numItems = 0;
   }


   // private helper function -------------------------------------------------

   // findKey()
   // returns a reference to the Node at position with the same key
   private Node findKey(String key){
      Node N = head;
      if(numItems == 1){
    	  if(!N.key.equals(key)){
    		  return null;
    	  }
      }
      else{
    	  for(int i=1; i<=numItems; i++){ 
    		  
    		  if(N.key.equals(key)){
    			  break;
    		  }
    		  N = N.next;
    		  if(i == numItems){
    			  return null;
    		  }
    	  }
      }	  
      return N;
   }


   // ADT operations ----------------------------------------------------------

   // isEmpty()
   // pre: none
   // post: returns true if this Dictionary is empty, false otherwise
   public boolean isEmpty(){
      return(numItems == 0);
   }

   // size()
   // pre: none
   // post: returns the number of keys in this Dictionary
   public int size() {
      return numItems;
   }

   // lookup()
   // pre: there are items in the list
   // post: returns value of the specified key
   public String lookup(String key){
	   if(findKey(key) == null){
		   return null;
	   }
	   return findKey(key).value;
   }

   // insert()
   // inserts new item into the dictionary at the end
   // pre: list does not already have a key of the same name
   // post: new item with corresponding key and value is at the end
   public void insert(String key, String value) 
      throws KeyCollisionException{
      
      if(lookup(key) != null){
         throw new KeyCollisionException(
            "cannot insert duplicate keys");
      }
      Node N;
      if(numItems == 0){ // if there are no items in the list
    	  N = new Node(key,value);
    	  N.next = head;
    	  head = N;
    	  tail = head;
      }
      else{ // if there are items in the list, new items are added to the end
    	  N = tail;
    	  Node O = new Node(key,value);
    	  N.next = O;
    	  tail = O;
      }	  
      numItems++;
   }

   // remove()
   // deletes item that has the specified key
   // pre: the item is in the list
   // post: deletes the item from the list
   public void delete(String key)
      throws KeyNotFoundException{
      if(lookup(key) == null){
         throw new KeyNotFoundException(
            "cannot delete non-existent key");
      }
      
      if(head == findKey(key)){
    	  Node N = head;
    	  head = head.next;
    	  N.next = null;
      }
      else{
    	  Node N = head;
    	  while(true){
    		  if(N.next == findKey(key)){
    			  Node P = N;
    	    	  N = P.next;
    	    	  P.next = N.next;
    	    	  N.next = null;
    	    	  break;
    		  }
    		  N = N.next;
    	  }
    	  
      }
      numItems--;
   }

   // removeAll()
   // pre: none
   // post: isEmpty()
   // Clears the list of keys and their values
   public void makeEmpty(){
      head = null;
      numItems = 0;
   }

   // toString()
   // pre: none
   // post:  prints current state to stdout
   // Overrides Object's toString() method
   public String toString(){
      StringBuffer sb = new StringBuffer();
      Node N = head;
      for( ; N!=null; N=N.next) sb.append(N.key).append(" ").append(N.value+"\n");
      return new String(sb);
   }
}