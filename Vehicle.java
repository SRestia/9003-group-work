public abstract class Vehicle {
    protected String vehicleId;
    protected double velocity;
    protected int[] lanes;
    protected double currentPosition;
    protected double speedPerMoveTime;
    protected int currentLaneIndex;
    protected double moveTime;

    // Constructor 
    public Vehicle(String vehicleId, double speedPerMoveTime){
        this.vehicleId = vehicleId;
        this.velocity = 0.0;
        this.lanes  = new int[]{1,2,3};
        this.currentPosition = 0.0;
        this.speedPerMoveTime = speedPerMoveTime;
        this.currentLaneIndex = 0;
        this.moveTime = 5.0;
    }

    // Getters
    protected int getLane(){
        return  this.lanes[currentLaneIndex];
    }
    protected String getVehicleId(){
        return this.vehicleId;
    }

    protected double getVelocity(){
        return this.velocity;
    }

    protected double getCurrentPosition(){
        return this.currentPosition;    
    }

    // setters
    protected void setVelocity(double newVelocity) {
        this.velocity = newVelocity;
    }

    protected void setPosition(double newPosition){
        this.currentPosition = newPosition;
    }

    // Calculate methods

    protected double calculateVelocity(){
        return speedPerMoveTime/ moveTime;
    }

    protected void changeLane() throws SimulationException{
        switch (currentLaneIndex) {
            case 0:
                currentLaneIndex = 1;
                break;
            case 1:
                currentLaneIndex = 2;
                break;
            case 2:
                currentLaneIndex = 0;
                break;
            
            default:
                // error handling for incorrect lane 
                throw new SimulationException("Invalid lane Index (not from 0-2): " + currentLaneIndex ); 
        }
    }

    public abstract void move() throws SimulationException;

    public void showTrafficState(){
        System.out.printf("Traffic State: Vehicle Id = %s | Lane = %d | Velocity = %.2f m/s | Position = %.2f\n", getVehicleId(), getLane(), getVelocity(), getCurrentPosition());
    }

    public static void main(String[] args) {
        try {
            Car car = new Car("C001");
            Bus bus = new Bus("B001");
            Truck truck = new Truck("T001");
            for (int i = 0; i < 5; i++){
                System.out.println("TIME " + Integer.toString(i));
                car.move();
                bus.move();
                truck.move();
                System.out.println();
                System.out.println();

            }
            System.out.println("Traffic State");
            car.showTrafficState();
            bus.showTrafficState();
            truck.showTrafficState();
            

        } catch (SimulationException e) {
            System.err.println("Simulation error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}   

// Subclass Car
class Car extends Vehicle {

    public Car(String vehicleId){
        super(vehicleId, 100.0 );
    }

    @Override
    public void move() throws SimulationException {
        try {
            // change the lane 
            changeLane();

            // calculte velocity
            setVelocity(calculateVelocity());
            
            // update the distance
            setPosition(getCurrentPosition() + getVelocity() * moveTime);
            
            // print the result info
            System.out.println("ACTION: CAR MOVED");
            showTrafficState();
            System.out.println("#############");
        } catch (Exception e) {
            throw new SimulationException("Move Error: ", e);
        }
    }
}


// Subclass Bus
class Bus extends Vehicle {

    public Bus(String vehicleId){
        super(vehicleId, 80.0 );
    }

    @Override
    public void move() throws SimulationException {
        try {
            // change the lane 
            changeLane();

            // calculte velocity
            setVelocity(calculateVelocity());
            
            // update the distance
            setPosition(getCurrentPosition() + getVelocity() * moveTime);
            
            // print the result info
            System.out.println("ACTION: BUS MOVED");
            showTrafficState();
            System.out.println("#############");
        } catch (Exception e) {
            throw new SimulationException("Move Error: ", e);
        }
    }
}

// Subclass Truck
class Truck extends Vehicle {

    public Truck(String vehicleId){
        super(vehicleId, 90.0 );
    }

    @Override
    public void move() throws SimulationException {
        try {
            // change the lane 
            changeLane();

            // calculte velocity
            setVelocity(calculateVelocity());
            
            // update the distance
            setPosition(getCurrentPosition() + getVelocity() * moveTime);
            
            // print the result info
            System.out.println("ACTION: TRUCK MOVED");
            showTrafficState();
            System.out.println("#############");
        } catch (Exception e) {
            throw new SimulationException("Move Error: ", e);
        }
    }
}