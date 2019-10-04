/*
 * Purpose: Data Structure and Algorithms Shopping Center Project
 * Status: Complete and thoroughly tested. Fully commented line by line and with Javadoc documentation.
 * Last update: 12/09/18
 * Submitted:  12/09/18
 * Comment: This class handles QueueExceptions.
 * @author: Kyle Kaminski
 * @version: 1.0
 */

package ShoppingCenter;

public class QueueException extends RuntimeException {

	public QueueException(String s) {
		super(s); // Print a message for when a Queue Exception is caught.
	}
}