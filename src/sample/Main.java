package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

public class Main extends Application {

//    public LinkedHashMap<Character, Integer> readFile(String fileLocation) throws IOException {
//        LinkedHashMap<Character, Integer> temp = new LinkedHashMap<>();
//        for (char c = 'a'; c <= 'z'; c++) {
//            temp.put(c,0);
//        }
//        File f = new File(fileLocation);
//        FileReader fr = new FileReader(f);
//        BufferedReader br = new BufferedReader(fr);
//        int c = 0;
//        while ((c = br.read()) != -1) {
//            char readChar = (char) c;
//            if (Character.isLetter(readChar)) {
//                readChar = Character.toLowerCase(readChar);
//                temp.put(readChar, temp .get(readChar) + 1);
//            }
//        }
//        return temp;
//    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("My Pie Chart");
        Group root = new Group();
        int w = 600, h = 400;
        Canvas canvas = new Canvas(w, h);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        HistogramAlphabet data = new HistogramAlphabet();
        data.setFrequency("./assets/aliceInWonderland.txt");
        System.out.println(data.getFrequency());

        data.setProbability();
        System.out.println(data.getProbability());


        root.getChildren().add(canvas);
        Scene sc = new Scene(root, w, h);
        primaryStage.setScene(sc);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
