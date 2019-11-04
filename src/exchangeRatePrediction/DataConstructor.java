import java.util.ArrayList;

public class DataConstructor {
    public double[][] data; //Array of data
    public double[] attribute; //Array of the desire output of each set of data.

    /**
     * The constructor took the data made from data parser and turns it into form of array.
     * And at the same time, preserving some test set for testing accuracy later on.
     * @param set
     * @param training_set_size
     */
    public DataConstructor(ArrayList<DataParser.Node> set, int training_set_size) {
        data = new double[set.size() / training_set_size][training_set_size];
        attribute = new double[set.size() / training_set_size];

        for (int i = 0; i < set.size() / training_set_size; i++) {
            for (int j = 0; j < training_set_size / 2; j++) {
                data[i][j] = set.get(i * training_set_size / 2 + j).data;
            }
            for (int j = 0; j < training_set_size / 2; j++) {
                data[i][j + training_set_size / 2] = set.get(set.size() / 2 + i * training_set_size / 2 + j).data;
            }
            attribute[i] = data[i][training_set_size / 2 - 1] - data[i][training_set_size / 2 - 2]  > 0 ? 1 : 0;
        }
    }
}
