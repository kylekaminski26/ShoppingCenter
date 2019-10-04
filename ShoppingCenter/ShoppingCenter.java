/*
 * Purpose: Data Structure and Algorithms Shopping Center Project
 * Status: Complete and thoroughly tested. Fully commented line by line and with Javadoc documentation.
 * Last update: 12/09/18
 * Submitted:  12/09/18
 * Comment: This class contains all of the methods for the Driver.
 * Data retrieval from the user is handled in the Driver, while it is manipulated here. 
 * @author: Kyle Kaminski
 * @version: 1.0
 */

package ShoppingCenter;

import java.util.ArrayList;
import java.util.Iterator;

public class ShoppingCenter {
	private ArrayList<Customer> shoppers; // List for the shoppers currently shopping in the shopping center.
	private ArrayList<Item> inventory; // List for the inventory of the shopping center.
	private Queue<Customer> express; // Checkout line for customers with <= 4 items.
	private Queue<Customer> regular1; // One of the two checkout lines for customers. Default, if both lines are equal size.
	private Queue<Customer> regular2; // One of the two checkout lines for customers.

	final int EXPRESS_MAX = 4; // Max amount of items the customer can have if they want to go in the express lane.
	final int RESTOCK_WARNING = 3; // Amount of items for warning message that inventory of an item is running low.
	final int RESTOCK_NEEDED = 0; // Amount of items for warning message that inventory of an item needs to be restocked.

	public ShoppingCenter() { // Constructor.
		shoppers = new ArrayList<>(); // Initializes a new list for shoppers.
		inventory = new ArrayList<>(); // Initializes a new list for inventory.
		express = new Queue<>(); // Initializes a new queue for the express line.
		regular1 = new Queue<>(); // Initializes a new queue for the regular line 1.
		regular2 = new Queue<>(); // Initializes a new queue for the regular line 2.
	}

	public void printCustomer(String name) throws InvalidException {
		Customer customer = searchCustomer(name); // Customer object is the name an already added customer, if they match.
		if (customer == null) { // If the customer object could not find a duplicate that was already added under the same name.
			throw new InvalidException("Customer " + name + " does not exist");
		}
		System.out.println(customer); // Prints the customer.
	}

	public void printAllCustomerAndInfoStillShopping() {
		int a = 0; // Variable for if the shoppers list is empty.
		for (Customer cus : shoppers) { // For all Customer elements in the shoppers list.
			System.out.println(cus); // Print each customer object in the shoppers list.
			a++; // Int a is the number of times the for loop executed.
		}
		if (a == 0) { // If a is 0 (there were no shoppers in the shoppers list).
			System.out.println("There are no customers currently shopping."); // Print message.
		}
	}

	public void printAllCustomerNamesStillShopping() {
		for (Customer cus : shoppers) { // For all Customer objects in the shoppers list.
			System.out.println(cus.getName()); // Print the name of all the customers.
		}
	}

	public Customer expressCheckout() {
		return express.dequeue(); // Returns the removed first element of the express queue.
	}

	public Customer regular1Checkout() {
		return regular1.dequeue(); // Returns the removed first element of the regular line 1.
	}

	public Customer regular2Checkout() {
		return regular2.dequeue(); // Returns the removed first element of the regular line 2.
	}

	private int getCheckoutLineSize(Queue<Customer> checkoutLine) {
		int size = 0;
		DEQ<Customer> temp = new DEQ<Customer>(); // Temp DEQ list.
		while (!checkoutLine.isEmpty()) { // While the checkout line is not empty.
			temp.enqueue(checkoutLine.dequeue()); // The first object enqueued into temp is the first item dequeued from the specified checkout line.
			size++; // Increment the size variable.
		}
		checkoutLine = temp; // Return the checkout line parameter back to normal.
		return size; // Returns the size variable, which is the size of the queue.
	}

	public int getExpressSize() { // Method specifically for the express line.
		int size = 0;
		DEQ<Customer> temp = new DEQ<Customer>(); // Temp DEQ list.
		while (!express.isEmpty()) { // While express is not empty.
			temp.enqueue(express.dequeue()); // The first object enqueued into temp is the first item dequeued from express.
			size++; // Increment the size variable.
		}
		express = temp; // Return express back to normal.
		return size; // Returns the size variable, which is the size of express.
	}

	public int getRegular1Size() { // Method specifically for regular line 1.
		int size = 0;
		DEQ<Customer> temp = new DEQ<Customer>(); // Temp DEQ list.
		while (!regular1.isEmpty()) { // While regular line 1 is not empty.
			temp.enqueue(regular1.dequeue()); // The first object enqueued into temp is the first item dequeued from regular line 1.
			size++; // Increment the size variable.
		}
		regular1 = temp; // Return regular 1 back to normal.
		return size; // Returns the size variable, which is the size of regular line 1.
	}

