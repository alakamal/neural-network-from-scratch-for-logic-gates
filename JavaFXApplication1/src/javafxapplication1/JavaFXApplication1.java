/*




msh had
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.util.stream.IntStream;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

import javafx.scene.layout.Pane;

import javafx.stage.Stage;

/**
 *
 * @author makbwk
 */
public class JavaFXApplication1 extends Application {
    static double  m = 0;
    static double b = 0; 
    static	int [][][] data = pperceptron.AndData;
   
    public static void Doeverything(String[] args){
        
        
		double[] weights = pperceptron.INITIAL_WEIGHTS;
		JavaFXApplication1 driver = new JavaFXApplication1();		
		pperceptron perceptron = new pperceptron();
		int epochNumber = 0 ; 
		boolean errorflag = true;
		double error = 0;
		double [] adjustedWeight =null;
	

		
		while(errorflag) {
			driver.printHeading(epochNumber++);
			errorflag =false;
			error = 0;
			for(int x=0; x < data.length; x++) {
				double weightedSum = perceptron.calculateWeightedSum(data[x][0], weights);
				int result = perceptron.applyActivationFunction(weightedSum);
				error = data[x][1][0] - result;
				if (error != 0){
                                    errorflag = true;
                                }
				adjustedWeight = perceptron.adjustingWeighrs(data[x][0], weights, error);
				driver.printVector(data[x], weights, result, error, weightedSum, adjustedWeight);
				weights = adjustedWeight;
                        }
		}
                
                
         m = -weights[2]/weights[1]; 
                b = (pperceptron.THRESHOLD/weights[1])-(weights[0]/weights[1]); 
                System.out.println("\ny = " +String.format("%.2f",m)+"x" + "+" + String.format("%.2f",b));
                
                 launch(args);
        
    }
    

    /**
     * @param args the command line arguments
     */
   
	
    public void start(Stage stage) {
        
        stage.setTitle("Nural network");
        XYChart.Series<Number , Number> series1 = new XYChart.Series<Number , Number>();
        series1.setName("zero");
        XYChart.Series<Number , Number> series2 = new XYChart.Series<Number , Number>();
        series2.setName("One");
        IntStream.range (0 , data.length).forEach(i ->{
        int x = data[i][0][1],y = data[i][0][2];
        if(data[i][1][0]==0) series1.getData().add(new XYChart.Data<Number , Number>(x,y));
        else series2.getData().add(new XYChart.Data<Number , Number>(x,y));
    });
      double x1 = 0 , y1 =b , x2 = -(b/m) , y2 = 0;
      String title = new String ("y = "+ String.format("%.2f", m)+"x + " + String.format("%.2f", b) 
              + "  (0, " + String.format("%.2f", b) + ")  |  ("+ String.format("%.2f", (-b/m))+ ", 0)");
        
        NumberAxis xAxis = new NumberAxis(0, 4 , 1);
        NumberAxis yAxis = new NumberAxis(0, 4 , 1);
        ScatterChart<Number , Number> scatterChart = new ScatterChart<Number , Number>(xAxis,yAxis);
scatterChart.setTitle(title);
scatterChart.getData().add(series1);
scatterChart.getData().add(series2);

        XYChart.Series<Number , Number> series3 = new XYChart.Series<Number , Number>();
       series3.getData().add(new XYChart.Data<Number , Number>(x1,y1));
       series3.getData().add(new XYChart.Data<Number, Number>(x2,y2));
       LineChart<Number , Number>  lineChart = new  LineChart<Number , Number> (xAxis,yAxis);
       lineChart.getData().add(series3);
       lineChart.setOpacity(0.1);
       Pane pane = new Pane();
       pane.getChildren().addAll(scatterChart,lineChart);
       stage.setScene(new Scene(pane, 500, 400));
       stage.show();
       
    }
	
	
	public void printHeading (int epochNumber) {
		System.out.println("\n========================= Epoch  # "+ epochNumber+"==================== ");
		System.out.println("  W1      |      W2  |    x0    |    X1  |    X2  | Target Result  | Result | Weighted Sum  | adjust W1  |  adjust  W2  ");
		System.out.println("-----------------------------------------------------------------------------------------------");
		
		
	}
	public void printVector (int [][] data , double [] weights , int result , double error , double weightedsum , double[] adjustedWeight) {
     		System.out.println("  "+String.format("%.2f", weights[0]) + " | " + String.format("%.2f", weights[1]) + " | " + data[0][0] + " | "+ data[0][1] + " | " +  data[0][2]+ "  |  "+
				 data[1][0] + " | " + result + "   |  " + error + "  |  "+ String.format("%.2f", weightedsum)+ "  |  "+ String.format("%,.2f", adjustedWeight[0])+"  |  "+ String.format("%,.2f", adjustedWeight[1]));
		

        
        
    }
    
}
