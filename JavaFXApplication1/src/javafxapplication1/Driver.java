/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static javafxapplication1.JavaFXApplication1.data;

/**
 *
 * @author SYSCOM
 */
public class Driver extends  Application{
    static double [] m;
     static double []b;
     
     
      static double a;
       static double e;
       
    
     
     
     
     
     
     
    static int NUMB_OF_EPOCHS=1000;
    public static NeuralNetwork neuralNetwork = new NeuralNetwork();
 public static double Training_data[][][]= new double[][][]{
            {{0,0},{NewJFrame.x1}},
            {{0,1},{NewJFrame.x2}},
            {{1,0},{NewJFrame.x3}},
            {{1,1},{NewJFrame.x4}}};
    /**
     * @param args the command line arguments
     */
 
 public  void RUN(String[] args) throws IOException {

 /*
BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(System.in));
boolean flag=true;
while(flag){
System.out.println("> what do you want to do (train,run,exit)");
String command;
       command = bufferedReader.readLine();*/


double[] result=new double[]{0,0,0,0};
IntStream.range(0,Training_data.length).forEach(i->{
result[i]=neuralNetwork.forwardprop(Driver.Training_data[i][0])
.getNeurons()[NeuralNetwork.n_n_input+NeuralNetwork.n_n_hidden]//
.getOutput();

m[i] = - (neuralNetwork.forwardprop(Driver.Training_data[i][0]).getNeurons()[NeuralNetwork.n_n_input]
.getWeights()[1]/neuralNetwork.forwardprop(Driver.Training_data[i][0]).getNeurons()[NeuralNetwork.n_n_input]
.getWeights()[0]);
b[i] = neuralNetwork.forwardprop(Driver.Training_data[i][0]).getNeurons()[NeuralNetwork.n_n_input]
.getThreshold()/neuralNetwork.forwardprop(Driver.Training_data[i][0]).getNeurons()[NeuralNetwork.n_n_input]
.getWeights()[0];
});
printResult(result);






 



launch(args);



}
 
 public  void TRAIN() throws IOException {
IntStream.range(0,NUMB_OF_EPOCHS).forEach(i->{
System.out.println("[epoch" +i+"]");
IntStream.range(0,Training_data.length).forEach(j->
System.out.println(neuralNetwork.forwardprop(Driver.Training_data[j][0])
.backdropError(Driver.Training_data[j][1][0])));
});
System.out.println("[done training]");
}

/*else{
flag=false;
}
}
System.exit(0);

}*/
 
 
 
 
 
   public void start(Stage stage) {
        
        stage.setTitle("Nural network");
        XYChart.Series<Number , Number> series1 = new XYChart.Series<Number , Number>();
        series1.setName("zero");
        XYChart.Series<Number , Number> series2 = new XYChart.Series<Number , Number>();
        series2.setName("One");
        IntStream.range (0 , Training_data.length).forEach(i ->{
        double x = Training_data[i][0][0],y = Training_data[i][0][1];
        if(Training_data[i][1][0]==0) series1.getData().add(new XYChart.Data<Number , Number>(x,y));
        else series2.getData().add(new XYChart.Data<Number , Number>(x,y));
    });
        
        
        
      double x1 = 0 , y1 =b , x2 = -(b/m) , y2 = 0;
      
      
      
    //  String title = new String ("y = "+ String.format("%.2f", m)+"x + " + String.format("%.2f", b) 
         //     + "  (0, " + String.format("%.2f", b) + ")  |  ("+ String.format("%.2f", (-b/m))+ ", 0)");
        
        NumberAxis xAxis = new NumberAxis(0, 4 , 1);
        NumberAxis yAxis = new NumberAxis(0, 4 , 1);
        ScatterChart<Number , Number> scatterChart = new ScatterChart<Number , Number>(xAxis,yAxis);
//scatterChart.setTitle(title);
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


        
  

    
 public static void printResult(double[] result){
System.out.println("Input 1  | Input 2   | Target Result  | Result");
System.out.println("------------------------------------------------");
IntStream.range(0,Training_data.length).forEach(i->{
        IntStream.range(0,Training_data[0][0].length).forEach(j->System.out.print("      "+ Training_data[i][0][j]+"    | "));
       System.out.print("     "+ Training_data[i][1][0]+"    | "+String.format("%.5f", result[i] )+"     \n");
});

}
}
