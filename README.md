# Car Rental System (Java CLI)

This project is a simple **CLI-based Car Rental System** written in Java. It uses basic programming concepts and stores data in text files. The program is designed to be easy to understand and follows beginner-level logic.

## Features

* View available cars
* View rented (taken) cars
* Rent a car for specific days
* Add new cars to the system
* View total rent and payment status
* Data persistence using `.txt` files

## Rules Followed

* CLI based program
* No OOP concepts used (only `public class Main`)
* Uses basic Java concepts:

  * Conditions
  * Loops
  * Functions
  * Try-catch
  * File handling
* Simple human-like logic
* No code comments

## Files Used

The program automatically creates and uses the following files:

* `cars_available.txt` — stores available cars
* `cars_taken.txt` — stores rented cars
* `payments.txt` — stores payment details

## Data Format

Cars are stored in the following format:

```
CarName,NumberPlate,RentPerDay
```

Example:

```
Toyota Corolla,LEA-2345,5000
Honda Civic,ICT-9981,6500
Suzuki Mehran,KHI-1122,3000
```

## How to Run

1. Make sure Java is installed
2. Compile the program:

```
javac Main.java
```

3. Run the program:

```
java Main
```

## Menu Options

* View Available Cars
* View Taken Cars
* Rent a Car
* Add New Car
* View Payments
* Exit

## Notes

* Same car models can exist with different number plates
* Payment status is stored as Pending by default
* All data is stored locally in text files

## Author

Ahsan Raza

---

This project is suitable for academic submission and basic Java CLI practice.
