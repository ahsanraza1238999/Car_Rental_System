import java.io.*;
import java.util.Scanner;

public class Main {
    
    // Arrays to store car information
    static String[] carModels = {"Toyota Camry", "Honda Accord", "Ford Mustang", "Tesla Model 3", "BMW X5"};
    static double[] carRates = {4500.0, 5000.0, 8500.0, 9500.0, 12000.0};
    static boolean[] carAvailable = {true, true, true, true, true};
    
    // Global Scanner (Input)
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== CAR RENTAL SYSTEM =====");
            System.out.println("1. View All Cars");
            System.out.println("2. Rent a Car");
            System.out.println("3. Return a Car");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            if (choice == 1) {
                viewCars();
            } else if (choice == 2) {
                rentCar();
            } else if (choice == 3) {
                returnCar();
            } else if (choice == 4) {
                System.out.println("Thank you! Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
    
    // Method to view all cars
    static void viewCars() {
        System.out.println("\n===== ALL CARS =====");
        
        for (int i = 0; i < carModels.length; i++) {
            System.out.print((i + 1) + ". " + carModels[i]);
            System.out.print(" - PKR " + carRates[i] + "/day");
            
            if (carAvailable[i]) {
                System.out.println(" [AVAILABLE]");
            } else {
                System.out.println(" [RENTED]");
            }
        }
    }

    // Method to view available cars
    static boolean viewAvailableCars() {
        System.out.println("\n===== AVAILABLE CARS =====");
        
        boolean anyAvailable = false; // Track if any car is available
        
        for (int i = 0; i < carModels.length; i++) {
            // Only show if car is available
            if (carAvailable[i]) {
                System.out.print((i + 1) + ". " + carModels[i]);
                System.out.println(" - PKR " + carRates[i] + "/day [AVAILABLE]");
                anyAvailable = true;
            }
        }
        
        // If no cars are available
        if (!anyAvailable) {
            System.out.println("Sorry, no cars available right now.");
        }

        return anyAvailable ? true : false;
    }
    
    // Method to rent a car
    static void rentCar() {
        System.out.println("\n===== RENT A CAR =====");
        boolean anyAvailable = viewAvailableCars();

        if(!anyAvailable){
            return;
        }
        
        System.out.print("\nEnter car number (1-5): ");
        int carNumber = scanner.nextInt();
        scanner.nextLine(); 
        
        // Check if valid car number
        if (carNumber < 1 || carNumber > 5) {
            System.out.println("Invalid car number!");
            return;
        }
        
        int index = carNumber - 1;
        
        // Check if car is available
        if (!carAvailable[index]) {
            System.out.println("Sorry, this car is already rented!");
            return;
        }
        
        // Get customer details
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        
        System.out.print("Enter number of days: ");
        int days = scanner.nextInt();
        scanner.nextLine(); 
        
        // Calculate cost with discount
        double totalCost = calculateCost(carRates[index], days);
        
        // Mark car as rented
        carAvailable[index] = false;
        
        // Save rental to file
        saveRentalToFile(customerName, carModels[index], days, totalCost);
        
        System.out.println("\n--- RENTAL CONFIRMED ---");
        System.out.println("Customer: " + customerName);
        System.out.println("Car: " + carModels[index]);
        System.out.println("Days: " + days);
        System.out.println("Total Cost: PKR " + totalCost);
        System.out.println("------------------------");
    }
    
    // Method to calculate cost
    static double calculateCost(double dailyRate, int days) {
        double baseCost = dailyRate * days;
        double discount = 0;
        
        if (days >= 7) {
            discount = 0.10; // 10% discount for 7+ days
            System.out.println("10% discount applied!");
        } else if (days >= 3) {
            discount = 0.05; // 5% discount for 3-6 days
            System.out.println("5% discount applied!");
        }
        
        double finalCost = baseCost - (baseCost * discount);
        return finalCost;
    }
    
    // Method to return a car
    static void returnCar() {
        System.out.println("\n===== RETURN A CAR =====");
        
        // Show rented cars
        boolean anyRented = false;
        for (int i = 0; i < carModels.length; i++) {
            if (!carAvailable[i]) {
                System.out.println((i + 1) + ". " + carModels[i] + " [RENTED]");
                anyRented = true;
            }
        }
        
        if (!anyRented) {
            System.out.println("No cars are currently rented.");
            return;
        }
        
        System.out.print("\nEnter car number to return (1-5): ");
        int carNumber = scanner.nextInt();
        scanner.nextLine();
        
        // Get customer name
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        
        // Check if valid car number
        if (carNumber < 1 || carNumber > 5) {
            System.out.println("Invalid car number!");
            return;
        }
        
        int index = carNumber - 1;
        
        // Check if car was actually rented
        if (carAvailable[index]) {
            System.out.println("This car was not rented!");
            return;
        }
        
        // Mark car as available
        carAvailable[index] = true;

        // Store to file 
        saveReturningToFile(customerName, carModels[index]);
        
        System.out.println("Car returned successfully!");
        System.out.println(carModels[index] + " is now available.");
    }
    
    // Method to save rental to file
    static void saveRentalToFile(String customerName, String carModel, int days, double cost) {
        try {
            FileWriter writer = new FileWriter("rentals.txt", true);
            
            writer.write("Customer: " + customerName + "\n");
            writer.write("Car: " + carModel + "\n");
            writer.write("Days: " + days + "\n");
            writer.write("Cost: PKR " + cost + "\n");
            writer.write("-------------------\n");
            
            writer.close();
            System.out.println("Rental saved to file!");
            
        } catch (IOException e) {
            System.out.println("Error saving to file!");
        }
    }

    // Method to save returning to file
    static void saveReturningToFile(String customerName, String carModel) {
        try {
            FileWriter writer = new FileWriter("returnings.txt", true);
            
            writer.write("Customer: " + customerName + "\n");
            writer.write("Car: " + carModel + "\n");
            writer.write("-------------------\n");
            
            writer.close();
            System.out.println("Returning saved to file!");
            
        } catch (IOException e) {
            System.out.println("Error saving to file!");
        }
    }
}
