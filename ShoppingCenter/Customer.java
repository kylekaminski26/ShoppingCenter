/*
 * Purpose: Data Structure and Algorithms Shopping Center Project
 * Status: Complete and thoroughly tested. Fully commented line by line and with Javadoc documentation.
 * Last update: 12/09/18
 * Submitted:  12/09/18
 * Comment: This class creates Customer objects.
 * @author: Kyle Kaminski
 * @version: 1.0
 */

package ShoppingCenter;

public class Customer {
	private String name; // The name of the customer.
	private int numItems; // The amount of items the customer has in their cart.
	private int time; // The amount of time the customer has been in the shopping center.

	public Customer(String name, int numItems) { // Constructor.
		this.name = name; // Initializes the name of the customer.
		this.numItems = numItems; // Initializes the number of items in the customer's cart.
	}

	public String getName() { // Name getter method.
		return name; // Return the name of the customer when called.
	}

	public int getNumItems() { // Number of items method.
		return numItems; // Returns the number of items in the customer's cart when called.
	}

	public void addItems(int number) { // Method for adding items to the customer's cart.
		numItems += number; // Increments the number of item's in the customer's cart by 1.
	}

	public void removeItems(int number) { // Method for removing items from a customer's cart.
		numItems -= number; // Decrements the number of item's in the customer's cart by 1.
	}

	public int getTime() { // Getter for the time.
		return time; // Returns the time a customer has spent in the shopping center so far.
	}

	public void incrementTime() { // Method for incrementing time.
		time++; // Increments the amount of time a customer has been in the store by 1.
	}

	public String toString() { // toString to print information about the customer.
		if (time != 1) { // Proper grammar.
			return new String(name + " has shopped for " + time + " minutes.");
		} else {
			return new String(name + " has shopped for 1 minute.");
		}
	}
} // End Customer class.