	public int getRegular2Size() { // Method specifically for regular line 2.
		int size = 0;
		DEQ<Customer> temp = new DEQ<Customer>(); // Temp DEQ list.
		while (!regular2.isEmpty()) { // While regular line 2 is not empty.
			temp.enqueue(regular2.dequeue()); // The first object enqueued into temp is the first item dequeued from regular line 2.
			size++; // Increment the size variable.
		}
		regular2 = temp; // Return regular 2 back to normal.
		return size; // Returns the size variable, which is the size of regular line 2.
	}

	public void addCustomer(String name, int numItems) throws NonUniqueException {
		for (Customer c : shoppers) { // For all Customer objects in the shoppers list.
			if (c.getName().equalsIgnoreCase(name)) { // If a Customer object is the same as a name of one of the customers.
				throw new InvalidException("Customer's name must be unique."); // Throws exception if a customer has the same name as an existing customer.
			}
		}
		Customer customer = new Customer(name, numItems); // Pass the parameters to a method that creates a new Customer object.
		shoppers.add(customer); // Adds the Customer object to the shoppers list.
	}

	public void addCustomer(Customer customer) throws NonUniqueException {
		for (Customer c : shoppers) { // For all Customer objects in the shoppers list.
			if (c.getName().equalsIgnoreCase(customer.getName())) { // If a Customer object is the same as a name of one of the customer's names.
				throw new NonUniqueException("Customer's name must be unique."); // Throws exception if a customer has the same name as an existing customer.
			}
		}
		shoppers.add(customer); // Adds the Customer object to the shoppers list.
	}

	public void removeCustomer(String name) throws NonUniqueException {
		Customer customer = searchCustomer(name); // Customer object that is the customer's name.
		if (customer == null) { // If the customer object is null.
			throw new InvalidException("Customer " + name + " does not exist.");
		}
		shoppers.remove(customer); // Remove the customer from the shoppers list.
	}

	public void removeCustomer(Customer customer) throws NonUniqueException {
		if (searchCustomer(customer.getName()) == null) { // If customer's name is null.
			throw new InvalidException("Customer " + customer.getName() + " does not exist.");
		}
		shoppers.remove(customer); // Remove the customer from the shoppers list.
	}

	public void addItem(String name, int amount) throws NonUniqueException {
		for (Item i : inventory) { // For all Item objects in the inventory list.
			if (i.getName().equalsIgnoreCase(name)) { // If the Item object name matches a current item
				throw new NonUniqueException("Item's name must be unique");
			}
		}
		Item item = new Item(name, amount); // Passes parameters to a method that creates a new Item object.
		inventory.add(item); // Adds the new item to the inventory list.
	}

	public void addItem(Item item) throws NonUniqueException {
		for (Item i : inventory) {// For all Item objects in the inventory list.
			if (i.getName().equalsIgnoreCase(item.getName())) { // If the Item object's name is the same name as the parameter.
				throw new NonUniqueException("Item's name must be unique"); // Throw exception.
			}
		}
		inventory.add(item); // Add the item to the inventory.
	}

	public Customer searchCustomer(String name) {
		Iterator<Customer> shopperI = shoppers.iterator(); // Iterator data structure for the shoppers list.
		while (shopperI.hasNext()) { // While there are objects to index through.
			Customer customer = shopperI.next(); // Create Customer object for the indexed object.
			if (customer.getName().equalsIgnoreCase(name)) { // If the customer name matches the parameter.
				return customer; // Return the Customer object.
			}
		}
		return null; // Return null if the search was not successful.
	}

	public Item searchItem(String name) {
		Iterator<Item> inventoryI = inventory.iterator(); // Iterator data structure for the inventory list.
		while (inventoryI.hasNext()) { // While there are objects to index through.
			Item item = inventoryI.next(); // Create Item object for the indexed object.
			if (item.getName().equalsIgnoreCase(name)) { // If the item name matches the parameter.
				return item;  // Return the Item object.
			}
		}
		return null; // Return null if the search was not successful.
	}

	public Item searchItem(int index) {
		return inventory.get(index); // Returns the specified index of the inventory list.
	}

	public void reorderSpecificItem(String itemName, int amount) {
		Item item = searchItem(itemName); // Item object for the item of the same name that already exists.

		if (item == null) { // If item is null (item was not found).
			throw new InvalidException("Item does not exist"); // Throw exception.
		} else {
			item.increaseAmount(amount); // Add specified amount to the item.
		}
	}

	public void takeItem(String customerName, String itemName) throws InvalidException {
		Customer customer = searchCustomer(customerName); // Customer object for the customer name that should exist.
		Item item = searchItem(itemName); // Item object for the item name that should exist.
		if (customer == null) { // If customer object was not found.
			throw new InvalidException("Customer " + customerName + " does not exist."); // Throw exception.
		}
		if (item == null) { // If item object was not found.
			throw new InvalidException("Item " + itemName + " does not exist."); // Throw exception.
		}
		if (item.getAmount() >= 1) { // If item amount is more than or equal to 1.
			item.decreaseAmount(1); // Decrement the item's amount by 1.
		} else {
			throw new InvalidException("Could not take " + item + " because there is no more stock."); // Throw exception.
		}
		customer.addItems(1); // Add item value to the customer's shopping cart.
		Item removedItem = new Item(itemName, 1); // Pass parameters to the create new Item method.
		for (Customer c : shoppers) { // For all Customer objects in the shoppers list.
			c.incrementTime(); // Increment their times by 1.
		}
	}

