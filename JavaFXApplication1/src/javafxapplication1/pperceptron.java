/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;
public class pperceptron {
    
    public static final double THRESHOLD = 1.0 ;


          
  
  public static final int [][][] AndData =         {{{1,0,0}  , {NewJFrame.x1}},
			                            {{1,0,1}    , {NewJFrame.x2}},
			                            {{1,1,0}  , {NewJFrame.x3}},
			                            {{1,1,1}  , {NewJFrame.x4}}};
          
	
        
	public static final double LEARNNING_RATE = 0.05;
	public static final double[] INITIAL_WEIGHTS = {Math.random() ,Math.random() , Math.random()};
	
	public double calculateWeightedSum(int [] data , double [] weights) {
		double weightedsum = 0;
		for(int x = 0; x < data.length; x++) {
			weightedsum  += data[x] * weights[x];
		}
		
		return weightedsum;
		
	}
	public int applyActivationFunction(double weightedSm) {
		int result = 0;
		if(weightedSm > 1) {
			result =1;
		}
		return result;
	}
	public double[] adjustingWeighrs (int [] data , double [] weights , double error ) {
		double [] adjustedWeughts = new double[weights.length];
		for(int x=0; x<weights.length; x++) {
			adjustedWeughts[x] = LEARNNING_RATE * error * data[x] + weights[x];
		}
		
		return adjustedWeughts;
	}
	
	
}

