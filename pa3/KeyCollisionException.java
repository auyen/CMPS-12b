// Austin Yen
// 4/28/15
// KeyCollisionException.java
// Thrown when attempting to add a value with the same key
// as an existing one
public class KeyCollisionException extends RuntimeException {
	public KeyCollisionException(String s){
		super(s);
	}
}
