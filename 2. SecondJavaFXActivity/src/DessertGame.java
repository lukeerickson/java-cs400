import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.geometry.Pos;
import java.util.Random;

public class DessertGame extends Application {
  
  private int score = 0;

  @Override
  public void start(final Stage stage) {
    Button[] bArray = new Button[8];
    Random rand = new Random();
    

    // Step 3 & 4
    BorderPane borderPane = new BorderPane();
    Scene scene = new Scene(borderPane, 640, 480);
    stage.setTitle("Dessert in the Desert JavaFX Game");

    // Step 5
    Label scoreLabel = new Label("Score: " + score);
    borderPane.setTop(scoreLabel);
    BorderPane.setAlignment(scoreLabel, Pos.TOP_LEFT);

    Button exitButton = new Button("Exit");
    exitButton.setOnAction(event -> {
      //score++;
      Platform.exit();
      //System.out.println(score);
    });
    borderPane.setBottom(exitButton);
    BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);

    // Step 6
    Pane pane = new Pane();
    borderPane.setCenter(pane);
    BorderPane.setAlignment(pane, Pos.CENTER);

    // TODO: Step 7-10
    Button b1 = new Button("Dessert");
    Button b2 = new Button("Desert");
    Button b3 = new Button("Desert");
    Button b4 = new Button("Desert");
    Button b5 = new Button("Desert");
    Button b6 = new Button("Desert");
    Button b7 = new Button("Desert");
    Button b8 = new Button("Desert");

    bArray[0] = b1;
    bArray[1] = b2;
    bArray[2] = b3;
    bArray[3] = b4;
    bArray[4] = b5;
    bArray[5] = b6;
    bArray[6] = b7;
    bArray[7] = b8;
    
    b1.setOnAction(event -> {
      score++;
      scoreLabel.setText("Score: " + score);
      randomizeButtonPositions(rand, bArray);
      exitButton.requestFocus();
    });
    
    for (int i = 1; i < bArray.length; i++) {
      
        bArray[i].setOnAction(event -> {
          score--;
          scoreLabel.setText("Score: " + score);
          randomizeButtonPositions(rand, bArray);
          exitButton.requestFocus();
        });
      }
    
    for(int i = 0; i < bArray.length; i++) {
      pane.getChildren().add(bArray[i]);
    }
      
    randomizeButtonPositions(rand, bArray);
    exitButton.requestFocus();
    stage.setScene(scene);
    stage.show();
  }

  private void randomizeButtonPositions(Random rand, Button[] bArray) {
    // System.out.println(rand.nextInt(600));
    for (int i = 0; i < bArray.length; i++) {
      bArray[i].setLayoutX(rand.nextInt(600));
      bArray[i].setLayoutY(rand.nextInt(400));
    }
  }

  public static void main(String[] args) {
    Application.launch();
  }
}
