package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("My Pie Chart");
        BorderPane border = new BorderPane();
        Group g = new Group();
        border.setCenter(g);
        HBox inputMenu = new HBox();
        inputMenu.setPadding(new Insets(15,10,15,10));
        inputMenu.setSpacing(10);
        inputMenu.setStyle("-fx-background-color: #b2bec3");
        inputMenu.setAlignment(Pos.CENTER);
        TextField inputField = new TextField();
        Button addBtn = new Button("Enter");
        inputMenu.getChildren().addAll(inputField, addBtn);
        border.setTop(inputMenu);


        addBtn.setOnAction( e -> {
            int num = isInt(inputField);
            HistogramAlphabet data = new HistogramAlphabet();
            try {
                data.setFrequency("./assets/aliceInWonderland.txt");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            data.setProbability();

            MyPieChart chart = new MyPieChart();
            chart.draw(num, data.getProbability(), g);
        });

        Scene scene = new Scene(border, 650, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

//    public HBox addHBox() {
//        HBox hb = new HBox();
//        hb.setPadding(new Insets(15,10,15,10));
//        hb.setSpacing(10);
//        hb.setStyle("-fx-background-color: #b2bec3");
//
//        TextField probabilityInput = new TextField();
//        Button addBtn = new Button("Enter");
//        addBtn.setOnAction( e -> isInt(probabilityInput));
//        addBtn.setPrefSize(75,20);
//        hb.getChildren().addAll(probabilityInput, addBtn);
//
//        return hb;
//    }

    public int isInt(TextField input) {
        try {
            int num = Integer.parseInt(input.getText());
            System.out.println("N: " + num);
            return num;
        } catch (NumberFormatException e) {
            System.out.println("Error! Input is not a number");
            return -1;
        }
    }
}
