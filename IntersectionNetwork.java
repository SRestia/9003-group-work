import java.util.Arrays;

class IntersectionNetwork {
    private final int[][] intersectionNetwork = new int[3][10];//Use a 2d-array to model intersection network

    public IntersectionNetwork() {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(intersectionNetwork[i], 0);//Firstly fill whole array with "1"
        }
        //Then replace the intersections 2,4,6,... of lane 1 and 3 with "0"
        for (int i = 0; i < 10; i += 2) {
            intersectionNetwork[0][i] = 1;
            intersectionNetwork[2][i] = 1;
        }
    }
    //showIntersectionStatus() method to return the status of intersection network
    public String showIntersectionStatus() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                sb.append(intersectionNetwork[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean isIntersection(int lane, int intrsction) {
        return intersectionNetwork[lane][intrsction] == 1;
    }
}
