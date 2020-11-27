/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.util.Arrays;
import java.util.stream.IntStream;
import static javafxapplication1.NeuralNetwork.LayerType.H;
import static javafxapplication1.NeuralNetwork.LayerType.I;

/**
 *
 * @author SYSCOM
 */
public class NeuralNetwork {
static enum LayerType{I,H,O}
static final double LEARNING_RATE=0.1;
    final static int n_n_input=2;
     final static int n_n_hidden=2;
     final static int n_total=n_n_input + n_n_hidden+ 1;
     private Neuron[] neurons = new Neuron[n_total];
 public NeuralNetwork(){
     IntStream.range(0, n_n_input).forEach(i->neurons[i]=new Neuron(LayerType.I));
     IntStream.range(n_n_input, n_n_input+n_n_hidden).forEach(i->neurons[i]=new Neuron(LayerType.H));
                neurons[n_n_input+n_n_hidden]=new Neuron(LayerType.O);
}
 public NeuralNetwork forwardprop(double input[]){
double weightedSum=0.0;
for(int i=0;i<neurons.length;i++){
switch(neurons[i].getLayerType()){
    
case I:
neurons[i].setOutput(input[i]);
break;
case H:
weightedSum=neurons[i].getThreshold()+neurons[i].getWeights()[0]*neurons[0].getOutput()+neurons[i].getWeights()[1]*neurons[1].getOutput();
neurons[i].applyActivationFunction(weightedSum);
break;
case O:
weightedSum=neurons[i].getThreshold()+
            neurons[i].getWeights()[0]*neurons[2].getOutput()+
        neurons[i].getWeights()[1]*neurons[3].getOutput();
        neurons[i].applyActivationFunction(weightedSum);
break;
}
}
return this;
}
 public NeuralNetwork backdropError(double targetResult){
neurons[4].setError((targetResult-neurons[4].getOutput())*neurons[4].derivative());
neurons[4].setThreshold(neurons[4].getThreshold()+LEARNING_RATE*neurons[4].getError());
neurons[4].getWeights()[0]=neurons[4].getWeights()[0]+LEARNING_RATE*neurons[4].getError()*neurons[2].getOutput();
neurons[4].getWeights()[1]=neurons[4].getWeights()[1]+LEARNING_RATE*neurons[4].getError()*neurons[3].getOutput();
neurons[3].setError((neurons[4].getWeights()[1]*neurons[4].getError())*neurons[3].derivative());
neurons[3].setThreshold(neurons[3].getThreshold()+LEARNING_RATE*neurons[3].getError());
neurons[3].getWeights()[0]=neurons[3].getWeights()[0]+LEARNING_RATE*neurons[3].getError()*neurons[0].getOutput();
neurons[3].getWeights()[1]=neurons[3].getWeights()[1]+LEARNING_RATE*neurons[3].getError()*neurons[1].getOutput();
neurons[2].setError((neurons[4].getWeights()[0]*neurons[4].getError())*neurons[2].derivative());
neurons[2].setThreshold(neurons[2].getThreshold()+LEARNING_RATE*neurons[2].getError());
neurons[2].getWeights()[0]=neurons[2].getWeights()[0]+LEARNING_RATE*neurons[2].getError()*neurons[0].getOutput();
neurons[2].getWeights()[1]=neurons[2].getWeights()[1]+LEARNING_RATE*neurons[2].getError()*neurons[1].getOutput();

return this;
}
 public Neuron[] getNeurons(){ return neurons;}
public String toString(){ return Arrays.toString(neurons);}  

 
    
}
