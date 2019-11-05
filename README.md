# Neural-network-made-in-java
This repo mostly serve as educational proposes, project itself is a implementation of one layer neural network (and can also be modified to be convulotional neural network). I made this solely for practicing and help other students understand the concept of machine learning with neural network.

## Getting Started
First, bear with me through the mathematical model behind this project. It's crucial for understanding and using the library. 
To better understand back propagation and neural network, I recommend checking out [3Blue1Brown's video series](https://www.youtube.com/playlist?list=PLZHQObOWTQDNU6R1_67000Dx_ZCJB-3pi). Feel free to skip this part if you are already familiar with the math behind neural network.

### The Cost function
<img src="/images/Cost.png" width="60%">

### The partial derivative of weights and biases
Used Chain Rules to calculate the gradient of our neural network.
<img src="/images/Cw.png" width="60%"> <img src="/images/cb.png" width="60%">

### The derivatives used above
These are the derivatives we used to calculate the total gradient.
<img src="/images/deri.png" width="60%">

### The definition of equations used in the neuron
the sigmoid function and how weights and biases are used.
<img src="/images/sigmoid defi.png" width="60%">

After knowing the underlying mathematics, check out the [documentation](/src/README.md) for how to use this project.
Note: the neural network is not built with matrix, rather with simple for loop and nodes. 
## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments
3Blue1Brown
