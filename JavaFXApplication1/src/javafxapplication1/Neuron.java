/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

/**
 *
 * @author SYSCOM
 */
public class Neuron {
   private NeuralNetwork.LayerType layerType;
private static  double threshold=0.5- Math.random();
private static double[] weights={0.5-Math.random(),0.5-Math.random()};
private double output=0;
private double error=0;
public Neuron(NeuralNetwork.LayerType layerType){this.layerType =layerType;}
public void applyActivationFunction(double weightedSum){output =1.0/(1+Math.exp(-1* weightedSum));}
public double derivative(){ return output *(1.0 -output);}
public NeuralNetwork.LayerType getLayerType(){ return layerType;}
public void setThreshold(double threshold){this.threshold =threshold;}
public static double getThreshold(){return threshold;}

public static double[] getWeights(){return weights;}
public double getOutput(){ return output;}
public void setOutput(double output){this.output=output;}
public double getError(){ return error;}
public void setError(double error){this.error=error;}
public String toString(){
String returnValue="";
if(layerType ==NeuralNetwork.LayerType.I) returnValue="("+layerType+":"+String.format("%.2f",output)+")";
else  returnValue= "("+layerType+","+
        String.format("%.2f",weights[0])+","+String.format("%.2f",weights[1])+","
        +String.format("%.2f",threshold)+","
        +String.format("%.5f",output)+")";
return returnValue;
}
}