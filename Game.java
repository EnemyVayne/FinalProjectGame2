/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author kylef
 */
public class Game extends Application
{
   
   public static final int WIDTH = 1300;
   public static final int HEIGHT = 800;
   Color colorGrass = new Color(0,.47,0,.8);
   Color colorRoom = Color.SIENNA;
   Color colorFade;
   
   //private Handler handler;
   Label titleLabel;
 



   @Override
   public void start( Stage stage ) throws Exception
   {
      //handler = new Handler();
      titleLabel = new Label("The Suave Squad");
      titleLabel.setFont(new Font(50)); 

      titleLabel.setTextFill(Color.WHITE);
  
      Rectangle rectangle = new Rectangle(HEIGHT, HEIGHT, WIDTH+100, WIDTH+100); 
      rectangle.setFill(Color.BLACK); 
      
      
      StackPane titlePane = new StackPane(rectangle);
      GridPane roomPane = new GridPane();
      


      titlePane.setPadding(new Insets(0,0,0,0)); 

      
      
      titlePane.getChildren().add(titleLabel);


      roomPane.setPadding(new Insets(100,100,100,100));
      


      
      
      Scene TitleScreen = new Scene(titlePane, WIDTH, HEIGHT);
      Scene Room = new Scene(roomPane, WIDTH, HEIGHT);

      
      


      Room.setFill(colorRoom);
      
      stage.setTitle("RPG");
      stage.setScene(TitleScreen);
      stage.show();
      stage.setResizable(false);
      
      

      
   }
   
       public static void main(String [] args)
    { 

       Application.launch(args);  
    }
   

}
