import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        ArrayList<TrafficSignal> trafficSignals = new ArrayList<>(3);
        ArrayList<IntersectionNetwork> intersectionNetworks = new ArrayList<>(3);
        while (true) {
            System.out.println("Urban Traffic Simulation System");
            System.out.println("Main Menu");
            System.out.println("1. View current status of vehicles");
            System.out.println("2. View current status of traffic signal");
            System.out.println("3. Add vehicle");
            System.out.println("4. Remove vehicle");
            System.out.println("5. Adjust signal timers");
            System.out.println("6. View intersection networks");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    if (vehicles.size() > 0) {
                        for (Vehicle v : vehicles) {
                            v.showTrafficState();
                        }
                    } else {
                        System.out.println("No vehicles available.");
                    }
                }

                case 2 -> {
                    if (trafficSignals.size() > 0) {
                        for (TrafficSignal t : trafficSignals) {
                            t.showTrafficSignal();
                        }
                    } else {
                        System.out.println("No traffic signal available.");
                    }
                }

                case 3 -> {
                    System.out.println("Select the type of vehicle to add: 1->Car, 2->Bus, 3->Truck");
                    int vehicleType = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please enter the vehicle ID:");
                    String id = scanner.nextLine();
                    switch (vehicleType) {
                        case 1 -> vehicles.add(new Car(id));
                        case 2 -> vehicles.add(new Bus(id));
                        case 3 -> vehicles.add(new Truck(id));
                        default -> System.out.println("Invalid vehicle type.");
                    }
                }

                case 4 -> {
                    System.out.println("Please enter the vehicle ID:");
                    String removeID = scanner.nextLine();
                    try {
                        for (Vehicle v : vehicles) {
                            if (v.vehicleId.equals(removeID)) {
                                vehicles.remove(v);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("This vehicle does not exist.");
                    }
                }

                case 5 -> {
                    System.out.println("Choose the signal state which you like to adjust its timer: 1->red, 2->yellow, 3->green");
                    String adjustedSignal = scanner.nextLine();
                    scanner.nextLine();
                    int adjustedSignalIndex = -1;
                    //Check whether the signal state is one of the signal states
                    for (int i= 0; i < TrafficSignal.states.length; i++) {
                        if (adjustedSignal.equals(TrafficSignal.states[i])) {
                            adjustedSignalIndex = i;
                        }
                    }
                    if (adjustedSignalIndex >= 0 && adjustedSignalIndex < 2) {
                        System.out.println("Signal state chosen. ");
                        System.out.println("Enter the time you want to adjust to: ");
                        int adjustedTime = scanner.nextInt();
                        scanner.nextLine();
                        if (adjustedTime >= 0) {
                            TrafficSignal.timers[adjustedSignalIndex] = adjustedTime;
                        } else {
                            System.out.println("Invalid time");
                        }
                    } else {
                        System.out.println("Invalid signal type. ");
                    }
                    }

                    case 6 -> {
                    for (IntersectionNetwork i : intersectionNetworks) {
                        i.showIntersectionStatus();
                    }
                    }

                    case 7 -> {
                    System.out.println("Existed");
                    return;
                    }

                    default -> System.out.println("Invalid choice");
                }
            }
        }
}