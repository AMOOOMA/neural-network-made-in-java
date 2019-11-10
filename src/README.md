# Documentation of the project
This is the docuementation for using this project. For import, you can directly use IntelliJ to import this project. (Note: the project included a sample of test data set and exchange rate data for training.)
## Data Parser class
Data Parser takes csv file path as an input and use the built-in node class to construct an arrayList of nodes of data.
Node class is defined as follows: 
```java
public class Node{
  public String country;
    public String date;
    public double data;
    public Node(String country, String date, double data){
      this.country = country;
      this.date = date;
      this.data = data;
    }        
}
```
Modified the node class if you want to customize your own data input. 

## Data Constructor class
Data Constructor takes the arrayList created by data parser and process into a matrix form and built label/attribute according for training. Constructor's input is defined as follow.
```java
public DataConstructor(ArrayList<DataParser.Node> set, int training_set_size) {
  //Code
}
```

## Trainer class
The trainer class is pre-built with a single layer neural network. Feel free to modified it to your desire form. In short, it uses a class named Node (constructor defined as follow) and does gradient desent to train the neural network. Currently back propagation is done by training the outlayer and hiddenlayer seperately, but if you prefer to have more than one hiddenLayer of nodes, you can changed the calculateDesiredHiddenLayerActivition method to your preference.  
```java
public Node(double activationThreshold, int size){
  this.activationThreshold = activationThreshold;
  weights = new double[size];
  bias = 0.0;
  dw = new double[size];
  db = 0.0;
}
```
