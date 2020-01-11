package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class Controller {

    public void loginButtonClicked(){
        System.out.println("Користувач зареєструвався");
    }

    public ChoiceBox<String> selectInterval;
    public ChoiceBox<String> selectTypeOf;


    @FXML
    public void initialize() {
        selectInterval.getItems().addAll("Послідовна", "Паралельна", "Послідовно-паралельна");
        selectInterval.setValue("Послідовно-паралельна");

        selectTypeOf.getItems().addAll("Критерій Кульбака", "Критерій Шенонна");
        selectTypeOf.setValue("Критерій Кульбака");

    }

}
