/*
 * Purpose: Data Structure and Algorithms Shopping Center Project
 * Status: Complete and thoroughly tested. Fully commented line by line and with Javadoc documentation.
 * Last update: 12/09/18
 * Submitted:  12/09/18
 * Comment: This class handles NonUniqueExceptions.
 * @author: Kyle Kaminski
 * @version: 1.0
 */

package ShoppingCenter;

public class NonUniqueException extends RuntimeException {

	public NonUniqueException(String s) {
		super(s); // Prints a message when a non unique exception is caught.
	}
}
