import java.util.Arrays;

public class Trainer {

    private double[][] data;
    private double[] attribute;
    private Node[] hiddenLayer;
    private Node[] outputLayer;
    private double[] activation;
    private double[] desireHiddenLayerActivation;

    /**
     * The trainer takes the user input and build the neural network accordingly.
     * You can customize the size of the hiddenLayer and outputLayer.
     * @param data
     * @param attribute
     * @param hiddenLayerNodeCount
     * @param outputLayerNodeCount
     */
    public Trainer(double[][] data, double[] attribute, int hiddenLayerNodeCount, int outputLayerNodeCount){
        this.data = data;
        this.attribute = attribute;
        hiddenLayer = new Node[hiddenLayerNodeCount];
        outputLayer = new Node[outputLayerNodeCount];
        activation = new double[hiddenLayerNodeCount];
        desireHiddenLayerActivation = new double[hiddenLayerNodeCount];
        Node n = new Node(0.5, data[0].length - 1);
        Node no = new Node(0.5, activation.length);
        Arrays.fill(hiddenLayer, n); Arrays.fill(outputLayer, no);
    }

    /**
     * Epoch trains data and println the accuracy.
     * @param learningRate
     * @param times
     * @param testSize
     */
    public void epoch(double learningRate, int times, int testSize){
        int count = 1;
        System.out.println("The untrained whole set accuracy is " + testWholeSetAccuracy());
        while(count <= times){
            double loss = 0;
            scramble();
            for (int i = 0; i < data.length - testSize; i++ ){
                calculateOutput(i);
                calculateGradients(i, learningRate);
                loss += calculateLoss(i);
            }
            System.out.println("This is the " + count + " epoch. The loss is " +  loss / (data.length - testSize));
            System.out.println("The test set accuracy is " + testAccuracy(testSize));
            System.out.println("The whole set accuracy is " + testWholeSetAccuracy());
            count++;
        }
    }

    /**
     * This method calculates the output of the neural network.
     * @param dataIndex
     * @return
     */
    public int calculateOutput(int dataIndex){
        for (int i = 0; i < hiddenLayer.length; i++) {
            activation[i] = hiddenLayer[i].a = sigmoid(hiddenLayer[i].calculateZ(data[dataIndex]));
        }
        int ans = -1;
        double max = 0;
        for (int i = 0; i < outputLayer.length; i++) {
            outputLayer[i].a =  sigmoid(outputLayer[i].calculateZ(activation));
            if (outputLayer[i].a > outputLayer[i].activationThreshold) {
                if (max < outputLayer[i].a) {
                    max = outputLayer[i].a;
                    ans = i;
                }
            }
        }
        return ans;
    }

    /**
     * This method is used to adjust either hiddenLayer weights and bias or outputLayer's
     * @param learningRate
     * @param layer
     */
    public void adjust(double learningRate, boolean layer){
        //Layer false == adjust hiddenLayer
        //Layer true == adjust outputLayer
        if (layer) {
            for (int i = 0; i < outputLayer.length; i++) {
                for (int j = 0; j < outputLayer[i].weights.length; j++) {
                    outputLayer[i].weights[j] -= learningRate*outputLayer[i].dw[j];
                }
                outputLayer[i].bias -= learningRate*outputLayer[i].db;
            }
        } else {
            for (int i = 0; i < hiddenLayer.length; i++) {
                for (int j = 0; j < hiddenLayer[i].weights.length; j++) {
                    hiddenLayer[i].weights[j] -= learningRate*hiddenLayer[i].dw[j];
                }
                hiddenLayer[i].bias -= learningRate*hiddenLayer[i].db;
            }
        }
    }

    /**
     * This method calculate the Gradient of the neural network and adjust the weights and biases.
     * @param dataIndex
     * @param learningRate
     */
    public void calculateGradients(int dataIndex, double learningRate){
        for (int i = 0; i < outputLayer.length; i++) {
            for (int j = 0; j < outputLayer[i].weights.length; j++) {
                outputLayer[i].dw[j] = -2*((attribute[dataIndex] == i ? 1 : 0) - sigmoid(outputLayer[i].z))*sigmoidDeri(outputLayer[i].z)*outputLayer[i].data[j];
            }
            outputLayer[i].db = -2*((attribute[dataIndex] == i ? 1 : 0) - sigmoid(outputLayer[i].z))*sigmoidDeri(outputLayer[i].z);
        }

        adjust(learningRate, true);
        calculateDesireHiddenLayerActivation(dataIndex);

        for (int i = 0; i < hiddenLayer.length; i++) {
            for (int j = 0; j < hiddenLayer[i].weights.length; j++) {
                hiddenLayer[i].dw[j] = -2*(desireHiddenLayerActivation[i] - hiddenLayer[i].a)*sigmoidDeri(hiddenLayer[i].z)*hiddenLayer[i].data[j];
            }
            hiddenLayer[i].db = -2*(desireHiddenLayerActivation[i] - hiddenLayer[i].a)*sigmoidDeri(hiddenLayer[i].z);
        }

        adjust(learningRate, false);
    }

    /**
     * Used for back propagation.
     * @param dataIndex
     */
    public void calculateDesireHiddenLayerActivation(int dataIndex) {
        for (int i = 0; i < hiddenLayer.length; i++) {
            double count = 0;
            for (int j = 0; j < outputLayer.length; j++) {
                count += -2*((attribute[dataIndex] == i ? 1 : 0) - sigmoid(outputLayer[j].z))*sigmoidDeri(outputLayer[j].z)*outputLayer[j].dw[i];
            }
            desireHiddenLayerActivation[i] = hiddenLayer[i].a - count / outputLayer.length;
        }
    }

    /**
     * Use the cost function to calculate total loss.
     * @param dataIndex
     * @return
     */
    public double calculateLoss(int dataIndex){
        double ans = 0;
        for (int i = 0; i < outputLayer.length; i++) {
            ans += Math.pow((attribute[dataIndex] == i ? 1 : 0) - sigmoid(outputLayer[i].z), 2);
        }
        return ans / outputLayer.length;
    }

    public double sigmoid(double input){
        return 1 / (1 + Math.exp(-input));
    }

    public double sigmoidDeri(double z) {
        return sigmoid(z)*(1 - sigmoid(z));
    }

    public double testAccuracy(int testSize){
        double ans = 0;
        for (int i = data.length - testSize - 1; i < data.length; i++){
            if (calculateOutput(i) == attribute[i]) {
                ans++;
            }
        }
        return ans / testSize;
    }

    public double testWholeSetAccuracy(){
        double ans = 0;
        scramble();
        for (int i = 0; i < data.length; i++){
            if (calculateOutput(i) == attribute[i]) {
                ans++;
            }
        }
        return ans / data.length;
    }

    /**
     * Used to scramble the data.
     */
    public void scramble(){
        for (int i = 0; i < data.length / 2; i++){
            int randomIndex1 = (int)(Math.random()*(data.length - 1));
            int randomIndex2 = (int)(Math.random()*(data.length - 1));
            swap(randomIndex1, randomIndex2);
        }
    }

    public void swap(int x, int y){
        double temp = attribute[x];
        attribute[x] = attribute[y];
        attribute[y] = temp;
        double[] tempSet = data[x];
        data[x] = data[y];
        data[y] = tempSet;
    }
}