package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application{

    private static final int MATRIX_COUNT = 10;

    /**
     * Вспомогательная функция логарифма
     */
    public static double log2(double num) {
        return (Math.log(num)/Math.log(2));
    }

    /**
     * Расчет по формуле
     */
    public static double formula(double aplha, double beta) {
        double sum = aplha + beta;
        return (log2((2 - sum) / sum) * (1 - sum));
    }




    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Система діагностування багатоканатної шахтної підйомної машини");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        // создаем матрицы
        List<Matrix> matrixList = new ArrayList<>();
        for(int i = 0; i < MATRIX_COUNT; i++) {
            matrixList.add(new Matrix());
        }
        // System.exit(0);
        // сравниваем первую матрицу со всеми остальными + ищем матрицу с миним. отличиями
        List<Float> referenceVector = matrixList.get(0).getBinaryVector();
        int minIndex = 0;
        int minCount = referenceVector.size();
        for(int i = 1; i < MATRIX_COUNT; i++) {
            int count = Matrix.getRowDiff(referenceVector, matrixList.get(i).getBinaryVector());
            if(minCount > count) {
                minCount = count;
                minIndex = i;
            }
        }

        // различия эталонного вектора с первой и минимальной матрицей
        List<Integer> countListFirstMatrix = new ArrayList<>();
        List<Integer> countListMinMatrix = new ArrayList<>();
        List<Float> alphaList = new ArrayList<>();
        List<Float> betaList = new ArrayList<>();
        Matrix firstMatrix = matrixList.get(0);
        Matrix minMatrix = matrixList.get(minIndex);
        for(int i = 0; i < Section.ROW_COUNT; i++) {
            int count = Matrix.getRowDiff(referenceVector, firstMatrix.getBinaryRow(i));
            countListFirstMatrix.add(count);
            alphaList.add(1 - (float)(count / Section.ROW_COUNT));
            count = Matrix.getRowDiff(referenceVector, minMatrix.getBinaryRow(i));
            countListMinMatrix.add(count);
            betaList.add((float)count / Section.ROW_COUNT);
        }
        System.out.println(countListFirstMatrix + "cont");
        System.out.println(countListMinMatrix);
        System.out.println(betaList);
        // System.exit(0);

        // считаем по формуле
        List<Double> eList = new ArrayList<>();
        for(int i = 0; i < Section.ROW_COUNT; i++) {
            eList.add(1 / formula((double)alphaList.get(i) / (double)Row.ROW_SIZE, (double)betaList.get(i) / (double)Row.ROW_SIZE));
            System.out.print(eList.get(i) + "E");
        }

    }

}
