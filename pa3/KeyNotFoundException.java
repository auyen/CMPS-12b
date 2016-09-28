// Austin Yen
// 4/28/15
// KeyNotFoundException.java
// Thrown when a requested key for deletion is not found
public class KeyNotFoundException extends RuntimeException {
	public KeyNotFoundException(String s){
		super(s);
	}
}
