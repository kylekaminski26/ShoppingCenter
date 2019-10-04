/*
 * Purpose: Data Structure and Algorithms Shopping Center Project
 * Status: Complete and thoroughly tested. Fully commented line by line and with Javadoc documentation.
 * Last update: 12/09/18
 * Submitted:  12/09/18
 * Comment: This class is an interface for Queue.
 * @author: Kyle Kaminski
 * @version: 1.0
 */

package ShoppingCenter;

public interface ArrayInterface<T> {
	public boolean isEmpty();

	public void add(T newItem) throws ArrayException;

	public T remove() throws ArrayException;

	public void removeAll();

	public T retrieve() throws ArrayException;

	public String toString();
}