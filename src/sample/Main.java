package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My Pie Chart");

        //Declaring necessary UI elements
        BorderPane border = new BorderPane();
        HBox inputMenu = new HBox();
        Group group = new Group();
        int w = 1250, h = 650;
        Canvas canvas = new Canvas(w, h);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //Designing the top input section
        inputMenu.setPadding(new Insets(15, 10, 15, 10));
        inputMenu.setSpacing(10);
        inputMenu.setStyle("-fx-background-color: #b2bec3");
        inputMenu.setAlignment(Pos.CENTER);
        TextField inputField = new TextField();
        Button addBtn = new Button("Enter");
        inputMenu.getChildren().addAll(inputField, addBtn);
        border.setTop(inputMenu);

        //Adding group to center of layout to draw the pie chart
        group.getChildren().add(canvas);
        border.setCenter(group);

        //Event listener to listen for button click and then draw pie chart
        addBtn.setOnAction( e -> {
            int num = isInt(inputField);
            HistogramAlphabet data = new HistogramAlphabet();

            try {
                data.setFrequency("./assets/aliceInWonderland.txt");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            data.setProbability();

            MyPieChart pieChart = new MyPieChart(w*0.3, h/3, num, 300, 300);
            pieChart.draw(gc, data.getProbability());

        });



        Scene scene = new Scene(border, w, h);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public int isInt(TextField input) {
        try {
            return Integer.parseInt(input.getText());
        } catch (NumberFormatException e) {
            System.out.println("Error! Input is not a number");
            return -1;
        }
    }
}
