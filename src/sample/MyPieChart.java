package sample;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyPieChart extends MyShape {

    //Variables
    private int n;
    private double xLength;
    private double yLength;
    private double arcAngle;
    private double startAngle = 0;

    //Constructor
    public MyPieChart(double x, double y) {
        super(x, y);
    }

    public MyPieChart(double x, double y, int n, double xLength, double yLength) {
        super(x, y);
        this.n = n;
        this.xLength = xLength;
        this.yLength = yLength;
    }

    public MyPieChart(int n, double xLength, double yLength) {
        super(0,0);
        this.n = n;
        this.xLength = xLength;
        this.yLength = yLength;
    }

    public MyPieChart(int n) {
        super(0,0);
        this.n = n;
    }



    //MyPieChart specific methods
    private LinkedHashMap<String, Double> getPieChartData(LinkedHashMap<Character, Double> data) {
        LinkedHashMap<String, Double> tempData = new LinkedHashMap<>();
        Iterator<Map.Entry<Character, Double>> itr = data.entrySet().iterator();
        int i = 0;
        double remaining = 0;

        while (itr.hasNext()) {
            Map.Entry<Character, Double> entry = itr.next();
            if (i < this.n) {
                tempData.put(String.valueOf(entry.getKey()), entry.getValue());
                i++;
            } else {
                remaining += entry.getValue();
            }

        }
        tempData.put("All other letters", remaining);
        System.out.println(tempData);
        return tempData;
    }

    //Overridden methods from MyShape
    public String toString() {
        return null;
    }
    public void draw(GraphicsContext gc) {

    }
    public void draw(GraphicsContext gc, LinkedHashMap<Character, Double> data) {
        int ovalX = 850; // starting x cord for small colored oval
        int ovalY = 50; // starting y cord for small colored oval
        int xCord = 925; // x cord for label
        int yCord = 65; // y cord for label
        int x_label = 630; // Initial x coordinate for the legend
        int y_label = 30; // Initial y coordinate for the legend
        int x_labe12 = 690;
        int y_label2 = 50;

        LinkedHashMap<String, Double> pieChartData = this.getPieChartData(data);
        Iterator<Map.Entry<String, Double>> itr = pieChartData.entrySet().iterator();

        while (itr.hasNext()) {
            Map.Entry<String, Double> entry = itr.next();
            arcAngle = entry.getValue() * (double) 360;
            gc.setFill(color.getRandomColor());
            gc.fillArc(x - x/3, y - y/1.25,xLength,yLength, startAngle, arcAngle,ArcType.ROUND);
            startAngle += arcAngle;
            if (yCord > xCord && ovalY > xCord) {
                gc.fillOval(x_label, y_label, 50, 30); // oval representing piechart segment
                // labels
                gc.fillText(entry.getKey() + "," + (double) Math.round(entry.getValue() * 10000) / 10000, x_labe12, y_label2);
                y_label2 += 40;
                y_label += 40;
            } else {
                gc.fillOval(ovalX, ovalY, 50, 30);
                gc.fillText(entry.getKey() + "," + (double) Math.round(entry.getValue() * 10000) / 10000, xCord, yCord);

                ovalY += 40; // Next oval is pushed down 40 units below previous
                yCord += 40; //Next label is doing same as above
            }
        }
    }

    //Overridden methods from MyShapePosition
    public MyRectangle getBoundingBox() {
        return new MyRectangle(0,0,0,0);
    }
    public boolean doOverlap(MyShape shape2) {
        return doMyRectangleOverlap(this.getBoundingBox(), shape2.getBoundingBox());
    }
}
