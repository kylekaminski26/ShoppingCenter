/*
 * Purpose: Data Structure and Algorithms Shopping Center Project
 * Status: Complete and thoroughly tested. Fully commented line by line and with Javadoc documentation.
 * Last update: 12/09/18
 * Submitted:  12/09/18
 * Comment: This class handles all of the logic and method calls.
 * This is what Eclipse runs when the program is running.
 * @author: Kyle Kaminski
 * @version: 1.0
 */

package ShoppingCenter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
	public static void main(String args[]) {
		boolean run = true; // Easy way to end the main menu loop.
		Scanner sc = new Scanner(System.in); // Creates scanner object for user input.
		ShoppingCenter shoppingCenter = new ShoppingCenter(); // Runs the shopping center class.
		System.out.println("How many items would you like to add? ");
		int ask = sc.nextInt(); // Amount of times user will be asked to enter items.
		System.out.println("Specify restock amount: ");
		final int RESTOCK_AMOUNT = sc.nextInt(); // Final variable for the amount of restock an item gets every time.
		try {
			while (ask > 0) {
				String item = ""; // Empty item.
				int amount = 0; // Empty amount.
				System.out.print("Name of item " + ask + ": ");
				item = sc.next(); // Captures user input for the item name.
				System.out.print("Amount of item " + ask + ": ");
				amount = sc.nextInt(); // Captures user input for amount of items.
				shoppingCenter.addItem(item, amount); // Creates Item object with the item name and amount.
				ask--; // Decreases the amount of times the user will be asked by 1.
			}
		} catch (InputMismatchException e) {
			System.out.println("Please enter a valid input."); // Catches an exception if the user inputs incorrect data types.
		}

		System.out.println("Here are the menu options:"); // Menu that will only print once for the user.
		System.out.println("0. Close the Shopping Center.");
		System.out.println("1. Customer enters Shopping Center.");
		System.out.println("2. Customer picks an item and places it in the shopping cart.");
		System.out.println("3. Customer removes an item from the shopping cart.");
		System.out.println("4. Customer is done shopping.");
		System.out.println("5. Customer checks out.");
		System.out.println("6. Print info about customers who are shopping.");
		System.out.println("7. Print info about customers in checkout lines.");
		System.out.println("8. Print info about items at or below re-stocking level.");
		System.out.println("9. Reorder an item.");

		try {
			while (run == true) { // Will run infinitely until run is false.
				System.out.println("\nEnter menu selection: ");
				int choice = sc.nextInt(); // Captures user input for the menu choice they want.
				switch (choice) { // Switch statement for the menu system/
				case 0:
					System.out.println("The Shopping Center is closing...come back tomorrow.");
					run = false; // Ends the while loop.
					sc.close(); // Closes memory leak from the Scanner.
					break;
				case 1:
					try {
						System.out.print("Enter name: ");
						String cust1 = sc.next(); // Captures user input for the customer name.
						shoppingCenter.addCustomer(cust1, 0); // Creates a Customer object for the customer's name and the items in their cart.
						System.out.println(cust1 + " added."); // Confirm customer addition to shopping center.
					} catch (InvalidException e) { // Catches exception for invalid data type.
						System.out.println(e); // Prints exception message.
					}
					break;
				case 2:
					// Customer picks an item and places it in the shopping cart.
					try {
						System.out.print("Enter customer that picked up an item: ");
						String cust2 = sc.next(); // Captures user input for customer name.
						System.out.println("Enter item that was picked up by " + cust2 + ": ");
						String item2 = sc.next(); // Captures user input for item name.
						shoppingCenter.takeItem(cust2, item2); // Passes parameters to method that will add the item to the customer's cart.
					} catch (InvalidException e) { // Catches exception for invalid data type.
						System.out.println(e); // Prints exception message.
					}
					break;
				case 3:
					// Customer removes an item from the shopping cart.
					System.out.print("Enter customer that removed item from cart: ");
					String cust3 = sc.next(); // Captures user input for the customer's name.
					shoppingCenter.removeItemFromCart(cust3); // Passes parameter to method that decrements the number of items in the customer's cart by 1.
					break;
				case 4:
					// Customer is done shopping.
					try {
						Customer customerAdded = shoppingCenter.addToCheckoutLine(); // Customer object is equal to the customer that was added to a line.
						if (customerAdded != null) { // If Customer object is not null
							System.out.println("Customer " + customerAdded.getName() + " added to checkout lines."); // Confirmation message.
						} else {
							throw new InvalidException("Customer does not exist."); // Catches exception if the customer does not exist.
						}
					} catch (QueueException e) { // Catches Queue exception.
						System.out.println(e); // Prints exception message.
					}
					break;
				case 5:
					// Customer checks out.
					Customer custChecked; // Customer object for the customer that checked out
					if (shoppingCenter.getExpressSize() != 0) { // If express is not empty.
						custChecked = shoppingCenter.expressCheckout(); // Checkout express.
					} else if (shoppingCenter.getRegular1Size() != 0) { // If Regular Line 1 is not empty.
						custChecked = shoppingCenter.regular1Checkout(); // Checkout Regular 1.
					} else if (shoppingCenter.getRegular2Size() != 0) { // If Regular Line 2 is not empty.
						custChecked = shoppingCenter.regular2Checkout(); // Checkout Regular 2.
					} else {
						System.out.println("There is no one in any of the lines."); // If all the lines are empty.
						break;
					}
					System.out.println("Customer " + custChecked.getName() + " has finished checking out.");
					System.out.print("Should they leave? (Y/N): ");
					String ans5 = sc.next(); // Captures user input for the yes or no question.
					if (ans5.equalsIgnoreCase("Y")) { // If the customer wants to leave.
						System.out.println(custChecked.getName() + " has left the Shopping Center."); // Garbage collector removes the customer object when this case ends.
					} else if (ans5.equalsIgnoreCase("N")) { // If the customer wants to stay.
						shoppingCenter.addCustomer(custChecked.getName(), 0); // Create a brand new customer of the same name.
						System.out.println(custChecked.getName() + " has gone back shopping.");
					} else { // If the user entered anything else besides 'Y' or 'N'
						System.out.println("That was not a valid response. " + custChecked.getName()
								+ " has automatically left the Shopping Center."); // Garbage collector removes the customer object when this case ends.
					}
					break;
				case 6:
					// Print info about customers who are shopping.
					shoppingCenter.printAllCustomerAndInfoStillShopping(); // Calls a method that prints all customers still shopping.
					break;
				case 7:
					// Print info about customers in checkout lines.
					System.out.println("     Express Line: ");
					shoppingCenter.printExpress(); // Prints express line in front to back order.
					System.out.println("     Regular 1: ");
					shoppingCenter.printRegular1(); // Prints regular line 1 in front to back order.
					System.out.println("     Regular 2: ");
					shoppingCenter.printRegular2(); // Prints regular line 2 in front to back order.
					break;
				case 8:
					// Print info about items at or below re-stocking level.
					shoppingCenter.printInventory(); // Prints the inventory of the shopping center.
					break;
				case 9:
					// Reorder an item.
					try {
						System.out.print("Enter item: ");
						String item9 = sc.next(); // Captures user input for the item that needs to be restocked.
						shoppingCenter.reorderSpecificItem(item9, RESTOCK_AMOUNT); // Passes parameters to a method that reorders more of an item.
					} catch (InvalidException e) { // Catches an invalid exception for an incorrect data type.
						System.out.println(e); // Prints exception message.
					}
					break;
				default:
					// Default.
					System.out.println("Please enter a valid reponse."); // If the user entered an int value that is not a case choice.
					break;
				}
			}
		} catch (InputMismatchException e) { // Catches input mismatch exception for if the user entered a value of an incorrect data type.
			System.out.println("Please enter a valid input."); // Prints error message to the user.
			// Program terminates with an InputMismatchException.
		}
	}
}