	public void removeItemFromCart(String customerName) {
		Customer customer = searchCustomer(customerName); // Customer object for the name of the customer.
		if (customer.getNumItems() > 0) { // If customer's amount of items is more than 0.
		customer.removeItems(1); // Remove an item from the customer's cart.
		}
		else {
			throw new InvalidException("The customer's cart is already empty."); // Throw exception.
		}
	}

	public void printInventory() {
		for (Item i : inventory) { // For all item objects in the inventory list.
			System.out.print(i); // Print their names.
			System.out.println(", quantity of " + i.getAmount()); // Print their amounts.
			if (i.getAmount() <= RESTOCK_WARNING) { // If an item has RESTOCK_WARNING or less in the inventory.
				if (i.getAmount() == RESTOCK_NEEDED) { // If an item has RESTOCK_NEEDED in the inventory.
					System.out.println("***" + i + " needs to be restocked."); // Warning message.
				} else {
					System.out.println("*" + i + " is running low."); // Warning message.
				}
			}
		}
	}

	public void printExpress() { // Custom method for printing express.
		if (!express.isEmpty()) { // If express is not empty.
			DEQ<Customer> temp = new DEQ<Customer>(); // Create temp DEQ.
			while (!express.isEmpty()) { // While express is not empty.
				System.out.println(express.peek().getName()); // Print the name of the first object in express.
				temp.enqueue(express.dequeue()); // Dequeue the first object in express and enqueue it in temp. 
			}
			express = temp; // Return express back to normal.
		} else {
			System.out.println("Express is empty."); // Message for if express is empty.
		}
	}

	public void printRegular1() { // Custom method for printing regular 1.
		if (!regular1.isEmpty()) { // If regular 1 is not empty.
			DEQ<Customer> temp = new DEQ<Customer>(); // Create temp DEQ.
			while (!regular1.isEmpty()) { // While regular 1 is not empty.
				System.out.println(regular1.peek().getName()); // Print the name of the first object in regular 1.
				temp.enqueue(regular1.dequeue()); // Dequeue the first object in regular 1 and enqueue it in temp.
			}
			regular1 = temp; // Return regular 1 back to normal.
		} else {
			System.out.println("Regular Line 1 is empty."); // Message for if regular 1 is empty.
		}
	}

	public void printRegular2() { // Custom method for printing regular 2.
		if (!regular2.isEmpty()) { // If regular 2 is not empty.
			DEQ<Customer> temp = new DEQ<Customer>(); // Create temp DEQ.
			while (!regular2.isEmpty()) { // While regular 2 is not empty.
				System.out.println(regular2.peek().getName()); // Print the name of the first object in regular 2.
				temp.enqueue(regular2.dequeue()); // Dequeue the first object in regular 2 and enqueue it in temp.
			}
			regular2 = temp; // Return regular 2 back to normal.
		} else {
			System.out.println("Regular Line 2 is empty."); // Message for if regular 2 is empty.
		}
	}

	public Customer addToCheckoutLine() {
		if (shoppers.size() > 0) { // If there are shoppers in the shopping center.
			Iterator<Customer> i = shoppers.iterator(); // Create iterator for shoppers list.
			Customer customerWithGreatestTime = i.next(); // Create Customer object for customer with the greatest time in the store.
			while (i.hasNext()) { // While there is something to iterate through in the shoppers list.
				Customer c = i.next(); // Customer object c is the next iterated index.
				if (c.getTime() > customerWithGreatestTime.getTime()) { // If the time of c is more than the time of the customer
					customerWithGreatestTime = c; // Customer is the value of c.
				}
			}
			// At this point in the method, the Customer with the greatest time has been found.
			if (customerWithGreatestTime.getNumItems() <= EXPRESS_MAX) { // If customer has less than or equal to EXPRESS_MAX items.
				express.enqueue(customerWithGreatestTime); // Add customer to express line.
			} else {
				if (getCheckoutLineSize(regular1) <= getCheckoutLineSize(regular2)) { // If size of regular 1 is less than or equal to regular 2.
					regular1.enqueue(customerWithGreatestTime); // Add to regular 1.
				} else {
					regular2.enqueue(customerWithGreatestTime); // Add to regular 2.
				}
			}
			shoppers.remove(customerWithGreatestTime); // Remove customer from the shoppers queue.
			return customerWithGreatestTime; // Return the customer.
		} else {
			throw new InvalidException("No customers currently shopping."); // Throw exception.
		}
	}
}