/*
 * Purpose: Data Structure and Algorithms Shopping Center Project
 * Status: Complete and thoroughly tested. Fully commented line by line and with Javadoc documentation.
 * Last update: 12/09/18
 * Submitted:  12/09/18
 * Comment: This class creates Item objects.
 * @author: Kyle Kaminski
 * @version: 1.0
 */

package ShoppingCenter;

public class Item {
	private String name; // Item name.
	private int amount; // Item amount.
	
	public Item(String name, int amount) { // Constructor.
		this.name = name; // Initializes the name of the item.
		this.amount = amount; // Initializes the amount of the item.
	}
	
	public String getName() { // Getter for the name.
		return name; // Returns the name of the item.
	}
	
	public int getAmount() { // Getter for the amount.
		return amount; // Returns the amount of the item.
	}
	
	public void increaseAmount(int number) { // Method for increasing the amount of an item.
		amount += number; // Increments the amount of an item by 1.
	}
	
	public void decreaseAmount(int number) { // Method for decreasing the amount of an item.
		amount -= number; // Decrements the amount of an item by 1.
	}
	
	public void setAmount(int number) { // Setter for the amount of an item.
		amount = number; // Amount of the item is the parameter's value.
	}
	
	public String toString() {
		return name; // Returns the name of the item.
	}
}
