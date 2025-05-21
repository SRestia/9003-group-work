import java.util.ArrayList;

public class TrafficSignal {
    private int signalID;
    private String currentState;
    private int currentTimer;
    private static final String[] states = {"red", "yellow", "green"};
    private static int[] timers = {10, 2, 15};
    private int signalCount = -1;//callTime to store the time of calling signal()

    //Getter and setter for signalID
    public TrafficSignal(int signalID) {
        this.signalID = signalID;
    }
    public int getSignalID() {
        return signalID;
    }
    public void setSignalID(int signalID) { this.signalID = signalID; }

    //Method Signal() to cycle through signal states
    public void signal() {
        signalCount++;
        //Try-catch statement to handel errors
        try {
            //Ensure the time of calling signal() is between 1 to 3 for each object
            if (signalCount > 0 && signalCount < states.length) {
                currentState = states[signalCount];
                currentTimer = timers[signalCount];
                System.out.println("The signal state has changed to " + states[signalCount] + ".");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            currentState = null;
            currentTimer = 0;
            System.out.println(signalID + ": Invalid signal transition.");
        }
    }

    public void showTrafficSignal() {
        System.out.println(signalID + ": " + currentState);
    }

}
