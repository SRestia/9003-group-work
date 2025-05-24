import java.util.*;

public class Main {
    private static final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static final TrafficSignal signal = new TrafficSignal();
    private static final IntersectionNetwork intersectionNetwork = new IntersectionNetwork();
    private static final Random rand = new Random();
    private static final List<String> logs = new ArrayList<>();//Used to record the process of simulation

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        //User Interface: Main Menu to allow user to choose the operation
        while (running) {
            try {
                System.out.println("Urban Traffic Simulation System\n###############");
                System.out.println("Choose an operation:");
                System.out.println("1. Add vehicle");
                System.out.println("2. Remove vehicle");
                System.out.println("3. Simulate traffic");
                System.out.println("4. View current states of all vehicles");
                System.out.println("5. View current status of all traffic signals");
                System.out.println("6. View current status of intersection network");
                System.out.println("7. Adjust timers of traffic signals");
                System.out.println("8. View logs");
                System.out.println("9. Exist");

                int operation = scanner.nextInt();
                scanner.nextLine();
                switch (operation) {
                    case 1://Add new vehicle into simulation system
                        System.out.println("Enter vehicle type (Car, Bus, Truck):");
                        String vehicleType = scanner.nextLine();
                        System.out.println("Enter vehicle ID: ");
                        String ID = scanner.nextLine();
                        int Lane = rand.nextInt(3) + 1;//Allocate the vehicle to one of the lanes randomly
                        //Allow user to choose whether the vehicle change lanes by default direction or negative direction
                        System.out.println("Default direction of lanes changing is 1->2->3->1, negative direction is 3->2->1->3.\nEnter 1 for default direction, 2 for negative direction:");
                        int directionChoice = scanner.nextInt();
                        boolean defaultDirection = directionChoice == 1;
                        Vehicle vehicle = switch (vehicleType.toLowerCase()) {
                            case "car" -> new Car(ID, Lane, defaultDirection);
                            case "bus" -> new Bus(ID, Lane, defaultDirection);
                            case "truck" -> new Truck(ID, Lane, defaultDirection);
                            default -> null;
                        };
                        if (vehicle != null) {
                            vehicles.add(vehicle);
                            System.out.println(vehicleType + ": " + ID + " added successfully to Lane " + Lane + ".");
                            logs.add(vehicleType + ": " + ID + " added successfully to Lane " + Lane + ".");
                        } else {
                            System.out.println("Invalid vehicle type.");
                        }
                        break;

                    case 2://Remove vehicle from simulation system
                        System.out.println("Enter vehicle ID to remove:");
                        String removeID = scanner.nextLine();
                        scanner.nextLine();
                        for (Vehicle v: vehicles) {
                            if (v.vehicleID.equals(removeID)) {
                                vehicles.remove(v);
                            }
                        }
                        logs.add("Vehicle ID: " + removeID + "was removed successfully.");
                        break;

                    case 3:
                        System.out.println("Enter simulation time (unit: minute):");//Allow user to decide the time of simulation
                        int simulationMinute = scanner.nextInt();
                        int simulationTime = simulationMinute * 60;//Transfer the unit of simulation time into second
                        signal.update(simulationTime);
                        for (Vehicle v: vehicles) {
                            if (v.moveThroughIntersection) continue;//Ensure the vehicle is on this road
                            v.move();
                            int remainingTime = simulationTime - 5;//Every call of move() method takes 5 seconds
                            if (remainingTime < 0) remainingTime = 0;
                            int laneIndex = v.currentLane - 1;
                            int position = v.currentPosition;
                            boolean atIntersection = (laneIndex == 0 || laneIndex ==2) && position % 2 == 1;//Intersections are located at positions of odd numbers at Lane1 and Lane3
                            if (atIntersection && signal.getCurrentState().equals("red")){//When the signal is red, vehicles need to stop and wait
                                System.out.println(v.vehicleID + ": Stopped at red signal at position " + position + ".");
                                logs.add(v.vehicleID + ": Stopped at red signal at position " + position + ".");
                            } else if (atIntersection) {
                                if (rand.nextBoolean()) {//Whether the vehicle moves through an intersection is determined randomly automatically
                                    System.out.println(v.vehicleID + ": Moved through the intersection at " + position + ".");
                                    v.moveThroughIntersection = true;
                                    logs.add(v.vehicleID + ": Moved through the intersection at " + position + ".");
                                } else {
                                    v.moveForward(remainingTime);//If the vehicle does not move through the intersection, then it keeps moving forward on this road
                                    System.out.println(v.vehicleID + ": Kept moving forward at " + position + ".");
                                    logs.add(v.vehicleID + ": Kept moving forward at " + position + ".");
                                }
                            } else {
                                v.moveForward(remainingTime);//When the vehicle is not at an intersection, it can only keep moving forward
                                System.out.println(v.vehicleID + ": Kept moving forward at " + position + ".");
                                logs.add(v.vehicleID + ": Kept moving forward at " + position + ".");
                            }
                        }
                        if (simulationTime % 60 == 0) {
                            logs.add("One minute simulation completed.");
                        }
                        break;

                    case 4:
                        for (Vehicle v: vehicles) {
                            System.out.println(v.showTrafficState());
                        }
                        logs.add("Viewed all vehicle status.");
                        break;

                    case 5:
                        System.out.println(signal.showTrafficSignal());
                        logs.add("Viewed all traffic signal status.");
                        break;

                    case 6:
                        System.out.println(intersectionNetwork.showIntersectionStatus());
                        logs.add("Viewed intersection network status.");
                        break;

                    case 7:
                        System.out.println("Set the duration of each traffic signal state as follows (unit:second):");
                        System.out.println("Red:");
                        int red = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Yellow:");
                        int yellow = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Green:");
                        int green = scanner.nextInt();
                        scanner.nextLine();
                        signal.setDuration(red, yellow, green);
                        logs.add("Traffic signal durations set: Red(" + red + "), Yellow(" + yellow + "), Green(" + green + ").");
                        break;

                    case 8:
                        logs.forEach(System.out::println);//Show the record of the simulation
                        break;

                    case 9:
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid operation, please choose again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred:" + e.getMessage());
                scanner.nextLine();
            }
            System.out.print("");
        }
    }
}