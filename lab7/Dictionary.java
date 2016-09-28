// Austin Yen
// 5/27/15
// Dictionary.java
// Dictionary ADT, provides methods for use
// in creating a tree of keys and their values


public class Dictionary implements DictionaryInterface{


	// private types and functions ------------------------------------------------

	// NodeObj
	private class Node{
	   String key;
	   String value;
	   Node left;
	   Node right;
	   public Node(String k, String v){
		   key = k;
		   value = v;
		   left = null;
		   right = null;
	   }
	}
	private Node root;
	private int numPairs;



	// findKey()
	// returns the Node containing key k in the subtree rooted at R, or returns
	// null if no such Node exists
	Node findKey(Node R, String k){
	   if(R==null || k.equals(R.key)) 
	      return R;
	   if( k.compareTo(R.key) < 0) 
	      return findKey(R.left, k);
	   else // k.compareTo(R.key)>0
	      return findKey(R.right, k);
	}


	// findParent()
	// returns the parent of N in the subtree rooted at R, or returns null 
	// if N is equal to R. (pre: R != null)
	Node findParent(Node N, Node R){
	   Node P= null;
	   if( N!=R ){
	      P = R;
	      while( P.left!=N && P.right!=N ){
	         if(N.key.compareTo(P.key)<0)
	            P = P.left;
	         else
	            P = P.right;
	      }
	   }
	   return P;
	}

	// findLeftmost()
	// returns the leftmost Node in the subtree rooted at R, or null if R is null.
	Node findLeftmost(Node R){
	   Node L = R;
	   if( L!=null ) for( ; L.left!=null; L=L.left) ;
	   return L;
	}

	// printInOrder()
	// appends the (key, value) pairs belonging to the subtree rooted at R in order
	// of increasing keys to a stringbuffer pointed to by a.
	void printInOrder(Node R, StringBuffer a){
	   if( R!=null ){
	      printInOrder(R.left, a);
	      a.append(R.key+" "+R.value+"\n");
	      printInOrder(R.right, a);
	   }
	}

	// deleteAll()
	// deletes all Nodes in the subtree rooted at N.
	void deleteAll(Node N){
	   if( N!=null ){
	      deleteAll(N.left);
	      deleteAll(N.right);
	   }
	}

	// public functions -----------------------------------------------------------

	// Dictionary()
	// constructor for the Dictionary type
	public Dictionary(){
	   root = null;
	   numPairs = 0;
	}

	// isEmpty()
	// returns true if this Dictionary is empty, false otherwise
	// pre: none
	public boolean isEmpty(){
	   return(numPairs==0);
	}

	// size()
	// returns the number of entries in this Dictionary
	// pre: none
	public int size(){
	   return(numPairs);
	}

	// lookup()
	// returns value associated key, or null reference if no such key exists
	// pre: none
	public String lookup(String key){
	   Node N;
	   N = findKey(root, key);
	   if(N == null){
		   return null;
	   }
	   return N.value;
	}

	// insert()
	// inserts new (key,value) pair into this Dictionary
	// pre: lookup(key)==null
	public void insert(String key, String value) throws KeyCollisionException{
	   Node N, A, B;
	   if( lookup(key)!=null ){
	      throw new KeyCollisionException("cannot insert duplicate key");
	   }
	   N = new Node(key, value);
	   B = null;
	   A = root;
	   while( A!=null ){
	      B = A;
	      if( key.compareTo(A.key)<0 ) A = A.left;
	      else A = A.right;
	   }
	   if( B==null ) root = N;
	   else if( key.compareTo(B.key) < 0) B.left = N;
	   else B.right = N;
	   numPairs++;
	}

	// delete()
	// deletes pair with the given key
	// pre: lookup(key)!=null
	public void delete(String key) throws KeyNotFoundException{
	   Node N, P, S;
	   N = findKey(root, key);
	   if( N==null ){
	      throw new KeyNotFoundException("cannot delete nonexistent key");
	   }
	   if( N.left==null && N.right==null ){
	      if( N==root ){
	         root = null;
	      }else{
	         P = findParent(N, root);
	         if( P.right==N ) P.right = null;
	         else P.left = null;
	      }
	   }else if( N.right==null ){
	      if( N==root ){
	         root = N.left;
	      }else{
	         P = findParent(N, root);
	         if( P.right==N ) P.right = N.left;
	         else P.left = N.left;
	      }
	   }else if( N.left==null ){
	      if( N==root ){
	         root = N.right;
	      }else{
	         P = findParent(N, root);
	         if( P.right==N ) P.right = N.right;
	         else P.left = N.right;
	      }
	   }else{  // N.left!=null && N.right!=null
	      S = findLeftmost(N.right);
	      N.key = S.key;
	      N.value = S.value;
	      P = findParent(S, N);
	      if( P.right==S ) P.right = S.right;
	      else P.left = S.right;
	   }
	   numPairs--;
	}

	// makeEmpty()
	// pre: none
	public void makeEmpty(){
	   deleteAll(root);
	   root = null;
	   numPairs = 0;
	}

	// toString()
	// returns a String representation of this Dictionary
	// overrides Object's toString() method
	// pre: none
	public String toString(){
		StringBuffer dict = new StringBuffer("");
		printInOrder(root,dict);
		String dicti = dict.toString();
		return dicti;
	}


}
