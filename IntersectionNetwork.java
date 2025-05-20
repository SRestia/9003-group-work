import java.util.*;

public class IntersectionNetwork {
    // define the number of lanes and intersections
    private static final int L = 3;
    private static final int Inter_section = 10;

    // define the 2d array for simulating the intersection network
    private ArrayList<ArrayList<Integer>> Inter_section_2d;

    // define the vehicle in which lane and position, also the vehicle id
    private int c_lane;
    private int c_position;
    private String v_id;

    // storage for the vehicle log
    private ArrayList<String> statusLog;

    // constructor
    public IntersectionNetwork(String v_id, int c_lane) {
        this.v_id = v_id;
        this.c_lane = c_lane;

        // Inter_section_2d[0/2] initial odd as 1，else 0
        // Inter_section_2d[1] initial 0
        statusLog = new ArrayList<>();
        Inter_section_2d = new ArrayList<>();

        for (int i = 0; i < L; i++) {
            Inter_section_2d.add(new ArrayList<>(Collections.nCopies(Inter_section, 0)));
            if (i == 1) continue;
            for (int j = 0; j < Inter_section; j++) {
                if (j % 2 == 0) {
                    Inter_section_2d.get(i).set(j, 1);
                }
            }
        }
        System.out.println(Inter_section_2d);
    }

    // moveThrough, move the vehicle through the intersection
    public void moveThrough() {
        // if the vehicle is already at the end of the lane, return
        if (this.c_position >= Inter_section) {
            statusLog.add(this.v_id + "already reach the end.");
            return;
        }

        // add current status to the log
        int value = this.Inter_section_2d.get(this.c_lane).get(this.c_position);
        String c_status = this.v_id + "at Lane " + (this.c_lane + 1) + this.c_position + ", current at " + (value == 1 ? "intersection" : "non-intersection.");
        this.statusLog.add(c_status);
        this.c_position += 1;
    }

    // get the status log
    public void showIntersectionStatus() {
        System.out.println("-------Status Log for " + this.v_id + ": ");
        for (String status : this.statusLog) System.out.println(status);
    }
}

