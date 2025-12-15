package Car_Rental_System;

import java.io.*;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1 View Available Cars");
            System.out.println("2 View Taken Cars");
            System.out.println("3 Rent a Car");
            System.out.println("4 Add New Car");
            System.out.println("5 View Payments");
            System.out.println("6 Exit");
            System.out.print("Choice: ");

            String ch = sc.nextLine();

            if (ch.equals("1")) {
                showFile("cars_available.txt");
            } else if (ch.equals("2")) {
                showFile("cars_taken.txt");
            } else if (ch.equals("3")) {
                rentCar();
            } else if (ch.equals("4")) {
                addCar();
            } else if (ch.equals("5")) {
                showFile("payments.txt");
            } else if (ch.equals("6")) {
                break;
            }
        }
    }

    static void addCar() {
        try {
            System.out.print("Car Name: ");
            String name = sc.nextLine();
            System.out.print("Number Plate: ");
            String plate = sc.nextLine();
            System.out.print("Rent per day: ");
            String rent = sc.nextLine();

            FileWriter fw = new FileWriter("cars_available.txt", true);
            fw.write(name + "," + plate + "," + rent + "\n");
            fw.close();

            System.out.println("Car Added");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    static void rentCar() {
        List<String> cars = readLines("cars_available.txt");
        if (cars.size() == 0) {
            System.out.println("No Cars Available");
            return;
        }

        for (int i = 0; i < cars.size(); i++) {
            System.out.println((i + 1) + " " + cars.get(i));
        }

        System.out.print("Select Car No: ");
        int pick = Integer.parseInt(sc.nextLine()) - 1;
        if (pick < 0 || pick >= cars.size())
            return;

        String car = cars.get(pick);
        System.out.print("Days: ");
        int days = Integer.parseInt(sc.nextLine());

        String[] parts = car.split(",");
        int total = Integer.parseInt(parts[2]) * days;

        try {
            FileWriter fw1 = new FileWriter("cars_taken.txt", true);
            fw1.write(car + "," + days + "\n");
            fw1.close();

            FileWriter fw2 = new FileWriter("payments.txt", true);
            fw2.write(parts[0] + "," + parts[1] + ",Total=" + total + ",Pending\n");
            fw2.close();

            cars.remove(pick);
            writeAll("cars_available.txt", cars);

            System.out.println("Car Rented. Total Rent: " + total);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    static void showFile(String file) {
        try {
            File f = new File(file);
            if (!f.exists()) {
                System.out.println("No Data");
                return;
            }

            Scanner fs = new Scanner(f);
            while (fs.hasNextLine()) {
                System.out.println(fs.nextLine());
            }
            fs.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    static List<String> readLines(String file) {
        List<String> list = new ArrayList<>();
        try {
            File f = new File(file);
            if (!f.exists())
                return list;

            Scanner fs = new Scanner(f);
            while (fs.hasNextLine()) {
                list.add(fs.nextLine());
            }
            fs.close();
        } catch (Exception e) {
        }
        return list;
    }

    static void writeAll(String file, List<String> list) {
        try {
            FileWriter fw = new FileWriter(file);
            for (String s : list) {
                fw.write(s + "\n");
            }
            fw.close();
        } catch (Exception e) {
        }
    }
}
