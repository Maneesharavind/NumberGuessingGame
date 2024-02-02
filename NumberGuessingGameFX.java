package application;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Random;

public class NumberGuessingGameFX extends Application {
    private Random random;
    private int targetNumber;
    private int maxAttempts = 10;
    private int attempts;
    private int score;

    // JavaFX components
    private TextField guessField;
    private Button guessButton;
    private Label resultLabel;
    private Label scoreLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        random = new Random();
        score = 0;

        // Initialize JavaFX components
        guessField = new TextField();
        guessButton = new Button("Submit Guess");
        resultLabel = new Label();
        scoreLabel = new Label("Score: 0");

        // Set action handler for the guess button
        guessButton.setOnAction(event -> handleGuess());

        // Start a new game
        startNewGame();

        // Create a vertical layout
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle(
                "-fx-background-color: #dee2d7;" + // Dark background
                "-fx-font-size: 20;" +
                "-fx-text-fill: #d9fca4;"); // Font color

        // Styling components
        guessField.setStyle(
                "-fx-font-size: 20;" +
                "-fx-background-color: #34495E;" + // Input field background
                "-fx-text-fill: #d9fca4;" +// Text color
    			"-fx-alignment: CENTER;"); // Center text

        guessField.setMaxWidth(100); // Set the maximum width
        guessButton.setStyle(
                "-fx-background-color: #3498DB; -fx-text-fill: white;" + // Button color
                "-fx-font-size: 14;");
        resultLabel.setStyle("-fx-text-fill: #181a15;"); // Result label color
        scoreLabel.setStyle("-fx-text-fill: #3d5d0f; -fx-padding: 0 0 10 0;"); // Gold color for score label

        layout.getChildren().addAll(
                new Label("Welcome to the Number Guessing Game!"),
                new Label("Guess a number between 1 and 100:"),
                guessField,
                guessButton,
                resultLabel,
                scoreLabel
        );

        // Set up the scene and show the stage
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Number Guessing Game");
        primaryStage.show();
    }

    private void startNewGame() {
        targetNumber = random.nextInt(100) + 1;
        attempts = 0;
        resultLabel.setText("");
        updateScoreLabel();
    }

    private void handleGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            attempts++;

            if (userGuess == targetNumber) {
                resultLabel.setText("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                score++;
                startNewGame();
            } else if (userGuess < targetNumber) {
                resultLabel.setText("Too low! Try again.");
            } else {
                resultLabel.setText("Too high! Try again.");
            }

            updateScoreLabel();
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        } finally {
            guessField.clear();
        }
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + score);
    }
}
