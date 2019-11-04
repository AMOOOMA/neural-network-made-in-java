import java.util.Arrays;

public class Node {
    public double[] weights;
    public double bias;
    public double[] dw;
    public double db;
    public double[] data;
    public double activationThreshold;
    public double z;
    public double a;

    /**
     * Constructor
     * @param activationThreshold
     * @param size data size
     */
    public Node(double activationThreshold, int size){
        this.activationThreshold = activationThreshold;
        weights = new double[size];
        bias = 0.0;
        dw = new double[size];
        db = 0.0;
    }

    /**
     * calculate the z for sigmoid computation.
     * @param data
     */
    public double calculateZ(double[] data) {
        this.data = data;
        double ans = 0;
        for (int i = 0; i < weights.length; i++) {
            ans += data[i]*weights[i];
        }
        return z = (ans + bias);
    }


}
