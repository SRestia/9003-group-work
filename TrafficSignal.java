class TrafficSignal {
    private int redDuration = 10;
    private int yellowDuration = 2;
    private int greenDuration = 15;
    private int timer = 0;
    private String currentState = "red";//Assume all traffic signals begin from "red"

    public void update(int seconds) {
        timer += seconds;//Track the time
        int cycle = redDuration +yellowDuration +greenDuration;//The duration of one cycle of traffic signal operation
        int timeInCycle = timer % cycle;//Calculate which moment it is in a traffic signal operation cycle
        //ELSE-IF statement to judge the current state of traffic signal
        if (timeInCycle < redDuration) {
            currentState = "red";
        } else if (timeInCycle < redDuration+ yellowDuration) {
            currentState = "yellow";
        } else {
            currentState = "green";
        }
    }

    public void setDuration(int red, int yellow, int green) {
        redDuration = red;
        yellowDuration = yellow;
        greenDuration = green;
    }

    //Get which state the signals are at: red, yellow or green
    public String getCurrentState() {
        return currentState;
    }

    public String showTrafficSignal() {
        return "Current State: " + currentState + " | Red Duration: " + redDuration + " | Yellow Duration: " + yellowDuration + " | Green Duration: " + greenDuration;
    }
}
