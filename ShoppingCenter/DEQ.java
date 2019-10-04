/*
 * Purpose: Data Structure and Algorithms Shopping Center Project
 * Status: Complete and thoroughly tested. Fully commented line by line and with Javadoc documentation.
 * Last update: 12/09/18
 * Submitted:  12/09/18
 * Comment: This class is a local implementation of DEQ.
 * @author: Kyle Kaminski
 * @version: 1.0
 */

package ShoppingCenter;

public class DEQ<T> extends Queue<T> implements ExtendedQueueInterface<T> {

	public DEQ() { // Constructor.
		super();
	}

	@Override // Overrides Java's enqueueFirst method for DEQ.
	public void enqueueFirst(T newItem) throws ExtendedQueueException {
		checkSize(); // Calls the checkSize method.
		for (int i = (back - 1); i >= front; i--) { // For when i is less than or equal to the front of the DEQ.
			if (items[i] != null) { // If the index of i of the items in DEQ is not null.
				items[i + 1] = items[i]; // Move index to the right.
			}
		}
		items[front] = newItem; // The front is now the value of the parameter.
		back = (back + 1) % items.length; // The back of the DEQ is moved to the right.
		numItems++; // Increment the amount of items in the DEQ by 1.
		checkSize();
	}

	@Override
	public T dequeueLast() throws ExtendedQueueException {
		if (isEmpty() == true) {
			throw new ExtendedQueueException("Queue is empty");
		} else {
			checkSize();
			T removedItem = (T) items[back - 1]; // Removed item is the second to last element.
			items[back - 1] = null; // The second to last item is null.
			numItems--; // Decrement the number of items by 1.
			back = (back - 1) % items.length; // The back of the DEQ is now shifted to the left.
			checkSize();
			return removedItem;
		}

	}
	
	public T peekFirst() throws ExtendedQueueException {
		if (isEmpty() == true) {
			throw new QueueException("Queue is empty");
		} else {
			return items[front]; // Peek last returns the first item in the DEQ.
		}
	}

	@Override
	public T peekLast() throws ExtendedQueueException {
		if (isEmpty() == true) {
			throw new QueueException("Queue is empty");
		} else {
			return items[back - 1]; // Peek last returns the last item in the DEQ.
		}
	}
}