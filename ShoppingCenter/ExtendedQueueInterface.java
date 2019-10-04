/*
 * Purpose: Data Structure and Algorithms Shopping Center Project
 * Status: Complete and thoroughly tested. Fully commented line by line and with Javadoc documentation.
 * Last update: 12/09/18
 * Submitted:  12/09/18
 * Comment: This class creates an Interface for the Queue class.
 * @author: Kyle Kaminski
 * @version: 1.0
 */

package ShoppingCenter;

public interface ExtendedQueueInterface<T> extends QueueInterface<T> {
	public void enqueueFirst(T newItem) throws ExtendedQueueException;

	public T dequeueLast() throws ExtendedQueueException;

	public T peekLast() throws ExtendedQueueException;
} // end ExtendedQueueInterface