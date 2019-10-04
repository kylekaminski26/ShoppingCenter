package ShoppingCenter;

public interface ExtendedArrayInterface<T> extends ArrayInterface<T> {
	public void add(T newItem) throws ExtendedQueueException;

	public T remove() throws ExtendedQueueException;

	public T retrieve() throws ExtendedQueueException;
}