
package Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
   This class hold the "end of credits" scene after the player dies.
   The class should in theory find the video you want to play and create a new
   scene and plays the video

 @author William Sarabia
 */
public class Video extends Application
{
  
   @Override
   public void start( Stage primaryStage )
   {
      try
      {
        Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource( "application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
      }
      catch(Exception e)
      {
         System.out.println("File not found");
      }
     
     
   }
   
   public static void main(String[] args)
   {
      launch(args);
   }
   
}
