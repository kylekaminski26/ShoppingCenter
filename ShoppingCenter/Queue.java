/*
 * Purpose: Data Structure and Algorithms Shopping Center Project
 * Status: Complete and thoroughly tested. Fully commented line by line and with Javadoc documentation.
 * Last update: 12/09/18
 * Submitted:  12/09/18
 * Comment: This class is a local implementation of Queue.
 * @author: Kyle Kaminski
 * @version: 1.0
 */

package ShoppingCenter;

public class Queue<T> implements QueueInterface<T> {
	protected int front, back, numItems;
	protected T[] items;
	
	public Queue() { // Constructor.
		front = 0; // Initializes the front of the queue.
		back = 0; // Initializes the back of the queue.
		numItems = 0; // Initializes the number of items in the queue.
		items = (T[]) (new Object[3]);
	}
	
	public boolean isEmpty() {
		return numItems == 0; // Returns a check for an empty queue.
	}
	public void enqueue(T newItem) throws QueueException {
		checkSize();
		items[back] = newItem; // The last item is the value of the parameter.
		back = (back + 1) % items.length; // The back is moved to the right.
		numItems++; // Increment the number of items in the queue by 1.
		checkSize();
	}

	public T dequeue() throws QueueException {
		if (isEmpty() == true) {
			throw new QueueException("Queue is empty");
		}
		checkSize();
		T removedItem = items[front]; // Removed item is the first item in the queue.
		items[front] = null; // The front item is null.
		numItems--; // Decrement the number of items in the queue.
		front = (front + 1) % items.length; // The front is moved to the right.
		checkSize();
		return removedItem;
	}

	public void dequeueAll() {
		while (numItems > 0) {
			dequeue(); // Dequeues all elements in the list.
		}
	}

	public T peek() throws QueueException {
		if (isEmpty() == true) {
			throw new QueueException("Queue is empty");
		}
		return items[front]; // Return the front item of the queue.
	}

	protected void checkSize() {
		if (numItems == items.length) { // If the number of items in the queue is the same as the length of the queue.
			T[] biggerQueue = (T[]) (new Object[items.length * 2]); 
			for (int i = 0; i < numItems; i++) { // For when the index is less than the number of items in the queue.
				biggerQueue[i] = items[(front + i) % items.length];
			}
			items = biggerQueue;
			front = 0;
			back = numItems;
		}
		else if((numItems > 0) && (numItems== items.length / 4)) { // If the amount of items in the queue is bigger than a fourth of the length.
			T[] smallerQueue = (T[]) (new Object[items.length / 2]);
			for (int i = 0; i < numItems; i++) { // For if the index is less than the number of items in the queue.
				smallerQueue[i] = items[(front + i) % items.length];
			}
			items = smallerQueue;
			front = 0;
			back = numItems;
		}
	}
	
	public String toString() { // Returns the elements of the queue.
		String result = "[";
		for (int i = front; i < (front + numItems); i++) {
			if (i == front && items[i] != null) {
				result = result + items[i].toString();
			}
			else if (items[i] != null) {
				result = result + ", " + items[i].toString();
			}
		}
		return result + "]";
	}
}
