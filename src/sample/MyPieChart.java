package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;

import java.util.*;

public class MyPieChart {

    //Variables
    PieChart chart;

    //Methods
//    private ObservableList<PieChart.Data> calculateData(int num, LinkedHashMap<Character, Integer> data) {
//
//        return pieChartData;
//    }

    public void draw(int num, LinkedHashMap<Character, Double> data, Group g) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Iterator<Map.Entry<Character, Double>> itr  = data.entrySet().iterator();
        int i = 0;
        double restOfProbabilities = 0;
        while (itr.hasNext()) {
            Map.Entry<Character, Double> entry = itr.next();
            if(i < num) {
                pieChartData.add(new PieChart.Data(String.valueOf(entry.getKey()), entry.getValue()));
                i++;
            } else {
                restOfProbabilities += entry.getValue();
            }
        }
        pieChartData.add(new PieChart.Data("All other letters", restOfProbabilities));
        PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Probabilities of n most frequent characters");
        g.getChildren().add(chart);
    }

}
