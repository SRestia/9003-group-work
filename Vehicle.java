public abstract class Vehicle {
    protected String vehicleID;
    protected int velocity;
    protected int currentLane;
    protected int currentPosition;
    protected int moveLaneCount;//Track the times of calling move() method
    protected static final int MAX_MOVE_LANE_COUNT = 5;
    protected boolean moveThroughIntersection = false;
    protected boolean defaultOrder = true;


    public Vehicle(String vehicleID, int currentLane, boolean defaultOrder) {
        this.vehicleID = vehicleID;
        this.currentLane = currentLane;
        this.defaultOrder = defaultOrder;
        this.currentPosition = 0;
        this.moveLaneCount = 0;
        setVelocity();
    }

    public abstract void setVelocity();

    //move() method to make vehicle change lanes by the default order of 1->2->3->1->2
    public void move() {
        move(defaultOrder);
    }

    //Overloading of move() method
    public void move(boolean moveLaneDirection) {
        try {
            if (moveLaneCount >= MAX_MOVE_LANE_COUNT) {
                throw new SimulationException("Vehicle " + vehicleID + "has reached the maximum number of changing lanes.");
            }
            //Change lanes by default order
            if (moveLaneDirection) {
                if (currentLane == 3) {
                    currentLane = 1;
                } else {
                    currentLane++;
                }
            } else {//Change lanes by negative order: 3->2->1-3->2
                if (currentLane == 1) {
                    currentLane = 3;
                } else {
                    currentLane--;
                }
            }
            moveLaneCount++;
            System.out.println("Vehicle " + vehicleID + "has moved to Lane " + currentLane + ".");
        } catch (SimulationException e) {
            System.out.println("Error while changing lanes: ;" + e.getMessage());
        }
    }


    //Keep updating the position of vehicle when it is not changing lanes and stopping
    public void moveForward(int time) {
        try{
            currentPosition += (velocity * time / 3600);
        } catch (Exception e) {
            System.out.println("Vehicle " + vehicleID + ": Error while moving forwared: " + e.getMessage());
        }
    }

    //showTrafficState() method to show the current status of vehicle
    public String showTrafficState() {
        return this.getClass().getSimpleName() + "Vehicle ID: " + vehicleID + " | Velocity: " + velocity + " | Current Lane: " + currentLane + " | Current Position: " + currentPosition;
    }
}

//Subclass Car
class Car extends Vehicle {
    public Car(String vehicleID, int currentLane, boolean defaultOrder) {
        super(vehicleID, currentLane, defaultOrder);
    }

    public void setVelocity() {
        this.velocity = 100;
    }

    @Override
    public String showTrafficState() {
        return "Vehicle Type: Car | " +  super.showTrafficState();
    }
}

//Subclass Bus
class Bus extends Vehicle {
    public Bus(String vehicleID, int currentLane, boolean defaultOrder) {
        super(vehicleID, currentLane, defaultOrder);
    }

    public void setVelocity() {
        this.velocity = 80;
    }

    @Override
    public String showTrafficState() {
        return "Vehicle Type: Bus | " + super.showTrafficState();
    }
}

//Subclass Truck
class Truck extends Vehicle {
    public Truck(String vehicleID, int currentLane, boolean defaultOrder) {
        super(vehicleID, currentLane, defaultOrder);
    }

    public void setVelocity() {
        this.velocity = 90;
    }

    @Override
    public String showTrafficState() {
        return "Vehicle Type: Truck | " + super.showTrafficState();
    }
}

