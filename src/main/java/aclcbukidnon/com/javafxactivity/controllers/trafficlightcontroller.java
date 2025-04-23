package aclcbukidnon.com.javafxactivity.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class trafficlightcontroller {

    @FXML
    private Circle greenLight;

    @FXML
    private Circle orangeLight;

    @FXML
    private Circle redLight;

    private int state = 0; // 0 = green, 1 = orange, 2 = red

    private Timeline timeline;

    @FXML
    public void initialize() {
        // Set up a repeating timer to change lights every 3 seconds
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> changeLight()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    public void onTimerChange(MouseEvent event) {
        changeLight();
    }

    private void changeLight() {
        switch (state) {
            case 0:
                greenLight.setFill(Color.LIMEGREEN);
                orangeLight.setFill(Color.web("#575757"));
                redLight.setFill(Color.web("#575757"));
                break;
            case 1:
                greenLight.setFill(Color.web("#575757"));
                orangeLight.setFill(Color.ORANGE);
                redLight.setFill(Color.web("#575757"));
                break;
            case 2:
                greenLight.setFill(Color.web("#575757"));
                orangeLight.setFill(Color.web("#575757"));
                redLight.setFill(Color.RED);
                break;
        }
        state = (state + 1) % 3;
    }
}
