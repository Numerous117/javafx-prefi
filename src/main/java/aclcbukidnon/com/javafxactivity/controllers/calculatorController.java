package aclcbukidnon.com.javafxactivity.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class calculatorController {

    @FXML
    private Label display;

    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean startNewInput = false;

    @FXML
    private void handleButtonClick(MouseEvent event) {
        Button clicked = (Button) event.getSource();
        String value = clicked.getText();

        switch (value) {
            case "CLEAR":
                currentInput = "";
                operator = "";
                firstNumber = 0;
                startNewInput = false;
                display.setText("0");
                break;

            case "BCKSPC":
                if (!currentInput.isEmpty()) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                    display.setText(currentInput.isEmpty() ? "0" : formatDisplay(firstNumber, operator, currentInput));
                }
                break;

            case "+": case "-": case "*": case "/":
                if (!currentInput.isEmpty()) {
                    firstNumber = Double.parseDouble(currentInput);
                    operator = value;
                    startNewInput = true;
                    display.setText(formatDisplay(firstNumber, operator, ""));
                }
                break;

            case "=":
                if (!operator.isEmpty() && !currentInput.isEmpty()) {
                    double secondNumber = Double.parseDouble(currentInput);
                    double result = switch (operator) {
                        case "+" -> firstNumber + secondNumber;
                        case "-" -> firstNumber - secondNumber;
                        case "*" -> firstNumber * secondNumber;
                        case "/" -> secondNumber != 0 ? firstNumber / secondNumber : 0;
                        default -> 0;
                    };

                    display.setText(formatDisplay(firstNumber, operator, currentInput) + " = " + result);
                    currentInput = String.valueOf(result);
                    operator = "";
                    startNewInput = true;
                }
                break;

            case ".":
                if (!currentInput.contains(".")) {
                    currentInput += ".";
                    display.setText(formatDisplay(firstNumber, operator, currentInput));
                }
                break;

            default: // Numbers
                if (startNewInput) {
                    currentInput = "";
                    startNewInput = false;
                }
                currentInput += value;
                display.setText(formatDisplay(firstNumber, operator, currentInput));
                break;
        }
    }

    private String formatDisplay(double first, String op, String second) {
        if (!op.isEmpty()) {
            return String.valueOf(first) + " " + op + " " + second;
        } else {
            return second.isEmpty() ? String.valueOf(first) : second;
        }
    }
}
